package com.wanma.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 封装redis 缓存服务器服务接口
 * 
 * @author hk
 * 
 *         2012-12-16 上午3:09:18
 */
@Service(value = "redisService")
public class RedisService  {
	@Autowired
    private StringRedisTemplate redisTemplate;

   

    /** 
     * 设值 
     */  
    public Long listSet(String key, String value) {  
        return redisTemplate.opsForList().leftPush(key, value);  
    } 
    /** 
     * 置值 
     *  
     * @param key 
     * @param index 
     * @param value 
     */  
    public void listSet(String key, long index, String value) {  
        redisTemplate.opsForList().set(key, index, value);  
    }  
    /** 
     * 压栈 
     */
    public Long listStackPush(String key, String value) {  
        return redisTemplate.opsForList().leftPush(key, value);  
    }  
    /** 
     * 出栈 
     */
    public String listStackPop(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
  
    /** 
     * 入队 
     */  
    public Long listQueuePush(String key, String value) {  
        return redisTemplate.opsForList().rightPush(key, value);  
    }  
  
    /** 
     * 出队 
     */  
    public String ListQueuePop(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
  
    /** 
     * 栈/队列长 
     *  
     * @param key 
     * @return 
     */  
    public Long listLength(String key) {  
        return redisTemplate.opsForList().size(key);  
    }  
  
    public List<String> listGetAll(String key){
    	return listGetByRange(key, 0, -1);
    }
    /** 
     * 范围检索 
     */  
    public List<String> listGetByRange(String key, int start, int end) {  
        return redisTemplate.opsForList().range(key, start, end);  
    }  
  
    /** 
     * 移除 
     *  
     * @param key 
     * @param i 
     * @param value 
     */  
    public void listRemove(String key, String value) {  
        redisTemplate.opsForList().remove(key, 10, value);  
    }  
    
    
  
    /** 
     * 检索 
     *  
     * @param key 
     * @param index 
     * @return 
     */  
    public String listGetByIndex(String key, long index) {  
        return redisTemplate.opsForList().index(key, index);  
    }  
  
    
    /** 
     * 移除 
     *  
     * @param key 
     * @param i 
     * @param value 
     */  
    public void listRemove(String key, long i, String value) {  
        redisTemplate.opsForList().remove(key, i, value);  
    }  
    /** 
     * 裁剪 
     *  
     * @param key 
     * @param start 
     * @param end 
     */  
    public void listTrim(String key, long start, int end) {  
        redisTemplate.opsForList().trim(key, start, end);  
    }  


    /**
     * String Set
     * @param key
     * @param value
     */
    public void strSet(String key,String value){
    			redisTemplate.opsForValue().set(key, value);
	}
	/**
	 * String Get
	 * @param key
	 * @return
	 */
	public String strGet(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	public void strRemove(String key){
		redisTemplate.delete(key);
	}
	
	public void strIncr(String key){
		String value=strGet(key);
		if(null==value){
			strSet(key,"1");
		}else{
			int incrValue=Integer.parseInt(value)+1;
			strSet(key,incrValue+"");
		}
	}

}