package com.spring.thrift.service;

import com.spring.thrift.Person;
import com.spring.thrift.PersonService;
import org.apache.thrift.TException;

/**
 * 使用thrift
 * @author 三多
 * @Time 2019/9/18
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws TException {
        System.out.println("get client username = "+ username);
        Person person = new Person().setUsername("张三").setAge(20).setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws TException {
        System.out.println("get client param");
        System.out.println(person.getUsername()+"-"+person.getAge()+"-"+person.isMarried());
    }
}
