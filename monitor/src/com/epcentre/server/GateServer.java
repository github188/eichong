package com.epcentre.server;


import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.conf.CoreConfig;

import com.netCore.model.conf.ServerConfig;
import com.netCore.model.conf.ServerConfigs;
import com.netCore.netty.server.AbstractNettyServer;
import com.netCore.server.impl.AbstractGameServer;




public class GateServer extends AbstractGameServer{
	private static final Logger logger = LoggerFactory.getLogger(GateServer.class);
	
	private static GateServer gameServer;
	
	private static Object lock = new Object();
	
	/**EpGate连接的nettyServer*/
	public static AbstractNettyServer EpGateNettyServer;
	
	public GateServer(){
		//super();
		//创建netty服务器
		ServerConfigs serverConfigs = CoreConfig.serverConfigs;
		if (serverConfigs != null) {
			
			//gate服务器
			if (serverConfigs.getDataAnalyzeServer() !=null) {
				ServerConfig serverConfig = serverConfigs.getDataAnalyzeServer();
				
				ByteToMessageDecoder decoder = new AnalyzeNettyDecoder();
				MessageToByteEncoder encoder = new AnalyzeNettyEncoder();
				
				AnalyzeNettyServer nettyServer = new AnalyzeNettyServer(serverConfig, decoder, encoder);
				nettyServerList.add(nettyServer);
				EpGateNettyServer = nettyServer;
			}else {
				String errMsg = "【监控服务器】缺少【外部】访问配置...服务器强行退出！";
				logger.error(errMsg);
				throw new RuntimeException(errMsg);
			}
		}
	}
	
	/**
	 * 创建服务端服务器
	 * @author HeWeiwen
	 * 2014-11-28
	 * @return
	 */
	public static GateServer getInstance(){
		synchronized(lock){
			if(gameServer==null){
				gameServer = new GateServer();
			}
		}
		return gameServer;
	}
	
	public void init(){
		super.init();
		logger.info("初始化服务成功...");
	}
	
	@Override
	public void start() {
		super.start();
		
	}

	@Override
	public void stop() {
		
		//1、停止 netty服务器、停止 netty客户端、关闭线程池、关闭任务池
		super.stop();
		
	}
	
}
