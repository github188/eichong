package com.netCore.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 閫氫俊淇￠亾宸ュ巶绫�
 * @author haojian
 * May 16, 2012 11:29:48 PM
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	INettyServer server;
	
	public ServerChannelInitializer(INettyServer server){
		
		this.server = server;
	}
	
	@Override
	public boolean isSharable() {
		return super.isSharable();
	}

	@Override
    public void initChannel(SocketChannel ch) throws Exception {
    	
        ChannelPipeline pipeline = ch.pipeline();
        
		pipeline.addLast("decoder", server.getDecoder().getClass().newInstance());
		pipeline.addLast("encoder", server.getEncoder().getClass().newInstance());
		pipeline.addLast("handler", new ServerHandler(server));
		
		
		
		
		
	}

}
