package com.rufeng.vuemall.validator.annotation;

import com.rufeng.vuemall.validator.ExistInDbValidatorForSpPermissionCollection;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 黄纯峰
 * @time 2021-12-06 22:39
 * @package com.rufeng.vuemall.validator.annotation
 * @description TODO
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistInDbValidatorForSpPermissionCollection.class})
public @interface ExistInDbForSpPermissionCollection {
    String message();


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
