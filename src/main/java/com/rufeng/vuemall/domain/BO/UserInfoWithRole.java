package com.rufeng.vuemall.domain.BO;

import com.rufeng.vuemall.domain.SpRole;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author 黄纯峰
 * @time 2021-12-02 15:40
 * @package com.rufeng.vuemall.domain.BO
 * @description 带角色信息的UserInfo，用于管理员查看
 */
public class UserInfoWithRole extends UserInfoImpl implements RoleContainer {
    private List<SpRole> roles;

    @Override
    public List<SpRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SpRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserInfoWithRole.class.getSimpleName() + "[", "]")
                .add("roles=" + roles)
                .toString();
    }
}
