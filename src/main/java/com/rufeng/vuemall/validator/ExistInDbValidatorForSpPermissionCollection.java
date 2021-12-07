package com.rufeng.vuemall.validator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.service.SpPermissionService;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpPermissionCollection;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @author 黄纯峰
 * @time 2021-12-06 22:44
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
@Component
public class ExistInDbValidatorForSpPermissionCollection implements ConstraintValidator<ExistInDbForSpPermissionCollection, Collection<Integer>> {
    private final SpPermissionService service;

    public ExistInDbValidatorForSpPermissionCollection(SpPermissionService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Collection<Integer> value, ConstraintValidatorContext context) {
        if (value == null || value.size() == 0) {
            return false;
        }
        return service.count(new QueryWrapper<SpPermission>().in("id", value)) == value.size();
    }
}
