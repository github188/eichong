package com.wanma.common;

/**
 *  缓存管理
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2014-9-24 上午1:00:33
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class CacheEntity {
	 private String key;//缓存ID    
     private Object value;//缓存数据    
     private long timeOut;//更新时间    
     private boolean expired; //是否终止    true 终止   FALSE 不终止
     public CacheEntity() {    
             super();    
     }    

     public CacheEntity(String key, Object value, long timeOut, boolean expired) {    
             this.key = key;    
             this.value = value;    
             this.timeOut = timeOut;    
             this.expired = expired;    
     }    

     public String getKey() {    
             return key;    
     }    

     public long getTimeOut() {    
             return timeOut;    
     }    

     public Object getValue() {    
             return value;    
     }    

     public void setKey(String string) {    
             key = string;    
     }    

     public void setTimeOut(long l) {    
             timeOut = l;    
     }    

     public void setValue(Object object) {    
             value = object;    
     }    

     public boolean isExpired() {    
             return expired;    
     }    

     public void setExpired(boolean b) {    
             expired = b;    
     }   
}