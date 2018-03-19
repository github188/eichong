package com.appCore.core.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.appCore.core.threadFactory.ServerThreadFactory;

public class TaskPoolFactory {
	
	public static Map<String,ScheduledExecutorService> taskPoolMap = new ConcurrentHashMap<String,ScheduledExecutorService>();
	
	/**
	 * 在给定的延时后，周期性执行某个任务，线程池中是单线程
	 * @author haojian
	 * Mar 5, 2013 3:15:33 PM
	 * @param taskPoolKey
	 * @param task
	 * @return
	 */
	public static ScheduledExecutorService scheduleAtFixedRate(String taskPoolKey,Runnable task,long initialDelay,long period,TimeUnit unit){
		ScheduledExecutorService schedule = TaskPoolFactory.getSingleThreadScheduledExecutor(taskPoolKey);
		schedule.scheduleAtFixedRate(task, initialDelay, period, unit);
		return schedule;
	}
	
	
	/**
	 * 获取一个单线程的任务线程池
	 * @author haojian
	 * Mar 5, 2013 3:16:11 PM
	 * @param taskPoolKey
	 * @return
	 */
	private static ScheduledExecutorService getSingleThreadScheduledExecutor(String taskPoolKey){
		synchronized(taskPoolMap){
			ScheduledExecutorService schedule = taskPoolMap.get(taskPoolKey);
			if(schedule==null){
				schedule = Executors.newSingleThreadScheduledExecutor(new ServerThreadFactory(taskPoolKey));
				taskPoolMap.put(taskPoolKey, schedule);
			}
			return schedule;
		}
	}
	
	
	/**
	 * 执行定时任务，需要指定实际执行任务的线程池
	 * 
	 * @author haojian
	 * @date 2014-7-28 下午1:56:55 
	 * @param threadPool
	 * @param task
	 * @param initialDelay
	 * @param period
	 * @param unit
	 * @return void
	 */
	public static void scheduleByThreadPool(String threadPoolKey,Runnable task,long initialDelay,long period,TimeUnit unit){
		
		TimeTask timeTask = new TimeTask(threadPoolKey, task);
		//定时任务池，执行时间到的时候，调用指定的线程池去执行任务
		TaskPoolFactory.scheduleAtFixedRate("PUBLIC_TIME_TASK", timeTask, initialDelay, period, unit);
		
	}
	
	
	/**
	 * 执行定时任务
	 * 为了不跟游戏逻辑线程冲突，任务池(ScheduledExecutorService) 并不亲自执行任务，在执行时间到的时候，
	 * 任务池会调用指定的线程池(ExecutorService)来执行任务。
	 * @author haojian
	 * @date 2014-7-28 下午1:56:30 
	 *
	 */
	private static class TimeTask implements Runnable {
		
		//实际要执行任务的线程池
		private String threadPoolKey;
		//实际要定时执行的任务
		private Runnable task;
			
		public TimeTask(String threadPoolKey, Runnable task){
			this.threadPoolKey = threadPoolKey;
			this.task = task;
		}

		@Override
		public void run() {
			
			ThreadPoolFactory.submit(threadPoolKey, task);

		}
		
	}


}
