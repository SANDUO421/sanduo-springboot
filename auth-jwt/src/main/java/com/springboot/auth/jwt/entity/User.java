package com.springboot.auth.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 三多
 * @Time 2019/8/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String Id;
    String username;
    String password;
}
