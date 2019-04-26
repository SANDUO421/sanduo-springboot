package com.redis.application.service.impl;

import com.redis.application.dao.AddressRepository;
import com.redis.application.entity.Address;
import com.redis.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 三多
 * @Time 2019/4/25
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address getAddress(String province) {
        List<Address> list = addressRepository.findAddressesByProvince(province);
        if(list.size()>0) {
            list.stream().forEach(item -> {
                System.out.println(item.toString());
            });
            return addressRepository.findAddressesByProvince(province).get(0);
        }
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.insert(address);
    }
}
