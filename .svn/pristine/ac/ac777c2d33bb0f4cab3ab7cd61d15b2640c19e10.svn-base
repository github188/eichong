/*
 * concurrenthashmap实现的简单缓存类文件
 * Author: John.liu
 * Create: 2014/05/16
 * Last Modified: 2014/05/16
 * Version: V1.0
 */
package com.bluemobi.product.cache;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �?��缓存�?
 * @author John.liu
 * @date 2014/05/16
 */
public class SimpleCache implements Cache {
	//缓存Map
	private static HashMap<String, ConcurrentHashMap<String, Object>> cacheMap = new HashMap<String, ConcurrentHashMap<String,Object>>(16);
	//缓存
	private ConcurrentHashMap<String, Object> cache;
	
	public SimpleCache(String cacheName){
		if(null == cacheMap.get(cacheName)){
			//每个cache都是�?��concurrenthashmap
			cacheMap.put(cacheName, new ConcurrentHashMap<String, Object>());
		}
		//从缓存堆中获取缓�?
		cache = cacheMap.get(cacheName);
	}

	@Override
	public void set(String key, Object value) {
		cache.put(key, value);
	}

	@Override
	public Object get(String key) {
		return cache.get(key);
	}

	@Override
	public void remove(String key) {
		cache.remove(key);
	}

	@Override
	public CacheStatistics getCacheStatistics() {
		// TODO Auto-generated method stub
		return null;
	}
}
