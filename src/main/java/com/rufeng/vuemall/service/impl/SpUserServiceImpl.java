package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.AO.LoginParam;
import com.rufeng.vuemall.domain.BO.UserInfo;
import com.rufeng.vuemall.domain.BO.UserInfoWithRole;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUser;
import com.rufeng.vuemall.domain.SpUserRole;
import com.rufeng.vuemall.mapper.SpUserMapper;
import com.rufeng.vuemall.security.SpAuthenticatedUser;
import com.rufeng.vuemall.service.SpRolePermissionService;
import com.rufeng.vuemall.service.SpUserRoleService;
import com.rufeng.vuemall.service.SpUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import java.util.function.Function;
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
    private static final Function<SpUser, UserInfoWithRole> USER_TO_USERINFO = u -> {
        UserInfoWithRole userInfo = new UserInfoWithRole();
        BeanUtils.copyProperties(u, userInfo);
        return userInfo;
    };

    private final PasswordEncoder passwordEncoder;
    private final SpUserRoleService userRoleService;
    private final SpRolePermissionService rolePermissionService;


    public SpUserServiceImpl(PasswordEncoder passwordEncoder,
                             SpUserRoleService userRoleService,
                             SpRolePermissionService rolePermissionService) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 根据用户名查询用户并授权
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
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
     * @param param
     * @return {@link UserInfo}
     * @throws
     * @author 黄纯峰
     * @date 2021/11/30 11:37
     */
    @Override
    public UserInfo login(LoginParam param) throws AuthenticationException {
        SpAuthenticatedUser user = loadUserByUsername(param.getUsername());
        if (!passwordEncoder.matches(param.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(user.getUsername() + " password error!");
        }
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUserInfo(), null, user.getAuthorities());
        token.eraseCredentials();
        SecurityContextHolder.getContext().setAuthentication(token);
        return user.getUserInfo();
    }

    /**
     * 分页查询用户数据
     *
     * @param page         分页参数
     * @param queryWrapper 条件实体
     * @return {@link List<UserInfo>}
     * @author 黄纯峰
     * @date 2021/11/30 13:47
     */
    @Override
    public IPage<UserInfoWithRole> selectPage(IPage<SpUser> page, QueryWrapper<SpUser> queryWrapper) {
        IPage<SpUser> selectPage = this.baseMapper.selectPage(page, queryWrapper);
        return setRoles(selectPage);
    }

    @Override
    public void deleteById(Long id) {
        SpUser user = new SpUser();
        user.setId(id);
        user.setStatus(1);
        baseMapper.updateById(user);
    }

    @Override
    public IPage<UserInfoWithRole> searchPage(IPage<SpUser> page, String query) {
        QueryWrapper<SpUser> wrapper = new QueryWrapper<>();
        wrapper.like("username", query).or()
                .like("qq", query).or()
                .like("introduction", query).or()
                .like("email", query).or()
                .eq("id", query).or()
                .eq("gender", query);

        IPage<SpUser> userPage = baseMapper.queryPage(page, wrapper);
        return setRoles(userPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addUser(SpUser user, List<Integer> roleIds) {
        user.setLastLoginTime(new Date());
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        Integer ret = baseMapper.insert(user);
        List<SpUserRole> userRoles = roleIds2UserRole(user.getId(), roleIds);
        userRoleService.saveBatch(userRoles);
        return ret;
    }

    @Override
    public boolean checkUniqueColumn(String column, String value) {
        return baseMapper.selectOne(new QueryWrapper<SpUser>().eq(column, value)) == null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateUser(SpUser user, List<Integer> roles) {
        Integer ret = baseMapper.updateById(user);
        userRoleService.removeByMap(Collections.singletonMap("user_id", user.getId()));
        List<SpUserRole> list = roleIds2UserRole(user.getId(), roles);
        userRoleService.saveBatch(list);
        return ret;
    }


    private List<SpUserRole> roleIds2UserRole(Long userId, List<Integer> roles) {
        return roles.stream().map(r -> {
            SpUserRole userRole = new SpUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(r);
            return userRole;
        }).collect(Collectors.toList());
    }

    /**
     * 为每个user查询并设置roleList
     * @param userPage userpage
     * @return useri
     */
    private IPage<UserInfoWithRole> setRoles(IPage<SpUser> userPage) {
        IPage<UserInfoWithRole> userInfoWithRoles = userPage.convert(USER_TO_USERINFO);
        /*开始查询角色*/
        List<UserInfoWithRole> users = userInfoWithRoles.getRecords();
        for (UserInfoWithRole user : users) {
            user.setRoles(userRoleService.getRoleListById(user.getId()));
        }
        return userInfoWithRoles;
    }
}
