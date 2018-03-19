package com.netCore.service;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.service.ServerConfigService;
import com.netCore.conf.CoreConfig;
import com.netCore.model.conf.ServerConfig;
import com.netCore.model.conf.ServerConfigs;

public class ServerConfigService {
	
	private static final Logger initConfigLog = LoggerFactory.getLogger("InitConfigLog");
	
	public static ServerConfigs initServerConfigs() {
		
initConfigLog.info("开始初始化服务器配置...");
		
		ServerConfigs serverConfigs = new ServerConfigs();
		
		Element root =  CoreConfig.getRootElement("server-config.xml");
		
		//外网客户端连接此网关
		Element gateServerElement = root.getChild("gate-server");
			
		if(gateServerElement!=null){
			ServerConfig epGateServer = ServerConfigService.initServerConfig(gateServerElement);
			serverConfigs.setEpGateServer(epGateServer);
		}
		Element epGateMonitorServerElement = root.getChild("gate-monitor-server");
		if(epGateMonitorServerElement!=null){
			ServerConfig epGateMonitorServer = ServerConfigService.initServerConfig(epGateMonitorServerElement);
			serverConfigs.setEpGateMonitorServer(epGateMonitorServer);
		}
		//manage server-> ep gate
		Element manageElement = root.getChild("manage-server");
		if(manageElement!=null){
			ServerConfig manageServer = ServerConfigService.initServerConfig(manageElement);
			serverConfigs.setManageServer(manageServer);
		}
		
		//epgate-> data server
		Element dataCentreServerElement = root.getChild("data-centre-server");
		if(dataCentreServerElement!=null){
			ServerConfig dataCentreServer = ServerConfigService.initServerConfig(dataCentreServerElement);
			serverConfigs.setDataCentreServer(dataCentreServer);
		}
		
		//dataserver montior
		Element dataCentreMontiorServerElement = root.getChild("data-centre-montior-server");
		if(dataCentreMontiorServerElement!=null){
			ServerConfig dataCentreMontiorServer = ServerConfigService.initServerConfig(dataCentreServerElement);
			serverConfigs.setDataCentreMontiorServer(dataCentreMontiorServer);
		}
		
		Element phoneServerElement = root.getChild("phone-server");
		if(phoneServerElement!=null){
			ServerConfig phoneServer = ServerConfigService.initServerConfig(phoneServerElement);
			serverConfigs.setPhoneServer(phoneServer);
		}
		
		Element httpServerElement = root.getChild("http-server");
		if(httpServerElement!=null){
			ServerConfig httpServer = ServerConfigService.initServerConfig(httpServerElement);
			serverConfigs.setHttpServer(httpServer);
		}
		
		Element dataAnalyzeServerElement = root.getChild("data-Analyze-server");
		if(dataAnalyzeServerElement!=null){
			ServerConfig dataAnalyzeServer = ServerConfigService.initServerConfig(dataAnalyzeServerElement);
			serverConfigs.setDataAnalyzeServer(dataAnalyzeServer);
		}
		
		initConfigLog.info("初始化服务器配置结束...配置信息:【{}】", new Object[]{ serverConfigs.toString() });
		
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
