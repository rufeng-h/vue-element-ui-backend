package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_role")
public class SpRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String desp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpRole.class.getSimpleName() + "[", "]")
                .add("roleId=" + id)
                .add("name='" + name + "'")
                .add("desp='" + desp + "'")
                .toString();
    }
}
