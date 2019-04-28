package com.springboot.function.controller;
import com.api.common.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 三多
 * @Time 2019/4/28
 */
@Api(description = "注解测试类")
@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @ApiOperation(value = "测试环境OK")
    @GetMapping("/test")
    public ApiResponse test() {
        return ApiResponse.ofSuccess("测试环境");
    }

}
