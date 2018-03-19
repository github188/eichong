package com.appCore.service;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.appCore.conf.CoreConfig;
import com.appCore.model.conf.GameConfig;

public class GameConfigService {
	
	private static final Logger initConfigLog = Logger.getLogger("InitConfigLog");
	
	public static GameConfig initGameConfig() {
		
		initConfigLog.info("开始初始化游戏服配置...");
		
		Element e =  CoreConfig.getRootElement("game-config.xml");
		
		GameConfig gameConfig = new GameConfig();
		
		String description = e.getChild("description").getValue();
		String name = e.getChild("server-name").getValue();
		int id = Integer.valueOf(e.getChild("server-id").getValue());
		int pfId = Integer.valueOf(e.getChild("pf-id").getValue());
		int zoneId = Integer.valueOf(e.getChild("zone-id").getValue());
		
		int shutdownPort = Integer.valueOf(e.getChild("shutdown-port").getValue());
		
		
		gameConfig.setDescription(description);
		gameConfig.setName(name);
		gameConfig.setId(id);
		gameConfig.setPfId(pfId);
		gameConfig.setZoneId(zoneId);
		gameConfig.setShutdownPort(shutdownPort);
		
		
		initConfigLog.info("初始化游戏服配置结束，配置信息:【" + gameConfig.toString() + "】");
		
		return gameConfig;
	}
	

}
