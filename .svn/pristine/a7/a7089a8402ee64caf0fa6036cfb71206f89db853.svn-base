/**
 * FileName: DBManager.java
 * Author: Administrator
 * Create: 2014年10月1日
 * Last Modified: 2014年10月1日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;


/**
 * 数据库管理类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年10月1日
 */
public class DBManager {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(DBManager.class);
	private static PooledConnection conn;
	private static ConnectionPool connectionPool;
	private static DBManager inst;

	public void close() {
		try {
			connectionPool.closeConnectionPool();
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
	}

	public DBManager() {
		if (inst != null) {
			return;
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

		String connStr = String.format(url);
		connectionPool = new ConnectionPool(driver, connStr, username, password);
		try {
			connectionPool.createPool();
			inst = this;

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

	}

	public static PooledConnection getConnection() {
		if (inst == null)
			new DBManager();

		try {

			conn = connectionPool.getConnection();

		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		return conn;
	}

	public static void main(String[] args) {
		Connection connection = DBManager.getConnection().getConnection();
		try {
			System.out.println(connection.isValid(10));
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
	}

}
