package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.AO.LoginParam;
import com.rufeng.vuemall.domain.BO.UserInfo;
import com.rufeng.vuemall.domain.BO.UserInfoWithRole;
import com.rufeng.vuemall.domain.SpUser;
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
     * @return {@link UserInfo}
     * @throws AuthenticationException 认证错误
     * @author 黄纯峰
     * @date 2021/11/30 10:38
     */
    UserInfo login(LoginParam param) throws AuthenticationException;

    /**
     * 分页查询
     *
     * @param page         分页参数
     * @param queryWrapper 查询条件
     * @return {@link IPage<UserInfo>}
     * @author 黄纯峰
     * @date 2021/11/30 14:18
     */
    IPage<UserInfoWithRole> selectPage(IPage<SpUser> page, QueryWrapper<SpUser> queryWrapper);

    /**
     * 逻辑删除
     *
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 根据关键字查询
     *
     * @param page  分页参数
     * @param query 查询关键字
     * @return {@link IPage}
     */
    IPage<UserInfoWithRole> searchPage(IPage<SpUser> page, String query);

    /**
     *
     * @param roleIds
     * @return
     */
    Integer addUser(SpUser user, List<Integer> roleIds);

    /**
     * 校验字段值在数据库中是否已存在
     *
     * @param column
     * @param value
     * @return true 通过校验，否则 false
     */
    boolean checkUniqueColumn(String column, String value);

    /**
     *
     * @param user entity
     * @param roles roleList
     * @return
     */
    Integer updateUser(SpUser user, List<Integer> roles);
}
