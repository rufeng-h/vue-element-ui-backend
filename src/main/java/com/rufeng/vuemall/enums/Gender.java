package com.rufeng.vuemall.enums;

/**
 * @author 黄纯峰
 * @time 2021-12-06 16:49
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
public enum Gender {
    /**
     * 性别
     */
    MALE("男"), FEMALE("女"),
    male("男"), female("女");
    private final String name;

    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
