package com.rufeng.vuemall.validator.annotation;

import com.rufeng.vuemall.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验数据库unique约束
 *
 * @author 黄纯峰
 * @time 2021-12-01 20:57
 * @package com.rufeng.vuemall.validator
 * @description TODO 如何支持枚举类
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.class})
public @interface EnumValue {
    String message() default "错误的枚举值";

    /*允许的值*/
    String[] value() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 必须是下面枚举类中的一个，如果定义了这个值，value将被忽略
     * 长度最多为1
     */
    Class<? extends Enum<?>>[] targetEnum() default {};
}
