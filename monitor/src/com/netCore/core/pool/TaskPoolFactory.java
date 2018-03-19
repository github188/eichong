package com.netCore.core.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.netCore.core.threadFactory.ServerThreadFactory;

public class TaskPoolFactory {
	
	public static Map<String,ScheduledExecutorService> taskPoolMap = new ConcurrentHashMap<String,ScheduledExecutorService>();
	
	
	public static ScheduledExecutorService scheduleAtFixedRate(String taskPoolKey,Runnable task,long initialDelay,long period,TimeUnit unit){
		ScheduledExecutorService schedule = TaskPoolFactory.getSingleThreadScheduledExecutor(taskPoolKey);
		schedule.scheduleAtFixedRate(task, initialDelay, period, unit);
		return schedule;
	}
	public static ScheduledExecutorService scheduleAtFixedRate(String taskPoolKey,Runnable task,long initialDelay,TimeUnit unit){
		ScheduledExecutorService schedule = TaskPoolFactory.getSingleThreadScheduledExecutor(taskPoolKey);
		schedule.schedule(task, initialDelay, unit);
		return schedule;
	}
	
	
	/**
	 * 鑾峰彇涓�涓崟绾跨▼鐨勪换鍔＄嚎绋嬫睜
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
	 * 鎵ц瀹氭椂浠诲姟锛岄渶瑕佹寚瀹氬疄闄呮墽琛屼换鍔＄殑绾跨▼姹�
	 * 
	 * @author haojian
	 * @date 2014-7-28 涓嬪崍1:56:55 
	 * @param threadPool
	 * @param task
	 * @param initialDelay
	 * @param period
	 * @param unit
	 * @return void
	 */
	public static void scheduleByThreadPool(String threadPoolKey,Runnable task,long initialDelay,long period,TimeUnit unit){
		
		TimeTask timeTask = new TimeTask(threadPoolKey, task);
		//瀹氭椂浠诲姟姹狅紝鎵ц鏃堕棿鍒扮殑鏃跺�欙紝璋冪敤鎸囧畾鐨勭嚎绋嬫睜鍘绘墽琛屼换鍔�
		TaskPoolFactory.scheduleAtFixedRate("PUBLIC_TIME_TASK", timeTask, initialDelay, period, unit);
		
	}
	
	
	/**
	 * 鎵ц瀹氭椂浠诲姟
	 * 涓轰簡涓嶈窡搴旂敤閫昏緫绾跨▼鍐茬獊锛屼换鍔℃睜(ScheduledExecutorService) 骞朵笉浜茶嚜鎵ц浠诲姟锛屽湪鎵ц鏃堕棿鍒扮殑鏃跺�欙紝
	 * 浠诲姟姹犱細璋冪敤鎸囧畾鐨勭嚎绋嬫睜(ExecutorService)鏉ユ墽琛屼换鍔°��
	 * @author haojian
	 * @date 2014-7-28 涓嬪崍1:56:30 
	 *
	 */
	private static class TimeTask implements Runnable {
		
		//瀹為檯瑕佹墽琛屼换鍔＄殑绾跨▼姹�
		private String threadPoolKey;
		//瀹為檯瑕佸畾鏃舵墽琛岀殑浠诲姟
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
