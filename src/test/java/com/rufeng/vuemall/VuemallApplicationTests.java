package com.rufeng.vuemall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUser;
import com.rufeng.vuemall.enums.Gender;
import com.rufeng.vuemall.mapper.SpRolePermissionMapper;
import com.rufeng.vuemall.mapper.SpUserMapper;
import com.rufeng.vuemall.mapper.SpUserRoleMapper;
import com.rufeng.vuemall.service.SpRoleService;
import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootTest
class VuemallApplicationTests {

    @Autowired
    private SpRoleService roleService;
    @Autowired
    private SpUserMapper userMapper;

    @Autowired
    private SpUserRoleMapper userRoleMapper;

    @Autowired
    private SpRolePermissionMapper rolePermissionMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testPasswordEncoder() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    public void testUserRoleMapper() {
        System.out.println(userRoleMapper.getRoleList(2L));
    }

    @Test
    public void testRolePermissionMapper() {
        SpRole role = new SpRole();
        role.setId(2);
        SpRole role1 = new SpRole();
        role1.setId(4);
        ArrayList<SpRole> list = new ArrayList<>();
        list.add(role);
        list.add(role1);
        System.out.println(rolePermissionMapper.getPermissionList(list));
    }

    @Test
    public void test() {
        Class<?> aClass = Gender.class;

    }


    @Test
    public void testSearchUser() {
        String query = "rufeng";
        QueryWrapper<SpUser> wrapper = new QueryWrapper<>();
        wrapper.like("username", query).or()
                .like("qq", query).or()
                .like("introduction", query).or()
                .like("email", query).or()
                .eq("id", query).or()
                .eq("gender", query);

        System.out.println(userMapper.queryPage(Page.of(1, 2), wrapper));
    }
}
