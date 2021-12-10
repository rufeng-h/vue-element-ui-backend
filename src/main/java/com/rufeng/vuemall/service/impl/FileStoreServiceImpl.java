package com.rufeng.vuemall.service.impl;

import com.rufeng.vuemall.exception.FileStoreException;
import com.rufeng.vuemall.service.FileStoreService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * @author 黄纯峰
 * @time 2021-12-09 21:24
 * @package com.rufeng.vuemall.service.impl
 * @description TODO
 */
@Service
public class FileStoreServiceImpl implements FileStoreService {
    private static final Path UPLOAD_PATH;
    private static final Path ROOT_PATH = Paths.get("E:/");

    static {
        try {
            UPLOAD_PATH = ROOT_PATH.resolve(Paths.get("upload"));
            if (!Files.exists(UPLOAD_PATH)) {
                Files.createDirectory(UPLOAD_PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException("upload文件夹创建失败");
        }
    }

    @Override
    public URI store(MultipartFile file) {
        Assert.notNull(file.getOriginalFilename(), "文件名为空");
        String fileName = generateFileName(file.getOriginalFilename());
        Path filepath;
        Path dir = UPLOAD_PATH.resolve(LocalDate.now().toString());
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            filepath = dir.resolve(fileName);
            file.transferTo(filepath);
        } catch (IOException e) {
            throw new FileStoreException("文件上传失败", e);
        }
        return ROOT_PATH.toUri().relativize(filepath.toUri());
    }

    @Override
    public boolean remove(String uri) {
        Path path = ROOT_PATH.resolve(Paths.get(uri));
        System.out.println(path);
        try {
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new FileStoreException("删除失败", e);
        }
    }

    private String generateFileName(String originalName) {
        int idx = originalName.lastIndexOf('.');
        Assert.isTrue(idx != -1, "无法解析的后缀名");
        String ext = originalName.substring(idx + 1);
        return DigestUtils.md5DigestAsHex(
                (originalName.substring(0, idx) +
                        System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)) + "." + ext;
    }
}
