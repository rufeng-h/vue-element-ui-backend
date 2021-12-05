package com.rufeng.vuemall.controller;

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.BO.PermissionWithChild;
import com.rufeng.vuemall.service.SpPermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 黄纯峰
 * @time 2021-12-04 19:37
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@RestController
@Validated
@PreAuthorize("hasAnyRole('admin', 'super_admin')")
@RequestMapping("/api/permission")
public class PermissionController {
    private final SpPermissionService permissionService;

    public PermissionController(SpPermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @GetMapping("/list")
    public CommonResponse<List<PermissionWithChild>> list() {
        List<PermissionWithChild> permissions = permissionService.listPermissionWithChild();
        HashMap<Integer, PermissionWithChild> map = new HashMap<>(permissions.size());
        permissions.forEach(p -> map.put(p.getId(), p));
        permissions.forEach(p -> {
            if (p.getParentId() != 0) {
                map.get(p.getParentId()).append(p);
            }
        });
        permissions = permissions.stream().filter(p -> p.getParentId() == 0).collect(Collectors.toList());
        return CommonResponse.success(permissions);
    }
}
