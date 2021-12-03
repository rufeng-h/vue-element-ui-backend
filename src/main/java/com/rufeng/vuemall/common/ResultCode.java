package com.rufeng.vuemall.common;


import java.io.Serializable;

/**
 * @author Pluto
 */

public enum ResultCode implements Serializable {
    /**
     * 错误码
     */
    SUCCESS(200L, "操作成功"),
    FAILED(400L, "操作失败"),
    VALIDATE_FAILED(400L, "参数检验失败"),
    FORBBIDEN(403L, "没有相关权限"),
    AUTHENTICATE_FAILED(401L, "认证失败"),
    REDIRECT(301L, "");
    private final Long code;
    private final String message;

    ResultCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
