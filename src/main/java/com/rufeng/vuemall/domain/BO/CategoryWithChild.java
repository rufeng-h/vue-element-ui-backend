package com.rufeng.vuemall.domain.BO;

import com.rufeng.vuemall.domain.SpCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-05 23:07
 * @package com.rufeng.vuemall.domain.BO
 * @description TODO
 */
public class CategoryWithChild extends SpCategory {
    private List<CategoryWithChild> children;

    public List<CategoryWithChild> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryWithChild> children) {
        this.children = children;
    }

    public void append(CategoryWithChild child){
        if (this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }
}
