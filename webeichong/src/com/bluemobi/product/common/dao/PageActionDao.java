/**
 * FileName:PageActionDao.java
 * Author: Administrator
 * Create: 2014年7月3日
 * Last Modified: 2014年7月3日
 * Version: V1.0 
 */
package com.bluemobi.product.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bluemobi.product.model.common.TreeModel;

/**
 * 页面DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class PageActionDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(PageActionDao.class);

	/**
	 * 取得页面的页面功能一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param pageId
	 *            页面ID
	 * @return List<TreeModel> 页面功能一览
	 * @throws 无
	 */
	public List<TreeModel> getPageActionList(String pageId) {
		// 页面功能一览
		List<TreeModel> realChildList = new ArrayList<TreeModel>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmp.page_id,");
		sql.append(" pma.action_id,");
		sql.append(" pma.action_name");
		sql.append(" from");
		sql.append(" p_m_action pma,");
		sql.append(" p_m_page pmp");
		sql.append(" where");
		sql.append(" pma.page_id = pmp.page_id");
		sql.append(" and pma.page_id = ?");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 页面ID
			pstmt.setString(1, pageId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 页面对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 页面ID
				modelKey = resultSet.getString("page_id");
				// 页面功能ID
				modelKey = modelKey + "," + resultSet.getString("action_id");
				treeModel.setModelKey(modelKey);
				// 页面名称
				treeModel.setModelName(resultSet.getString("action_name"));

				// 加入到页面功能一览
				realChildList.add(treeModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回页面功能一览
		return realChildList;
	}

	/**
	 * 取得页面的子页面数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param pageId
	 *            页面ID
	 * @return int 最大深度
	 * @throws 无
	 */
	public long getPageActionCount(String pageId) {
		// 最大深度
		long childPageActionCount = 0;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" count(pma.action_id) data_count");
		sql.append(" from");
		sql.append(" p_m_action pma,");
		sql.append(" p_m_page pmp");
		sql.append(" where");
		sql.append(" pma.page_id = pmp.page_id");
		sql.append(" and pma.page_id = ?");
		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 页面ID
			pstmt.setString(1, pageId);

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得查询结果
				childPageActionCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回查询结果数
		return childPageActionCount;
	}

	/**
	 * 取得最顶层页面一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 最顶层页面一览
	 * @throws 无
	 */
	public List<TreeModel> getPages() {
		// 最顶层页面一览
		List<TreeModel> pages = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmp.page_id,");
		sql.append(" pmp.page_name");
		sql.append(" from");
		sql.append(" p_m_page pmp");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 页面对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 页面ID
				modelKey = resultSet.getString("page_id");
				treeModel.setModelKey(modelKey);
				// 页面名称
				treeModel.setModelName(resultSet.getString("page_name"));

				// 加入到最顶层页面一览
				pages.add(treeModel);

			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回最顶层页面一览
		return pages;
	}
}
