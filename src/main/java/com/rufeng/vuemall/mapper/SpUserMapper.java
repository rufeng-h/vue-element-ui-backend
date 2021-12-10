package com.rufeng.vuemall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.rufeng.vuemall.domain.BO.UserInfoWithRole;
import com.rufeng.vuemall.domain.SpUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpUserMapper extends BaseMapper<SpUser> {
    /**
     * 分页搜索
     *
     * @param page    p
     * @param wrapper w
     * @return {@link IPage<SpUser>}
     * @author 黄纯峰
     * @date 2021/11/30 20:44
     */
    IPage<UserInfoWithRole> queryUserWithRole(IPage<SpUser> page, @Param(Constants.WRAPPER) QueryWrapper<SpUser> wrapper);


    UserInfoWithRole queryUserWithRole(@Param(Constants.WRAPPER) QueryWrapper<SpUser> wrapper);
}
