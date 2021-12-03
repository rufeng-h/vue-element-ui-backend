package com.rufeng.vuemall.domain.AO;

import com.rufeng.vuemall.domain.BO.UserInfoImpl;
import com.rufeng.vuemall.service.impl.SpRoleServiceImpl;
import com.rufeng.vuemall.validator.annotation.ExistInDbForSpRole;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-02 20:06
 * @package com.rufeng.vuemall.domain.AO
 * @description TODO
 */
public class UserAddParam extends UserInfoImpl {
    @Size(min = 1)
    @ExistInDbForSpRole(message = "参数错误", tableColumn = "id", service = SpRoleServiceImpl.class)
    private List<Integer> roles;

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}
