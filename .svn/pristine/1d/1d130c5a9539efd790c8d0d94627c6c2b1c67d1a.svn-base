/** 
 * FileName PropertiesManager.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/12
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.base.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import com.base.util.ObjectUtil;

public class SystemFileMgr {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(SystemFileMgr.class);

	/** 消息配置文件 */
	private static Properties messageProperties = null;
	/** 系统配置文件 */
	private static Properties sysProperties = null;
	/** 数据库连接配置文件 */
	private static Properties jdbcProperties = null;
	/** 数据库连接配置文件 */
	private static Map<String, String> jdbcMap = null;

	/**  */
	private static SystemFileMgr propertiesManager = null;

	static {
		// 加载属性文件
		try {
			// 消息配置文件
			InputStream inputStreamMsg = SystemFileMgr.class.getClassLoader()
					.getResourceAsStream(
							CommonConsts.PRO_FILE_MESSAGE_PROPERTIES);

			// 系统配置文件
			InputStream inputStreamSys = SystemFileMgr.class.getClassLoader()
					.getResourceAsStream(CommonConsts.PRO_FILE_SYSTEM_SETTING);

			// 数据库连接配置文件
			InputStream inputStreamJdbc = SystemFileMgr.class.getClassLoader()
					.getResourceAsStream(CommonConsts.PRO_FILE_JDBC_SETTING);
			try {
				//
				// 消息配置文件
				//
				messageProperties = new Properties();
				messageProperties.load(inputStreamMsg);
				// 记录系统设置文件加载成功
				log.info("Secure " + CommonConsts.PRO_FILE_MESSAGE_PROPERTIES
						+ " is loaded.");

				//
				// 系统配置文件
				//
				sysProperties = new Properties();
				sysProperties.load(inputStreamSys);

				// 记录系统设置文件加载成功
				log.info("Secure " + CommonConsts.PRO_FILE_SYSTEM_SETTING
						+ " is loaded.");

				//	 ·
				// 数据库连接配置文件
				//
				jdbcProperties = new Properties();
				jdbcProperties.load(inputStreamJdbc);
				// 记录数据库连接设置文件加载成功
				log.info("Secure " + CommonConsts.PRO_FILE_JDBC_SETTING
						+ " is loaded.");

				jdbcMap = getJdbcMap();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStreamMsg.close();
				inputStreamSys.close();
				inputStreamJdbc.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 构造方法
	 */
	public SystemFileMgr() {
	}

	/**
	 * 初始化Manager
	 */
	public static SystemFileMgr getMessageManager() {
		SystemFileMgr manager = null;
		if (propertiesManager == null) {
			manager = new SystemFileMgr();
		} else {
			manager = propertiesManager;
		}

		return manager;

	}

	/**
	 * 取得系统设置文件中的信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * 
	 * @param properties
	 *            properties文件名
	 * @return propertiesMap properties文件信息
	 * 
	 */
	public Map<String, String> getSystemProperties() {
		// 定义系统设置文件信息
		Map<String, String> propertiesMap = null;
		// 实例化系统设置文件信息
		propertiesMap = new HashMap<String, String>();

		// 用户默认密码
		propertiesMap
				.put(CommonConsts.MAP_KEY_DEFAULT_USER_PASSWORD,
						sysProperties
								.getProperty(CommonConsts.PRO_KEY_DEFAULT_USER_PASSWORD));

		// 提前预约天数
		propertiesMap
				.put(CommonConsts.MAP_KEY_BOOK_AHEAD_DAYS, sysProperties
						.getProperty(CommonConsts.PRO_KEY_BOOK_AHEAD_DAYS));

		// 系统设置文件信息
		return propertiesMap;

	}

	/**
	 * 根据key取得系统设置文件中的信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * 
	 * @param String
	 *            messageKey 信息内容关键字
	 * @return String信息内容
	 * 
	 */
	public String getSystemProperties(String messageKey) {

		// 信息内容
		String message = "";
		// 信息内容
		message = sysProperties.getProperty(messageKey);

		if (message == null) {
			// 将KEY设置成值
			message = messageKey;
		}

		// 返回信息内容
		return message;
	}

	/**
	 * 取得信息内容的信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * 
	 * @param String
	 *            messageKey 信息内容关键字
	 * @return String信息内容
	 * 
	 */
	public String getMessage(String messageKey) {

		// 信息内容
		String message = "";

		if (ObjectUtil.isEmpty(messageKey)) {
			return "";
		}

		// 信息内容
		message = messageProperties.getProperty(messageKey);

		if (message == null) {
			// 将KEY设置成值
			message = messageKey;
		}

		// 返回信息内容
		return message;
	}

	/**
	 * 取得数据库连接设置文件中的信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * 
	 * @param properties
	 *            properties文件名
	 * @return propertiesMap properties文件信息
	 * 
	 */
	public Map<String, String> getJdbcProperties() {
		return jdbcMap;
	}

	/**
	 * 取得数据库连接设置文件中的信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * 
	 * @param properties
	 *            properties文件名
	 * @return propertiesMap properties文件信息
	 * 
	 */
	public static Map<String, String> getJdbcMap() {
		// 定义系统设置文件信息
		Map<String, String> propertiesMap = null;
		// 实例化系统设置文件信息
		propertiesMap = new HashMap<String, String>();

		// URL
		propertiesMap.put(CommonConsts.MAP_KEY_DB_URL,
				jdbcProperties.getProperty(CommonConsts.PRO_KEY_DB_URL));

		// 驱动
		propertiesMap.put(CommonConsts.MAP_KEY_DB_DRIVER,
				jdbcProperties.getProperty(CommonConsts.PRO_KEY_DB_DRIVER));

		// 用户名
		propertiesMap.put(CommonConsts.MAP_KEY_DB_USERNAME,
				jdbcProperties.getProperty(CommonConsts.PRO_KEY_DB_USERNAME));

		// 密码
		propertiesMap.put(CommonConsts.MAP_KEY_DB_PASSWORD,
				jdbcProperties.getProperty(CommonConsts.PRO_KEY_DB_PASSWORD));

		// 数据库连接设置文件信息
		return propertiesMap;

	}

	/**
	 * 根据key取得第三方登录设置文件中的信息
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * 
	 * @param String
	 *            messageKey 信息内容关键字
	 * @return String信息内容
	 * 
	 */
	public String getRemoteAuthenProperties(String messageKey) {
		// 加载配置
		Properties props = new Properties();

		// 信息内容
		String message = "";
		try {

			// 加载classpath下的信息配置文件
			props.load(this.getClass().getClassLoader()
					.getResourceAsStream(CommonConsts.REMOTE_OAUTH_PROPERTIES));

			// 信息内容
			message = props.getProperty(messageKey);

			if (message == null) {
				// 将KEY设置成值
				message = messageKey;
			}

			// 记录第三方登录设置文件加载成功
			log.info("Secure " + CommonConsts.REMOTE_OAUTH_PROPERTIES
					+ " is loaded.");
		} catch (IOException e) {
			// 记录properties文件加载失败
			log.error("Causing Error when loading secure "
					+ CommonConsts.REMOTE_OAUTH_PROPERTIES + " configuration!");
			e.printStackTrace();
			// 将KEY设置成值
			message = messageKey;
		}

		// 返回信息内容
		return message;
	}

	public static void main(String[] args) {
		Map<String, String> propertiesMap = SystemFileMgr.getMessageManager()
				.getSystemProperties();
		System.out.println(propertiesMap
				.get(CommonConsts.MAP_KEY_BOOK_AHEAD_DAYS));
		System.out.println(SystemFileMgr.getMessageManager().getMessage(
				"test.test01.button.save"));
	}

}
