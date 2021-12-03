package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_report_2")
public class SpReport2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String rp2Page;

    private Integer rp2Count;

    private LocalDate rp2Date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRp2Page() {
        return rp2Page;
    }

    public void setRp2Page(String rp2Page) {
        this.rp2Page = rp2Page;
    }
    public Integer getRp2Count() {
        return rp2Count;
    }

    public void setRp2Count(Integer rp2Count) {
        this.rp2Count = rp2Count;
    }
    public LocalDate getRp2Date() {
        return rp2Date;
    }

    public void setRp2Date(LocalDate rp2Date) {
        this.rp2Date = rp2Date;
    }

    @Override
    public String toString() {
        return "SpReport2{" +
            "id=" + id +
            ", rp2Page=" + rp2Page +
            ", rp2Count=" + rp2Count +
            ", rp2Date=" + rp2Date +
        "}";
    }
}
