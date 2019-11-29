package com.spring.netty.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 测试concurrent
 *
 * @author 三多
 * @Time 2019/10/11
 */
public class ConcurrentTest {

    private final  ConcurrentMap<String,Object> concurrentMap = new ConcurrentHashMap<>();

    @Test
    public void  testConcurrentHashMapHashMap(){
        Object a = concurrentMap.putIfAbsent("name", "sanduo");
        System.out.println(a);
        Object b = concurrentMap.putIfAbsent("name", "sanduo");
        System.out.println(b);
        concurrentMap.put("age",20);
        System.out.println(concurrentMap.get("name")+"-"+concurrentMap.get("age"));

    }
}
