package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.BO.PermissionWithChild;
import com.rufeng.vuemall.domain.SpPermission;
import com.rufeng.vuemall.mapper.SpPermissionMapper;
import com.rufeng.vuemall.service.SpPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpPermissionServiceImpl extends ServiceImpl<SpPermissionMapper, SpPermission> implements SpPermissionService {

    private static final Function<SpPermission, PermissionWithChild> TRANSFER = p -> {
        PermissionWithChild withChild = new PermissionWithChild();
        BeanUtils.copyProperties(p, withChild);
        return withChild;
    };

    @Override
    public List<PermissionWithChild> listPermissionWithChild() {
        return this.list().stream().map(TRANSFER).collect(Collectors.toList());
    }
}
