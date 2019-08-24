package com.lee.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 9:50
 * @Description
 **/
@Service
public class RedisService {

    @Autowired
    private JedisCluster jedisCluster;

    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public Long del(String key) {
        return jedisCluster.del(key);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public Long expire(String key, Integer seconds) {
        return jedisCluster.expire(key, seconds);
    }

}
