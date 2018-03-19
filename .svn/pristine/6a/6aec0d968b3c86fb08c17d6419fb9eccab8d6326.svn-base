package com.appCore.core.thread;


import org.apache.log4j.Logger;

import com.appCore.server.IGameServer;

/**
 * 服务器关闭钩子
 * @author haojian
 * Mar 27, 2013 4:26:51 PM
 */
public class GameServerShutdownHook extends Thread {
	
	private static final Logger logger = Logger.getLogger(GameServerShutdownHook.class);
	
	private IGameServer server;
	public GameServerShutdownHook(IGameServer server){
		this.server = server;
	}
	
	@Override
	public void run(){
		
		server.stop();
		
	}



}
