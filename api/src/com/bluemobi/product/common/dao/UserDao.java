/**
 * FileName:RoleDao.java
 * Author: Administrator
 * Create: 2014年7月3日
 * Last Modified: 2014年7月3日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.CommonConsts;

/**
 * 角色DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class UserDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(UserDao.class);

	/**
	 * 判断用户是否是超级用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * 
	 * @return boolean 是否是超级用户
	 * @throws 无
	 */
	public boolean isSuperUser(String userId) {
		// 是否是超级用户
		boolean isSuperUser = false;
		// 用户级别
		String userLevel = "";

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" min(pmu.user_level) user_level");
		sql.append(" from p_m_user pmu");
		sql.append(" where pmu.user_id = '" + userId + "'");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				userLevel = resultSet.getString("user_level");

				if (CommonConsts.SUPPER_USER_LEVEL_ID.equals(userLevel)) {
					isSuperUser = true;
				}
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 是否是超级用户
		return isSuperUser;
	}

}
