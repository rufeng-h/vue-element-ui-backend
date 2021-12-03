package com.rufeng.vuemall.security;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-28 8:47
 * @Version: 1.0
 * @Description:
 */

import com.rufeng.vuemall.domain.BO.UserInfo;
import com.rufeng.vuemall.domain.BO.UserInfoImpl;
import com.rufeng.vuemall.domain.SpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author rufeng
 */
public class SpAuthenticatedUser extends User {
    private final UserInfo userInfo;

    public SpAuthenticatedUser(SpUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.userInfo = new UserInfoImpl();
        BeanUtils.copyProperties(user, this.userInfo);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
