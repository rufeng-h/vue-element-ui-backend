package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpRolePermission;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpRolePermissionService extends IService<SpRolePermission> {
    /**
     * 查询角色列表中角色的组合权限
     *
     * @param roleList 角色列表，永远不为空
     * @return 权限列表，永远不为空，如果无权限，返回空列表
     */
    @NonNull
    List<SpPermission> getPermissionList(List<SpRole> roleList);

    /**
     * 查询指定角色所具有的所有权限
     *
     * @param roleId
     * @return
     */
    List<SpPermission> getPermissionListByRoleId(Integer roleId);
}
