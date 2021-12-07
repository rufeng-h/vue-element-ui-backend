package com.rufeng.vuemall.validator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.service.SpRoleService;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpRoleCollection;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @author 黄纯峰
 * @time 2021-12-06 22:31
 * @package com.rufeng.vuemall.validator
 * @description 校验整个集合
 */
@Component
public class ExistInDbValidatorForSpRoleCollection implements ConstraintValidator<ExistInDbForSpRoleCollection, Collection<Integer>> {
    private final SpRoleService service;

    public ExistInDbValidatorForSpRoleCollection(SpRoleService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Collection<Integer> value, ConstraintValidatorContext context) {
        if (value.size() == 0) {
            return true;
        }
        return service.list(new QueryWrapper<SpRole>().in("id", value)).size() == value.size();
    }
}
