package com.rufeng.vuemall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.vuemall.domain.SpAttribute;

import java.util.List;

/**
 * <p>
 * 属性表 服务类
 * </p>
 *
 * @author rufeng
 * @since 2021-11-28
 */
public interface SpAttributeService extends IService<SpAttribute> {
    /**
     * 通过cateid查询
     *
     * @param categoryId cateid
     * @return list
     */
    List<SpAttribute> listByCateIdAndSel(Integer categoryId, String attrSel);
}
