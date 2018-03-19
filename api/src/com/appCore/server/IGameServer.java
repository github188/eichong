package com.appCore.server;





/**
 * 服务器接口
 * @author hao
 * Mar 17, 2014 6:03:27 PM
 */
public interface IGameServer extends IServer{
	
	/**初始化Log4j*/
	public void initLog4j();
	/**启动停止服务*/
	public void startStopServer();
	/**注册钩子*/
	public void addShutdownHook();
	
	/**启动定时任务*/
	public void startTimerServer();
	
	
	

}
