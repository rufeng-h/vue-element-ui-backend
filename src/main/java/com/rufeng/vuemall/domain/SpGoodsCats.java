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
@TableName("sp_goods_cats")
public class SpGoodsCats implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 分类排序
     */
    private Integer catSort;

    /**
     * 数据标记
     */
    private Integer dataFlag;

    /**
     * 创建时间
     */
    private Integer createTime;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
    public Integer getCatSort() {
        return catSort;
    }

    public void setCatSort(Integer catSort) {
        this.catSort = catSort;
    }
    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }
    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SpGoodsCats{" +
            "catId=" + catId +
            ", parentId=" + parentId +
            ", catName=" + catName +
            ", isShow=" + isShow +
            ", catSort=" + catSort +
            ", dataFlag=" + dataFlag +
            ", createTime=" + createTime +
        "}";
    }
}
