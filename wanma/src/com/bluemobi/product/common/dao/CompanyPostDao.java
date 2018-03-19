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

import com.bluemobi.product.model.common.TreeModel;

/**
 * 公司职位DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class CompanyPostDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(CompanyPostDao.class);

	/**
	 * 取得职位的公司职位一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param companyId
	 *            公司ID
	 * @return List<TreeModel> 公司职位一览
	 * @throws 无
	 */
	public List<TreeModel> getCompanyPostList(String companyId) {
		// 职位一览
		List<TreeModel> postList = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append(" select ");
		sql.append("     pmcp.company_id,");
		sql.append("     pmcp.post_id,");
		sql.append("     pmcp.post_name");
		sql.append(" from");
		sql.append("     p_m_company_post pmcp,");
		sql.append("     p_m_company pmc");
		sql.append(" where");
		sql.append("     pmc.company_id = pmcp.company_id");
		sql.append("         and pmcp.company_id = ?");
		sql.append(" order by pmcp.sort_key desc,");
		sql.append(" pmcp.post_id asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 公司ID
			pstmt.setString(1, companyId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 职位对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 公司ID
				modelKey = resultSet.getString("company_id");
				// 职位ID
				modelKey = modelKey + "," + resultSet.getString("post_id");
				treeModel.setModelKey(modelKey);
				// 职位名称
				treeModel.setModelName(resultSet.getString("post_name"));

				// 加入到公司职位一览
				postList.add(treeModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回公司职位一览
		return postList;
	}
}
