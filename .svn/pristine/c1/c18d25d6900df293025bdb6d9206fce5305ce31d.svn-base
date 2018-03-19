package com.appCore.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.appCore.model.conf.ClientConfig;

public abstract class AbstractNettyClient implements INettyClient {
	
	private static final Logger logger = Logger.getLogger(AbstractNettyClient.class);
	
	protected Bootstrap bootstrap;
	
	protected boolean isStop = false;
	
	public ClientConfig serverConfig;
	private ByteToMessageDecoder decoder; 
	private MessageToByteEncoder encoder;
	
	public AbstractNettyClient(ClientConfig serverConfig, ByteToMessageDecoder decoder, MessageToByteEncoder encoder){
		this.serverConfig = serverConfig;
		this.decoder = decoder;
		this.encoder = encoder;
	}
	
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void start() {
		
		this.init();
		//启动netty5客户端
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			bootstrap = new Bootstrap();
			bootstrap.group(workerGroup);
			bootstrap.channel(NioSocketChannel.class);
			
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
		    bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		    bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			
			bootstrap.handler(new ClientChannelInitializer(this));
			
			//连接游戏服
			this.connection();
		}catch(Exception e){
			logger.error("netty5客户端启动出现错误！【" + e.getMessage() + "】");
			e.printStackTrace();
		}finally{
			//workerGroup.shutdownGracefully();
		}
		
		
	}


	@Override
	public void stop(){
		isStop = true;
	};
	
	
	/**
	 * 连接服务器
	 * @author haojian
	 * @date 2014-10-15 上午9:54:05 
	 * @return void
	 */
	private void connection() throws Exception{
		
		// Start the client.
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(serverConfig.getIp(), serverConfig.getPort()))
				;//.sync();
		
		//logger.info("连接成功！服务器【{}:{}】", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
		
		logger.info("WWWW开始连接！服务器【" + serverConfig.getIp() + ":" + serverConfig.getPort() + "】");
		
		future.addListener(
				new ChannelFutureListener(){
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						if(future.isSuccess()){
							logger.info("连接成功！服务器【" + serverConfig.getIp() + ":" + serverConfig.getPort() + "】");
						}else{
							logger.info("连接失败！服务器【" + serverConfig.getIp() + ":" + serverConfig.getPort() + "】");
						}
						
					}
				}
			);
		
        // Wait until the connection is closed.
		//future.channel().closeFuture().sync();
		
	}
	
	public void reconnection() throws Exception{
		
		logger.info("10秒后进行断线重连...");
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("bbbb开始断线重连...");
		try{
			this.connection();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override 
	public ByteToMessageDecoder getDecoder() {
		return this.decoder;
	}

	@Override  
	public MessageToByteEncoder getEncoder() {
		return this.encoder;
	}
	
	

}
