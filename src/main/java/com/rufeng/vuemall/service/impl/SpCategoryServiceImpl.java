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
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
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

    @Override
    public IPage<CategoryWithChild> pageTree(Integer pageNum, Integer pageSize) {
        /* 1级 */
        IPage<SpCategory> page = Page.of(pageNum, pageSize);
        QueryWrapper<SpCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_pid", 0);
        IPage<SpCategory> l1 = page(page, wrapper);
        if (l1.getRecords().size() != 0) {
            /*2级*/
            List<Integer> p1 = l1.getRecords().stream().map(SpCategory::getCatId).collect(Collectors.toList());
            QueryWrapper<SpCategory> wrapper1 = new QueryWrapper<>();
            wrapper1.in("cat_pid", p1);
            List<SpCategory> l2 = list(wrapper1);
            l1.getRecords().addAll(l2);

            if (l2.size() != 0) {
                List<Integer> p2 = l2.stream().map(SpCategory::getCatId).collect(Collectors.toList());
                /*3级*/
                QueryWrapper<SpCategory> wrapper2 = new QueryWrapper<>();
                wrapper2.in("cat_pid", p2);
                List<SpCategory> l3 = list(wrapper2);
                l1.getRecords().addAll(l3);
            }
        }
        return l1.convert(c -> {
            CategoryWithChild withChild = new CategoryWithChild();
            BeanUtils.copyProperties(c, withChild);
            return withChild;
        });
    }

    @Override
    public List<CategoryWithChild> treeLevel2() {
        QueryWrapper<SpCategory> wrapper = new QueryWrapper<>();
        wrapper.le("cat_level", 1);
        List<SpCategory> list = list(wrapper);
        return list.stream().map(c -> {
            CategoryWithChild withChild = new CategoryWithChild();
            BeanUtils.copyProperties(c, withChild);
            return withChild;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean addCategory(String name, @Nullable Integer level, @Nullable Integer pid) {
        SpCategory category = new SpCategory();
        category.setUpdateTime(new Date());
        category.setCatName(name);
        if (pid == null) {
            category.setCatLevel(0);
            category.setCatPid(0);
        } else if (level == null) {
            throw new ValidationException("参数错误");
        } else {
            if (getById(pid) == null) {
                throw new ValidationException("不存在的父分类");
            }
            category.setCatLevel(level);
            category.setCatPid(pid);
        }
        return this.saveOrUpdate(category);
    }
}
