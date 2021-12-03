package com.rufeng.vuemall.validator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpRole;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-03 9:06
 * @package com.rufeng.vuemall.validator
 * @description TODO
 */
@Component
public class ExistInDbValidatorForSpRole implements ConstraintValidator<ExistInDbForSpRole, List<Integer>>, ApplicationContextAware {
    private String column;
    private ApplicationContext context;
    private Class<? extends IService<SpRole>> serviceCls;

    @Override
    public void initialize(ExistInDbForSpRole constraintAnnotation) {
        column = constraintAnnotation.tableColumn();
        serviceCls = constraintAnnotation.service();
    }

    @Override
    public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
        int size = value.size();
        if (size == 0) {
            return true;
        }
        IService<SpRole> service = this.context.getBean(serviceCls);
        QueryWrapper<SpRole> wrapper = new QueryWrapper<>();
        wrapper.eq(column, value.get(0));
        for (int i = 1; i < size; i++) {
            wrapper.or().eq(column, value.get(i));
        }
        return service.getBaseMapper().selectCount(wrapper) == size;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
