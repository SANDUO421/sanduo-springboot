package com.mq.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author 三多
 * @Time 2019/5/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programmer implements Serializable {
    private String name;
    private Integer age;
    private LocalDate createDate;


}
