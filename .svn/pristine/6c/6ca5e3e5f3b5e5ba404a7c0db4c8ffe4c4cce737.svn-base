/*
 * 实现ServletContextListener，获取spring context的类文件
 * @Author John.liu 
 * Created: 2014/04/21
 * Updated: 2014/04/21
 */
package com.bluemobi.product.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 实现ServletContextListener，获取spring context的类
 * 需要在web.xml中配置listener并引用该类
 * @author John.liu
 */
public class SpringContextHolder implements ServletContextListener {
	//Spring Context
	private static ApplicationContext applicationContext;
	
	public SpringContextHolder(){
		super();
	}
	/**
	 * 获取WebApplicationContext的方法
	 * 该方法必须在启动Spring Listener之后使用
	 * @return
	 */
	public static ApplicationContext getSpringContext(){
		return applicationContext;
	}
	/**
	 * 获取Spring管理的数据源
	 * @return
	 */
	public static DataSource getSpringPooledDataSource(){
		if(null == applicationContext) return null;
		return applicationContext.getBean(DataSource.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("web application context destroyed...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("web application context initialized...");
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

}
