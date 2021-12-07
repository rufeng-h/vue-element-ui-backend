package com.rufeng.vuemall.controller;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-29 15:53
 * @Version: 1.0
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.AO.UserAddParam;
import com.rufeng.vuemall.domain.BO.UserInfoWithRole;
import com.rufeng.vuemall.domain.SpUser;
import com.rufeng.vuemall.service.SpUserService;
import com.rufeng.vuemall.validator.group.Insert;
import com.rufeng.vuemall.validator.group.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * @author rufeng
 */
@RestController
@Validated
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('admin', 'super_admin')")
public class UserController {
    private final SpUserService userService;

    public UserController(SpUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/list")
    public CommonResponse<RestPage<UserInfoWithRole>> userList(
            @RequestBody(required = false) SpUser user,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        IPage<SpUser> page = Page.of(pageNum, pageSize);
        IPage<UserInfoWithRole> userPage = userService.selectPage(page, new QueryWrapper<>(user));
        RestPage<UserInfoWithRole> restPage = new RestPage<>();
        BeanUtils.copyProperties(userPage, restPage);
        return CommonResponse.success(restPage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CommonResponse<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return CommonResponse.success("删除成功!");
    }

    /**
     * @param query
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResponse<RestPage<UserInfoWithRole>>}
     * @author 黄纯峰
     * @date 2021/12/1 19:15
     */
    @GetMapping("/search")
    public CommonResponse<RestPage<UserInfoWithRole>> searchUser(
            @RequestParam @Size(min = 1, message = "请输入查询关键字!") String query,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        IPage<SpUser> page = Page.of(pageNum, pageSize);
        IPage<UserInfoWithRole> userInfoPage = userService.searchPage(page, query);
        RestPage<UserInfoWithRole> restPage = new RestPage<>();
        BeanUtils.copyProperties(userInfoPage, restPage);
        return CommonResponse.success(restPage);
    }

    @PostMapping("/add")
    public CommonResponse<Void> addUser(
            @Validated(Insert.class) @RequestBody UserAddParam param) {
        SpUser user = new SpUser();
        BeanUtils.copyProperties(param, user);
        return userService.addUser(user, param.getRoles()) == 1 ? CommonResponse.success("添加成功") : CommonResponse.failed("未知错误");
    }


    /**
     * 校验username是否已在数据库中
     * 由于前端已对字段进行校验并且该操作不会入库，故此处不再校验，下同
     *
     * @return
     * @author 黄纯峰
     * @time 2021/12/1 23:16
     */
    @GetMapping("/check")
    public CommonResponse<Map<String, Boolean>> checkField(@RequestParam String column, @RequestParam String value) {
        Field field = ReflectionUtils.findField(SpUser.class, column);
        if (field == null) {
            throw new ValidationException("参数错误");
        }
        return CommonResponse.success(Collections.singletonMap("isValid", userService.checkUniqueColumn(column, value)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResponse<Void> updateUser(@Validated(Update.class) @RequestBody UserAddParam param) {
        SpUser user = new SpUser();
        BeanUtils.copyProperties(param, user);
        return userService.updateUser(user, param.getRoles()) == 1 ? CommonResponse.success() : CommonResponse.failed("未知错误");
    }
}
