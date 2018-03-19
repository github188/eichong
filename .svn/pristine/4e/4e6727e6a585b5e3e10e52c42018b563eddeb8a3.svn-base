package com.netCore.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerHandler extends SimpleChannelInboundHandler<Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	
	INettyServer server;
	
	public ServerHandler(INettyServer server){
		
		this.server = server;
	}
	
	//连接成功
	@Override
	public void channelActive(ChannelHandlerContext ctx)throws Exception {
		super.channelActive(ctx);
		server.channelConnected(ctx);
		
	}

	//收到消息
	@Override
	public void messageReceived(ChannelHandlerContext ctx, Object obj)
			throws Exception {   
		server.messageReceived(ctx, obj);
		
	}
	
	//连接断开
	@Override
	public void channelInactive(ChannelHandlerContext ctx)
			throws Exception {	
		super.channelInactive(ctx);
		server.channelClosed(ctx);
		
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
		server.exceptionCaught(ctx,cause);
		
		
	}
	
}
