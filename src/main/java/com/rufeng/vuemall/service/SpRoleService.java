package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.BO.RoleWithPermission;
import com.rufeng.vuemall.domain.SpRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpRoleService extends IService<SpRole> {
    /**
     * 附带对应权限的角色列表
     *
     * @param page 分页参数
     * @return page
     */
    IPage<RoleWithPermission> pageRoleWithPermission(IPage<SpRole> page);


    /**
     * 为角色分配权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id列表
     * @return succ
     */
    boolean authorize(Integer roleId, List<Integer> permissionIds);

    /**
     * 为角色撤销权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id列表
     * @return succ
     */
    boolean unauthorize(Integer roleId, List<Integer> permissionIds);
}
