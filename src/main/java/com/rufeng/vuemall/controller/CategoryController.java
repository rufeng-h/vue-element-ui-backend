package com.rufeng.vuemall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.domain.BO.CategoryTreeNode;
import com.rufeng.vuemall.domain.SpCategory;
import com.rufeng.vuemall.service.SpCategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-12-05 19:48
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@RestController
@Validated
@RequestMapping("/api/category")
public class CategoryController {

    private final SpCategoryService categoryService;

    public CategoryController(SpCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public CommonResponse<SpCategory> add(@RequestParam @Size(min = 3, max = 10) String name,
                                          @RequestParam(required = false, defaultValue = "0")
                                          @Min(0) @Max(2) Integer level,
                                          @RequestParam(required = false, defaultValue = "0") Integer pid) {
        if (pid != 0) {
            SpCategory category = categoryService.getById(pid);
            if (category == null || category.getCatLevel() != level - 1) {
                throw new ValidationException("参数错误!");
            }
        }
        SpCategory category = categoryService.addCategory(name, level, pid);
        return CommonResponse.success(category);
    }

    @PostMapping("/delete")
    public CommonResponse<Void> delete(@RequestBody @NotEmpty List<Integer> categoryIds) {
        boolean b = categoryService.removeByIds(categoryIds);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }


    /**
     * 按父级id获取分类
     * 当分页参数指定时，返回分页数据
     * 当分页参数不指定时，返回所有数据的list
     *
     * @param pid      父级id
     * @param pageSize 每页条数
     * @param pageNum  页码
     * @return list or page data
     */
    @GetMapping("/list")
    public CommonResponse<Object> list(@RequestParam Integer pid,
                                       @RequestParam(required = false) Integer pageSize,
                                       @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null ^ pageSize == null) {
            throw new ValidationException("参数错误");
        }
        QueryWrapper<CategoryTreeNode> wrapper = new QueryWrapper<CategoryTreeNode>().eq("cat_pid", pid);
        /* 不分页 */
        if (pageNum == null) {
            return CommonResponse.success(categoryService.queryCateTreeNode(wrapper));
        }
        /* 分页 */
        IPage<CategoryTreeNode> page = Page.of(pageNum, pageSize);
        return CommonResponse.success(categoryService.queryCateTreeNode(wrapper, page));
    }
}
