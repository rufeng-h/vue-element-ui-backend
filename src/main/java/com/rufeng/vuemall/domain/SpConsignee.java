package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 收货人表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_consignee")
public class SpConsignee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "cgn_id", type = IdType.AUTO)
    private Integer cgnId;

    /**
     * 会员id
     */
    private Integer userId;

    /**
     * 收货人名称
     */
    private String cgnName;

    /**
     * 收货人地址
     */
    private String cgnAddress;

    /**
     * 收货人电话
     */
    private String cgnTel;

    /**
     * 邮编
     */
    private String cgnCode;

    /**
     * 删除时间
     */
    private Integer deleteTime;

    public Integer getCgnId() {
        return cgnId;
    }

    public void setCgnId(Integer cgnId) {
        this.cgnId = cgnId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getCgnName() {
        return cgnName;
    }

    public void setCgnName(String cgnName) {
        this.cgnName = cgnName;
    }
    public String getCgnAddress() {
        return cgnAddress;
    }

    public void setCgnAddress(String cgnAddress) {
        this.cgnAddress = cgnAddress;
    }
    public String getCgnTel() {
        return cgnTel;
    }

    public void setCgnTel(String cgnTel) {
        this.cgnTel = cgnTel;
    }
    public String getCgnCode() {
        return cgnCode;
    }

    public void setCgnCode(String cgnCode) {
        this.cgnCode = cgnCode;
    }
    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "SpConsignee{" +
            "cgnId=" + cgnId +
            ", userId=" + userId +
            ", cgnName=" + cgnName +
            ", cgnAddress=" + cgnAddress +
            ", cgnTel=" + cgnTel +
            ", cgnCode=" + cgnCode +
            ", deleteTime=" + deleteTime +
        "}";
    }
}
