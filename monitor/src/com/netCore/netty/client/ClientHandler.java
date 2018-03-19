package com.netCore.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	INettyClient server;

	public ClientHandler(INettyClient server) {
		this.server = server;
	}

	//连接成功
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		server.channelConnected(ctx);
		System.out.println("netty5 channelActive...");
	}

	// 收到消息  //channelRead messageReceived
	@Override
	public void messageReceived(ChannelHandlerContext ctx, Object obj)
			throws Exception {
		server.messageReceived(ctx, obj);
		System.out.println("netty5 receive...");


	}

	//远程 连接断开
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
		server.channelClosed(ctx);
		System.out.println("netty5 channelInactive...");
	}
	
	//连接断开
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		super.close(ctx, promise);
		server.channelClosed(ctx);
		System.out.println("netty5 close...");
	}

	// 出现异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
		server.exceptionCaught(ctx,cause);
		System.out.println("netty5 exception....");
	}

	
}
