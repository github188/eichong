/**
 * FileName:CommonDao.java
 * Author: Administrator
 * Create: 2014年7月1日
 * Last Modified: 2014年7月1日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 数据库基础类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月1日
 */
public class CommonDao {
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(ConnectionManager.class);

	/** 定义一个Connection 用来连接数据库 */
	private Connection connection = null;
	private PooledConnection pooledConnection;

	/** 定义一个int记录更新的记录数量 */
	int count = 0;
	/** 定义一个结果集 用于放回查询结果 */
	protected ResultSet resultSet = null;

	protected PreparedStatement pstmt = null;

	/**
	 * 建立数据的连接
	 * 
	 * @exception SQLException
	 *                , ClassNotFoundException
	 */
	public Connection getConnection() {
		pooledConnection = DBManager.getConnection();

		if (pooledConnection != null) {
			return pooledConnection.getConnection();
		} else {

			return ConnectionManager.getConnection();
		}
	}

	/**
	 * 查询方法
	 * 
	 * @param sql查询sql语句
	 * @return resultSet
	 */
	@SuppressWarnings("finally")
	public ResultSet query(String sql) {
		try {
			pstmt = connection.prepareStatement(sql);
			/** 查询 */
			resultSet = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return resultSet;
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param sql
	 *            更新sql语句
	 * @return
	 */
	public int update(String sql) {
		try {
			pstmt = connection.prepareStatement(sql);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("执行更新出错了");
		}

		return count;

	}

	/** 关闭连接 */
	public boolean coles() {
		boolean isColes = false;
		if (resultSet != null) {
			try {
				resultSet.close();
				resultSet = null;
				isColes = true;
			} catch (SQLException e) {
				isColes = false;
				e.printStackTrace();
				log.error("关闭结果集发生错误");
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
				isColes = true;
			} catch (SQLException e) {
				isColes = false;
				e.printStackTrace();
				log.error("关闭pstmt发生异常");
			}
		}
		if (pooledConnection != null) {
			pooledConnection.close();
		}

		return isColes;
	}

	/**
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		CommonDao db = new CommonDao();
		/** 调用查询方法 */
		// db.testQuery();
		/** 调用更新方法 */
		// db.testUpdate();
		/** 调用关闭连接方法 */
		db.coles();

		ConnectionManager.getConnection().close();
	}

}
