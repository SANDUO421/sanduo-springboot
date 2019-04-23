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
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 三多
 * @Time 2019/4/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
public class RedisServiceUtilsTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

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

    /**
     * 设置添加
     */
    @Test
    public void setValueOption() {
        UserEntity user = new UserEntity();
        user.setAddress("上海");
        user.setName("sanduo");
        user.setAge(23);
        valueOperations.set(user.getName(), user);
        System.out.println("用户名：" + valueOperations.get(user.getName()));
    }

    /**
     * 判断存在获取值
     */
    @Test
    public void existsKey() {
        //UserEntity user = new UserEntity();
        //user.setAddress("上海");
        //user.setName("测试dfas");
        //user.setAge(123);
        // String key = RedisKeyUtil.getKey(UserEntity.Table, "name", user.getName());
        ValueOperations opsStr = redisTemplate.opsForValue();
        boolean result = redisServiceUtils.expireKey("sanduo", 20, TimeUnit.SECONDS);
        if (result) {
            UserEntity userEntity = (UserEntity) opsStr.get("sanduo");
            System.out.println("获取key=sanduo的值:"+userEntity.toString());
        }
    }

    /**
     * 测试SetOperations
     */
    @Test
    public void setOperations() {

        UserEntity user = new UserEntity();
        user.setAddress("西安");
        user.setName("测试西安");
        user.setAge(85);

        UserEntity user1 = new UserEntity();
        user1.setAddress("兰州");
        user1.setName("测试兰州");
        user1.setAge(36);

        setOperations.add("hash:set",user,user1);
        Set<Object> members = setOperations.members("hash:set");
        System.out.println(members);

    }

    @Test
    public void hashOperations() {
        UserEntity user = new UserEntity();
        user.setAddress("北京");
        user.setName("测试北京");
        user.setAge(15);
        hashOperations.put("hash:user",user.hashCode()+"",user);
        System.out.println(hashOperations.get("hash:user",user.hashCode()+""));
    }

    @Test
    public void listOperations() {
        UserEntity user = new UserEntity();
        user.setAddress("北京");
        user.setName("tom");
        user.setAge(20);
        System.out.println(listOperations.leftPop("list:user"));
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