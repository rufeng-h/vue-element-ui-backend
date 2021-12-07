package com.rufeng.vuemall.exception;

/**
 * @author 黄纯峰
 * @time 2021-12-01 21:20
 * @package com.rufeng.vuemall.exceptions
 * @description TODO
 */
public class UniqueColumnException extends RuntimeException {
    private final String column;

    public UniqueColumnException(String message, String column) {
        super(message);
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
