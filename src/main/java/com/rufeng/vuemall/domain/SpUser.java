package com.rufeng.vuemall.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rufeng.vuemall.enums.Gender;
import com.rufeng.vuemall.validator.annotation.EnumValue;
import com.rufeng.vuemall.validator.annotation.Phone;
import com.rufeng.vuemall.validator.annotation.UniqueInDbForSpUser;
import com.rufeng.vuemall.validator.group.Insert;
import com.rufeng.vuemall.validator.group.Update;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author rufeng
 */
@TableName("sp_user")
public class SpUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Long id;

    @Size(min = 3, max = 10, message = "用户名3到10个字符")
    @UniqueInDbForSpUser(message = "用户名已存在", tableColumn = "username", groups = Insert.class)
    private String username;

    @UniqueInDbForSpUser(tableColumn = "qq", message = "QQ已存在", groups = Insert.class)
    private String qq;

    @JsonIgnore
    private String password;

    @EnumValue(targetEnum = Gender.class, message = "请选择正确的性别")
    private Gender gender;

    @Phone
    @UniqueInDbForSpUser(message = "手机号已存在", tableColumn = "mobile", groups = Insert.class)
    private String mobile;

    @Email(message = "邮箱格式错误")
    @UniqueInDbForSpUser(message = "邮箱已存在", tableColumn = "email", groups = Insert.class)
    private String email;
    private Date createTime;
    private Date lastLoginTime;
    private Integer status;
    @Min(18)
    @Max(100)
    private Integer age;

    private String introduction;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpUser.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("qq='" + qq + "'")
                .add("gender='" + gender + "'")
                .add("mobile='" + mobile + "'")
                .add("introduction='" + introduction + "'")
                .add("email='" + email + "'")
                .add("createTime=" + createTime)
                .add("lastLoginTime=" + lastLoginTime)
                .add("status=" + status)
                .add("age=" + age)
                .toString();
    }


}
