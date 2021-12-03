package com.rufeng.vuemall.controller;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-28 9:33
 * @Version: 1.0
 * @Description:
 */

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.SpOrder;
import com.rufeng.vuemall.service.SpOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * @author rufeng
 */
@RestController
@RequestMapping("/api/order")
@Validated
public class SpOrderController {
    private final SpOrderService orderService;

    public SpOrderController(SpOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public CommonResponse<SpOrder> getById(
            @PathVariable @Min(1) Long id) {
        return CommonResponse.success(orderService.getById(id));
    }
}
