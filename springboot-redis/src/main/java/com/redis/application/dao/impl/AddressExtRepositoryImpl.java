package com.redis.application.dao.impl;

import com.redis.application.dao.AddressExtRepository;
import com.redis.application.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 三多
 * @Time 2019/4/25
 */
@Repository
public class AddressExtRepositoryImpl implements AddressExtRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Address findByProvinceAndCity(String province, String city) {
        Query query = new Query(Criteria.where("province").is(province).and("city").is(city));
        return mongoTemplate.findOne(query, Address.class, "address");
    }


}
