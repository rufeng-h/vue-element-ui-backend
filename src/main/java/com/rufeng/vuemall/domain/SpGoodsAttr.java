package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品-属性关联表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_goods_attr")
public class SpGoodsAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 属性id
     */
    private Integer attrId;

    /**
     * 商品对应属性的值
     */
    private String attrValue;

    /**
     * 该属性需要额外增加的价钱
     */
    private BigDecimal addPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }
    public BigDecimal getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(BigDecimal addPrice) {
        this.addPrice = addPrice;
    }

    @Override
    public String toString() {
        return "SpGoodsAttr{" +
            "id=" + id +
            ", goodsId=" + goodsId +
            ", attrId=" + attrId +
            ", attrValue=" + attrValue +
            ", addPrice=" + addPrice +
        "}";
    }
}
