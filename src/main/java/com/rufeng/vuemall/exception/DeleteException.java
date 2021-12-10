package com.rufeng.vuemall.exception;

/**
 * @author 黄纯峰
 * @time 2021-12-09 14:10
 * @package com.rufeng.vuemall.exception
 * @description TODO
 */
public class DeleteException extends CrudException {
    public DeleteException(String message, Throwable cause, Object entity) {
        super(message, cause, entity);
    }

    public DeleteException(String message, Object entity) {
        this(message, null, entity);
    }
}
