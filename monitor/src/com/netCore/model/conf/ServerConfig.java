package com.netCore.model.conf;

import com.netCore.model.GameObject;

/**
 * 服务端配置
 * @author hao
 * Mar 18, 2014 11:58:25 AM
 */
public class ServerConfig extends GameObject{
	
	//服务器名称
	private String name;
	//服务器描述
	private String description;
	//服务器端口
	private int port;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	

}
