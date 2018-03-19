package com.netCore.model.conf;

import com.netCore.model.conf.ServerConfig;
import com.netCore.model.GameObject;



/**
 * 客户端配置
 * @author hao
 * Mar 18, 2014 11:58:33 AM
 */
public class ServerConfigs extends GameObject{
	
	//电桩连接此服务	
	private ServerConfig epGateServer;
	
	//电桩gate监控调试服务
	private ServerConfig epGateMonitorServer;
		
	//后台管理API连接此服务
	private ServerConfig manageServer;

	//数据中心服务
	private ServerConfig dataCentreServer;
	
	//数据中心监控服务
	private ServerConfig dataCentreMontiorServer;
	
	//手机充电长链接服务
	private ServerConfig phoneServer;
	
	//第三方充电服务
	private ServerConfig httpServer;
	
	//数据监控分析服务器
	private ServerConfig dataAnalyzeServer;

	

	public ServerConfig getDataAnalyzeServer() {
		return dataAnalyzeServer;
	}

	public void setDataAnalyzeServer(ServerConfig dataAnalyzeServer) {
		this.dataAnalyzeServer = dataAnalyzeServer;
	}

	public ServerConfig getHttpServer() {
		return httpServer;
	}

	public void setHttpServer(ServerConfig httpServer) {
		this.httpServer = httpServer;
	}

	public ServerConfig getManageServer() {
		return manageServer;
	}

	public void setManageServer(ServerConfig manageServer) {
		this.manageServer = manageServer;
	}

	public ServerConfig getEpGateServer() {
		return epGateServer;
	}

	public void setEpGateServer(ServerConfig epGateServer) {
		this.epGateServer = epGateServer;
	}

	public ServerConfig getEpGateMonitorServer() {
		return epGateMonitorServer;
	}

	public void setEpGateMonitorServer(ServerConfig epGateMonitorServer) {
		this.epGateMonitorServer = epGateMonitorServer;
	}

	

	public ServerConfig getDataCentreServer() {
		return dataCentreServer;
	}

	public void setDataCentreServer(ServerConfig dataCentreServer) {
		this.dataCentreServer = dataCentreServer;
	}

	public ServerConfig getDataCentreMontiorServer() {
		return dataCentreMontiorServer;
	}

	public void setDataCentreMontiorServer(ServerConfig dataCentreMontiorServer) {
		this.dataCentreMontiorServer = dataCentreMontiorServer;
	}

	public ServerConfig getPhoneServer() {
		return phoneServer;
	}
	public void setPhoneServer(ServerConfig phoneServer) {
		this.phoneServer = phoneServer;
	}
	
}
