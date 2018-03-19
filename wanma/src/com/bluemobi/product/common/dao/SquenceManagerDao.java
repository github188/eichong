/**
 * FileName:SquenceManagerDao.java
 * Author: Administrator
 * Create: 2014年8月16日
 * Last Modified: 2014年8月16日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bluemobi.product.utils.DateUtil;

/**
 * 序号管理DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月16日
 */
public class SquenceManagerDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(SquenceManagerDao.class);

	public long getSequence(String squenceId, boolean isResetByYear) {

		// 新的序号
		long newSquence = 0;
		// 是否需要更新年份
		boolean isYearUpdate = false;
		// 现在的年份
		String nowYear = DateUtil.getCurrentYear();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("tms.sequence_year, ");
		sql.append("tms.sequence_now+1 as new_sequence ");
		sql.append("from  ");
		sql.append("tb_m_sequence tms ");
		sql.append("where ");
		sql.append("tms.sequence_id = ? ");
		if (isResetByYear) {
			sql.append("and tms.sequence_year = ? ");
		}
		sql.append("for update ");

		try {

			connection.setAutoCommit(false);
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 排序分类ID
			pstmt.setString(1, squenceId);
			if (isResetByYear) {
				// 当前年份
				pstmt.setString(2, DateUtil.getCurrentYear());
			}

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (!resultSet.next() && isResetByYear) {
				isYearUpdate = true;
				newSquence = 1;
			} else {
				newSquence = resultSet.getLong("new_sequence");
			}

			sql = new StringBuffer();
			// SQL生成
			sql.append(" update tb_m_sequence set ");
			sql.append(" sequence_now = " + newSquence);
			if (isYearUpdate) {
				sql.append(" ,sequence_year = '" + nowYear + "'");

			}
			sql.append(" where ");
			sql.append(" sequence_id = '" + squenceId + "'");
			pstmt = null;
			pstmt = connection.prepareStatement(sql.toString());

			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			// 执行更新处理
			pstmt.execute();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回新序号
		return newSquence;

	}

}
