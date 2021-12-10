package com.rufeng.vuemall.exception;

/**
 * @author 黄纯峰
 * @time 2021-12-10 9:22
 * @package com.rufeng.vuemall.exception
 * @description TODO
 */
public class FileStoreException extends RuntimeException {
    public FileStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStoreException(String message) {
        this(message, null);
    }
}
