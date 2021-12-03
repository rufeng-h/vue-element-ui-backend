package com.rufeng.vuemall.controller;

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.AO.Menu;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-11-30 12:49
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@RestController
@Validated
@PreAuthorize("hasAnyRole('admin', 'super_admin')")
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping("/menuList")
    public CommonResponse<List<Menu>> menuList() {
        return CommonResponse.success(Menu.getMenuList());
    }
}
