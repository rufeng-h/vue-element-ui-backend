package com.rufeng.vuemall.domain.BO;

import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUser;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author 黄纯峰
 * @time 2021-12-02 15:40
 * @package com.rufeng.vuemall.domain.BO
 * @description 带角色信息的User
 */
public class UserInfoWithRole extends SpUser {
    private List<SpRole> roles;

    public List<SpRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SpRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString() + new StringJoiner(", ", UserInfoWithRole.class.getSimpleName() + "[", "]")
                .add("roles=" + roles);
    }
}
