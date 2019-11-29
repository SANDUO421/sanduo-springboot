package com.spring.netty.userdefined.protocol;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 自定义协议
 *
 * @author 三多
 * @Time 2019/11/14
 */
@Data
public class PersonProtocol {
    /**
     * 消息长度
     *
     */
    @NotNull
    @Length(min = 0,max = 100)
    private int length;
    /**
     * 内容
     */
    @NotNull
    private byte[] content;
}
