package com.rufeng.vuemall.domain.BO;

import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.domain.SpRole;

import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-03 16:33
 * @package com.rufeng.vuemall.domain.BO
 * @description TODO
 */
public class RoleWithPermission extends SpRole {
    private List<SpPermission> permissions;

    public List<SpPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SpPermission> permissions) {
        this.permissions = permissions;
    }
}
