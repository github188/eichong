package com.netCore.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ResourceLeakDetector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.model.conf.ServerConfig;

public abstract class AbstractNettyServer implements INettyServer {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractNettyServer.class);
	
	public ServerConfig serverConfig;
	private ByteToMessageDecoder decoder; 
	private MessageToByteEncoder encoder;
	
	protected boolean isStop = false;
	
	public AbstractNettyServer(ServerConfig serverConfig, ByteToMessageDecoder decoder, MessageToByteEncoder encoder){
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
		
		ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
		
		
		//鍒涘缓netty5鏈嶅姟鍣�
		EventLoopGroup bossGroup   = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			
			bootstrap.channel(NioServerSocketChannel.class);
			
			
			//用内存池
			bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);//关键是这句
			
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.option(ChannelOption.SO_RCVBUF, 1024*256);
            bootstrap.option(ChannelOption.SO_SNDBUF, 1024*256);
            
			bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		    bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		    bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
		  
		    
			bootstrap.childHandler(new ServerChannelInitializer(this));
			
			// Start the server.
            ChannelFuture future = bootstrap.bind(serverConfig.getPort());//.sync();
            
            
            
            future.addListener(
    				new ChannelFutureListener(){
    					@Override
    					public void operationComplete(ChannelFuture future)
    							throws Exception {
    						if(future.isSuccess()){
    							logger.info("銆恵}銆憂ettyServer鍚姩鎴愬姛锛�...鐩戝惉绔彛锛氥�恵}銆�", new Object[]{serverConfig.getDescription(),serverConfig.getPort()});
    						}else{
    							logger.info("銆恵}銆憂ettyServer鍚姩澶辫触锛�...绔彛銆恵}銆戝彲鑳借鍗犵敤锛佽繘绋嬭嚜鍔ㄧ粨鏉燂紒", new Object[]{serverConfig.getDescription(),serverConfig.getPort()});
    							System.exit(0);
    						}
    						
    					}
    				}
    			);
            
    		//瑕佸惎鐢ㄤ笅闈㈢殑浠ｇ爜锛屽繀椤诲紓姝ユ墽琛宯etty鐨勫惎鍔ㄦ柟娉�
            // Wait until the server socket is closed.
            //future.channel().closeFuture().sync();
		}catch (Exception e){
			logger.error("netty5鏈嶅姟绔惎鍔ㄥ嚭鐜伴敊璇紒銆恵}銆�", new Object[]{ e.getMessage() });
			e.printStackTrace();
		}finally {
			//System.out.println("==========finally=========");
			//bossGroup.shutdownGracefully();
			//workerGroup.shutdownGracefully();
		}
		
	}
	
	@Override
	public void stop(){
		isStop = true;
	};
	
	@Override
	public ByteToMessageDecoder getDecoder() {
		return this.decoder;
	}

	@Override
	public MessageToByteEncoder getEncoder() {
		return this.encoder;
	}
	
}
