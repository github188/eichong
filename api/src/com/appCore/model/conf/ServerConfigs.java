package com.appCore.model.conf;

import com.appCore.model.GameObject;



/**
 * 客户端配置
 * @author hao
 * Mar 18, 2014 11:58:33 AM
 */
public class ServerConfigs extends GameObject{
	
	//单进程游戏服务器使用此配置，外网客户端直接连接此端口
	private ServerConfig gameServer;
	//外网客户端连接此网关
	private ServerConfig gateServer;
	//内网游戏服连接此网关
	private ServerConfig innerGateServer;
	//内网App服连接此网关
	private ServerConfig appGateServer;
	//外网客户端连接此公共服
	private ServerConfig publicServer;
	//内网游戏服连接此公共服
	private ServerConfig innerPublicServer;
	//内网游戏服连接此世界服
	private ServerConfig innerWorldServer;
	
	public ServerConfig getGameServer() {
		return gameServer;
	}
	public void setGameServer(ServerConfig gameServer) {
		this.gameServer = gameServer;
	}
	public ServerConfig getGateServer() {
		return gateServer;
	}
	public void setGateServer(ServerConfig gateServer) {
		this.gateServer = gateServer;
	}
	public ServerConfig getInnerGateServer() {
		return innerGateServer;
	}
	public void setInnerGateServer(ServerConfig innerGateServer) {
		this.innerGateServer = innerGateServer;
	}
	public ServerConfig getPublicServer() {
		return publicServer;
	}
	public void setPublicServer(ServerConfig publicServer) {
		this.publicServer = publicServer;
	}
	public ServerConfig getInnerPublicServer() {
		return innerPublicServer;
	}
	public void setInnerPublicServer(ServerConfig innerPublicServer) {
		this.innerPublicServer = innerPublicServer;
	}
	public ServerConfig getInnerWorldServer() {
		return innerWorldServer;
	}
	public void setInnerWorldServer(ServerConfig innerWorldServer) {
		this.innerWorldServer = innerWorldServer;
	}
	public ServerConfig getAppGateServer() {
		return appGateServer;
	}
	public void setAppGateServer(ServerConfig appGateServer) {
		this.appGateServer = appGateServer;
	}
	
	
}
