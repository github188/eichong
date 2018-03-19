package com.netCore.service;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netCore.conf.CoreConfig;
import com.netCore.model.conf.ClientConfig;
import com.netCore.model.conf.ClientConfigs;



public class ClientConfigService {
	
	private static final Logger initConfigLog = LoggerFactory.getLogger("InitConfigLog");
	
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
		if(root!=null)
		{
			Element analyzeServerElement = root.getChild("analyze-server");
			
			
			if(analyzeServerElement!=null){
				
				ClientConfig AnalyzeServerConfig = ClientConfigService.initClientConfig(analyzeServerElement);
				clientConfig.getGateServerConfigs().add(AnalyzeServerConfig);
				clientConfig.setAanalyzeConfig(AnalyzeServerConfig);
				initConfigLog.info("初始化【gateClientConfig】配置...配置信息【{}】", new Object[]{AnalyzeServerConfig.toString()} );
				
			}
		}
		
		initConfigLog.info("初始化客户端配置结束...配置信息【{}】", new Object[]{clientConfig.toString()} );
		
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
		System.out.println(description);
		String name = e.getChild("server-name").getValue();
		String ip = e.getChild("server-ip").getValue();
		int port = Integer.valueOf(e.getChild("server-port").getValue());
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setDescription(description);
		clientConfig.setName(name);
		clientConfig.setIp(ip);
		clientConfig.setPort(port);
		
		return clientConfig;
	}
	
}

