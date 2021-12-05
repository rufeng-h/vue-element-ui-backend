package com.rufeng.vuemall.domain.BO;

import com.rufeng.vuemall.domain.SpPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-04 19:59
 * @package com.rufeng.vuemall.domain.BO
 * @description TODO
 */
public class PermissionWithChild extends SpPermission {
    private List<PermissionWithChild> children;

    public List<PermissionWithChild> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionWithChild> children) {
        this.children = children;
    }

    public void append(PermissionWithChild permission) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(permission);
    }
}
