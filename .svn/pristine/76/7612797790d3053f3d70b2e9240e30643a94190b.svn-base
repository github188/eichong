package com.wanma.dubbox.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 封装redis 缓存服务器服务接口
 * 
 * @author hk
 * 
 *         2012-12-16 上午3:09:18
 */
@Service(value = "redisService")
@SuppressWarnings({"rawtypes","unchecked"})
public class RedisService  {
	@Autowired
    private RedisTemplate redisTemplate;

    /** 
     * 设值 
     */  
    public Long listSet(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);  
    } 


    /** 
     * 存值
     */  
    public void objPut(String key1, String key2,Object value) {
    	redisTemplate.opsForHash().put(key1, key2, value);
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
    public Object listStackPop(String key) {  
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
    public Object ListQueuePop(String key) {  
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
    public Object listGetByIndex(String key, long index) {  
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
     * 记录数
     * @param keyPattern
     * @return
     */
    public int getTotalRecordsCount(String keyPattern) {
      return redisTemplate.keys(keyPattern).size();
    }
    
    /**
     * 记录keys
     * @param keyPattern
     * @return
     */
    public Set<String> getTotalRecords(String keyPattern) {
      return redisTemplate.keys(keyPattern);
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
	public Object strGet(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * Object Get
	 * @param key
	 * @return
	 */
	public Object objHashGet(String key){
		return redisTemplate.opsForHash().entries(key);
	}
	
	/**
	 * Object Get
	 * @param key
	 * @return
	 */
	public Object objGet(String key0,String key1){
		return redisTemplate.opsForHash().get(key0, key1);
	}
	
	public void objHashRemove(String key0,Object key1){
		redisTemplate.opsForHash().delete(key0, key1);
	}
	
	public void strRemove(String key){
		redisTemplate.delete(key);
	}
	
	public void strIncr(String key){
		Object value=strGet(key);
		if(null==value){
			strSet(key,"1");
		}else{
			int incrValue=Integer.parseInt(value.toString())+1;
			strSet(key,incrValue+"");
		}
	}
	
	/**
	 * 清空某个key下的数据
	 * @param key
	 * @return
	 */
	 public long del(final String key) {
	        return (long) redisTemplate.execute(new RedisCallback() {
	            public Long doInRedis(RedisConnection connection) throws DataAccessException {
	                long result = 0;
	                    result = connection.del(key.getBytes());
	                return result;
	            }
	        });
	    }
	 
	 /**
	  * 判断是否存在
	  * @param key
	  * @return
	  */
	   public boolean exists(final String key) {
	        return (boolean) redisTemplate.execute(new RedisCallback() {
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	                return connection.exists(key.getBytes());
	            }
	        });
	    }

}