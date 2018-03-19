package com.wanma.net;

import io.netty.channel.Channel;

public class GateConnectObject {
	private String gateId;
	private String gateIp;//
	private String localIp;//本地IP
	private int connTimes;//连接次数
	
	private int state;
	public GateConnectObject()
	{
		connTimes=0;
		gateId="";
		gateIp="";
		localIp="";
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getGateIp() {
		return gateIp;
	}
	public void setGateIp(String gateIp) {
		this.gateIp = gateIp;
	}
	public String getLocalIp() {
		return localIp;
	}
	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}
	
	
	public int getConnTimes() {
		return connTimes;
	}

	public void addConnectTimes() {
		this.connTimes = this.connTimes+1;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	

}
