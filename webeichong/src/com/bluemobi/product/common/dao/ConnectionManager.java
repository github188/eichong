/**
 * FileName:CompanyDao.java
 * Author: Administrator
 * Create: 2014年7月3日
 * Last Modified: 2014年7月3日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;

/**
 * 公司DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class ConnectionManager {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(ConnectionManager.class);

	/** 定义一个Connection 用来连接数据库 */
	public static Connection connection = null;

	/**
	 * 建立数据的连接
	 * 
	 * @exception SQLException
	 *                , ClassNotFoundException
	 */
	@SuppressWarnings("finally")
	public static synchronized Connection getConnection() {
		try {
			if (connection != null) {
				if (connection.isValid(10)) {
					return connection;
				} else {
					connection = null;
					DBManager.getConnection();
				}
			}

			MessageManager messageManager = MessageManager.getMessageManager();
			Map<String, String> jdbcSetMap = messageManager.getJdbcProperties();

			/** 连接数据库的URL */
			String url = jdbcSetMap.get(CommonConsts.MAP_KEY_DB_URL);
			/** 指定数据的用户名 */
			String username = jdbcSetMap.get(CommonConsts.MAP_KEY_DB_USERNAME);
			/** 指定数据的密码 */
			String password = jdbcSetMap.get(CommonConsts.MAP_KEY_DB_PASSWORD);
			/** 指定数据的驱动 */
			String driver = jdbcSetMap.get(CommonConsts.MAP_KEY_DB_DRIVER);

			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, username, password);
			log.info("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("建立数据库发生错误！");
		} finally {
			return connection;
		}
	}

	public static void closeConnection() {

		if (connection != null) {
			try {
				connection.close();
				connection = null;
				log.info("数据库关闭成功");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("数据库关闭发生异常");
			}
		}
	}

}
