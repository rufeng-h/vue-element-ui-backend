package com.rufeng.vuemall.domain.AO;

import com.rufeng.vuemall.domain.SpUser;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpRoleCollection;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-02 20:06
 * @package com.rufeng.vuemall.domain.AO
 * @description TODO
 */
public class UserAddParam extends SpUser {
    @Size(min = 1)
    @ExistInDbForSpRoleCollection(message = "参数错误")
    private List<Integer> roles;

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}
