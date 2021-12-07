package com.rufeng.vuemall.validator;

import com.rufeng.vuemall.service.SpRoleService;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpRole;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 黄纯峰
 * @time 2021-12-03 9:06
 * @package com.rufeng.vuemall.validator
 * @description 校验值必须在数据库中存在 TODO id
 */
@Component
public class ExistInDbValidatorForSpRole implements ConstraintValidator<ExistInDbForSpRole, Integer> {
    private final SpRoleService service;

    public ExistInDbValidatorForSpRole(SpRoleService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return service.getById(value) != null;
    }
}
