package com.epcentre.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 发送消息必须通过此类
 * @author heweiwen
 * 2014-11-28 上午11:32:54
 */
public class AnalyzeMessageSender {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalyzeMessageSender.class);
	
	public static ChannelFuture  sendMessage(Channel channel,Object object) {
		if(channel == null)
			return null;
		 
		if (channel == null || !channel.isWritable()) {
			return null;
		}

		ChannelFuture future = channel.writeAndFlush(object);
		return future;
	}
	
	
	

	
}
