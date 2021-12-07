package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rufeng.vuemall.enums.AttributeSel;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpCategory;
import com.rufeng.vuemall.validator.group.Delete;
import com.rufeng.vuemall.validator.group.Insert;
import com.rufeng.vuemall.validator.group.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 属性表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_attribute")
public class SpAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    @NotNull(groups = Delete.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名称
     */
    @NotEmpty(message = "属性名不能为空")
    private String name;

    /**
     * 外键，类型id
     */
    @ExistInDbForSpCategory
    private Integer categoryId;

    /**
     * only:输入框(唯一)  many:后台下拉列表/前台单选框
     */
    @NotNull
    private AttributeSel attrSel;

    /**
     * manual:手工录入  list:从列表选择
     */
    private String attrWrite;

    /**
     * 可选值列表信息,例如颜色：白色,红色,绿色,多个可选值通过逗号分隔
     */
    private String attrVals;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public AttributeSel getAttrSel() {
        return attrSel;
    }

    public void setAttrSel(AttributeSel attrSel) {
        this.attrSel = attrSel;
    }

    public String getAttrWrite() {
        return attrWrite;
    }

    public void setAttrWrite(String attrWrite) {
        this.attrWrite = attrWrite;
    }

    public String getAttrVals() {
        return attrVals;
    }

    public void setAttrVals(String attrVals) {
        this.attrVals = attrVals;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SpAttribute{" +
                "id=" + id +
                ", name=" + name +
                ", categoryId=" + categoryId +
                ", attrSel=" + attrSel +
                ", attrWrite=" + attrWrite +
                "}";
    }
}
