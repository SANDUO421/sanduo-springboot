package com.redis.application.service;

import com.redis.application.entity.Address;

/**
 * @author 三多
 * @Time 2019/4/25
 */
public interface UserService {
    Address getAddress(String province);

    Address save(Address address);
}
