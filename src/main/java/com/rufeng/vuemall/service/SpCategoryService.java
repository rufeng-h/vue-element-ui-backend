package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.BO.CategoryWithChild;
import com.rufeng.vuemall.domain.SpCategory;
import org.springframework.lang.Nullable;

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
    /**
     * 按一级分类分页，二级三级分类全部查询
     *
     * @param pageNum  current
     * @param pageSize size
     * @return page
     */
    IPage<CategoryWithChild> pageTree(Integer pageNum, Integer pageSize);

    /**
     * 查询所有一级与二级分类
     *
     * @return list
     */
    List<CategoryWithChild> treeLevel2();

    /**
     * 所有分类
     * @return list
     */
    List<CategoryWithChild> treeAll();

    /**
     * 新增分类
     *
     * @param name  分类名
     * @param level 级别 可以为空
     * @param pid   父分类id 可以为空
     * @return succ
     */
    boolean addCategory(String name, @Nullable Integer level, @Nullable Integer pid);
}
