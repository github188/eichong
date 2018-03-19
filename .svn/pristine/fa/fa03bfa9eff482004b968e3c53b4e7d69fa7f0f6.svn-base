/**
 * FileName:BaseBatchListenerServelt.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
package com.bluemobi.product.batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */
public class BatchContextListener implements ServletContextListener {
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(BaseBatchListener.class);
	private java.util.Timer timer = null;

	public BatchContextListener() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		timer = new java.util.Timer(true);
		log.info("batch listener starting...");
		timer.schedule(new BatchStatisticsTask(event.getServletContext()), 0,
				60 * 1000);// 每隔1分钟
		log.info("batch listener has been started.");

	}

}
