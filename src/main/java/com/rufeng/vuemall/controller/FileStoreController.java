package com.rufeng.vuemall.controller;

import com.rufeng.vuemall.common.CommonResponse;
import com.rufeng.vuemall.service.FileStoreService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.Collections;

/**
 * @author 黄纯峰
 * @time 2021-12-09 16:37
 * @package com.rufeng.vuemall.controller
 * @description TODO
 */
@RestController
@RequestMapping("/api/file")
@Validated
public class FileStoreController {
    private final FileStoreService fileStoreService;

    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping("/upload")
    public CommonResponse<Object> handleFileUpload(@RequestParam("file") MultipartFile file) {
        URI uri = fileStoreService.store(file);
        return CommonResponse.success(Collections.singletonMap("uri", uri));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public CommonResponse<Void> remove(@RequestParam @NotEmpty String uri) {
        return fileStoreService.remove(uri) ? CommonResponse.success() : CommonResponse.failed();
    }
}
