package com.rufeng.vuemall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.vuemall.domain.BO.CategoryWithChild;
import com.rufeng.vuemall.domain.SpCategory;
import com.rufeng.vuemall.mapper.SpCategoryMapper;
import com.rufeng.vuemall.service.SpCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private static final Function<SpCategory, CategoryWithChild> CONVERT = c -> {
        CategoryWithChild child = new CategoryWithChild();
        BeanUtils.copyProperties(c, child);
        return child;
    };

    /**
     * 获取直接子分类
     *
     * @param pids 父分类id
     * @return list
     */
    @NonNull
    private List<SpCategory> getChildren(List<Integer> pids) {
        if (pids.size() == 0) {
            return new ArrayList<>();
        }
        QueryWrapper<SpCategory> wrapper1 = new QueryWrapper<>();
        wrapper1.in("cat_pid", pids);
        return list(wrapper1);
    }

    @Override
    public IPage<CategoryWithChild> pageTree(Integer pageNum, Integer pageSize) {
        /* 1级 */
        IPage<SpCategory> page = Page.of(pageNum, pageSize);
        QueryWrapper<SpCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_pid", 0);
        IPage<SpCategory> l1 = page(page, wrapper);
        /*2级*/
        List<Integer> p1 = l1.getRecords().stream().map(SpCategory::getCatId).collect(Collectors.toList());
        List<SpCategory> l2 = getChildren(p1);
        l1.getRecords().addAll(l2);
        List<Integer> p2 = l2.stream().map(SpCategory::getCatId).collect(Collectors.toList());
        /*3级*/
        List<SpCategory> l3 = getChildren(p2);
        l1.getRecords().addAll(l3);
        return l1.convert(CONVERT);
    }

    @Override
    public List<CategoryWithChild> treeLevel2() {
        QueryWrapper<SpCategory> wrapper = new QueryWrapper<>();
        wrapper.le("cat_level", 1);
        List<SpCategory> list = list(wrapper);
        return list.stream().map(CONVERT).collect(Collectors.toList());
    }

    @Override
    public List<CategoryWithChild> treeAll() {
        return list().stream().map(CONVERT).collect(Collectors.toList());
    }

    @Override
    public boolean addCategory(String name, @Nullable Integer level, @Nullable Integer pid) {
        SpCategory category = new SpCategory();
        category.setUpdateTime(new Date());
        category.setCatName(name);
        if (pid == null) {
            category.setCatLevel(0);
            category.setCatPid(0);
        } else {
            category.setCatLevel(level);
            category.setCatPid(pid);
        }
        return this.saveOrUpdate(category);
    }
}
