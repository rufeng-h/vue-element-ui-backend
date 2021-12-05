package com.rufeng.vuemall.domain.AO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-04 23:52
 * @package com.rufeng.vuemall.domain.AO
 * @description TODO
 */
public class AuthorizationParam {
    @Min(1)
    private Integer roleId;
    @NotEmpty
    private List<@Min(1) Integer> permissionIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
