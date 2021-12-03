package com.rufeng.vuemall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 黄纯峰
 * @time 2021-12-02 20:58
 * @package com.rufeng.vuemall.domain
 * @description TODO
 */
@TableName("sp_user_role")
public class SpUserRole {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;
    private Long userId;
    private Integer roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
