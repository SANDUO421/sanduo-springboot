package com.redis.application.utils;

import com.redis.application.RedisApplication;
import com.redis.application.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 三多
 * @Time 2019/4/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={RedisApplication.class})
public class RedisServiceUtilsTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;


    @Resource
    private RedisServiceUtils redisServiceUtils;

    @Test
    public void  setValueOption(){
        UserEntity user = new UserEntity();
        user.setAddress("上海");
        user.setName("sanduo");
        user.setAge(23);
        valueOperations.set(user.getName(),user);
        System.out.println(valueOperations.get(user.getName()));
    }

    @Test
    public void existsKey() {
        //UserEntity user = new UserEntity();
        //user.setAddress("上海");
        //user.setName("测试dfas");
        //user.setAge(123);
        //ValueOperations opsStr = redisTemplate.opsForValue();
        //redisServiceUtils.expireKey("name",20, TimeUnit.SECONDS);
        //String key = RedisKeyUtil.getKey(UserEntity.Table, "name", user.getName());
        //UserEntity userEntity = (UserEntity) opsStr.get(key);
        //System.out.println(userEntity.toString());

    }

    @Test
    public void renameKey() {
    }

    @Test
    public void renameKeyNotExist() {
    }

    @Test
    public void deleteKey() {
    }

    @Test
    public void deleteKey1() {
    }

    @Test
    public void deleteKey2() {
    }

    @Test
    public void expireKey() {
    }

    @Test
    public void expireKeyAt() {
    }

    @Test
    public void getKeyExpire() {
    }

    @Test
    public void persistKey() {
    }
}