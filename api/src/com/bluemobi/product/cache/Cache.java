/*
 * 缓存接口文件
 * Author: John.liu
 * Create: 2014/05/16
 * Last Modified: 2014/05/16
 * Version: V1.0 
 */
package com.bluemobi.product.cache;


/**
 * 缓存工具接口
 * @author John.liu
 * @date 2014/05/16
 */
public interface Cache {
	/**
	 * 存入缓存
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value);
	/**
	 * 获取缓存对象
	 * @param key
	 * @return
	 */
	public Object get(String key);
	/**
	 * 移除缓存对象
	 * @param key
	 */
	public void remove(String key);
	
	public CacheStatistics getCacheStatistics();
}
