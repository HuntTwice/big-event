package com.zc.bigeventbackend.controller;

import com.zc.bigeventbackend.result.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileUploadController {

    @Value("${file-system.dir}")
    private String dirs;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String newFilename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = new File(dirs,newFilename);
        multipartFile.transferTo(file);
        log.info("文件存储成功！");
        return Result.success("url地址");


    }
}
