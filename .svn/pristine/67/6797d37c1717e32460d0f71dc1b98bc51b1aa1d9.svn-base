/**     
 * @Title:  QuartzJobFactory.java   
 * @Package com.wanma.task   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月25日 下午8:16:01   
 * @version V1.0     
 */  
package com.wanma.task;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author bc
 *
 */
public class QuartzJobFactory  implements Job {
	private final Logger log = Logger.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        log.info("任务成功运行,名称 = [" + scheduleJob.getJobName() + "]");
        TaskUtils.invokMethod(scheduleJob); 
    }
}
