/*
 * Ehcache实现的缓存工具类文件
 * Author: John.liu
 * Create: 2014/05/16
 * Last Modified: 2014/05/16
 * Version: V1.0
 */
package com.bluemobi.product.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;
/**
 * Ehcache实现的缓存工具类
 * @author John.liu
 * 2014/05/16
 */
public class EhCache implements Cache{
	//cache manager
	private static CacheManager manager;
	//cache object
	private static net.sf.ehcache.Ehcache cache;
	//constructor 
	public EhCache(String cacheName){
		if(null == manager){
			//single instance of cache manager 
			manager = CacheManager.create();
			//get cache from cache manager
			cache = manager.getCache(cacheName);
		}
	}
	@Override
	public void set(String key, Object value) {
		//construct cache element
		Element element = new Element(key, value);
		//put element into cache
		cache.put(element);
	}
	@Override
	public Object get(String key) {
		//get cache element
		Element element = cache.get(key);
		//if element is not null, then cache value will be returned
		if (element != null) {
			return element.getObjectValue();
		}
		return null;
	}
	@Override
	public void remove(String key) {
		//remove cache element by key
		cache.remove(key);

	}
	@Override
	public CacheStatistics getCacheStatistics() {
		StatisticsGateway statistic = cache.getStatistics();
		CacheStatistics s = new CacheStatistics();
		s.setCacheSize(statistic.getSize());
		s.setDiskSize(statistic.getLocalDiskSize());
		s.setHeapSize(statistic.getLocalHeapSize());
		s.setWriterQueneLength(statistic.getWriterQueueLength());
		s.setHitCount(statistic.cacheHitCount());
		s.setMissCount(statistic.cacheMissCount());
		s.setCachePutCount(statistic.cachePutCount());
		s.setCacheRemoveCount(statistic.cacheRemoveCount());
		
		return s;
	}
	
}