package com.rufeng.vuemall.mapper;

import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpRolePermissionMapper {
    /**
     * 根据角色获取权限
     * @param roleList
     * @return
     */
    List<SpPermission> getPermissionList(List<SpRole> roleList);
}
