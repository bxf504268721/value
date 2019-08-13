package com.value;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValueApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;;

    @Test
    public void test01() {
        stringRedisTemplate.opsForValue().set("key1", "heheda");
    }
}
