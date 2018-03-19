package com.wanma.ims.task;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ReportTask {
	
	
//	@Scheduled(cron = "*/300 * * * * ?")
	public void QuartzCpyOrder(){
		System.out.println("生成大客户报表"+new Date().toString());
	}
	
//	@Scheduled(cron = "*/300 * * * * ?")
	public void QuartzPstationOrder(){
		System.out.println("生成充电点报表"+new Date().toString());
	}
}
