package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品-相册关联表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_goods_pics")
public class SpGoodsPics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "pics_id", type = IdType.AUTO)
    private Integer picsId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 相册大图800*800
     */
    private String picsBig;

    /**
     * 相册中图350*350
     */
    private String picsMid;

    /**
     * 相册小图50*50
     */
    private String picsSma;

    public Integer getPicsId() {
        return picsId;
    }

    public void setPicsId(Integer picsId) {
        this.picsId = picsId;
    }
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public String getPicsBig() {
        return picsBig;
    }

    public void setPicsBig(String picsBig) {
        this.picsBig = picsBig;
    }
    public String getPicsMid() {
        return picsMid;
    }

    public void setPicsMid(String picsMid) {
        this.picsMid = picsMid;
    }
    public String getPicsSma() {
        return picsSma;
    }

    public void setPicsSma(String picsSma) {
        this.picsSma = picsSma;
    }

    @Override
    public String toString() {
        return "SpGoodsPics{" +
            "picsId=" + picsId +
            ", goodsId=" + goodsId +
            ", picsBig=" + picsBig +
            ", picsMid=" + picsMid +
            ", picsSma=" + picsSma +
        "}";
    }
}
