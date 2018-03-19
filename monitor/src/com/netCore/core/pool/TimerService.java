package com.netCore.core.pool;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定时任务服务类，此类跟ScheduledExecutorService的区别是：
 * 此类由当前系统时间决定任务是否执行，而ScheduledExecutorService是由延迟时间决定任务是否执行。
 * @author hao
 * Mar 13, 2014 4:37:21 PM
 */
public class TimerService {
	
	private static List<TimeTask> taskList = new CopyOnWriteArrayList<TimeTask>();
	
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	
	static{
		startLoopThread();
	}
	
	/**
	 * 在给定延迟后启用的一次性操作
	 * @author hao
	 * Mar 12, 2014 4:05:42 PM
	 * @param command
	 * @param initialDelay
	 */
	public static void scheduleOneTime(Runnable command,long initialDelay){
		
		TimerService.schedule(command, initialDelay, Integer.MAX_VALUE, 1);
		
	}
	
	/**
	 * 在给定延迟后启用周期性操作
	 * @author hao
	 * Mar 12, 2014 4:05:38 PM
	 * @param command
	 * @param initialDelay
	 * @param period
	 */
	public static void schedule(Runnable command,long initialDelay,long period){
		
		TimerService.schedule(command, initialDelay, period, 0);
		
	}
	
	/**
	 * 在给定延迟后启用给定次数的周期性操作,runTimes如果小于等于0，就永久循环执行
	 * @author hao
	 * Mar 12, 2014 4:05:52 PM
	 * @param command
	 * @param initialDelay
	 * @param period
	 * @param runTimes
	 */
	public static void schedule(Runnable command,long initialDelay,long period,int runTimes){
		
		long executeTime = System.currentTimeMillis() + initialDelay ;
		
		TimeTask timeTask = new TimeTask();
		timeTask.setTask(command);
		timeTask.setExecuteTime(executeTime);
		timeTask.setPeriod(period);
		timeTask.setRunTimes(runTimes);
		
		taskList.add(timeTask);
		
	}
	
	/**
	 * 启动主任务线程
	 * @author hao
	 * Mar 12, 2014 4:16:05 PM
	 */
	private static void startLoopThread(){
		new Thread(){
			public void run(){
				loop();
			}
		}.start();
	}
	
	/**
	 * 间隔1毫秒扫描是否有可执行任务
	 * @author hao
	 * Mar 12, 2014 4:16:16 PM
	 */
	private static void loop(){
		
		while(true){
			try {
				loopTaskList();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 扫描任务set
	 * @author hao
	 * Mar 12, 2014 4:17:14 PM
	 */
	private static void loopTaskList(){
		
		long currentTime = System.currentTimeMillis();
		
		for(TimeTask timeTask : taskList){
			if(currentTime > timeTask.getExecuteTime()){
				//设置下次执行时间
				timeTask.setExecuteTime(timeTask.getExecuteTime() + timeTask.getPeriod());
				
				if(timeTask.getRunTimes()>0){
					//可执行次数减一
					timeTask.setRunTimes(timeTask.getRunTimes()-1);
					if(timeTask.getRunTimes()==0){
						//执行次数到，删除该任务
						taskList.remove(timeTask);
					}
				}
				
				//开始执行任务
				executorService.execute(timeTask.getTask());
				
			}
		}
		
	}
	
	/**
	 * 定时任务类
	 * @author hao
	 * Mar 13, 2014 4:33:41 PM
	 */
	private static class TimeTask {
		
		private Runnable task;//任务
		private long executeTime;//下次要执行的时间
		private long period;//周期
		private int runTimes;//剩余次数
		
		public Runnable getTask() {
			return task;
		}
		public void setTask(Runnable task) {
			this.task = task;
		}
		public long getExecuteTime() {
			return executeTime;
		}
		public void setExecuteTime(long executeTime) {
			this.executeTime = executeTime;
		}
		public long getPeriod() {
			return period;
		}
		public void setPeriod(long period) {
			this.period = period;
		}
		public int getRunTimes() {
			return runTimes;
		}
		public void setRunTimes(int runTimes) {
			this.runTimes = runTimes;
		}
		
	}

}
