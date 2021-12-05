package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.BO.RoleWithPermission;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpRolePermission;
import com.rufeng.vuemall.mapper.SpRoleMapper;
import com.rufeng.vuemall.service.SpPermissionService;
import com.rufeng.vuemall.service.SpRolePermissionService;
import com.rufeng.vuemall.service.SpRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpRoleServiceImpl extends ServiceImpl<SpRoleMapper, SpRole> implements SpRoleService {

    private final SpRolePermissionService rolePermissionService;

    public SpRoleServiceImpl(SpRolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public IPage<RoleWithPermission> pageRoleWithPermission(IPage<SpRole> page) {
        IPage<SpRole> rolePage = this.page(page);
        IPage<RoleWithPermission> roleWithPer = rolePage.convert(r -> {
            RoleWithPermission withPermission = new RoleWithPermission();
            BeanUtils.copyProperties(r, withPermission);
            return withPermission;
        });
        roleWithPer.getRecords().forEach(w -> w.setPermissions(rolePermissionService.getPermissionListByRoleId(w.getId())));

        return roleWithPer;
    }
}
