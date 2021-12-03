package com.rufeng.vuemall.service;

import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
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
public interface SpRolePermissionService {
    /**
     * 通过角色返回用户的权限
     *
     * @param roleList 角色列表，永远不为空
     * @return 权限列表，永远不为空，如果无权限，返回空列表
     */
    @NonNull
    List<SpPermission> getPermissionList(List<SpRole> roleList);
}
