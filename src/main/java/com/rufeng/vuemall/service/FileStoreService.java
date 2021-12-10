package com.rufeng.vuemall.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * @author 黄纯峰
 * @time 2021-12-09 21:20
 * @package com.rufeng.vuemall.service
 * @description TODO
 */
public interface FileStoreService {
    /**
     * 存储文件
     *
     * @param file file
     */
    URI store(MultipartFile file);

    boolean remove(String uri);
}
