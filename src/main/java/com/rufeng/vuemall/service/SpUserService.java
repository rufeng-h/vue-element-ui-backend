package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.AO.LoginParam;
import com.rufeng.vuemall.domain.BO.UserInfoWithRole;
import com.rufeng.vuemall.domain.SpUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpUserService extends IService<SpUser> {

    /**
     * 登录
     *
     * @param param 通用登录参数
     * @return {@link UserInfoWithRole}
     * @throws AuthenticationException 认证错误
     * @author 黄纯峰
     * @date 2021/11/30 10:38
     */
    Authentication login(LoginParam param) throws AuthenticationException;

    /**
     * 分页查询
     *
     * @param pageNum  num
     * @param pageSize size
     * @param ustatus  用户状态标值志
     * @return page
     */
    IPage<UserInfoWithRole> queryPage(Integer pageNum, Integer pageSize, Integer ustatus);

    /**
     * 逻辑删除
     *
     * @param id id
     */
   UserInfoWithRole deleteById(Long id);

    /**
     * 根据关键字查询
     *
     * @param page  分页参数
     * @param query 查询关键字
     * @return {@link IPage}
     */
    IPage<UserInfoWithRole> searchPage(IPage<SpUser> page, String query);

    /**
     * 添加用户
     *
     * @param user    用户信息
     * @param roleIds 该用户的角色id列表
     * @return added count
     */
    UserInfoWithRole addUser(SpUser user, List<Integer> roleIds);

    /**
     * 校验字段值在数据库中是否已存在
     *
     * @param column db列名
     * @param value  值
     * @return true 通过校验，否则 false
     */
    boolean checkUniqueColumn(String column, String value);

    /**
     * 更新用户信息
     *
     * @param user  entity
     * @param roles roleList
     * @return updated count
     */
    UserInfoWithRole updateUser(SpUser user, List<Integer> roles);


    UserInfoWithRole getInfoWithRoleById(Long id);
}
