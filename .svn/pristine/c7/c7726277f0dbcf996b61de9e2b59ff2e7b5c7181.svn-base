package com.wanma.ims.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service(value = "redisService")
public class RedisDataCenter {
	
	@Autowired
    private RedisTemplate redisTemplate;

    /**
     * Str set 带时间
     * @param key
     * @param value
     * @param time
     */
    public void strSetWithTime(String key,Object value,Long num,TimeUnit time){
        redisTemplate.opsForValue().set(key, value,num,time);
    }

    /**
     * String Set
     * @param key
     * @param value
     */
    public void strSet(String key,Object value){
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * String Get
     * @param key
     * @return
     */
    public Object strGet(String key){
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * list GetAll
     * @param key
     * @return
     */
    public Object listGetAll(String key){
        return redisTemplate.opsForList().range(key,0,-1);
    }
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
