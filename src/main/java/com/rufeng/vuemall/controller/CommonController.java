package com.rufeng.vuemall.controller;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-27 15:35
 * @Version: 1.0
 * @Description:
 */

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.AO.LoginParam;
import com.rufeng.vuemall.service.SpUserService;
import com.rufeng.vuemall.util.RequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rufeng
 */
@RestController
@Validated
public class CommonController {
    private static final String DEFAULT_REDIRECT_URL = "/home";
    private static final Log logger = LogFactory.getLog(CommonController.class);
    private final SpUserService userService;

    public CommonController(SpUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout")
    public CommonResponse<Map<String, Object>> logout(HttpServletRequest request) {
        if (RequestUtils.isLogin()) {
            request.getSession().invalidate();
            logger.debug(SecurityContextHolder.getContext().getAuthentication().getName() + " logout successfully!");
        }
        return CommonResponse.redirect(Collections.singletonMap("redirect", DEFAULT_REDIRECT_URL));
    }

    @GetMapping("/isLogin")
    public CommonResponse<Map<String, Object>> testLogin() {
        return CommonResponse.success(Collections.singletonMap("isLogin", RequestUtils.isLogin()));
    }


    @PostMapping("/login")
    public CommonResponse<Map<String, Object>> login(
            @Validated @RequestBody LoginParam param,
            @RequestParam(required = false) @Pattern(regexp = "/|/[^/]+/?", message = "参数错误") String redirect) {
        Map<String, Object> map = new HashMap<>(2);
        Authentication authentication;
        String msg;
        if (RequestUtils.isLogin()) {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            msg = "勿重复登录!";
        } else {
            authentication = userService.login(param);
            msg = "登录成功!";
            logger.debug(authentication.getName() + "login successfully!");
        }
        map.put("redirect", redirect == null ? DEFAULT_REDIRECT_URL : redirect);
        map.put("userInfo", authentication);
        return CommonResponse.redirect(msg, map);
    }
}
