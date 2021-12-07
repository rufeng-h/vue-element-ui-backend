package com.rufeng.vuemall.validator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rufeng.vuemall.domain.SpCategory;
import com.rufeng.vuemall.service.SpCategoryService;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpCategory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 黄纯峰
 * @time 2021-12-06 16:18
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
@Component
public class ExistInDbValidatorForSpCategory implements ConstraintValidator<ExistInDbForSpCategory, Integer> {

    private final SpCategoryService service;

    public ExistInDbValidatorForSpCategory(SpCategoryService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return service.getById(new QueryWrapper<SpCategory>().eq("id", value)) != null;
    }
}
