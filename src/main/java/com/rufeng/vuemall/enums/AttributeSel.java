package com.rufeng.vuemall.enums;

/**
 * @author 黄纯峰
 * @time 2021-12-06 22:24
 * @package com.rufeng.vuemall.enums
 * @description TODO
 */
public enum AttributeSel {
    /**
     * attr
     */
    MANY("many"), many("many"),
    ONLY("only"), only("only");

    private final String name;

    AttributeSel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
