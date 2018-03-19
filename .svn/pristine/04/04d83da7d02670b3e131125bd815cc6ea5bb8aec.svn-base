package com.netCore.server.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.conf.CoreConfig;
import com.netCore.core.pool.TaskPoolFactory;
import com.netCore.core.pool.ThreadPoolFactory;
import com.netCore.core.task.DumpMemoryTask;
import com.netCore.core.thread.GameServerShutdownHook;
//import com.gameCore.core.thread.SafeBox;
import com.netCore.core.thread.StopServerThread;
//import com.gameCore.netty.client.INettyClient;
import com.netCore.netty.server.INettyServer;
import com.netCore.server.IGameServer;
import com.netCore.util.TimeUtil;

/**
 * 应用服务器实现类
 * @author hao
 * Mar 17, 2014 5:19:46 PM
 */
public abstract class AbstractGameServer implements IGameServer{
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractGameServer.class);
	
	private static final Logger serverStartAndStopLog = LoggerFactory.getLogger("ServerStartAndStopLog");
	
	/**netty服务器list*/
	public List<INettyServer> nettyServerList = new ArrayList<INettyServer>();
	
	public AbstractGameServer(){
		this.init();
	}
	
	
	@Override
	public void init() {
		this.initLog4j();//初始化log4j
		new CoreConfig();//初始化核心配置
		
	}
	
	@Override
	public void start() {
		
		long begin = System.currentTimeMillis();
		serverStartAndStopLog.info("开始启动服务器！");
		
		this.startStopServer();//启动停止服务
		this.startTimerServer();//启动定时服务
		this.addShutdownHook();//启动钩子
		
		//1、启动netty服务端
		for(INettyServer nettyServer : nettyServerList){
			nettyServer.start();
			//new Thread(nettyServer).start();
		}
		
		long end = System.currentTimeMillis();
		serverStartAndStopLog.info("服务器启动成功！启动耗时：【{}】秒", new Object[]{(end-begin)/1000d} );	
		
	}
	
	@Override
	public void stop(){
		
		serverStartAndStopLog.info("开始关闭服务器！");
		long begin = System.currentTimeMillis();
		
		//1、关闭netty服务端
		for(INettyServer nettyServer : nettyServerList){
			nettyServer.stop();
		}
		
		//3、关闭任务池
		this.stopTaskPools(TaskPoolFactory.taskPoolMap);
				
		//4、关闭线程池
		this.stopThreadPools(ThreadPoolFactory.threadPoolMap);
		
		long end = System.currentTimeMillis();
		serverStartAndStopLog.info("服务器关闭成功！关闭耗时：【{}】秒", new Object[]{(end-begin)/1000d} );	
		
	}
	
	@Override
	public void initLog4j() {
		String time = "【"+TimeUtil.getFormatTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")+"】";
		System.out.println(time + "开始初始化log4j..." );
		DOMConfigurator.configure("conf/log4j.xml");
		logger.info("log4j初始化成功...");
		
	}
	
	@Override
	public void startStopServer() {
		
		StopServerThread thread = new StopServerThread(CoreConfig.gameConfig.getShutdownPort());
		thread.start();
		logger.info("打开服务器关闭端口成功...");
		
	}
	
	@Override
	public void addShutdownHook() {
		
		GameServerShutdownHook hook = new GameServerShutdownHook(this);
		Runtime.getRuntime().addShutdownHook(hook);
		logger.info("添加钩子成功...");
		
	}


	@Override
	public void startTimerServer() {
		
		//1、启动内存检测定时任务
		DumpMemoryTask dumpMemoryTask = new DumpMemoryTask();
		TaskPoolFactory.scheduleAtFixedRate("DUMP_MEMORY_TASK", dumpMemoryTask, 60, 300, TimeUnit.SECONDS);
		
	}
	
	
	/**
	 * 关闭线程池工厂中的所有线程池，并等待池中的任务都执行完毕
	 * @author haojian
	 * @date 2014-7-24 上午10:07:07 
	 * @param threadPoolMap
	 * @return void
	 */
	private void stopThreadPools(Map<String,ExecutorService> threadPoolMap){
		
		for(String key : threadPoolMap.keySet()){		
			ExecutorService executorService = threadPoolMap.get(key);
			logger.info("开始关闭线程池【{}】...", new Object[]{ key });
								
			try {
				executorService.shutdown();
				while(true){
					logger.info("等待线程池【{}】中的任务都执行完毕...", new Object[]{ key });
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(executorService.isTerminated()){
						logger.info("线程池【{}】已关闭，池中的任务都已执行完毕！", new Object[]{ key });
						break;
					}
				}
			} catch (Exception e) {
				logger.error("停服时候，关闭线程池工厂中的线程池【{}】的时候出错！error=【】", new Object[]{ key, e.toString() });
				e.printStackTrace();
			}			
		}	
		logger.info("线程池工厂【ThreadPoolFactory】中的所有任务池都已关闭！");
				
	}
	
	/**
	 * 关闭任务池工厂中的所有任务池，中断循环执行的任务
	 * @author haojian
	 * @date 2014-7-24 上午10:07:15 
	 * @param taskPoolMap
	 * @return void
	 */
	private void stopTaskPools(Map<String,ScheduledExecutorService> taskPoolMap){

		//2、关闭任务池工厂中的所有任务池，并等待池中的任务都执行完毕
		for(String key : taskPoolMap.keySet()){		
			ScheduledExecutorService executorService = taskPoolMap.get(key);
			logger.info("开始关闭任务池【{}】...", new Object[]{ key });
								
			try {
				//立即关闭定时任务
				executorService.shutdownNow();
				while(true){
					logger.info("等待任务池【{}】中的任务都执行完毕...", new Object[]{ key });
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(executorService.isTerminated()){
						logger.info("任务池【{}】已关闭，池中的任务都已执行完毕！", new Object[]{ key });
						break;
					}
				}
			} catch (Exception e) {
				logger.error("停服时候，关闭任务池工厂中的任务池【{}】的时候出错！error=【】", new Object[]{ key, e.toString() });
				e.printStackTrace();
			}			
		}	
		logger.info("任务池工厂【TaskPoolFactory】中的所有任务池都已关闭！");
				
	}
	
	

}
