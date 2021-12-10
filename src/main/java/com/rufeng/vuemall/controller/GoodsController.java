package com.rufeng.vuemall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.SpGoods;
import com.rufeng.vuemall.service.SpGoodsService;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄纯峰
 * @time 2021-12-07 17:05
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@Validated
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private final SpGoodsService goodsService;

    public GoodsController(SpGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/list")
    public CommonResponse<RestPage<SpGoods>> list(@RequestParam(required = false) String query,
                                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        IPage<SpGoods> page = Page.of(pageNum, pageSize);
        if (StringUtils.hasText(query)) {
            QueryWrapper<SpGoods> ew = new QueryWrapper<SpGoods>().like("goods_name", query);
            return CommonResponse.success(goodsService.queryPage(page, ew));
        }
        return CommonResponse.success(goodsService.queryPage(page));
    }


}
