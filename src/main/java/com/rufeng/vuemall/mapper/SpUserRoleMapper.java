package com.rufeng.vuemall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.vuemall.domain.SpRole;
import com.rufeng.vuemall.domain.SpUserRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpUserRoleMapper extends BaseMapper<SpUserRole> {
    /**
     * 获取用户角色列表
     *
     * @param userId userid
     * @return 永远返回有长度的列表
     */
    List<SpRole> getRoleList(Long userId);
}
