package com.wanma.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext ctx = null;

	public static Object getBean(String id) {
	   return ctx.getBean(id);
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	public static ApplicationContext getApplicationContext() {
	   return ctx;
	}
}

