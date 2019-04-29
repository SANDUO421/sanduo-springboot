package com.springboot.function.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author 三多
 * @Time 2019/4/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long  id;
    private String username;
    private String password;
    private String desc;
    private LocalDate createDate;
    private LocalDate createBy;
    private LocalDate lastUpdateDate;
    private LocalDate lastUpdateBy;
}
