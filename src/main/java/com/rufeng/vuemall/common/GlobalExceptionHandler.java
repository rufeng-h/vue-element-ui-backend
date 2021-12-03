package com.rufeng.vuemall.common;
/*
 * Created with IntelliJ IDEA.
 * @Author: Pluto
 * @Date: 2021/08/30/10:42
 * @package: com.pluto.mall.exception
 * @Version: 1.0
 * @Description:
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @author Pluto
 * controller全局异常处理器， 处理controller中发生的异常
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

//    /**
//     * 订单异常
//     */
//    @ExceptionHandler(OrderException.class)
//    CommonResponse<Object> orderErr(OrderException e) {
//        return CommonResponse.failed(e.getMessage());
//    }
//
//    /**
//     * 处理注册异常
//     *
//     * @param ex 抛出的注册异常
//     */
//    @ExceptionHandler(RegisterException.class)
//    CommonResponse<Void> register(Exception ex) {
//        return CommonResponse.failed(ex.getMessage());
//    }
//

    /**
     * 使用SpringSecurity时
     * 处理登录认证异常
     */
    @ExceptionHandler({AuthenticationException.class})
    CommonResponse<Void> authentication(AuthenticationException ex) {
        return CommonResponse.authenticationFailed(ex.getMessage());
    }

    /**
     * 权限不足
     */
    @ExceptionHandler({AccessDeniedException.class})
    CommonResponse<Void> accessDenied(Exception ex) {
        return CommonResponse.unAuthorized(ex.getMessage());
    }

    /**
     * 参数校验错误, 路径参数、请求参数
     */
    @ExceptionHandler(ValidationException.class)
    CommonResponse<Void> validateFailedDetails(ValidationException e) {
        return CommonResponse.validateFailed(e.getMessage());
    }

    /**
     * 字段校验不通过
     */
    @ExceptionHandler(BindException.class)
    public CommonResponse<Void> bindError(BindingResult result) {
        final String s = result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(","));
        return CommonResponse.validateFailed(s);
    }

    /**
     * 无法读取http请求体
     */
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,})
    public CommonResponse<Void> validateFailed() {
        return CommonResponse.validateFailed();
    }


//    @ExceptionHandler(UniqueColumnException.class)
//    public CommonResponse<Void> uniquColumn(Exception e) {
//        return CommonResponse.validateFailed(e.getMessage());
//    }
}
