package com.redis.application.controller;

import com.api.common.utils.ApiResponse;
import com.redis.application.utils.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * fastdfs demo
 *
 * @author 三多
 * @Time 2019/4/24
 */
@Api(description = "fastDfs 测试controller")
@RestController
@EnableCaching
@RequestMapping("/fastDfs")
public class FastDfsController {

    @Autowired
    private FastDFSClientWrapper dfsClient;

    /**
     * 上传图片
     * @return
     */
    @ApiOperation(value = "上传图片")
    @PostMapping("/upload")
    public  String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        // 省略业务逻辑代码。。。
        try {
            String imgUrl = dfsClient.uploadFile(file);
            return imgUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

    }
}
