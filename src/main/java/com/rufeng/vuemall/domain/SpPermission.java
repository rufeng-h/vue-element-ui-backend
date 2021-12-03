package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>
 *
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@TableName("sp_permission")
public class SpPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private String name;
    private String value;

    @Override
    public String toString() {
        return new StringJoiner(", ", SpPermission.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("roleId=" + roleId)
                .add("name='" + name + "'")
                .add("value='" + value + "'")
                .toString();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
