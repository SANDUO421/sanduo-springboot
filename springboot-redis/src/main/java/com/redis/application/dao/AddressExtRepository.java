package com.redis.application.dao;

import com.redis.application.entity.Address;

/**
 * @author 三多
 * @Time 2019/4/25
 */
public interface AddressExtRepository {

    Address findByProvinceAndCity(String province, String city);
}
