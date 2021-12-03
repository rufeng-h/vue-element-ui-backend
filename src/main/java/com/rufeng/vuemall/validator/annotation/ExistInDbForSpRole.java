package com.rufeng.vuemall.validator.annotation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.validator.ExistInDbValidatorForSpRole;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 黄纯峰
 * @time 2021-12-03 9:05
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistInDbValidatorForSpRole.class})
public @interface ExistInDbForSpRole {
    String message();

    String tableColumn();

    Class<? extends IService<SpRole>> service();


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
