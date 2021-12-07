package com.rufeng.vuemall.validator;

import com.rufeng.vuemall.validator.annotation.EnumValue;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 黄纯峰
 * @time 2021-12-01 20:56
 * @package com.rufeng.vuemall
 * @description TODO
 */
public class EnumValidator implements ConstraintValidator<EnumValue, String> {
    private String[] values;
    private Set<String> set;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        Class<?>[] cls = constraintAnnotation.targetEnum();
        if (cls.length == 0) {
            this.values = constraintAnnotation.value();
        } else if (cls.length > 1) {
            throw new IllegalArgumentException("仅可指定一个enun类型");
        } else {
            Class<? extends Enum<?>> enumCls = constraintAnnotation.targetEnum()[0];
            Assert.isTrue(enumCls.isEnum(), "需要enum类型!");
            set = Arrays.stream(enumCls.getEnumConstants()).map(Enum::toString).collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this.set == null) {
            return byValues(value);
        }
        return set.contains(value);
    }

    private boolean byValues(String value) {
        for (String s : values) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
