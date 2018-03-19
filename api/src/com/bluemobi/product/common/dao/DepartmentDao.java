/**
 * FileName:DepartmentDao.java
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

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.model.DepartmentModel;
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.utils.UserUtil;

/**
 * 部门DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class DepartmentDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(DepartmentDao.class);

	/**
	 * 取得部门的亲子部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param companyId
	 *            公司ID
	 * @param departmentId
	 *            部门ID
	 * @return List<TreeModel> 亲子部门一览
	 * @throws 无
	 */
	public List<TreeModel> getRealChildList(String companyId,
			String departmentId) {
		// 亲子部门一览
		List<TreeModel> realChildList = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmdi.company_id,");
		sql.append(" pmdi.department_id,");
		sql.append(" pmd.department_name");
		sql.append(" from");
		sql.append(" p_m_department_inclusion pmdi,");
		sql.append(" p_m_department pmd");
		sql.append(" where");
		sql.append(" pmd.company_id = pmdi.company_id");
		sql.append(" and pmd.department_id = pmdi.department_id");
		sql.append(" and pmd.company_id = ?");
		sql.append(" and pmdi.parent_department_id = ?");
		sql.append(" and pmdi.depth = 1");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 公司ID
			pstmt.setString(1, companyId);
			// 部门ID
			pstmt.setString(2, departmentId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 部门对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 公司ID
				modelKey = resultSet.getString("company_id");
				// 部门ID
				modelKey = modelKey + ","
						+ resultSet.getString("department_id");
				treeModel.setModelKey(modelKey);
				// 部门名称
				treeModel.setModelName(resultSet.getString("department_name"));

				// 加入到亲子部门一览
				realChildList.add(treeModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回亲子部门一览
		return realChildList;
	}

	/**
	 * 取得部门关系中的最大深度
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
		sql.append(" max(PMDI.DEPTH) max_depth");
		sql.append(" from p_m_department_inclusion PMDI");

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
	 * 取得部门的子部门数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param companyId
	 *            公司ID
	 * @param departmentId
	 *            部门ID
	 * @return int 最大深度
	 * @throws 无
	 */
	public long getChildDeptCount(String companyId, String departmentId) {
		// 最大深度
		long childDeptCount = 0;
		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" count(pmdi.department_id) data_count");
		sql.append(" from p_m_department_inclusion pmdi");
		sql.append(" where pmdi.company_id = ?");
		sql.append(" and pmdi.parent_department_id = ?");
		sql.append(" and pmdi.depth > 0");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 公司ID
			pstmt.setString(1, companyId);
			// 部门ID
			pstmt.setString(2, departmentId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				childDeptCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return childDeptCount;
	}

	/**
	 * 取得最顶层部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 最顶层部门一览
	 * @throws 无
	 */
	public List<TreeModel> getTopDepartments() {
		// 最顶层部门一览
		List<TreeModel> topDepartments = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" max(pmdi.depth) max_depth,");
		sql.append(" pmdi.company_id,");
		sql.append(" pmdi.department_id,");
		sql.append(" pmd.department_name");
		sql.append(" from");
		sql.append(" p_m_department_inclusion pmdi,");
		sql.append(" p_m_department pmd,");
		sql.append(" p_m_company pmc");
		sql.append(" where");
		sql.append(" pmd.company_id = pmc.company_id");
		sql.append(" and pmd.company_id = pmdi.company_id");
		sql.append(" and pmd.department_id = pmdi.department_id");

		//
		// 如果是后台管理员账号
		//
		if (!UserUtil.isManagerUser()) {
			sql.append(" and pmc.company_type = '"
					+ CommonConsts.COMPANY_TYPE_ESTATE + "'");
			sql.append(" and exists (select * from p_m_user_department pmud where pmud.department_id = pmd.department_id and pmud.user_id = '"
					+ UserUtil.getLoginUserId() + "')");
		} else if (UserUtil.isSuperUser()) {

		} else {
			sql.append(" and pmc.company_type = '"
					+ CommonConsts.COMPANY_TYPE_SYSTEM + "'");

		}
		sql.append(" group by pmdi.company_id , pmdi.department_id");
		sql.append(" having max_depth = 0");
		sql.append(" order by pmd.company_id asc,pmd.department_id asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 部门对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 公司ID
				modelKey = resultSet.getString("company_id");
				// 部门ID
				modelKey = modelKey + ","
						+ resultSet.getString("department_id");
				treeModel.setModelKey(modelKey);
				// 部门名称
				treeModel.setModelName(resultSet.getString("department_name"));

				// 加入到最顶层部门一览
				topDepartments.add(treeModel);

			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		if (topDepartments == null || topDepartments.size() == 0) {
			topDepartments = getNormalTopDepartments();
		}

		// 返回最顶层部门一览
		return topDepartments;
	}

	/**
	 * 取得用户部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 部门一览
	 * @throws 无
	 */
	public List<DepartmentModel> getUserDepartments(String userId) {
		// 部门一览
		List<DepartmentModel> departments = new ArrayList<DepartmentModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmd.company_id,");
		sql.append(" pmd.department_id,");
		sql.append(" pmd.department_name");
		sql.append(" from");
		sql.append(" p_m_user_department pmud,");
		sql.append(" p_m_department pmd,");
		sql.append(" p_m_company pmc");
		sql.append(" where");
		sql.append(" pmd.company_id = pmc.company_id");
		sql.append(" and pmd.company_id = pmud.company_id");
		sql.append(" and pmd.department_id = pmud.department_id");
		sql.append(" and pmud.user_id = '" + userId + "'");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 部门对象
				DepartmentModel departmentModel = new DepartmentModel();

				//
				// 取得查询结果
				//
				// 公司ID
				departmentModel.setCompanyId(resultSet.getString("company_id"));
				// 部门ID
				departmentModel.setDepartmentId(resultSet
						.getString("department_id"));
				// 部门名称
				departmentModel.setDepartmentName(resultSet
						.getString("department_name"));

				// 加入到部门一览
				departments.add(departmentModel);

			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();
		
		// 返回部门一览
		return departments;
	}

	/**
	 * 取得普通用户的最顶层部门一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 最顶层部门一览
	 * @throws 无
	 */
	public List<TreeModel> getNormalTopDepartments() {
		// 最顶层部门一览
		List<TreeModel> topDepartments = new ArrayList<TreeModel>();
		// 登录用户ID
		String userId = UserUtil.getLoginUserId();
		// 部门一览
		List<DepartmentModel> departments = getUserDepartments(userId);

		if (departments == null || departments.size() == 0) {
			return null;
		}

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		try {

			for (DepartmentModel departmentModel : departments) {
				sql = new StringBuffer();

				// SQL生成
				sql.append("select count(*) as data_count from p_m_department_inclusion pmdi");
				sql.append("  where pmdi.parent_department_id != pmdi.department_id ");
				sql.append(" and pmdi.parent_department_id in ("
						+ departmentsForIn(departments) + ")");
				sql.append(" and pmdi.department_id = ?");

				// SQL执行准备
				pstmt = connection.prepareStatement(sql.toString());
				pstmt.setString(1, departmentModel.getDepartmentId());

				// 执行查询处理
				resultSet = pstmt.executeQuery();

				if (resultSet.next()) {
					long parentCount = resultSet.getLong("data_count");

					if (parentCount == 0) {
						// 部门对象
						TreeModel treeModel = new TreeModel();
						String modelKey = "";
						//
						// 取得查询结果
						//
						// 公司ID
						modelKey = departmentModel.getCompanyId();
						// 部门ID
						modelKey = modelKey + ","
								+ departmentModel.getDepartmentId();
						treeModel.setModelKey(modelKey);
						// 部门名称
						treeModel.setModelName(departmentModel
								.getDepartmentName());

						// 加入到最顶层部门一览
						topDepartments.add(treeModel);
					}

				}
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回最顶层部门一览
		return topDepartments;
	}

	private String departmentsForIn(List<DepartmentModel> departments) {
		String inStr = "";
		for (int i = 0; i < departments.size(); i++) {
			DepartmentModel departmentModel = departments.get(i);
			if (i == 0) {
				inStr = "'" + departmentModel.getDepartmentId() + "'";
			} else {
				inStr = inStr + ",'" + departmentModel.getDepartmentId() + "'";

			}
		}
		return inStr;
	}
}
