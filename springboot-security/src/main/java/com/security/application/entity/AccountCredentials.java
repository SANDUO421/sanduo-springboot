package com.security.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 认证账户：存储用户名和密码
 * @author 三多
 * @Time 2019/3/23
 */
@Data
public class AccountCredentials {
    private String username;
    private String password;

}
