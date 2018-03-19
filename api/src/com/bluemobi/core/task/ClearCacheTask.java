package com.bluemobi.core.task;

import org.apache.log4j.Logger;

import com.bluemobi.service.CacheService;


/**
 * 清理缓存中过期的玩家数据
 * @author haojian
 * May 30, 2013 2:28:33 PM
 */
public class ClearCacheTask implements Runnable {
	
	private static Logger logger = Logger.getLogger(ClearCacheTask.class);
	
	@Override
	public void run() {
		
		//1、清理缓存
		try{
			CacheService.clearCache();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("清理缓存出错！");
		}
		
		
	}

}
