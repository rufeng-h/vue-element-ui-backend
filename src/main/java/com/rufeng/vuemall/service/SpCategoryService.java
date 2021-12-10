package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.BO.CategoryTreeNode;
import com.rufeng.vuemall.domain.SpCategory;
import com.rufeng.vuemall.exception.InsertException;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpCategoryService extends IService<SpCategory> {

    List<CategoryTreeNode> queryCateTreeNode();

    RestPage<CategoryTreeNode> queryCateTreeNode(IPage<CategoryTreeNode> page);

    /**
     * @param ew querywrapper
     * @return list
     */
    List<CategoryTreeNode> queryCateTreeNode(QueryWrapper<CategoryTreeNode> ew);

    RestPage<CategoryTreeNode> queryCateTreeNode(QueryWrapper<CategoryTreeNode> ew, IPage<CategoryTreeNode> page);

    /**
     * 新增分类
     *
     * @param name  分类名
     * @param level 级别 可以为空
     * @param pid   父分类id 可以为空
     * @return succ
     * @throws InsertException 插入异常
     */
    SpCategory addCategory(String name, Integer level, Integer pid) throws InsertException;
}
