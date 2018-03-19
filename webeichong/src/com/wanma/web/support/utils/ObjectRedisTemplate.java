package com.wanma.web.support.utils;


import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.wanma.model.Userinfo;

public class ObjectRedisTemplate<T> extends RedisTemplate<String, T> {

    public ObjectRedisTemplate(RedisConnectionFactory connectionFactory,
            Class<T> clazz) {

        /*RedisSerializer<T> objectSerializer = null;
        RedisSerializer<T> objectKeySerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer ss=new StringRedisSerializer();
        setKeySerializer(objectKeySerializer);
        setValueSerializer(objectSerializer);
        setHashKeySerializer(objectSerializer);
        setHashValueSerializer(objectSerializer);
*/
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection,
            boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
