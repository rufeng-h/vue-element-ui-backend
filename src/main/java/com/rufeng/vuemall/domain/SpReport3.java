package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_report_3")
public class SpReport3 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户来源
     */
    private String rp3Src;

    /**
     * 数量
     */
    private Integer rp3Count;

    private LocalDateTime rp3Date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRp3Src() {
        return rp3Src;
    }

    public void setRp3Src(String rp3Src) {
        this.rp3Src = rp3Src;
    }
    public Integer getRp3Count() {
        return rp3Count;
    }

    public void setRp3Count(Integer rp3Count) {
        this.rp3Count = rp3Count;
    }
    public LocalDateTime getRp3Date() {
        return rp3Date;
    }

    public void setRp3Date(LocalDateTime rp3Date) {
        this.rp3Date = rp3Date;
    }

    @Override
    public String toString() {
        return "SpReport3{" +
            "id=" + id +
            ", rp3Src=" + rp3Src +
            ", rp3Count=" + rp3Count +
            ", rp3Date=" + rp3Date +
        "}";
    }
}
