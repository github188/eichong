package com.netCore.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import com.netCore.server.IServer;

/**
 * netty服务器接口
 * @author hao
 * Mar 17, 2014 5:19:35 PM
 */
public interface INettyServer extends IServer{
	
	/**获取解码器*/
	public ByteToMessageDecoder getDecoder(); 
	/**获取编码器*/
	public MessageToByteEncoder getEncoder();
	
	/**连接成功*/
	public void channelConnected(ChannelHandlerContext ctx);
	/**消息处理*/
	public void messageReceived(ChannelHandlerContext ctx, Object obj);
	/**连接关闭*/
	public void channelClosed(ChannelHandlerContext ctx);
	/**异常处理*/
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);
	
}
