/**
 * FileName:BaseBatchListener.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
package com.bluemobi.product.batch;

import org.apache.log4j.Logger;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */
public class BaseBatchListener {
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(BaseBatchListener.class);

	public static void startBathListener() {
		long sleepTimes = 1000 * 60;
		log.info("start bath listener...");
		BaseBatchTread mt1 = new BaseBatchTread();

		try {

			while (true) {
				mt1.run();
				BaseBatchTread.sleep(sleepTimes);

			}
		} catch (InterruptedException e) {
			log.error(e.getLocalizedMessage());
		}
	}
}
