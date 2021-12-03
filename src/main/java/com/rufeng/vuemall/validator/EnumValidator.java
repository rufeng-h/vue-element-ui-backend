package com.rufeng.vuemall.validator;

import com.rufeng.vuemall.validator.annotation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 黄纯峰
 * @time 2021-12-01 20:56
 * @package com.rufeng.vuemall
 * @description TODO
 */
public class EnumValidator implements ConstraintValidator<EnumValue, String> {
    private String[] values;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String s : values) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
