package com.netCore.queue;

import io.netty.channel.Channel;

public class RepeatMessage {
	private int times;//重发次数
	private long lastSendTime;//最后发送时间
	private byte[] data;
	private String key;
	private int type;//协议指令
	
	private Channel ch;
	
	public RepeatMessage(Channel ch,String key,byte[] data)
	{
		this.times=0;//重发次数
		this.lastSendTime=0;//最后发送时间
		this.data=data;
		this.key= key;
		this.ch = ch;
	}
	
	/**
	 * 1:EpGate->Ep
	 * 2:EpGate->EpConsumer;
	 * 3:EpConsumer->EpGate;
	 */
	private int pos;//

	/**
	 * 定时任务调用已发送队列中的检查函数，队列遍历对象，对象调用该函数.
	 * @return 0:时间没到,忽略;1:重发,调用rePackage,send;2:移除
	 */
	public  int check()
	{
		long now = System.currentTimeMillis();
		if((now-lastSendTime)<10)
			return 0;
		else 
		{
			if(times<=5)
				return 1;
			else
				return 2;
			
		}
	}
	public void send()
	{
		//调用通讯链路，发送报文
		lastSendTime = System.currentTimeMillis();
		times+=1;
		
	}
	/**
	 * 
	 * @return 
	 */
	public  byte[] rePackage()
	{
		return null;
		
	}
	
	

	

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public long getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(long lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	

}
