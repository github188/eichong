/**
 * FileName:CompanyDao.java
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
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.utils.UserUtil;

/**
 * 公司DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class CompanyDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(CompanyDao.class);

	/**
	 * 取得的公司一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 公司一览
	 * @throws 无
	 */
	public List<TreeModel> getCompanyList() {
		// 一览
		List<TreeModel> postList = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" select ");
		sql.append("     pmc.company_id,");
		sql.append("     pmc.company_name");
		sql.append(" from");
		sql.append("     p_m_company pmc");
		sql.append("     where pmc.delete_flag = '0'");

		//
		// 如果是后台管理员账号
		//
		if (!UserUtil.isManagerUser()) {
			sql.append(" and pmc.company_type = '"
					+ CommonConsts.COMPANY_TYPE_ESTATE + "'");
			sql.append(" and exists (select * from p_m_user_department pmud,p_m_department pmd where pmud.department_id = pmd.department_id and pmc.company_id = pmd.company_id and pmud.user_id = '"
					+ UserUtil.getLoginUserId() + "')");
		} else {
			sql.append(" and pmc.company_type = '"
					+ CommonConsts.COMPANY_TYPE_SYSTEM + "'");

		}

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 公司ID
				modelKey = resultSet.getString("company_id");
				treeModel.setModelKey(modelKey);
				// 名称
				treeModel.setModelName(resultSet.getString("company_name"));

				// 加入到公司一览
				postList.add(treeModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回公司一览
		return postList;
	}
}
