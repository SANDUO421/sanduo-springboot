package com.redis.application.dao;

import com.redis.application.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author 三多
 * @Time 2019/4/25
 */
public interface AddressRepository extends AddressExtRepository,MongoRepository<Address, String> {

    /**
     * 根据省，拿地址列表.@Query(fields = "{'province': 0}")
     * @param province
     * @return
     */

    @Query()
    List<Address> findAddressesByProvince(String province);

    /**
     * 根据省和省，拿地址列表.
     *
     * fields表示包含的字段
     * @param province
     * @param city
     * @return
     */
    @Query()
    List<Address> findAddressesByProvinceAndCityAndDistrict(String province, String city, String district);
}
