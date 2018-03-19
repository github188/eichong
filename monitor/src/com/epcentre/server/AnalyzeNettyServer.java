package com.epcentre.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.epcentre.service.AnalyzeService;
import com.epcentre.util.IPUtil;
import com.netCore.model.conf.ServerConfig;
import com.netCore.netty.server.AbstractNettyServer;

public class AnalyzeNettyServer extends AbstractNettyServer{

	private static final Logger logger = LoggerFactory.getLogger(AnalyzeNettyServer.class);
	
	public AnalyzeNettyServer(ServerConfig serverConfig,ByteToMessageDecoder decoder, MessageToByteEncoder encoder) {
		super(serverConfig, decoder, encoder);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx) {
		
		//电桩断线
		Channel channel = ctx.channel();
		
		logger.info("server close..."+channel);
		channel.close();
		AnalyzeService.offLine(channel);
		
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx) {
		//logger.info("server conn...");	
		AnalyzeService.addCommClient(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.info("server exception...");
		
		Channel channel = ctx.channel();
		channel.close();
		AnalyzeService.offLine(channel);
		
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, Object obj) {
		Channel channel = ctx.channel();
		String name = IPUtil.getNameByChannel(channel);
		//logger.info("server receive...");
		
		AnalyzeMessage message = (AnalyzeMessage)obj;
		
		if(isStop){
			logger.error("服务器已经停止，不再处理消息！忽略来自【{}】的消息:【{}】", new Object[]{ name, message });
			return;
		}
		
		AnalyzeMessageHandler.handleMessage(channel, message);
		
	}
	
	@Override
	public void stop() {
		super.stop();
		logger.info("server stop...");
		
	}

}
