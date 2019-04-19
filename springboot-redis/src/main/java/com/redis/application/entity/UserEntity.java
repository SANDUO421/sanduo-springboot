package com.redis.application.entity;

import lombok.Data;

/**
 * @author 三多
 * @Time 2019/4/11
 */
@Data
public class UserEntity {

    public static final String Table = "t_user";

    private String name;
    private String address;
    private Integer age;

    //public static void main(String[] args) {
    //
    //    UserEntity userEntity = new UserEntity();
    //    userEntity.address = "西安";
    //    userEntity.age = 18;
    //    userEntity.name = "袁天罡";
    //    System.out.println(userEntity.address);
    //    System.out.println(userEntity.toString());
    //}

}
