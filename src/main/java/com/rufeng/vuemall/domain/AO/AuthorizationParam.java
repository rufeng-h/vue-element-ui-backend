package com.rufeng.vuemall.domain.AO;

import com.rufeng.vuemall.validator.annotation.ExistInDbForSpPermissionCollection;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpRole;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-04 23:52
 * @package com.rufeng.vuemall.domain.AO
 * @description 为角色添加权限
 */
public class AuthorizationParam {
    @Min(1)
    @ExistInDbForSpRole(message = "角色id不存在")
    private Integer roleId;

    @NotEmpty(message = "权限列表不能为空")
    @ExistInDbForSpPermissionCollection(message = "权限不存在")
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
