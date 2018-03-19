package com.wanma.ims.redis;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service(value = "redisService")
public class RedisService {
	
	@Autowired
    private RedisTemplate redisTemplate;

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
	
	public void strRemove(String key){
		redisTemplate.delete(key);
	}
	
	public void strIncr(String key){
		String value=strGet(key).toString();
		if(null==value){
			strSet(key,"1");
		}else{
			int incrValue=Integer.parseInt(value)+1;
			strSet(key,incrValue+"");
		}
	}

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
