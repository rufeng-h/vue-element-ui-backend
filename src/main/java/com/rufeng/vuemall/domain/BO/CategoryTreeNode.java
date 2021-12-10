package com.rufeng.vuemall.domain.BO;

import com.rufeng.vuemall.domain.SpCategory;

/**
 * @author 黄纯峰
 * @time 2021-12-08 9:39
 * @package com.rufeng.vuemall.domain.BO
 * @description TODO
 */
public class CategoryTreeNode extends SpCategory implements TreeNode {

    private Boolean hasChildren;

    @Override
    public boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public boolean getLeaf() {
        return !hasChildren;
    }

    @Override
    public String toString() {
        return getCatName() + " " + hasChildren;
    }
}
