package com.springboot.function.controller;
import com.api.common.utils.ApiResponse;
import com.api.common.utils.BaseUtils;
import com.springboot.function.entity.User;
import com.springboot.function.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 三多
 * @Time 2019/4/28
 */
@Api(description = "注解测试类")
@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "测试环境OK")
    @GetMapping("/test")
    public ApiResponse test() {
        return ApiResponse.ofSuccess("测试环境");
    }

    @ApiOperation(value = "测试注解@DateFilter")
    @GetMapping("/queryUserInfo")
    public ApiResponse queryUserInfo() {
        User user = userService.queryUserInfo(new HashMap<String, Object>());
        return ApiResponse.ofSuccess(user);
    }

    public static void main(String[] args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String md5 = BaseUtils.md5("Lydsj@2019");
        String oldPwd =  passwordEncoder.encode(md5);
        System.out.println(md5);
        boolean checkpw = BCrypt.checkpw(md5, "$2a$10$8pHy.Q5zT0t1Xrv4B/meVe9b0o36DOViOTe7wIZhY.JGpTuqy5uTm");
        System.out.println(passwordEncoder.encode(md5));
        System.out.println(checkpw);
    }


}
