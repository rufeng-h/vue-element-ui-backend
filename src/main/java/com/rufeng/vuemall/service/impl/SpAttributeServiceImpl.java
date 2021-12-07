package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.SpAttribute;
import com.rufeng.vuemall.mapper.SpAttributeMapper;
import com.rufeng.vuemall.service.SpAttributeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpAttributeServiceImpl extends ServiceImpl<SpAttributeMapper, SpAttribute> implements SpAttributeService {

    @Override
    public List<SpAttribute> listByCateIdAndSel(Integer categoryId, String attrSel) {
        QueryWrapper<SpAttribute> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId).eq("attr_sel", attrSel);
        return list(wrapper);
    }
}
