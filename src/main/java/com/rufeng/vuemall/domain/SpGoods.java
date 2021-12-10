package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_goods")
public class SpGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品数量
     */
    private Integer goodsNumber;

    /**
     * 商品重量
     */
    private Integer goodsWeight;

    /**
     * 类型id
     */
    private Integer catId;

    /**
     * 商品详情介绍
     */
    private String goodsIntroduce;

    /**
     * 图片logo大图
     */
    private String goodsBigLogo;

    /**
     * 图片logo小图
     */
    private String goodsSmallLogo;

    /**
     * 0:正常  1:删除
     */
    private String isDel;

    /**
     * 添加商品时间
     */
    private Date createTime;

    /**
     * 修改商品时间
     */
    private Date updateTime;

    /**
     * 一级分类id
     */
    private Integer catOneId;

    /**
     * 二级分类id
     */
    private Integer catTwoId;

    /**
     * 三级分类id
     */
    private Integer catThreeId;

    /**
     * 热卖数量
     */
    private Integer hotMumber;

    /**
     * 是否促销
     */
    private Integer isPromote;

    /**
     * 商品状态 0: 未通过 1: 审核中 2: 已审核
     */
    private Integer goodsState;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Integer goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getGoodsIntroduce() {
        return goodsIntroduce;
    }

    public void setGoodsIntroduce(String goodsIntroduce) {
        this.goodsIntroduce = goodsIntroduce;
    }

    public String getGoodsBigLogo() {
        return goodsBigLogo;
    }

    public void setGoodsBigLogo(String goodsBigLogo) {
        this.goodsBigLogo = goodsBigLogo;
    }

    public String getGoodsSmallLogo() {
        return goodsSmallLogo;
    }

    public void setGoodsSmallLogo(String goodsSmallLogo) {
        this.goodsSmallLogo = goodsSmallLogo;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }


    public Integer getCatOneId() {
        return catOneId;
    }

    public void setCatOneId(Integer catOneId) {
        this.catOneId = catOneId;
    }

    public Integer getCatTwoId() {
        return catTwoId;
    }

    public void setCatTwoId(Integer catTwoId) {
        this.catTwoId = catTwoId;
    }

    public Integer getCatThreeId() {
        return catThreeId;
    }

    public void setCatThreeId(Integer catThreeId) {
        this.catThreeId = catThreeId;
    }

    public Integer getHotMumber() {
        return hotMumber;
    }

    public void setHotMumber(Integer hotMumber) {
        this.hotMumber = hotMumber;
    }

    public Integer getIsPromote() {
        return isPromote;
    }

    public void setIsPromote(Integer isPromote) {
        this.isPromote = isPromote;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    @Override
    public String toString() {
        return "SpGoods{" +
                "goodsId=" + goodsId +
                ", goodsName=" + goodsName +
                ", goodsPrice=" + goodsPrice +
                ", goodsNumber=" + goodsNumber +
                ", goodsWeight=" + goodsWeight +
                ", catId=" + catId +
                ", goodsIntroduce=" + goodsIntroduce +
                ", goodsBigLogo=" + goodsBigLogo +
                ", goodsSmallLogo=" + goodsSmallLogo +
                ", isDel=" + isDel +
                ", catOneId=" + catOneId +
                ", catTwoId=" + catTwoId +
                ", catThreeId=" + catThreeId +
                ", hotMumber=" + hotMumber +
                ", isPromote=" + isPromote +
                ", goodsState=" + goodsState +
                "}";
    }
}
