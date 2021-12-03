package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_order")
public class SpOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 下订单会员id
     */
    private Integer userId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单总金额
     */
    private BigDecimal orderPrice;

    /**
     * 支付方式  0未支付 1支付宝  2微信  3银行卡
     */
    private String orderPay;

    /**
     * 订单是否已经发货
     */
    private String isSend;

    /**
     * 支付宝交易流水号码
     */
    private String tradeNo;

    /**
     * 发票抬头 个人 公司
     */
    private String orderFapiaoTitle;

    /**
     * 公司名称
     */
    private String orderFapiaoCompany;

    /**
     * 发票内容
     */
    private String orderFapiaoContent;

    /**
     * consignee收货人地址
     */
    private String consigneeAddr;

    /**
     * 订单状态： 0未付款、1已付款
     */
    private String payStatus;

    /**
     * 记录生成时间
     */
    private Integer createTime;

    /**
     * 记录修改时间
     */
    private Integer updateTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay;
    }
    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
    public String getOrderFapiaoTitle() {
        return orderFapiaoTitle;
    }

    public void setOrderFapiaoTitle(String orderFapiaoTitle) {
        this.orderFapiaoTitle = orderFapiaoTitle;
    }
    public String getOrderFapiaoCompany() {
        return orderFapiaoCompany;
    }

    public void setOrderFapiaoCompany(String orderFapiaoCompany) {
        this.orderFapiaoCompany = orderFapiaoCompany;
    }
    public String getOrderFapiaoContent() {
        return orderFapiaoContent;
    }

    public void setOrderFapiaoContent(String orderFapiaoContent) {
        this.orderFapiaoContent = orderFapiaoContent;
    }
    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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
        return "SpOrder{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", orderNumber=" + orderNumber +
            ", orderPrice=" + orderPrice +
            ", orderPay=" + orderPay +
            ", isSend=" + isSend +
            ", tradeNo=" + tradeNo +
            ", orderFapiaoTitle=" + orderFapiaoTitle +
            ", orderFapiaoCompany=" + orderFapiaoCompany +
            ", orderFapiaoContent=" + orderFapiaoContent +
            ", consigneeAddr=" + consigneeAddr +
            ", payStatus=" + payStatus +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
