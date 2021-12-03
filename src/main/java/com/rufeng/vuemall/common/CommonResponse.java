package com.rufeng.vuemall.common;
/*
 * Created with IntelliJ IDEA.
 * @Author: Pluto
 * @Date: 2021/08/27/23:26
 * @package: com.pluto.springsecurity.common
 * @Version: 1.0
 * @Description:
 */


import java.io.Serializable;

/**
 * @author Pluto
 */
public class CommonResponse<T> implements Serializable {
    private final Long code;
    private final T data;
    private final String message;

    private CommonResponse(Long code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> CommonResponse<T> redirect(T data) {
        return redirect(ResultCode.REDIRECT.getMessage(), data);
    }

    public static <T> CommonResponse<T> redirect(String message, T data) {
        return new CommonResponse<>(ResultCode.REDIRECT.getCode(), message, data);
    }

    public static <T> CommonResponse<T> failed(String message) {
        return CommonResponse.failed(message, null);
    }

    public static <T> CommonResponse<T> failed() {
        return CommonResponse.failed(ResultCode.FAILED.getMessage());
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResponse<T> success(String message) {
        return CommonResponse.success(message, null);
    }

    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> CommonResponse<T> success() {
        return CommonResponse.success(ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> CommonResponse<T> failed(String message, T data) {
        return CommonResponse.failed(ResultCode.FAILED.getCode(), message, data);
    }

    public static <T> CommonResponse<T> failed(Long code, String message, T data) {
        return new CommonResponse<>(code, message, data);
    }

    public static <T> CommonResponse<T> unAuthorized(String message) {
        return CommonResponse.failed(ResultCode.FORBBIDEN.getCode(), message, null);
    }

    public static <T> CommonResponse<T> authenticationFailed(String message) {
        return CommonResponse.failed(ResultCode.AUTHENTICATE_FAILED.getCode(), message, null);
    }

    public static <T> CommonResponse<T> validateFailed(String message) {
        return CommonResponse.failed(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    public static <T> CommonResponse<T> validateFailed() {
        return CommonResponse.failed(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(), null);
    }

    public String getMessage() {
        return message;
    }


    public Long getCode() {
        return code;
    }


    public T getData() {
        return data;
    }

}
