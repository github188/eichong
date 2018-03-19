package com.bluemobi.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 执行定时任务
 * 为了不跟游戏逻辑线程冲突，任务池(ScheduledExecutorService) 并不亲自执行任务，在执行时间到的时候，
 * 任务池会调用指定的线程池(ExecutorService)来执行任务。
 * @author heweiwen
 * 2014-11-26 下午4:06:16
 */
public class TimeTask implements Runnable{
	
	//实际要执行任务的线程池
	private ExecutorService threadPool;
	//实际要定时执行的任务
	private Runnable task;
	
	//重写timeTask方法
	public TimeTask(ExecutorService threadPool, Runnable task){
		this.threadPool = threadPool;
		this.task = task;
	}
	
	@Override
	public void run() {
		threadPool.submit(task);
	}
	
	public static void schedule(ExecutorService threadPool,Runnable task,long initialDelay,long period,TimeUnit unit){
		TimeTask timeTask = new TimeTask(threadPool,task);
		//定时任务池，执行时间到的时候，调用指定的线程池去执行任务
		//TaskPoolFactory.scheduleAtFixedRate("PUBLIC_TIME_TASK", timeTask, initialDelay, period, unit);
	}

}
