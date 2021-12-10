package com.rufeng.vuemall.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author 黄纯峰
 * @time 2021-11-30 13:54
 * @package com.rufeng.vuemall.common
 * @description TODO
 */
public class RestPage<T> {
    private Long current;
    private Long total;
    private Long size = 10L;
    private Long pages;
    private List<T> records;

    public static <T> RestPage<T> convert(IPage<T> iPage) {
        RestPage<T> restPage = new RestPage<>();
        BeanUtils.copyProperties(iPage, restPage);
        return restPage;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
