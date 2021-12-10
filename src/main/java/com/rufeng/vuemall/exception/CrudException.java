package com.rufeng.vuemall.exception;

/**
 * @author 黄纯峰
 * @time 2021-12-07 21:51
 * @package com.rufeng.vuemall.exception
 * @description TODO
 */
public abstract class CrudException extends RuntimeException {
    protected final Object entity;

    public CrudException(String message, Throwable cause, Object entity) {
        super(message, cause);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
