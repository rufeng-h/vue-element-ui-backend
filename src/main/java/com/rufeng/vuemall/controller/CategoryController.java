package com.rufeng.vuemall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.common.RestPage;
import com.rufeng.vuemall.domain.BO.CategoryWithChild;
import com.rufeng.vuemall.service.SpCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private List<CategoryWithChild> treefy(List<CategoryWithChild> records) {
        HashMap<Integer, CategoryWithChild> map = new HashMap<>(records.size());
        records.forEach(s -> map.put(s.getCatId(), s));
        records.forEach(s -> {
            if (s.getCatPid() != 0) {
                map.get(s.getCatPid()).append(s);
            }
        });
        return records.stream().filter(s -> s.getCatPid() == 0).collect(Collectors.toList());
    }

    @GetMapping("/tree")
    public CommonResponse<RestPage<CategoryWithChild>> tree(
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        IPage<CategoryWithChild> page = categoryService.pageTree(pageNum, pageSize);
        List<CategoryWithChild> records = page.getRecords();
        page.setRecords(this.treefy(records));
        RestPage<CategoryWithChild> restPage = new RestPage<>();
        BeanUtils.copyProperties(page, restPage);
        return CommonResponse.success(restPage);
    }


    @GetMapping("/treeLevel2")
    public CommonResponse<List<CategoryWithChild>> treeLevel2() {
        List<CategoryWithChild> records = categoryService.treeLevel2();
        return CommonResponse.success(treefy(records));
    }

    @PostMapping("/add")
    public CommonResponse<Void> add(@RequestParam String name,
                                    @RequestParam(required = false) @Min(0) @Max(2) Integer level,
                                    @RequestParam(required = false) Integer pid) {
        boolean b = categoryService.addCategory(name, level, pid);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }

    @PostMapping("/delete")
    public CommonResponse<Void> delete(@RequestBody @NotEmpty List<Integer> categoryIds) {
        boolean b = categoryService.removeByIds(categoryIds);
        return b ? CommonResponse.success() : CommonResponse.failed();
    }
}
