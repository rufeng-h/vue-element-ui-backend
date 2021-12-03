package com.rufeng.vuemall.domain.BO;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-29 16:56
 * @Version: 1.0
 * @Description:
 */

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

/**
 * @author rufeng
 */
public interface UserInfo extends Serializable, Principal {
    Long getId();

    String getUsername();

    String getEmail();

    String getMobile();

    String getQq();

    String getGender();

    Date getLastLoginTime();

    Date getCreateTime();

    String getIntroduction();

    Integer getStatus();

    Integer getAge();
}
