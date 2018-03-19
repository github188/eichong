package com.netCore.core.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netCore.core.threadFactory.ServerThreadFactory;


/**
 * 线程池工厂类
 * @author haojian
 * Oct 18, 2012 3:31:07 PM
 */
public class ThreadPoolFactory {
	

	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolFactory.class);
	
	public static Map<String,ExecutorService> threadPoolMap = new ConcurrentHashMap<String,ExecutorService>();
	
	/**
	 * 提交任务到 单线程的线程池 中执行
	 * @author haojian
	 * @date 2014-8-4 下午3:33:03 
	 * @param threadPoolKey
	 * @param task
	 * @return
	 * @return Future
	 */
	public static Future submit(String threadPoolKey,Runnable task){
		 Future future = ThreadPoolFactory.getSingleThreadExecutor(threadPoolKey).submit(task);
		 return future;
	}
	
	/**
	 * 提交任务到 多线程的线程池 中执行
	 * @author haojian
	 * @date 2014-8-4 下午3:33:48 
	 * @param threadPoolKey
	 * @param task
	 * @return
	 * @return Future
	 */
	public static Future submitToCached(String threadPoolKey,Runnable task){
		 Future future = ThreadPoolFactory.getCachedThreadExecutor(threadPoolKey).submit(task);
		 return future;
	}
	
	
	/**
	 * 根据唯一标示，获取一个单线程的线程池
	 * @author haojian
	 * @date 2014-8-4 下午3:28:28 
	 * @param threadPoolKey
	 * @return
	 * @return ExecutorService
	 */
	private static ExecutorService getSingleThreadExecutor(String threadPoolKey){
		synchronized(threadPoolMap){
			ExecutorService executorService = threadPoolMap.get(threadPoolKey);
			if(executorService==null){
				executorService = Executors.newSingleThreadExecutor(new ServerThreadFactory(threadPoolKey));
				threadPoolMap.put(threadPoolKey, executorService);
				logger.info("创建一个【单线程】的【线程池】,key=【{}】", new Object[]{threadPoolKey} );
			}
			return executorService;
		}
	}
	
	
	/**
	 * 根据唯一标示，获取一个可以自动扩展线程数量的线程池
	 * @author haojian
	 * @date 2014-8-4 下午3:28:52 
	 * @param threadPoolKey
	 * @return
	 * @return ExecutorService
	 */
	private static ExecutorService getCachedThreadExecutor(String threadPoolKey){
		synchronized(threadPoolMap){
			ExecutorService executorService = threadPoolMap.get(threadPoolKey);
			if(executorService==null){
				executorService = Executors.newCachedThreadPool(new ServerThreadFactory(threadPoolKey));
				threadPoolMap.put(threadPoolKey, executorService);
				logger.info("创建一个【多线程】的【线程池】,key=【{}】", new Object[]{threadPoolKey} );
			}
			return executorService;
		}
	}
	

}
