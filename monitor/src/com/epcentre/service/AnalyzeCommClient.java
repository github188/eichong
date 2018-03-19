package com.epcentre.service;

import io.netty.channel.Channel;

public class AnalyzeCommClient {

	private Channel channel;//netty channel
	
	private long lastUseTime;

    protected long lastSendHeartTime;
    
    private int revNum;
    
	
	public AnalyzeCommClient()
	{
		channel =null;//netty channel
		lastSendHeartTime = 0;
		lastUseTime =0;
		revNum=0;

	}
	
	
	
	
	public int getRevNum() {
		return revNum;
	}


	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}


	public long getLastUseTime() {
		return lastUseTime;
	}
	public void setLastUseTime(long lastUseTime) {
		this.lastUseTime = lastUseTime;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public long getLastSendHeartTime() {
		return lastSendHeartTime;
	}

	public void setLastSendHeartTime(long lastSendHeartTime) {
		this.lastSendHeartTime = lastSendHeartTime;
	}
}
