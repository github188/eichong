/*
 * 登录授权类文�?
 * Author: John.liu
 * Create: 2014/05/16
 * Last Modified: 2014/05/16
 * Version: V1.0
 */
package com.bluemobi.product.security;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bluemobi.product.cache.Cache;
import com.bluemobi.product.cache.EhCache;
import com.bluemobi.product.cache.SimpleCache;
import com.bluemobi.product.model.common.AccessToken;

/**
 * 登录授权�?
 * 
 * @author John.liu
 * @date 2014/05/16
 */
public class Authorization {
	private static Logger log = Logger.getLogger(Authorization.class);

	private String client_ip; // 客户端IP
	private String principal; // 用户�?
	private String role; // 角色
	private static long liveSeconds = 3600; // 存活时间（秒�?
	private static String tokenStrategy = "uuid"; // 令牌生成策略
	private static Cache cache; // 缓存
	private static String cacheName = "defaultCacheName";// 缓存名称

	private static boolean configLoad = false; // 配置是否加载

	private HttpServletRequest request;

	private void loadConfiguration() {
		// 加载配置
		Properties props = new Properties();
		try {
			// 加载classpath下的配置文件
			props.load(this.getClass().getClassLoader()
					.getResourceAsStream("security.properties"));
			// access token存活时长（秒�?
			liveSeconds = (null == props.getProperty("live_seconds") ? liveSeconds
					: Long.parseLong(props.getProperty("live_seconds")));
			// 生成token的策�?
			tokenStrategy = (null == props.getProperty("token_strategy")) ? tokenStrategy
					: props.getProperty("token_strategy");
			// 缓存
			String whatCache = props.getProperty("cache");
			// 缓存名称
			cacheName = (null == props.getProperty("cache_name")) ? cacheName
					: props.getProperty("cache_name");
			// 缓存
			if ("ehcache".equals(whatCache)) {
				// 使用ehcache
				cache = new EhCache(cacheName);
			} else if ("simple".equals(whatCache)) {
				// 使用concurrent hashmap
				cache = new SimpleCache(cacheName);
			} else {
				// 默认使用simple cache
				cache = new SimpleCache(cacheName);
			}
			// 配置加载完毕
			configLoad = true;
			log.info("Secure Authorization configuration loaded.");
		} catch (IOException e) {
			log.error("Causing Error when loading secure authorization configuration!");
			e.printStackTrace();
		}
	}

	public Authorization(HttpServletRequest request) {
		this.request = request;
		// 客户端IP
		this.client_ip = request.getRemoteAddr();
		// 配置只加载一�?
		if (!configLoad) {
			loadConfiguration();
		}
	}

	public Authorization(HttpServletRequest request, String principal) {
		this(request);
		// 设置principal
		this.principal = principal;
	}

	public Authorization(HttpServletRequest request, String principal,
			String role) {
		this(request, principal);
		// 设置角色
		this.role = role;
	}

	/**
	 * 更新token
	 * 
	 * @return
	 */
	public AccessToken refreshAccessToken() {
		// 判断缓存中是否保存有客户端的access token信息
		AccessToken token = (AccessToken) cache.get(client_ip);
		if (null != token) {
			// 如果存在，则移除
			cache.remove(client_ip);
		}
		AccessToken access_token = new AccessToken();
		// 设置超时
		access_token.setExpire(System.currentTimeMillis() + liveSeconds * 1000);
		// 设置token
		access_token.setToken(generateToken());
		// 设置用户�?
		access_token.setPrincipal(principal);
		// 设置角色
		access_token.setRole(role);
		// 更新缓存access token信息
		cache.set(client_ip, access_token);
		// 返回token�?
		return access_token;
	}

	/**
	 * 获取并验证缓存的access token
	 * 
	 * @param token
	 * @return
	 */
	public AccessToken authAccessToken(String token) {
		if (null == token) {
			return null;
		}
		if (null == cache.get(client_ip)) {
			return null;
		}
		AccessToken at = (AccessToken) cache.get(client_ip);

		// 验证token是否过期
		if (System.currentTimeMillis() > at.getExpire()) {
			return null;
		}
		// 验证token
		if (!at.getToken().equals(token)) {
			return null;
		}

		// 返回access token
		return (AccessToken) cache.get(client_ip);
	}

	/**
	 * 废除access token
	 */
	public void revokeAccessToken(String token) {
		cache.remove(client_ip);
	}

	// 生成token的方�?
	private String generateToken() {
		if ("uuid".equals(tokenStrategy)) {
			return UUID.randomUUID().toString();
		} else if ("timestamp".equals(tokenStrategy)) {
			return "" + System.currentTimeMillis();
		}
		return UUID.randomUUID().toString();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
