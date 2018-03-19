package com.bluemobi.server;

import io.netty.channel.Channel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.appCore.core.pool.TaskPoolFactory;
import com.appCore.model.conf.ClientConfig;
import com.appCore.netty.coder.GateToGameDecoder;
import com.appCore.netty.coder.NettyEncoder;
import com.appCore.server.impl.AbstractGameServer;
import com.bluemobi.cache.GameTemplate;
import com.bluemobi.cache.GameWorld;
import com.bluemobi.config.GameConfig;
import com.bluemobi.constant.threadPool.T;
import com.bluemobi.core.task.ClearCacheTask;
import com.bluemobi.service.UserService;
import com.wanma.common.ApplicationContextUtils;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblGateservice;
import com.wanma.net.ApiGateConnectManager;
import com.wanma.service.GateService;

public class GameServer extends AbstractGameServer {
	
	private static final Logger logger = Logger.getLogger(GameServer.class);

	private static GameServer gameServer;
	
	/**key:gateId,value:channel*/
	public static ConcurrentHashMap<Integer,Channel> channelMap = new ConcurrentHashMap<Integer,Channel>();
	
	private static Object lock = new Object();
	
	private GameServer() throws Exception{
//		super();
		//创建netty客户端
		GateService gateService=(GateService)ApplicationContextUtils.getBean("gateServiceImpl");
    	List<TblGateservice> gateList=gateService.getGateList1();
    	
    	ApiGateConnectManager.connectAllGate(gateList);
		
		
	}
	
	/**
	 * 创建一个netty客户端
	 * @author haojian
	 * @date 2015-7-9 下午4:52:59 
	 * @param tblGateservice
	 * @return
	 * @return NettyClient
	 */
	public static void createNettyClient(TblGateservice tblGateservice) throws Exception{

		ByteToMessageDecoder decoder = new GateToGameDecoder();
		MessageToByteEncoder encoder = new NettyEncoder();
		
		ClientConfig serverConfig = new ClientConfig();
		serverConfig.setIp(tblGateservice.getGtseGateip());
		serverConfig.setPort(tblGateservice.getGtseGateport());
		serverConfig.setDescription(tblGateservice.getGtseGatename());
		serverConfig.setName(tblGateservice.getGtseGatename());
		
		GameToGateNettyClient nettyClient = new GameToGateNettyClient(serverConfig, decoder, encoder);
		nettyClient.setGateId(JudgeNullUtils.nvlStr(tblGateservice.getPkGateid()));
	
		nettyClient.createChannel();
	
	}
	
	
	
	/**
	 * 创建服务端服务器
	 * @author hao
	 * Mar 19, 2014 9:55:52 AM
	 * @return
	 * @throws Exception 
	 */
	public static GameServer getInstance() throws Exception{
		synchronized(lock){
			if(gameServer==null){
				gameServer = new GameServer();
			}
		}
		return gameServer;
	}
	
	@Override
	public void init() {
		super.init();
		new GameConfig();//初始化服务器基础配置
		//new GameParam();//初始化策划配置的服务参数
		new GameTemplate();//初始化策划配置的服务基础数据
		//new GameContext();//初始化spring,加载数据库全局数据
		new GameWorld();//初始化服务世界
		logger.info("初始化服务成功...");

	}
	
	@Override
	public void start() {
		super.start();
		
	}
	
	/*@Override
	public void startTimerServer() {
		
		super.startTimerServer();
		
		//1、启动每秒执行的定时任务
		//SecondTask secondTask = new SecondTask();
		//TaskPoolFactory.scheduleAtFixedRate(T.SECOND_TASK, secondTask, 10, 1, TimeUnit.SECONDS);
		
		//2、启动清理缓存定时任务
		ClearCacheTask clearCacheTask = new ClearCacheTask();
		TaskPoolFactory.scheduleAtFixedRate(T.CLEAR_CACHE_TASK, clearCacheTask, 20, 20, TimeUnit.MINUTES);
		
		
		//3、启动子关卡英雄排名定时任务
//		SubLevelService.startSubLevelRankingTask();
		
		
		logger.info("所有定时任务启动成功!");
		
	}*/

	/*@Override
	public void stop() {
		
		//1、停止 netty服务器、停止 netty客户端、关闭线程池、关闭任务池
		super.stop();
		
		//2、让用户下线，并保存相应的数据
		this.closeAllUserConnection();
		
	}*/
	
	/**
	 * 关闭所有用户的连接，并将用户T下线
	 * @author haojian
	 * @date 2014-8-6 下午2:23:15 
	 * @return void
	 */
	/*private void closeAllUserConnection(){
		
		for(Channel channel:GameWorld.getPlayerMapKeyChannel().keySet()){
			try{
				//1)、发送通知
				////SystemSender.sendSystemMsg(channel, "服务器即将关闭，请下线！");
				
				//2)、用户离线
				UserService.offLine(channel);
				
			}catch(Exception e){
				logger.error("关闭服务器的时候出现异常...");
				e.printStackTrace();
			}
		}
		
	}*/
	
	
	
	
}
