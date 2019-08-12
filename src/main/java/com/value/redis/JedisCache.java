package com.value.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
* @Description Jedis工具类
* @Author bxf
* @Date   2019/8/12
*/

@Component("jedisCache")
public class JedisCache {
    private Logger logger = LoggerFactory.getLogger(JedisCache.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 给某个key设值
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        try {
            getJedis().set(key, value);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        } finally {
            //getJedis().close();
        }

    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public Object get(String key) {
        try {
            return getJedis().get(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            //getJedis().close();
        }
        return null;
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean existKey(String key) {
        try {
            return getJedis().exists(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            //getJedis().close();
        }
        return false;
    }

    /**
     * 获取Jedis客户端
     * @return
     */
    private Jedis getJedis() {
        return jedisPool.getResource();
    }
}
