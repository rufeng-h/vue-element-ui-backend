package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 外键，类型id
     */
    private Integer categoryId;

    /**
     * only:输入框(唯一)  many:后台下拉列表/前台单选框
     */
    private String attrSel;

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
    /**
     * 删除时间标志
     */
    private Integer deleteTime;

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

    public String getAttrSel() {
        return attrSel;
    }

    public void setAttrSel(String attrSel) {
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

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "SpAttribute{" +
                "id=" + id +
                ", name=" + name +
                ", categoryId=" + categoryId +
                ", attrSel=" + attrSel +
                ", attrWrite=" + attrWrite +
                ", attrVals=" + attrVals +
                ", deleteTime=" + deleteTime +
                "}";
    }
}
