package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUserRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpUserRoleService extends IService<SpUserRole> {
    /**
     * 通过用于id查询角色列表
     *
     * @param userId id
     * @return 角色列表，永远返回长度至少为1的列表
     */
    List<SpRole> getRoleListById(Long userId);
}
