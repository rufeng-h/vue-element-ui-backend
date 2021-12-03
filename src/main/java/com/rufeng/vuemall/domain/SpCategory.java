package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_category")
public class SpCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类唯一ID
     */
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 分类父ID
     */
    private Integer catPid;

    /**
     * 分类层级 0: 顶级 1:二级 2:三级
     */
    private Integer catLevel;

    /**
     * 是否删除 1为删除
     */
    private Integer catDeleted;

    private String catIcon;

    private String catSrc;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    public Integer getCatPid() {
        return catPid;
    }

    public void setCatPid(Integer catPid) {
        this.catPid = catPid;
    }
    public Integer getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(Integer catLevel) {
        this.catLevel = catLevel;
    }
    public Integer getCatDeleted() {
        return catDeleted;
    }

    public void setCatDeleted(Integer catDeleted) {
        this.catDeleted = catDeleted;
    }
    public String getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon;
    }
    public String getCatSrc() {
        return catSrc;
    }

    public void setCatSrc(String catSrc) {
        this.catSrc = catSrc;
    }

    @Override
    public String toString() {
        return "SpCategory{" +
            "catId=" + catId +
            ", catName=" + catName +
            ", catPid=" + catPid +
            ", catLevel=" + catLevel +
            ", catDeleted=" + catDeleted +
            ", catIcon=" + catIcon +
            ", catSrc=" + catSrc +
        "}";
    }
}
