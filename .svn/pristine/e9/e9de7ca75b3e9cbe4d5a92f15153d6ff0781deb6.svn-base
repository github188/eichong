/**
 * FileName:InitDeployUrlDao.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 初始化发布环境URL
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployUrlDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(InitDeployUrlDao.class);

	/**
	 * 初始化发布环境URL等信息
	 * 
	 * @param deployUrl
	 *            发布环境URL
	 * @param realFilePath
	 *            文件存放根目录
	 */
	public void initDeployInfo(String deployUrl, String parentPath,String imagesCanUrl) {
		boolean isSetted = false;
		long dataCount = 0;

		// 数据库连接
		Connection connection = getConnection();

		StringBuffer sqlSelect = new StringBuffer();

		// SQL生成
		sqlSelect.append(" select ");
		sqlSelect.append("     count(*) as data_count");
		sqlSelect.append(" from");
		sqlSelect.append("    p_m_deploy_info");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sqlSelect.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 对象
				dataCount = resultSet.getLong("data_count");
				if (dataCount > 0) {
					isSetted = true;
				}

				sqlSelect = new StringBuffer();

				if (isSetted) {
					// SQL生成
					sqlSelect.append(" update ");
					sqlSelect.append("     p_m_deploy_info");
					sqlSelect.append("     set");
					sqlSelect.append("     deploy_url = '" + deployUrl + "',");
					sqlSelect.append("     parent_path = '" + parentPath + "',");
					sqlSelect.append("     image_ScanUrl = '" + imagesCanUrl + "'");
					// SQL执行准备
					pstmt = connection.prepareStatement(sqlSelect.toString());
					// 执行更新处理
					pstmt.execute();
				} else {
					// SQL生成
					sqlSelect.append(" insert into");
					sqlSelect.append("     p_m_deploy_info");
					sqlSelect.append("     (deploy_url,");
					sqlSelect.append("     parent_path,image_ScanUrl)");
					sqlSelect.append("     values");
					sqlSelect.append("     ( '" + deployUrl + "',");
					sqlSelect.append("     '" + parentPath + "','" + imagesCanUrl + "')");
					// SQL执行准备
					pstmt = connection.prepareStatement(sqlSelect.toString());
					// 执行添加处理
					pstmt.execute();
				}
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

	}
}
