package com.rufeng.vuemall.util;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-27 15:19
 * @Version: 1.0
 * @Description:
 */

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author rufeng
 */
public class RequestUtils {
    private static final String ANONYMOUSE_USER = "anonymousUser";

    public static boolean isLogin() {
        return !ANONYMOUSE_USER.equals(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
