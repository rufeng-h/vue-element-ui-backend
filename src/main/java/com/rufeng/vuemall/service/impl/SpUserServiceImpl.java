package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.AO.LoginParam;
import com.rufeng.vuemall.domain.BO.UserInfoWithRole;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUser;
import com.rufeng.vuemall.domain.SpUserRole;
import com.rufeng.vuemall.exception.DeleteException;
import com.rufeng.vuemall.exception.InsertException;
import com.rufeng.vuemall.mapper.SpUserMapper;
import com.rufeng.vuemall.security.SpAuthenticatedUser;
import com.rufeng.vuemall.service.SpRolePermissionService;
import com.rufeng.vuemall.service.SpUserRoleService;
import com.rufeng.vuemall.service.SpUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpUserServiceImpl extends ServiceImpl<SpUserMapper, SpUser> implements SpUserService, UserDetailsService {
    private static final Log logger = LogFactory.getLog(SpUserServiceImpl.class);
    private static final String DEFAULT_PASSWORD = "123456";
    private final SpUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SpUserRoleService userRoleService;
    private final SpRolePermissionService rolePermissionService;


    public SpUserServiceImpl(PasswordEncoder passwordEncoder,
                             SpUserRoleService userRoleService,
                             SpRolePermissionService rolePermissionService,
                             SpUserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 根据用户名查询用户并授权
     *
     * @param username 用户名
     * @return user authenticated
     * @throws UsernameNotFoundException exp
     */
    @Override
    public SpAuthenticatedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SpUser> users = baseMapper.selectByMap(Collections.singletonMap("username", username));
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        SpUser user = users.get(0);
        logger.debug("find user " + user.getUsername());

        /* 更新登录时间 */
        user.setLastLoginTime(new Date());
        this.baseMapper.updateById(user);

        /*TODO 授权，优化sql*/
        List<SpRole> roleList = userRoleService.getRoleListById(user.getId());
        List<SpPermission> permissionList = rolePermissionService.getPermissionList(roleList);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(
                roleList.stream().map(SpRole::getName).toArray(String[]::new));
        authorities.addAll(AuthorityUtils.createAuthorityList(
                permissionList.stream().map(SpPermission::getValue).toArray(String[]::new)));
        logger.debug("authorized " + authorities);

        return new SpAuthenticatedUser(user, authorities);
    }

    /**
     * @param param loginParam
     * @return {@link SpAuthenticatedUser}
     * @throws AuthenticationException auth exp
     * @author 黄纯峰
     * @date 2021/11/30 11:37
     */
    @Override
    public Authentication login(LoginParam param) throws AuthenticationException {
        SpAuthenticatedUser user = loadUserByUsername(param.getUsername());
        if (!passwordEncoder.matches(param.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(user.getUsername() + " password error!");
        }
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return token;
    }

    /**
     * @param pageNum  num
     * @param pageSize size
     * @param status   status
     * @return page
     */
    @Override
    public IPage<UserInfoWithRole> queryPage(Integer pageNum, Integer pageSize, Integer status) {
        IPage<SpUser> page = Page.of(pageNum, pageSize);
        QueryWrapper<SpUser> wrapper = new QueryWrapper<SpUser>()
                .eq("user.status", status);
        return this.userMapper.queryUserWithRole(page, wrapper);
    }

    @Override
    public UserInfoWithRole deleteById(Long id) {
        UserInfoWithRole user = getInfoWithRoleById(id);
        user.setStatus(0);
        if (!this.updateById(user)) {
            throw new DeleteException("删除用户失败", user);
        }
        return user;
    }

    @Override
    public IPage<UserInfoWithRole> searchPage(IPage<SpUser> page, String query) {
        QueryWrapper<SpUser> wrapper = new QueryWrapper<SpUser>().eq("user.status", 1).and(c -> c
                .like("username", query).or()
                .like("qq", query).or()
                .like("introduction", query).or()
                .like("email", query).or()
                .eq("user.id", query).or()
                .eq("gender", query));

        return userMapper.queryUserWithRole(page, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoWithRole addUser(SpUser user, List<Integer> roleIds) {
        user.setLastLoginTime(new Date());
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        int ret = userMapper.insert(user);
        if (ret != 1) {
            throw new InsertException("添加用户失败", user);
        }
        List<SpUserRole> userRoles = roleIds2UserRole(user.getId(), roleIds);
        if (userRoleService.saveBatch(userRoles)) {
            return getInfoWithRoleById(user.getId());
        }
        throw new InsertException("分配角色失败", user);
    }

    @Override
    public boolean checkUniqueColumn(String column, String value) {
        return baseMapper.selectOne(new QueryWrapper<SpUser>().eq(column, value)) == null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoWithRole updateUser(SpUser user, List<Integer> roles) {
        baseMapper.updateById(user);
        userRoleService.removeByMap(Collections.singletonMap("user_id", user.getId()));
        List<SpUserRole> list = roleIds2UserRole(user.getId(), roles);
        userRoleService.saveBatch(list);
        return getInfoWithRoleById(user.getId());
    }


    private List<SpUserRole> roleIds2UserRole(Long userId, List<Integer> roles) {
        return roles.stream().map(r -> {
            SpUserRole userRole = new SpUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(r);
            return userRole;
        }).collect(Collectors.toList());
    }

    @Override
    public UserInfoWithRole getInfoWithRoleById(Long id) {
        QueryWrapper<SpUser> wrapper = new QueryWrapper<SpUser>().eq("user.id", id);
        return userMapper.queryUserWithRole(wrapper);
    }
}
