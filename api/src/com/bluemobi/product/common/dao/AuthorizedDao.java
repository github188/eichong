/**
 * FileName:AuthorizedDao.java
 * Author: Administrator
 * Create: 2014年7月1日
 * Last Modified: 2014年7月1日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 权限相关查询DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月1日
 */
public class AuthorizedDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(AuthorizedDao.class);

	/**
	 * 根据用户登录ID和功能ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param actionId
	 *            功能ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getActionRoleCount(String userId, String actionId) {
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_action.action_id) as data_count");
		sql.append(" from p_m_action,");
		sql.append("    p_m_role_inclusion,");
		sql.append("    p_m_action_role,");
		sql.append("    p_m_user_role,");
		sql.append("    p_m_role");
		sql.append(" where p_m_action.action_id = p_m_action_role.action_id");
		sql.append(" and p_m_user_role.role_id = p_m_role.role_id");
		sql.append(" and p_m_action.action_id = ?");
		sql.append(" and ");
		sql.append("  (");
		sql.append("   p_m_user_role.role_id = p_m_action_role.role_id");
		sql.append("   and p_m_user_role.user_id = ?");
		sql.append("  ) ");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 功能ID
			pstmt.setString(1, actionId);
			// 用户ID
			pstmt.setString(2, userId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return dataCount;
	}

	/**
	 * 根据用户登录ID和功能ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param actionId
	 *            功能ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getActionDepartmentCount(String userId, String actionId) {
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_action.action_id) as data_count");
		sql.append(" from p_m_action,");
		sql.append("    p_m_department_inclusion,");
		sql.append("    p_m_action_department,");
		sql.append("    p_m_user_department,");
		sql.append("    p_m_company,");
		sql.append("    p_m_department");
		sql.append(" where p_m_action.action_id = p_m_action_department.action_id");
		sql.append(" and p_m_department.department_id = p_m_action_department.department_id");
		sql.append(" and p_m_department.company_id = p_m_action_department.company_id");
		sql.append(" and p_m_department.company_id = p_m_company.company_id");
		sql.append(" and p_m_action.action_id = ?");
		sql.append(" and ");
		sql.append("  (");
		sql.append("   p_m_user_department.company_id = p_m_action_department.company_id");
		sql.append("   and p_m_user_department.department_id = p_m_action_department.department_id");
		sql.append("   and p_m_user_department.user_id = ?");
		sql.append("  ) ");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			log.debug(sql.toString());

			//
			// 设置SQL参数值
			//
			// 功能ID
			pstmt.setString(1, actionId);
			// 用户ID
			pstmt.setString(2, userId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return dataCount;
	}

	/**
	 * 根据用户登录ID和功能ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param actionId
	 *            功能ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getActionPostCount(String userId, String actionId) {
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_action.action_id) as data_count");
		sql.append(" from p_m_action,");
		sql.append("    p_m_action_post,");
		sql.append("    p_m_user_post,");
		sql.append("    p_m_company,");
		sql.append("    p_m_company_post");
		sql.append(" where p_m_action.action_id = p_m_action_post.action_id");
		sql.append(" and p_m_company_post.post_id = p_m_action_post.post_id");
		sql.append(" and p_m_company_post.company_id = p_m_action_post.company_id");
		sql.append(" and p_m_company_post.company_id = p_m_company.company_id");
		sql.append(" and p_m_action.action_id = ?");
		sql.append(" and ");
		sql.append("  (");
		sql.append("   p_m_user_post.company_id = p_m_action_post.company_id");
		sql.append("   and p_m_user_post.post_id = p_m_action_post.post_id");
		sql.append("   and p_m_user_post.user_id = ?");
		sql.append("  ) ");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			log.debug(sql.toString());

			//
			// 设置SQL参数值
			//
			// 功能ID
			pstmt.setString(1, actionId);
			// 用户ID
			pstmt.setString(2, userId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return dataCount;
	}

	/**
	 * 根据用户登录ID和App接口ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param appApiId
	 *            App接口ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getAppApiRoleCount(String userId, String appApiId) {
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_app_api.app_api_id) as data_count");
		sql.append(" from p_m_app_api,");
		sql.append("    p_m_role_inclusion,");
		sql.append("    p_m_app_api_role,");
		sql.append("    p_m_user_role,");
		sql.append("    p_m_role");
		sql.append(" where p_m_app_api.app_api_id = p_m_app_api_role.app_api_id");
		sql.append(" and p_m_user_role.role_id = p_m_role.role_id");
		sql.append(" and p_m_app_api.app_api_id = ?");
		sql.append(" and ");
		sql.append("  (");
		sql.append("   p_m_user_role.role_id = p_m_app_api_role.role_id");
		sql.append("   and p_m_user_role.user_id = ?");
		sql.append("  ) ");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// App接口ID
			pstmt.setString(1, appApiId);
			// 用户ID
			pstmt.setString(2, userId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return dataCount;
	}

	/**
	 * 根据用户登录ID和App接口ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param appApiId
	 *            App接口ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getAppApiDeptCount(String userId, String appApiId) {
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_app_api.app_api_id) as data_count");
		sql.append(" from p_m_app_api,");
		sql.append("    p_m_department_inclusion,");
		sql.append("    p_m_app_api_department,");
		sql.append("    p_m_user_department,");
		sql.append("    p_m_company,");
		sql.append("    p_m_department");
		sql.append(" where p_m_app_api.app_api_id = p_m_app_api_department.app_api_id");
		sql.append(" and p_m_department.department_id = p_m_app_api_department.department_id");
		sql.append(" and p_m_department.company_id = p_m_app_api_department.company_id");
		sql.append(" and p_m_department.company_id = p_m_company.company_id");
		sql.append(" and p_m_app_api.app_api_id = ?");
		sql.append(" and ");
		sql.append("  (");
		sql.append("   p_m_user_department.company_id = p_m_app_api_department.company_id");
		sql.append("   and p_m_user_department.department_id = p_m_app_api_department.department_id");
		sql.append("   and p_m_user_department.user_id = ?");
		sql.append("  ) ");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			log.debug(sql.toString());

			//
			// 设置SQL参数值
			//
			// App接口ID
			pstmt.setString(1, appApiId);
			// 用户ID
			pstmt.setString(2, userId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return dataCount;
	}

	/**
	 * 根据用户登录ID和App接口ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param appApiId
	 *            App接口ID
	 * @return int 用户数
	 * @throws 无
	 */
	public long getAppApiPostCount(String userId, String appApiId) {
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_app_api.app_api_id) as data_count");
		sql.append(" from p_m_app_api,");
		sql.append("    p_m_app_api_post,");
		sql.append("    p_m_user_post,");
		sql.append("    p_m_company,");
		sql.append("    p_m_company_post");
		sql.append(" where p_m_app_api.app_api_id = p_m_app_api_post.app_api_id");
		sql.append(" and p_m_company_post.post_id = p_m_app_api_post.post_id");
		sql.append(" and p_m_company_post.company_id = p_m_app_api_post.company_id");
		sql.append(" and p_m_company_post.company_id = p_m_company.company_id");
		sql.append(" and p_m_app_api.app_api_id = ?");
		sql.append(" and ");
		sql.append("  (");
		sql.append("   p_m_user_post.company_id = p_m_app_api_post.company_id");
		sql.append("   and p_m_user_post.post_id = p_m_app_api_post.post_id");
		sql.append("   and p_m_user_post.user_id = ?");
		sql.append("  ) ");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());
			log.debug(sql.toString());

			//
			// 设置SQL参数值
			//
			// App接口ID
			pstmt.setString(1, appApiId);
			// 用户ID
			pstmt.setString(2, userId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return dataCount;
	}

	/**
	 * 根据用户登录ID和App接口ID，查询用户的角色是否拥有使用权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param appApiId
	 *            App接口ID
	 * @return boolean 是否控制
	 * @throws 无
	 */
	public boolean getIsOnControl(String appApiId) {
		// 是否设置控制
		boolean isOnControl = false;
		// 查询结果数
		long dataCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append("    count(p_m_app_api.app_api_id) as data_count");
		sql.append(" from p_m_app_api");
		sql.append(" where p_m_app_api.app_api_id = ?");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// App接口ID
			pstmt.setString(1, appApiId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				dataCount = resultSet.getLong("data_count");

				if (dataCount > 0) {
					isOnControl = true;
				}
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果
		return isOnControl;
	}
}
