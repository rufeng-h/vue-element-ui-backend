package com.rufeng.vuemall.domain.BO;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-29 16:18
 * @Version: 1.0
 * @Description:
 */

import com.rufeng.vuemall.validator.annotation.*;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author rufeng
 * 用户非私密信息
 */
public class UserInfoImpl implements UserInfo {
    @NotNull(groups = Update.class)
    private Long id;
    @Size(min = 3, max = 10, message = "用户名3到10个字符")
    @Unique(message = "用户名已存在", tableColumn = "username", groups = Insert.class)
    private String username;
    @Unique(tableColumn = "qq", message = "QQ已存在", groups = Insert.class)
    private String qq;
    @EnumValue(value = {"男", "女"}, message = "请选择正确的性别")
    private String gender;
    @Phone
    @Unique(message = "手机号已存在", tableColumn = "mobile", groups = Insert.class)
    private String mobile;
    private String introduction;
    @Email(message = "邮箱格式错误")
    @Unique(message = "邮箱已存在", tableColumn = "email", groups = Insert.class)
    private String email;
    private Date createTime;
    private Date lastLoginTime;
    private Integer status;
    @Min(18)
    @Max(100)
    private Integer age;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
