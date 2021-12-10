package com.rufeng.vuemall.exception;

/**
 * @author 黄纯峰
 * @time 2021-12-07 21:47
 * @package com.rufeng.vuemall.exception
 * @description TODO
 */
public class InsertException extends CrudException {
    public InsertException(String message, Object entity) {
        this(message, null, entity);
    }

    public InsertException(String message, Throwable cause, Object entity) {
        super(message, cause, entity);
    }
}
