package com.value.controller.api;

import com.value.redis.JedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
* @Description 测试Jedis
* @Author bxf
* @Date   2019/8/12
*/
@RestController
@RequestMapping("/redis")
public class RedisApi {
    @Autowired
    private JedisCache jedisCache;

    /**
     * 赋值
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/set")
    public Object setValue(@RequestParam(required = true) String key, @RequestParam(required = true) String value) {
        Map<String, Object> result = new HashMap<>();
        try {
            jedisCache.set(key, value);
            result.put("success", "成功！");
            return result;
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return result;
        }

    }

    /**
     * 取值
     * @param key
     * @return
     */
    @RequestMapping("/get")
    public Object getValue(@RequestParam(required = true) String key) {
        Map<String, Object> result = new HashMap<>();
        if (!jedisCache.existKey(key)) {
            result.put("error", "消息已经失效!");
            return result;
        }
        result.put(key, jedisCache.get(key));
        return result;
    }
}
