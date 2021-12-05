package com.rufeng.vuemall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.AO.AuthorizationParam;
import com.rufeng.vuemall.domain.BO.PermissionWithChild;
import com.rufeng.vuemall.domain.BO.RoleWithPermission;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;
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

    public RoleController(SpRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public CommonResponse<List<SpRole>> list(){
        return CommonResponse.success(roleService.list());
    }

    @GetMapping("/withPermissions")
    public CommonResponse<RestPage<RoleWithPermission>> withPer(
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
        boolean b = roleService.authorize(roleId, permissionIds);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }

    @PostMapping("/unauthorize")
    public CommonResponse<Void> unauthorize(@Validated @RequestBody AuthorizationParam param) {
        Integer roleId = param.getRoleId();
        List<Integer> permissionIds = param.getPermissionIds();
        boolean b = roleService.unauthorize(roleId, permissionIds);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }
}
