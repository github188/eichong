package com.appCore.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.appCore.conf.CoreConfig;
import com.appCore.model.conf.ClientConfig;
import com.appCore.model.conf.ClientConfigs;

public class ClientConfigService {
	
	private static final Logger initConfigLog = Logger.getLogger("InitConfigLog");
	
	/**
	 * 初始化客户端配置
	 * @author hao
	 * Mar 18, 2014 4:51:06 PM
	 * @return
	 */
	public static ClientConfigs initClientConfigs() {
		
		initConfigLog.info("开始初始化客户端配置...");
		ClientConfigs clientConfig = new ClientConfigs();
		
		Element root = CoreConfig.getRootElement("client-config.xml");

		Element gateServers = root.getChild("gate-servers");
		Element publicServer = root.getChild("public-server");
		Element worldServer = root.getChild("world-server");
		
		if(gateServers!=null){
			List<Element> gateServerList = gateServers.getChildren("gate-server");
			initConfigLog.info("gateServerList数量=【" + gateServerList.size() + "】");
			for(Element gateServer : gateServerList){
				ClientConfig gateServerConfig = ClientConfigService.initClientConfig(gateServer);
				clientConfig.getGateServerConfigs().add(gateServerConfig);
				initConfigLog.info("初始化【gateServerConfig】配置...配置信息【 " + gateServerConfig.toString() + "】");
			}
		}
		
		if(publicServer!=null){
			ClientConfig publicServerConfig = ClientConfigService.initClientConfig(publicServer);
			clientConfig.setPublicServerConfig(publicServerConfig);
			initConfigLog.info("初始化【publicServerConfig】配置...配置信息【" + publicServerConfig.toString() + "】");
		}
		
		if(worldServer!=null){
			ClientConfig worldServerConfig = ClientConfigService.initClientConfig(worldServer);
			clientConfig.setWorldServerConfig(worldServerConfig);
			initConfigLog.info("初始化【worldServerConfig】配置...配置信息【" + worldServerConfig.toString() + "】");
		}
		
		
		initConfigLog.info("初始化客户端配置结束...配置信息【" + clientConfig.toString() + "】");
		
		return clientConfig;
	}
	
	/**
	 * 读取服务器配置，包括serverId，ip，端口
	 * @author haojian
	 * @date 2014-7-21 下午2:57:49 
	 * @param serverElement
	 * @return
	 * @return ServerConfig
	 */
	private static ClientConfig initClientConfig(Element e){
		
		String description = e.getChild("description").getValue();
		//System.out.println(description);
		String name = e.getChild("server-name").getValue();
		String ip = e.getChild("server-ip").getValue();
		int port = Integer.valueOf(e.getChild("server-port").getValue());
		
		ClientConfig serverConfig = new ClientConfig();
		serverConfig.setDescription(description);
		serverConfig.setName(name);
		serverConfig.setIp(ip);
		serverConfig.setPort(port);
		
		return serverConfig;
	}
	

}
