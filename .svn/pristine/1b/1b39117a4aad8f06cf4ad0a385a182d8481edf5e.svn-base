package com.wanma.polling.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wanma.polling.tasker.OkjTimer;

public class PollingContextListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Timer timer = (Timer) context.getAttribute("polling-task");
		timer.cancel();
		context.removeAttribute("polling-task");
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Calendar current = Calendar.getInstance();
		current.add(Calendar.SECOND, 10);
		Date start = current.getTime();
		Timer timer = new Timer();

		timer.schedule(new OkjTimer(), start, 1800000);

		context.setAttribute("polling-task", timer);
	}

}
