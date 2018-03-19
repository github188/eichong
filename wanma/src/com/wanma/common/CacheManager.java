package com.wanma.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


/**
 *  缓存管理
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2014-9-24 上午1:00:33
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@SuppressWarnings("unchecked")
public class CacheManager {
	
   	public static final long SZ_CACHE_TIMES =5*60*1000l; //缓存时间
	//全局变量用户存储缓存信息
	public static HashMap<String, Object> cacheMap = new HashMap<String, Object>();
	// 单实例构造方法
	private CacheManager() {
		super();
	}
	// 获取布尔值的缓存
	public static boolean getSimpleFlag(String key) {
		try {
			return (Boolean) cacheMap.get(key);
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static long getServerStartdt(String key) {
		try {
			return (Long) cacheMap.get(key);
		} catch (Exception ex) {
			return 0;
		}
	}

	// 设置布尔值的缓存
	public synchronized static boolean setSimpleFlag(String key, boolean flag) {
		if (flag && getSimpleFlag(key)) {// 假如为真不允许被覆盖
			return false;
		} else {
			cacheMap.put(key, flag);
			return true;
		}
	}

	public synchronized static boolean setSimpleFlag(String key,
			long serverbegrundt) {
		if (cacheMap.get(key) == null) {
			cacheMap.put(key, serverbegrundt);
			return true;
		} else {
			return false;
		}
	}

	// 得到缓存。同步静态方法
	private synchronized static CacheEntity getCache(String key) {
		return (CacheEntity) cacheMap.get(key);
	}

	// 判断是否存在一个缓存
	private synchronized static boolean hasCache(String key) {
		return cacheMap.containsKey(key);
	}

	// 清除所有缓存
	public synchronized static void clearAll() {
		cacheMap.clear();
	}

	// 清除某一类特定缓存,通过遍历HASHMAP下的所有对象，来判断它的KEY与传入的TYPE是否匹配
	public synchronized static void clearAll(String type) {
		Iterator<Entry<String, Object>> i = cacheMap.entrySet().iterator();
		String key;
		ArrayList<String> arr = new ArrayList<String>();
		try {
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				key = (String) entry.getKey();
				if (key.startsWith(type)) { // 如果匹配则删除掉
					arr.add(key);
				}
			}
			for (int k = 0; k < arr.size(); k++) {
				clearOnly(arr.get(k));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 清除指定的缓存
	public synchronized static void clearOnly(String key) {
		cacheMap.remove(key);
	}

	// 载入缓存
	public synchronized static void putCache(String key, CacheEntity obj) {
		cacheMap.put(key, obj);
	}

	// 获取缓存信息
	public static CacheEntity getCacheInfo(String key) {

		if (hasCache(key)) {
			CacheEntity cache = getCache(key);
			if (cacheExpired(cache)) { // 调用判断是否终止方法
				cache.setExpired(true);
			}
			return cache;
		} else{
			return null;
		}
	}

	// 载入缓存信息
	public static void putCacheInfo(String key, Object obj, long dt,
			boolean expired) {
		CacheEntity cache = new CacheEntity();
		cache.setKey(key);
		cache.setTimeOut(dt + System.currentTimeMillis()); // 设置多久后更新缓存
		cache.setValue(obj);
		cache.setExpired(expired); // 缓存默认载入时，终止状态为FALSE
		cacheMap.put(key, cache);
	}

	// 重写载入缓存信息方法
	public static void putCacheInfo(String key, Object obj, long dt) {
		CacheEntity cache = new CacheEntity();
		cache.setKey(key);
		cache.setTimeOut(dt + System.currentTimeMillis());
		cache.setValue(obj);
		cache.setExpired(false);
		cacheMap.put(key, cache);
	}
	
	// 重写载入缓存信息方法
	public static void putCacheInfo(String key, Object obj) {
		CacheEntity cache = new CacheEntity();
		cache.setKey(key);
		cache.setTimeOut(SZ_CACHE_TIMES + System.currentTimeMillis());
		cache.setValue(obj);
		cache.setExpired(false);
		cacheMap.put(key, cache);
	}

	// 判断缓存是否终止
	public static boolean cacheExpired(CacheEntity cache) {
		if (null == cache) { // 传入的缓存不存在
			return false;
		}
		long nowDt = System.currentTimeMillis(); // 系统当前的毫秒数
		long cacheDt = cache.getTimeOut(); // 缓存内的过期毫秒数
		if (cacheDt <= 0 || cacheDt > nowDt) { // 过期时间小于等于零时,或者过期时间大于当前时间时，则为FALSE
			return false;
		} else { // 大于过期时间 即过期
			return true;
		}
	}

	// 获取缓存中的大小
	public static int getCacheSize() {
		return cacheMap.size();
	}

	// 获取指定的类型的大小
	@SuppressWarnings("rawtypes")
	public static int getCacheSize(String type) {
		int k = 0;
		Iterator i = cacheMap.entrySet().iterator();
		String key;
		try {
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				key = (String) entry.getKey();
				if (key.indexOf(type) != -1) { // 如果匹配则删除掉
					k++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return k;
	}

	// 获取缓存对象中的所有键值名称
	@SuppressWarnings("finally")
	public static ArrayList<String> getCacheAllkey() {
		ArrayList a = new ArrayList();
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				a.add((String) entry.getKey());
			}
		} catch (Exception ex) {

		} finally {
			return a;
		}
	}

	// 获取缓存对象中指定类型 的键值名称
	@SuppressWarnings("finally")
	public static ArrayList<String> getCacheListkey(String type) {
		ArrayList a = new ArrayList();
		String key;
		try {
			Iterator i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				key = (String) entry.getKey();
				if (key.indexOf(type) != -1) {
					a.add(key);
				}
			}
		} catch (Exception ex) {

		} finally {
			return a;
		}
	}
}
