package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.SpGoods;
import com.rufeng.vuemall.mapper.SpGoodsMapper;
import com.rufeng.vuemall.service.SpGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpGoodsServiceImpl extends ServiceImpl<SpGoodsMapper, SpGoods> implements SpGoodsService {

    private <T> RestPage<T> convert(IPage<T> iPage) {
        RestPage<T> restPage = new RestPage<>();
        BeanUtils.copyProperties(iPage, restPage);
        return restPage;
    }

    @Override
    public RestPage<SpGoods> queryPage(IPage<SpGoods> page) {
        return convert(page(page));
    }

    @Override
    public RestPage<SpGoods> queryPage(IPage<SpGoods> page, Wrapper<SpGoods> ew) {
        return convert(page(page, ew));
    }
}
