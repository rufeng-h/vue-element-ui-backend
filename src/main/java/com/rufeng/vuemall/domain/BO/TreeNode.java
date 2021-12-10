package com.rufeng.vuemall.domain.BO;

/**
 * @author 黄纯峰
 * @time 2021-12-07 21:58
 * @package com.rufeng.vuemall.domain.BO
 * @description TODO
 */
public interface TreeNode {
    /**
     * haschildren
     *
     * @return bool
     */
    boolean getHasChildren();

    /**
     * isLeaf
     *
     * @return bool
     */
    boolean getLeaf();
}
