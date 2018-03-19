package com.appCore.core.task;

import org.apache.log4j.Logger;

/**
 * 内存监控任务
 * @author haojian
 * Apr 16, 2013 3:45:52 PM
 */
public class DumpMemoryTask implements Runnable {
	
	private static final Logger logger = Logger.getLogger(DumpMemoryTask.class);

	@Override
	public void run() {
		double maxMemory = Runtime.getRuntime().maxMemory()/1024d/1024d/1024d;
		double totalMemory = Runtime.getRuntime().totalMemory()/1024d/1024d/1024d;
		double freeMemory = Runtime.getRuntime().freeMemory()/1024d/1024d/1024d;
		double useMemory = totalMemory - freeMemory;
		int useRate = (int) Math
				.round((useMemory / totalMemory) * 100);
		logger.info(
					"-服务器内存，最大=" + Math.round(maxMemory*1000)/1000d + "G，"
							+ "分配=" + Math.round(totalMemory*1000)/1000d + "G,"
							+ "空闲=" + Math.round(freeMemory*1000)/1000d + "G，"
							+ "使用=" + Math.round(useMemory*1000)/1000d + "G，使用比=" + useRate + "%"
				);
		
		
	}

}
