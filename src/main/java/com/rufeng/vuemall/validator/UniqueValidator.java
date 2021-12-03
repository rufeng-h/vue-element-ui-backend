package com.rufeng.vuemall.validator;

import com.rufeng.vuemall.service.SpUserService;
import com.rufeng.vuemall.validator.annotation.Unique;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 黄纯峰
 * @time 2021-12-02 8:57
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
@Component
public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private final SpUserService userService;
    private String column;

    public UniqueValidator(SpUserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.column = constraintAnnotation.tableColumn();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.hasText(value) ||
                userService.checkUniqueColumn(column, value);
    }
}
