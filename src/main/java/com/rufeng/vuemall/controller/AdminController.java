package com.rufeng.vuemall.controller;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-28 15:26
 * @Version: 1.0
 * @Description:
 */

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.BO.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rufeng
 */
@RestController
@Validated
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('admin', 'super_admin')")
public class AdminController {
    @GetMapping("/auth")
    public CommonResponse<Authentication> authenticationInfo() {
        return CommonResponse.success(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/info")
    public CommonResponse<UserInfo> adminInfo() {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return CommonResponse.success(user);
    }
}
