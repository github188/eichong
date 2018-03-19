package com.netCore.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LoggingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	private static final Logger logger = LoggerFactory.getLogger(ClientChannelInitializer.class);
	
	INettyClient server;
	
	public ClientChannelInitializer(INettyClient server){
		this.server = server;
	}
	
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
    	
        ChannelPipeline pipeline = ch.pipeline();
        
		pipeline.addLast("decoder", server.getDecoder().getClass().newInstance());
		pipeline.addLast("encoder", server.getEncoder().getClass().newInstance());
		pipeline.addLast("handler", new ClientHandler(server));
		
	}

}
