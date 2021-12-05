package com.rufeng.vuemall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.AO.AuthorizationParam;
import com.rufeng.vuemall.domain.BO.PermissionWithChild;
import com.rufeng.vuemall.domain.BO.RoleWithPermission;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpRolePermission;
import com.rufeng.vuemall.service.SpRolePermissionService;
import com.rufeng.vuemall.service.SpRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author 黄纯峰
 * @time 2021-12-02 15:25
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@RestController
@Validated
@PreAuthorize("hasAnyRole('admin', 'super_admin')")
@RequestMapping("/api/role")
public class RoleController {
    private static final String RET_TYPE_LIST = "list";
    private static final String RET_TYPE_TREE = "tree";
    private final SpRoleService roleService;
    private final SpRolePermissionService rolePermissionService;
    /**
     * 将角色permission树形化
     */
    private final Consumer<RoleWithPermission> TREEFY = role -> {
        List<SpPermission> permissions = role.getPermissions();
        HashMap<Integer, PermissionWithChild> map = new HashMap<>(64);
        permissions.forEach(p -> {
            PermissionWithChild withChild = new PermissionWithChild();
            BeanUtils.copyProperties(p, withChild);
            map.put(p.getId(), withChild);
        });
        map.values().forEach(p -> {
            if (p.getParentId() != 0) {
                map.get(p.getId()).append(p);
            }
        });
        role.setPermissions(
                map.values().stream().filter(p -> p.getParentId() == 0).collect(Collectors.toList()));
    };

    public RoleController(SpRoleService roleService, SpRolePermissionService rolePermissionService) {
        this.roleService = roleService;
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("/list")
    public CommonResponse<RestPage<RoleWithPermission>> list(
            @RequestParam(required = false, defaultValue = RET_TYPE_LIST) String type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        IPage<SpRole> page = Page.of(pageNum, pageSize);
        IPage<RoleWithPermission> iPage = roleService.pageRoleWithPermission(page);
        if (RET_TYPE_TREE.equals(type)) {
            iPage.getRecords().forEach(TREEFY);
        }
        RestPage<RoleWithPermission> restPage = new RestPage<>();
        BeanUtils.copyProperties(iPage, restPage);
        return CommonResponse.success(restPage);
    }

    @PostMapping("/authorize")
    public CommonResponse<Void> authorize(@Validated @RequestBody AuthorizationParam param) {
        Integer roleId = param.getRoleId();
        List<Integer> permissionIds = param.getPermissionIds();
        List<SpRolePermission> collect = permissionIds.stream().map(id -> {
            SpRolePermission rolePermission = new SpRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            return rolePermission;
        }).collect(Collectors.toList());
        boolean b = rolePermissionService.saveOrUpdateBatch(collect);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }

    @PostMapping("/unauthorize")
    public CommonResponse<Void> unauthorize(@Validated @RequestBody AuthorizationParam param) {
        Integer roleId = param.getRoleId();
        List<Integer> permissionIds = param.getPermissionIds();
        QueryWrapper<SpRolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId).and(w -> w.in("permission_id", permissionIds));
        boolean b = rolePermissionService.remove(wrapper);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }
}
