package com.appCore.service;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.appCore.conf.CoreConfig;
import com.appCore.model.conf.ServerConfig;
import com.appCore.model.conf.ServerConfigs;

public class ServerConfigService {
	
	private static final Logger initConfigLog = Logger.getLogger("InitConfigLog");
	
	public static ServerConfigs initServerConfigs() {
		
		initConfigLog.info("开始初始化服务器配置...");
		
		ServerConfigs serverConfigs = new ServerConfigs();
		
		Element root =  CoreConfig.getRootElement("server-config.xml");
		
		//单进程游戏服务器使用此配置，外网客户端直接连接此端口
		Element gameServerElement = root.getChild("game-server");
		//外网客户端连接此网关
		Element gateServerElement = root.getChild("gate-server");
		//内网逻辑服连接此网关
		Element innerGateServerElement = root.getChild("inner-gate-server");
		//内网app逻辑服连接此网关
		Element appGateServerElement = root.getChild("app-gate-server");
		//外网客户端连接此公共服
		Element publicServerElement = root.getChild("public-server");
		//内网游戏服连接此公共服
		Element innerPublicServerElement = root.getChild("inner-public-server");
		//内网游戏服连接此世界服
		Element worldServerElement = root.getChild("inner-world-server");
		
		if(gameServerElement!=null){
			ServerConfig gameServer = ServerConfigService.initServerConfig(gameServerElement);
			serverConfigs.setGameServer(gameServer);
		}
		if(gateServerElement!=null){
			ServerConfig gateServer = ServerConfigService.initServerConfig(gateServerElement);
			serverConfigs.setGateServer(gateServer);
		}
		if(innerGateServerElement!=null){
			ServerConfig innerGateServer = ServerConfigService.initServerConfig(innerGateServerElement);
			serverConfigs.setInnerGateServer(innerGateServer);
		}
		if(appGateServerElement!=null){
			ServerConfig appGateServer = ServerConfigService.initServerConfig(appGateServerElement);
			serverConfigs.setAppGateServer(appGateServer);
		}
		if(publicServerElement!=null){
			ServerConfig publicServer = ServerConfigService.initServerConfig(publicServerElement);
			serverConfigs.setPublicServer(publicServer);
		}
		if(innerPublicServerElement!=null){
			ServerConfig innerPublicServer = ServerConfigService.initServerConfig(innerPublicServerElement);
			serverConfigs.setInnerPublicServer(innerPublicServer);
		}
		if(worldServerElement!=null){
			ServerConfig innerWorldServer = ServerConfigService.initServerConfig(worldServerElement);
			serverConfigs.setInnerWorldServer(innerWorldServer);
		}
		
		initConfigLog.info("初始化服务器配置结束...配置信息:【" + serverConfigs.toString() + "】");
		
		return serverConfigs;
	}
	
	/**
	 * 初始化服务器配置
	 * @author hao
	 * Mar 18, 2014 4:51:14 PM
	 * @return
	 */
	public static ServerConfig initServerConfig(Element e) {
		
		ServerConfig serverConfig = new ServerConfig();
		
		String name = e.getChild("server-name").getValue();
		int port = Integer.valueOf(e.getChild("server-port").getValue());
		String description = e.getChild("description").getValue();
		
		serverConfig.setDescription(description);
		serverConfig.setName(name);
		serverConfig.setPort(port);
		
		
		initConfigLog.info("初始化服务器配置:【"+serverConfig.toString()+"】" );
		
		return serverConfig;
		
	}


}
