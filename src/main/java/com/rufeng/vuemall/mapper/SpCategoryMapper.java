package com.rufeng.vuemall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.rufeng.vuemall.domain.BO.CategoryTreeNode;
import com.rufeng.vuemall.domain.SpCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpCategoryMapper extends BaseMapper<SpCategory> {


    List<CategoryTreeNode> queryCateTreeNode();

    IPage<CategoryTreeNode> queryCateTreeNode(IPage<CategoryTreeNode> page);

    /**
     * @param ew querywrapper
     * @return list
     */
    List<CategoryTreeNode> queryCateTreeNode(@Param(Constants.WRAPPER) QueryWrapper<CategoryTreeNode> ew);

    IPage<CategoryTreeNode> queryCateTreeNode(@Param(Constants.WRAPPER) QueryWrapper<CategoryTreeNode> ew, IPage<CategoryTreeNode> page);
}
