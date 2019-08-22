package com.redis.application.exception;

import com.redis.application.utils.CommonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 1.第一步：自定义业务异常
 * @author 三多
 * @Time 2019/8/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String msg;
    private String code;


    /**
     * 3. 第三部配置 未定异常处理类
     */
    @RestControllerAdvice("com.redis.application")
    
    static  class UnifiedExceptionHandle{

        @ExceptionHandler(BusinessException.class)
        public CommonResult<Void> handleException(BusinessException be){
            return CommonResult.errorResult(be.getMsg(),be.getCode());
        }

    }
}
