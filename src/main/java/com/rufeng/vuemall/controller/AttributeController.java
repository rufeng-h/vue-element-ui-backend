package com.rufeng.vuemall.controller;

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.SpAttribute;
import com.rufeng.vuemall.service.SpAttributeService;
import com.rufeng.vuemall.validator.group.Delete;
import com.rufeng.vuemall.validator.group.Insert;
import com.rufeng.vuemall.validator.group.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public CommonResponse<SpAttribute> addAttribute(
            @RequestBody @Validated(Insert.class) SpAttribute attribute) {
        if (attributeService.save(attribute)) {
            return CommonResponse.success(attributeService.getById(attribute.getId()));
        }
        return CommonResponse.failed();
    }

    @PostMapping("/update")
    public CommonResponse<SpAttribute> update(@RequestBody @Validated(Update.class) SpAttribute attribute) {
        attribute.setUpdateTime(new Date());
        return attributeService.updateById(attribute) ? CommonResponse.success(attribute) : CommonResponse.failed();
    }

    @PostMapping("/delete")
    public CommonResponse<SpAttribute> delete(@RequestBody @Validated(Delete.class) SpAttribute attribute) {
        attribute.setStatus(0);
        attribute.setUpdateTime(new Date());
        return attributeService.updateById(attribute) ? CommonResponse.success(attribute) : CommonResponse.failed();
    }

    @GetMapping("/{id}")
    public CommonResponse<SpAttribute> getById(@PathVariable Integer id) {
        return CommonResponse.success(attributeService.getById(id));
    }
}
