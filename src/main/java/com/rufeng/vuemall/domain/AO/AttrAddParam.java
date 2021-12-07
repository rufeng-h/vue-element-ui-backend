package com.rufeng.vuemall.domain.AO;

import com.rufeng.vuemall.enums.AttributeSel;
import com.rufeng.vuemall.service.SpCategoryService;
import com.rufeng.vuemall.validator.annotation.EnumValue;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpCategory;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author 黄纯峰
 * @time 2021-12-06 16:13
 * @package com.rufeng.vuemall.domain.AO
 * @description 为分类添加属性
 */
public class AttrAddParam {
    @Min(1)
    @ExistInDbForSpCategory(message = "该分类不存在")
    private Integer attrId;

    @Size(min = 3, max = 10, message = "3到10个字符")
    private String attrName;

    @EnumValue(targetEnum = AttributeSel.class)
    private String attrSel;

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrSel() {
        return attrSel;
    }

    public void setAttrSel(String attrSel) {
        this.attrSel = attrSel;
    }
}
