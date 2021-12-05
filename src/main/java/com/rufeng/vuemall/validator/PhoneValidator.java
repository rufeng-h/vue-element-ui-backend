package com.rufeng.vuemall.validator;

import com.rufeng.vuemall.validator.annotation.Phone;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author 黄纯峰
 * @time 2021-12-03 21:20
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
@Component
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private final Pattern pattern = Pattern.compile("^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][189]))[0-9]{8}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return pattern.matcher(value).matches();
    }
}
