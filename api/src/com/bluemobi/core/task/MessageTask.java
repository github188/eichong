package com.bluemobi.core.task;

import io.netty.channel.Channel;

import com.appCore.netty.message.GateToGameMessage;
import com.bluemobi.GateMessageHandler;

public class MessageTask implements Runnable{
	private Channel channel;
	private GateToGameMessage message;
	
	
	public MessageTask(Channel channel,GateToGameMessage message){
		this.channel = channel;
		this.message = message;
	}
	
	@Override
	public void run() {
		
		GateMessageHandler.processMessage(channel, message);
		
	}
}
