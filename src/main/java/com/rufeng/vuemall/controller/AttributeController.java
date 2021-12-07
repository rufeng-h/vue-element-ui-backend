package com.rufeng.vuemall.controller;

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.AO.AttrAddParam;
import com.rufeng.vuemall.domain.SpAttribute;
import com.rufeng.vuemall.service.SpAttributeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-06 15:11
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@RestController()
@Validated
@RequestMapping("/api/attribute")
public class AttributeController {
    private final SpAttributeService attributeService;

    public AttributeController(SpAttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public CommonResponse<List<SpAttribute>> getByCateId(@RequestParam Integer categoryId,
                                                         @RequestParam String attrSel) {
        return CommonResponse.success(attributeService.listByCateIdAndSel(categoryId, attrSel));
    }

    @PostMapping("/add")
    public CommonResponse<Void> addAttribute(
            @RequestBody @Validated AttrAddParam param) {
        return CommonResponse.success();
    }
}
