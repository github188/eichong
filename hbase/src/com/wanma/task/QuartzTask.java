/**     
 * @Title:  DataConversionTask.java   
 * @Package com.wanma.task   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月26日 上午9:44:23   
 * @version V1.0     
 */
package com.wanma.task;

import org.apache.log4j.Logger;

/**
 * @author bc
 *
 */
public class QuartzTask {
	private final Logger log = Logger.getLogger(this.getClass());
	public void run1() {
		log.info("任务线程1开始执行");
	}

	public void run2() {
		log.info("任务线程2开始执行");
	}

	public void run3() {
		log.info("任务线程3开始执行");
	}

	public void run4() {
		log.info("任务线程4开始执行");
	}

	public void run5() {
		log.info("任务线程5开始执行");
	}
}
