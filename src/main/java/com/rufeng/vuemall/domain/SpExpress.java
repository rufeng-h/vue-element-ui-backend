package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 快递表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_express")
public class SpExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单快递公司名称
     */
    private String expressCom;

    /**
     * 快递单编号
     */
    private String expressNu;

    /**
     * 记录生成时间
     */
    private Integer createTime;

    /**
     * 记录修改时间
     */
    private Integer updateTime;

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getExpressCom() {
        return expressCom;
    }

    public void setExpressCom(String expressCom) {
        this.expressCom = expressCom;
    }
    public String getExpressNu() {
        return expressNu;
    }

    public void setExpressNu(String expressNu) {
        this.expressNu = expressNu;
    }
    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SpExpress{" +
            "expressId=" + expressId +
            ", orderId=" + orderId +
            ", expressCom=" + expressCom +
            ", expressNu=" + expressNu +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
