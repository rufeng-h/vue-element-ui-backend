package com.rufeng.vuemall.controller;

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.service.SpRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private final SpRoleService roleService;

    public RoleController(SpRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public CommonResponse<List<SpRole>> list() {
        return CommonResponse.success(roleService.list());
    }
}
