package com.wanma.polling.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.wanma.common.JudgeNullUtils;
import com.wanma.polling.tasker.RenewCacheTimer;

 
public class PollingContextListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Timer timer = (Timer) context.getAttribute("renewCache-task");
		timer.cancel();
		context.removeAttribute("renewCache-task");
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Calendar current = Calendar.getInstance();
		current.add(Calendar.SECOND, 10);
		Date start = current.getTime();
		Timer timer = new Timer();

    	MessageManager messageManager = MessageManager.getMessageManager();
        String cacheTime=messageManager.getSystemProperties(CommonConsts.RENEW_CACHE_TIMER);
		timer.schedule(new RenewCacheTimer(), start, JudgeNullUtils.nvlInteget(cacheTime)*60*1000);

		context.setAttribute("renewCache-task", timer);
	}

}
