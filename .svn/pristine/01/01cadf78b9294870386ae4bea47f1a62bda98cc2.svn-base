package com.wanma.ims.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
public class SpringContextHolder implements ServletContextListener {
	private static ApplicationContext applicationContext;

	public SpringContextHolder() {
		super();
	}

	/**
	 * 获取WebApplicationContext的方法 该方法必须在启动Spring Listener之后使用
	 * 
	 * @return
	 */
	public static ApplicationContext getSpringContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}

	/**
	 * 获取Spring管理的数据源
	 * 
	 * @return
	 */
	public static DataSource getSpringPooledDataSource() {
		if (null == applicationContext)
			return null;
		return applicationContext.getBean(DataSource.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("web application context destroyed...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("web application context initialized...");
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
	}

}
