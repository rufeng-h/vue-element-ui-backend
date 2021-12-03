package com.rufeng.vuemall.service.impl;

import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.mapper.SpRolePermissionMapper;
import com.rufeng.vuemall.service.SpRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpRolePermissionServiceImpl implements SpRolePermissionService {

    private final SpRolePermissionMapper rolePermissionMapper;

    public SpRolePermissionServiceImpl(SpRolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override

    public List<SpPermission> getPermissionList(List<SpRole> roles) {
        List<SpPermission> permissionList = rolePermissionMapper.getPermissionList(roles);
        return permissionList == null ? Collections.emptyList() : permissionList;
    }
}
