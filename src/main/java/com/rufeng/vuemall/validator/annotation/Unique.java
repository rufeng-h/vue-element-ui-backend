package com.rufeng.vuemall.validator.annotation;

import com.rufeng.vuemall.validator.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 黄纯峰
 * @time 2021-12-02 8:55
 * @package com.rufeng.vuemall.validator
 * @description TODO 如何自动识别数据库字段
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueValidator.class})
public @interface Unique {
    String message();

    String tableColumn();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
