package com.bluemobi.cache;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/**
 * spring容器
 * @author haojian
 * Mar 29, 2013 3:53:06 PM
 */
public class GameContext {
	
	private static final Logger logger = Logger.getLogger(GameContext.class);

	/**
	 * spring 配置文件路径
	 */
	private static String confPath = 
		System.getProperty("file.separator")
		+ System.getProperty("user.dir")
		+ System.getProperty("file.separator") + "conf"
		+ System.getProperty("file.separator");
	
	/**
	 * spring容器
	 */
	private static ApplicationContext context = null;
	
	static{
		String[] files = {
				 confPath+"applicationContext-dataSource.xml"
				,confPath+"applicationContext-hibernate.xml"
				,confPath+"applicationContext-service.xml"
				//,confPath+"applicationContext-task.xml"
				};
		
		context = new FileSystemXmlApplicationContext(files); 
		logger.info("初始化spring配置结束...");
	}


	/**
	 * 获取Spring容器管理的对象
	 * @author haojian
	 * Apr 8, 2013 4:46:11 PM
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		Object obj = context.getBean(beanName);
		return obj;
	}

	
	
	
}
