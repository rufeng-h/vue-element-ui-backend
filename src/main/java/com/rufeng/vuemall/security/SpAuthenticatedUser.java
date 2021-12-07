package com.rufeng.vuemall.security;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-28 8:47
 * @Version: 1.0
 * @Description:
 */

import com.rufeng.vuemall.domain.SpUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author rufeng
 */
public class SpAuthenticatedUser extends SpUser implements UserDetails {
    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    public SpAuthenticatedUser(SpUser user, Collection<? extends GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        BeanUtils.copyProperties(user, this);
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        TreeSet<GrantedAuthority> set = new TreeSet<>(new AuthorityComparator());
        for (GrantedAuthority authority : authorities) {
            Assert.notNull(authority, "权限不能为null");
            set.add(authority);
        }
        this.authorities = set;
    }

    public SpAuthenticatedUser(SpUser user, Collection<? extends GrantedAuthority> authorities) {
        this(user, authorities, true, true, true, true);
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }

    }
}
