package com.rufeng.vuemall.validator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rufeng.vuemall.domain.SpUser;
import com.rufeng.vuemall.service.SpUserService;
import com.rufeng.vuemall.validator.annotation.UniqueInDbForSpUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 黄纯峰
 * @time 2021-12-02 8:57
 * @package com.rufeng.vuemall.validator
 * @description 数据库字段unique校验 sp_user
 */
@Component
public class UniqueInDbValidatorForSpUser implements ConstraintValidator<UniqueInDbForSpUser, String> {
    private final SpUserService userService;
    private String column;

    public UniqueInDbValidatorForSpUser(SpUserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueInDbForSpUser constraintAnnotation) {
        this.column = constraintAnnotation.tableColumn();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.hasText(value) ||
                userService.count(new QueryWrapper<SpUser>().eq(column, value)) == 0;
    }
}
