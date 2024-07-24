package com.bogeplus.common.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisUtil implements ApplicationContextAware {

    private static RedisTemplate<String, Serializable> redisTemplate;

    public static void incr(String userCountSms, int i) {
        redisTemplate.opsForValue().increment(userCountSms, i);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (RedisUtil.redisTemplate == null) {
            RedisUtil.redisTemplate = (RedisTemplate<String, Serializable>) applicationContext.getBean("redisTemplate");
        }
    }

    // 设置值
    public static boolean set(String key, Serializable value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 设置值并设置过期时间
    public static boolean set(String key, Serializable value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取值
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    // 删除值
    public static boolean del(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 设置过期时间
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取过期时间
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    // 判断是否有该key
    public static boolean exist(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}