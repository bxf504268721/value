package com.value;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValueApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 操作string类型使用stringRedisTemplate
     */
    @Test
    public void test01() {
        stringRedisTemplate.opsForValue().set("name", "zhangsan",30, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
        //stringRedisTemplate.delete("key1");
        System.out.println(stringRedisTemplate.hasKey("name"));;
    }

    /**
     * list(添加一个元素导列表的头部（左边）或者尾部（右边）)
     */
    @Test
    public void test02() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        //System.out.println(redisTemplate.opsForList().leftPushAll("list", list));
        //redisTemplate.delete("list");
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    /**
     * hash(将多个键值对存储到一个Redis键里面)
     */
    @Test
    public void test03() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 26);
        map.put("sex", "1");
        //redisTemplate.opsForHash().putAll("hash", map);
        System.out.println(redisTemplate.opsForHash().hasKey("hash", "name"));
        System.out.println(redisTemplate.opsForHash().hasKey("hash", "name1"));
        System.out.println(redisTemplate.opsForHash().get("hash", "name"));
    }

    /**
     * Set是string类型的无序集合,集合成员是唯一的
     */
    @Test
    public void test04() {
        String[] str = new String[]{"str1", "str2"};
        System.out.println(redisTemplate.opsForSet().add("set", str));
    }

    /**
     * zset
     * 有序集合和无序集合一样也是string类型元素的集合,且不允许重复的成员。
     * 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
     * 有序集合的成员是唯一的,但分数(score)却可以重复
     */
    @Test
    public void test05() {
        // 新增一个有序集合，存在的话为false，不存在的话为true
        System.out.println(redisTemplate.opsForZSet().add("zset1", "name", 2.0));
    }
}
