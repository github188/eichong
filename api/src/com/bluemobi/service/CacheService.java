package com.bluemobi.service;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bluemobi.cache.GameWorld;
import com.bluemobi.config.GameConfig;
import com.bluemobi.model.Player;
import com.wanma.app.util.DateUtil;

/**
 * 清理缓存服务
 * @author haojian
 * May 30, 2013 2:27:02 PM
 */
public class CacheService {
	
	
	private static Logger logger = Logger.getLogger(CacheService.class);
	
	/**
	 * 清理缓存
	 * 建议配置文件中 cacheExpiryTime 设为 180 分钟
	 * 建议配置文件中 maxCachePlayerNum 设为 5000 人
	 * @author haojian
	 * May 30, 2013 2:26:34 PM
	 */
	public static void clearCache(){
		
		Map<Integer, Player> playerMap = GameWorld.getAllPlayerMap();
		
		//缓存过期时间
		int cacheExpiryTime = GameConfig.cacheExpiryTime * 60 ;
		//最大缓存玩家数量
		int maxCachePlayerNum = GameConfig.maxCachePlayerNum;
		
		int currentPlayerNum = playerMap.size();
		if( currentPlayerNum >= maxCachePlayerNum ){
			cacheExpiryTime = (int) ( cacheExpiryTime * Math.pow( ( maxCachePlayerNum / (double)currentPlayerNum ) , 3 ) );
		}
		
		cacheExpiryTime = Math.max(cacheExpiryTime, 300);//设置最小缓存时间 300秒
		
		logger.info("开始清理缓存,当前缓存失效时间为：" + cacheExpiryTime);
		
		Set<Integer> keySet = playerMap.keySet();
		for(Integer playerId:keySet){
			Player player = playerMap.get(playerId);
			
			//在线的玩家千万不能清除！！！
			if(player.isOnline()){
				continue;
			}
			
			int lastAccessTime = player.getLastAccessTime();
			int currentTime = DateUtil.currentTimeSecond();
			
			if( (currentTime-lastAccessTime) > cacheExpiryTime ){
				//从内存中删除
				playerMap.remove(playerId);
				logger.info("玩家：" + player.getName() + "数据过期,清理缓存.");
			}
		}
		//清理结束后缓存玩家数量
		int endPlayerNum = playerMap.size();
		
		logger.info("清理缓存前玩家数量：【" + currentPlayerNum + "】，清理缓存后玩家数量：【" + endPlayerNum + "】,共清理数量：【" + (currentPlayerNum - endPlayerNum) + "】");
		
		
	}

}
