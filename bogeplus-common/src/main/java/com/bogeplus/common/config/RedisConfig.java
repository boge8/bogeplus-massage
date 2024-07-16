package com.bogeplus.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        //设置关于各种自定义类型的转换规则，如：设置string、hash、set的他们的值的类型的转换规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //我想将值，能够转为json，一定需要json依赖的支持，默认支持的json是fastjson
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //至于其他类型、hash、set、zset也需要支持自定义序列化的时候，就在这里直接按照上面套路来一一一个指定就行了
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //连接信息
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
