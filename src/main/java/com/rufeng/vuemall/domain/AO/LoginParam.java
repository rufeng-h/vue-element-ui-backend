package com.rufeng.vuemall.domain.AO;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-28 8:07
 * @Version: 1.0
 * @Description:
 */

/**
 * 通用登录参数
 *
 * @author rufeng
 */
public class LoginParam {
    private String username;
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
