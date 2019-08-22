package com.redis.application.controller;

import com.redis.application.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试异常处理效果
 * @author 三多
 * @Time 2019/8/22
 */
@Api("异常处理")
@RestController
@RequestMapping("/test")
public class ExceptionController {

    @ApiOperation(value="统一异常处理测试")
    @GetMapping(value="/exception/{id}",produces="text/plain;charset=UTF-8")
    public String testException(@PathVariable(value = "id",required = false) String id){
        if(! StringUtils.isNotBlank(id)){
            throw new BusinessException("id参数为空","222");
        }
        if(id.equals("3")){
            throw new BusinessException("id:"+id+",不正确","222");
        }
        return "请求成功";

    }
}
