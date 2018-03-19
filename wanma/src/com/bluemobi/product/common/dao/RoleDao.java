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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bluemobi.product.model.common.TreeModel;
import com.wanma.model.TblUser;

/**
 * 角色DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class RoleDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(RoleDao.class);

	/**
	 * 取得角色的亲子角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param roleId
	 *            角色ID
	 * @return List<TreeModel> 亲子角色一览
	 * @throws 无
	 */
	public List<TreeModel> getRealChildList(String roleId,TblUser loginUser) {
		// 亲子角色一览
		List<TreeModel> realChildList = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmri.role_id,");
		sql.append(" pmr.role_name");
		sql.append(" from");
		sql.append(" p_m_role_inclusion pmri,");
		sql.append(" p_m_role pmr");
		sql.append(" where");
		sql.append(" pmr.role_id = pmri.role_id");
		sql.append(" and pmr.CREATE_USER='"+loginUser.getUserId()+"'");
		sql.append(" and pmri.parent_role_id = ?");
		sql.append(" and pmri.depth = 1");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 角色ID
			pstmt.setString(1, roleId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 角色对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 角色ID
				modelKey = resultSet.getString("role_id");
				treeModel.setModelKey(modelKey);
				// 角色名称
				treeModel.setModelName(resultSet.getString("role_name"));

				// 加入到亲子角色一览
				realChildList.add(treeModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回亲子角色一览
		return realChildList;
	}

	/**
	 * 取得角色关系中的最大深度
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return int 最大深度
	 * @throws 无
	 */
	public long getMaxDepth() {
		// 最大深度
		long maxDepth = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" max(pmri.depth) max_depth");
		sql.append(" from p_m_role_inclusion pmri");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				maxDepth = resultSet.getLong("max_depth");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回最大深度
		return maxDepth;
	}

	/**
	 * 取得角色的子角色数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param roleId
	 *            角色ID
	 * @return int 最大深度
	 * @throws 无
	 */
	public long getChildRoleCount(String roleId) {
		// 最大深度
		long childRoleCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" count(pmri.role_id) data_count");
		sql.append(" from p_m_role_inclusion pmri");
		sql.append(" where pmri.parent_role_id = ?");
		sql.append(" and pmri.depth > 0");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 角色ID
			pstmt.setString(1, roleId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				childRoleCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return childRoleCount;
	}

	/**
	 * 取得最顶层角色一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 最顶层角色一览
	 * @throws 无
	 */
	public List<TreeModel> getTopRoles(TblUser loginUser) {
		// 最顶层角色一览
		List<TreeModel> topRoles = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" max(pmri.depth) max_depth,");
		sql.append(" pmri.role_id,");
		sql.append(" pmr.role_name");
		sql.append(" from");
		sql.append(" p_m_role_inclusion pmri,");
		sql.append(" p_m_role pmr");
		sql.append(" where");
		sql.append(" pmr.role_id = pmri.role_id");
		sql.append(" and pmr.CREATE_USER='"+loginUser.getUserId()+"'");
		sql.append(" group by pmri.role_id");
		sql.append(" having max_depth = 0");
		sql.append(" order by pmr.role_id asc");
       /**电桩修改(chengym20150609) start***/
		/**电桩修改(chengym20150609) end***/
		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 角色对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 角色ID
				modelKey = resultSet.getString("role_id");
				treeModel.setModelKey(modelKey);
				// 角色名称
				treeModel.setModelName(resultSet.getString("role_name"));

				// 加入到最顶层角色一览
				topRoles.add(treeModel);

			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回最顶层角色一览
		return topRoles;
	}

	/**
	 * 取得用户最小的角色级别(值越小权限越大)
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param userId
	 *            用户ID
	 * 
	 * @return String 用户最小的角色级别
	 * @throws 无
	 */
	public String getUserMinRole(String userId) {
		// 用户最小的角色级别
		String minCategory = "99";

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" min(pmr.category) minCategory ");
		sql.append(" from");
		sql.append(" p_m_user_role pmur,");
		sql.append(" p_m_role pmr");
		sql.append(" where");
		sql.append(" pmr.role_id = pmur.role_id");
		sql.append(" and pmur.user_id = '" + userId + "'");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 用户最小的角色级别
				minCategory = resultSet.getString("minCategory");

			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 用户最小的角色级别
		return minCategory;
	}
}
