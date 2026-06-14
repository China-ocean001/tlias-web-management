package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    // 允许上传的文件类型（仅图片格式）
    private static final Set<String> ALLOWED_CONTENT_TYPES = new HashSet<>(Arrays.asList(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/bmp"
    ));

    // 允许的文件扩展名
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList(
            ".jpg", ".jpeg", ".png", ".gif", ".bmp"
    ));

    // 文件最大大小：2MB
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}", image.getOriginalFilename());

        // === 安全校验1：检查文件是否为空 ===
        if (image == null || image.isEmpty()) {
            return Result.error("上传文件不能为空");
        }

        String originalFilename = image.getOriginalFilename();

        // === 安全校验2：检查文件名是否合法（防止路径遍历攻击）===
        if (originalFilename == null || originalFilename.contains("..") ||
                originalFilename.contains("/") || originalFilename.contains("\\")) {
            log.warn("非法文件名：{}", originalFilename);
            return Result.error("文件名不合法");
        }

        // === 安全校验3：检查文件扩展名 ===
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex == -1) {
            return Result.error("文件扩展名不合法，仅允许上传图片文件");
        }
        String extension = originalFilename.substring(dotIndex).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            log.warn("不允许的文件类型：{}", extension);
            return Result.error("仅允许上传 JPG/PNG/GIF/BMP 格式的图片文件");
        }

        // === 安全校验4：检查文件MIME类型 ===
        String contentType = image.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            log.warn("不允许的MIME类型：{}", contentType);
            return Result.error("仅允许上传图片格式的文件");
        }

        // === 安全校验5：检查文件大小（不超过2MB）===
        if (image.getSize() > MAX_FILE_SIZE) {
            log.warn("文件过大：{} bytes", image.getSize());
            return Result.error("文件大小不能超过 2MB");
        }

        // === 通过所有校验，调用阿里云OSS上传 ===
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url:{}", url);

        return Result.success(url);
    }
}
