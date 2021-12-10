package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.SpGoods;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpGoodsService extends IService<SpGoods> {

    RestPage<SpGoods> queryPage(IPage<SpGoods> page);

    RestPage<SpGoods> queryPage(IPage<SpGoods> page, Wrapper<SpGoods> ew);

}
