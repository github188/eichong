package com.netCore.service;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.conf.CoreConfig;
import com.netCore.model.conf.GameConfig;

public class GameConfigService {
	
	private static final Logger initConfigLog = LoggerFactory.getLogger("InitConfigLog");
	
	public static GameConfig initGameConfig() {
		
		initConfigLog.info("开始初始化应用服配置...");
		
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
		
		
		initConfigLog.info("初始化应用服配置结束，配置信息:【{}】", new Object[]{ gameConfig.toString() } );
		
		return gameConfig;
	}
	

}
