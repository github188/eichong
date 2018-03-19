package com.netCore.model.conf;

import com.netCore.model.GameObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 客户端配置
 * @author hao
 * Mar 18, 2014 11:58:33 AM
 */
public class ClientConfigs extends GameObject{
	
	//网关服务器配置
	private List<ClientConfig> gateServerConfigs = new ArrayList<ClientConfig>();

	//数据中心服务器配置
	private ClientConfig AnalyzeConfig;
	

	public ClientConfig getAnalyzeConfig() {
		return AnalyzeConfig;
	}
	public void setAanalyzeConfig(ClientConfig analyzeConfig) {
		AnalyzeConfig = analyzeConfig;
	}
	
	public List<ClientConfig> getGateServerConfigs() {
		return gateServerConfigs;
	}
	public void setGateServerConfigs(List<ClientConfig> gateServerConfigs) {
		this.gateServerConfigs = gateServerConfigs;
	}
	
	
	
	
	
	
}

