/**     
 * @Title:  QuartzJobFactoryDisallowConcurrentExecution.java   
 * @Package com.wanma.task   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月26日 下午3:04:55   
 * @version V1.0     
 */  
package com.sgcc.task;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** 
 *  
 * @Description: 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作 
 * @author bc 
 * @date 2015年11月26日
 */  
@DisallowConcurrentExecution  
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {  
    public final Logger log = Logger.getLogger(this.getClass());  
  
    @Override  
    public void execute(JobExecutionContext context) throws JobExecutionException {  
    	ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
    	log.info("Concurrent任务成功运行,名称 = [" + scheduleJob.getJobName() + "]");
        TaskUtils.invokMethod(scheduleJob); 
    }  
} 
