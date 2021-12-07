package com.rufeng.vuemall.domain.AO;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-28 8:07
 * @Version: 1.0
 * @Description:
 */

import javax.validation.constraints.Size;

/**
 * 通用登录参数
 *
 * @author rufeng
 */
public class LoginParam {
    @Size(min = 3, max = 10, message = "用户名3到10个字符")
    private String username;
    @Size(min = 6, message = "长度至少为6个字符")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
