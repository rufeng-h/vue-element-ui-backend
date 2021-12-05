package com.rufeng.vuemall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpRolePermission;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpRolePermissionMapper extends BaseMapper<SpRolePermission> {
    /**
     * 根据角色获取权限
     *
     * @param roleList roleList
     * @return listPermission
     */
    List<SpPermission> getPermissionList(List<SpRole> roleList);

    /**
     * 由id获取角色的所有权限
     *
     * @param roleId 角色id
     * @return permissionList
     */
    List<SpPermission> getPermissionListByRoleId(Integer roleId);
}
