package com.appCore.model.conf;

import com.appCore.model.GameObject;

public class GameConfig extends GameObject{
	
	//服务器ID
	private int id;
	//服务器名称
	private String name;
	//服务器描述
	private String description;
	//平台id，两位数字，范围 10-99
	private int pfId;
	//区号，范围 1-999
	private int zoneId;
	//服务器关闭端口
	private int shutdownPort;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getPfId() {
		return pfId;
	}
	public void setPfId(int pfId) {
		this.pfId = pfId;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public int getShutdownPort() {
		return shutdownPort;
	}
	public void setShutdownPort(int shutdownPort) {
		this.shutdownPort = shutdownPort;
	}

	
}
