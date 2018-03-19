package com.netCore.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 内存监控任务
 * @author haojian
 * Apr 16, 2013 3:45:52 PM
 */
public class DumpMemoryTask implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(DumpMemoryTask.class);

	@Override
	public void run() {
		double maxMemory = Runtime.getRuntime().maxMemory()/1024d/1024d/1024d;
		double totalMemory = Runtime.getRuntime().totalMemory()/1024d/1024d/1024d;
		double freeMemory = Runtime.getRuntime().freeMemory()/1024d/1024d/1024d;
		double useMemory = totalMemory - freeMemory;
		int useRate = (int) Math
				.round((useMemory / totalMemory) * 100);
		
		if(useRate>70)
		{
			logger.debug("System.gc()");
			/*try
			{
				System.gc();
			}
			catch(Exception e)
			{
				logger.info("e.getStackTrace():{}",e.getStackTrace());
			}*/
		}
		logger.info(
					"-服务器内存，最大={}G，分配={}G,空闲={}G，使用={}G，使用比={}%",
					new Object[]{
							Math.round(maxMemory*1000)/1000d,
							Math.round(totalMemory*1000)/1000d,
							Math.round(freeMemory*1000)/1000d,
							Math.round(useMemory*1000)/1000d,
							useRate
					}
				);
		
		
	}

}
