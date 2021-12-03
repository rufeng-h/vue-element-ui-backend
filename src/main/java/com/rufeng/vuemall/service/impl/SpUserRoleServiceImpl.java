package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUserRole;
import com.rufeng.vuemall.mapper.SpUserRoleMapper;
import com.rufeng.vuemall.service.SpUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpUserRoleServiceImpl extends ServiceImpl<SpUserRoleMapper, SpUserRole> implements SpUserRoleService {
    private final SpUserRoleMapper userRoleMapper;

    public SpUserRoleServiceImpl(SpUserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<SpRole> getRoleListById(Long userId) {
        List<SpRole> roleList = userRoleMapper.getRoleList(userId);
        Assert.notEmpty(roleList, "用户必须拥有一个角色!");
        return roleList;
    }
}
