package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.BO.CategoryTreeNode;
import com.rufeng.vuemall.domain.SpCategory;
import com.rufeng.vuemall.exception.InsertException;
import com.rufeng.vuemall.mapper.SpCategoryMapper;
import com.rufeng.vuemall.service.SpCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 * TODO 查询三次数据库，优化
 *
 * @author rufeng
 * @since 2021-11-28
 */
@Service
public class SpCategoryServiceImpl extends ServiceImpl<SpCategoryMapper, SpCategory> implements SpCategoryService {

    private final SpCategoryMapper categoryMapper;

    public SpCategoryServiceImpl(SpCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    private <T> RestPage<T> convert(IPage<T> iPage) {
        RestPage<T> page = new RestPage<>();
        BeanUtils.copyProperties(iPage, page);
        return page;
    }

    @Override
    public List<CategoryTreeNode> queryCateTreeNode() {
        return categoryMapper.queryCateTreeNode();
    }

    @Override
    public RestPage<CategoryTreeNode> queryCateTreeNode(IPage<CategoryTreeNode> page) {
        return convert(categoryMapper.queryCateTreeNode(page));
    }

    @Override
    public List<CategoryTreeNode> queryCateTreeNode(QueryWrapper<CategoryTreeNode> ew) {
        return categoryMapper.queryCateTreeNode(ew);
    }

    @Override
    public RestPage<CategoryTreeNode> queryCateTreeNode(QueryWrapper<CategoryTreeNode> ew, IPage<CategoryTreeNode> page) {
        return convert(categoryMapper.queryCateTreeNode(ew, page));
    }

    @Override
    public SpCategory addCategory(String name, Integer level, Integer pid) {
        SpCategory category = new SpCategory();
        category.setUpdateTime(new Date());
        category.setCatName(name);
        category.setCatLevel(level);
        category.setCatPid(pid);
        if (this.save(category)) {
            return getById(category);
        }
        throw new InsertException("新增分类失败", category);
    }
}
