/**
 * FileName:BatchStatisticsTask.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
package com.bluemobi.product.batch;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */
public class BatchStatisticsTask extends TimerTask {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(BatchStatisticsTask.class);

	private static boolean isRunning = false;
	private ServletContext context = null;

	public BatchStatisticsTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		// System.out.println(isRunning);
		if (!isRunning) {
			isRunning = true;
			log.info("开始执行指定任务");

			// TODO 添加自定义的详细任务
			executeTask();

			// 指定任务执行结束
			isRunning = false;
			log.info("指定任务执行结束");
		} else {
			log.info("上一次任务执行还未结束");
		}

	}

	/**
	 * 执行任务
	 */
	public void executeTask() {
		// todo
	}

	/**
	 * @return the context
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(ServletContext context) {
		this.context = context;
	}

}
