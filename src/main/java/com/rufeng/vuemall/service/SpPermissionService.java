package com.rufeng.vuemall.service;

import com.rufeng.vuemall.domain.BO.PermissionWithChild;
import com.rufeng.vuemall.domain.SpPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpPermissionService extends IService<SpPermission> {

    List<PermissionWithChild> listPermissionWithChild();
}
