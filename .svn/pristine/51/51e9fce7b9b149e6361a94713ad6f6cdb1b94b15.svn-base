package com.netCore.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.model.conf.ClientConfig;

public abstract class AbstractNettyClient implements INettyClient {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractNettyClient.class);
	
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
		//鍚姩netty5瀹㈡埛绔�
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			bootstrap = new Bootstrap();
			bootstrap.group(workerGroup);
			bootstrap.channel(NioSocketChannel.class);
			
			bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
		    bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		    bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			
			bootstrap.handler(new ClientChannelInitializer(this));
			
			//杩炴帴娓告垙鏈�
			this.connection();
		}catch(Exception e){
			logger.error("netty5瀹㈡埛绔惎鍔ㄥ嚭鐜伴敊璇紒銆恵}銆�", new Object[]{ e.getMessage() });
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
	 * 杩炴帴鏈嶅姟鍣�
	 * @author haojian
	 * @date 2014-10-15 涓婂崍9:54:05 
	 * @return void
	 */
	private void connection() throws Exception{
		
		// Start the client.
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(serverConfig.getIp(), serverConfig.getPort()))
				;//.sync();
		
		//logger.info("杩炴帴鎴愬姛锛佹湇鍔″櫒銆恵}:{}銆�", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
		
		logger.info("寮�濮嬭繛鎺ユ湇鍔″櫒銆恵}:{}銆�", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
		
		future.addListener(
				new ChannelFutureListener(){
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						if(future.isSuccess()){
							logger.info("杩炴帴鎴愬姛锛佹湇鍔″櫒銆恵}:{}銆�", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
						}else{
							logger.info("杩炴帴澶辫触锛佹湇鍔″櫒銆恵}:{}銆�", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
						}
						
					}
				}
			);
		
        // Wait until the connection is closed.
		//future.channel().closeFuture().sync();
	}
	
	public void reconnection() throws Exception{
		
		logger.info("10绉掑悗杩涜鏂嚎閲嶈繛...");
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("寮�濮嬫柇绾块噸杩�...");
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
