package com.rufeng.vuemall.exception;

/**
 * @author 黄纯峰
 * @time 2021-12-07 21:55
 * @package com.rufeng.vuemall.exception
 * @description TODO
 */
public class UpdateException extends CrudException {
    public UpdateException(String message, Object entity) {
        this(message, null, entity);
    }

    public UpdateException(String message, Throwable cause, Object entity) {
        super(message, cause, entity);
    }
}
