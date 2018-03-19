package com.netCore.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.server.IGameServer;

/**
 * 服务器关闭钩子
 * @author haojian
 * Mar 27, 2013 4:26:51 PM
 */
public class GameServerShutdownHook extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(GameServerShutdownHook.class);
	
	private IGameServer server;
	public GameServerShutdownHook(IGameServer server){
		this.server = server;
	}
	
	@Override
	public void run(){
		
		server.stop();
		
	}



}
