/**
 * FileName:MenuDao.java
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
import com.bluemobi.product.utils.UserUtil;

/**
 * 菜单DAO
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class MenuDao extends CommonDao {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(MenuDao.class);

	/**
	 * 取得菜单的亲子菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param menuId
	 *            菜单ID
	 * @return List<TreeModel> 亲子菜单一览
	 * @throws 无
	 */
	public List<TreeModel> getRealChildList(String menuId) {
		// 亲子菜单一览
		List<TreeModel> realChildList = new ArrayList<TreeModel>();
		// 是否维护菜单
		boolean isMaintenance = true;

		// 取得亲子菜单一览
		realChildList = this.getRealChildList(menuId, isMaintenance, null);

		// 返回亲子菜单一览
		return realChildList;

	}

	/**
	 * 取得菜单的亲子菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param menuId
	 *            菜单ID
	 * @param isMaintenance
	 *            是否是维护
	 * @param userId
	 *            用户ID
	 * @return List<TreeModel> 亲子菜单一览
	 * @throws 无
	 */
	public List<TreeModel> getRealChildList(String menuId,
			boolean isMaintenance, String userId) {
		// 亲子菜单一览
		List<TreeModel> realChildList = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmm.menu_id,");
		sql.append(" pmm.url,");
		sql.append(" pmm.rel,");
		sql.append(" pmm.contents");
		sql.append(" from");
		sql.append(" p_m_menu pmm");
		sql.append(" where");
		sql.append(" pmm.parent_menu_id = ?");

		//
		// 如果不是菜单维护且登录用户不是超级用户，加上权限控制SQL语句
		//
		if (!isMaintenance && !UserUtil.isSuperUser()) {
			sql.append(getMenuAuthSql());
		}
		sql.append(" order by pmm.sort_number asc, pmm.menu_id asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 菜单ID
			pstmt.setString(1, menuId);

			//
			// 如果不是菜单维护且登录用户不是超级用户，为权限控制SQL语句设置参数
			//
			if (!isMaintenance && !UserUtil.isSuperUser()) {
				// 设置参数
				pstmt.setString(2, userId);
				pstmt.setString(3, userId);
				pstmt.setString(4, userId);
			}

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 菜单对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 菜单ID
				modelKey = resultSet.getString("menu_id");
				// 链接URL
				modelKey = modelKey + "," + resultSet.getString("url");
				// 标签
				modelKey = modelKey + "," + resultSet.getString("rel");

				treeModel.setModelKey(modelKey);
				// 菜单名称
				treeModel.setModelName(resultSet.getString("contents"));

				// 加入到亲子菜单一览
				realChildList.add(treeModel);
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回亲子菜单一览
		return realChildList;
	}

	/**
	 * 取得菜单的子菜单数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param menuId
	 *            菜单ID
	 * @return int 最大深度
	 * @throws 无
	 */
	public long getChildMenuCount(String menuId) {
		// 子菜单数
		long childMenuCount = 0;
		// 是否维护菜单
		boolean isMaintenance = true;

		// 取得子菜单数
		childMenuCount = getChildMenuCount(menuId, isMaintenance, null);

		// 返回取得子菜单数
		return childMenuCount;

	}

	/**
	 * 取得菜单的子菜单数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @param menuId
	 *            菜单ID
	 * @param isMaintenance
	 *            是否是维护
	 * @param userId
	 *            用户ID
	 * @return long 子菜单数
	 * @throws 无
	 */
	public long getChildMenuCount(String menuId, boolean isMaintenance,
			String userId) {
		// 子菜单数
		long childMenuCount = 0;

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" count(pmm.menu_id) data_count");
		sql.append(" from");
		sql.append(" p_m_menu pmm");
		sql.append(" where");
		sql.append(" pmm.parent_menu_id = ?");

		//
		// 如果不是菜单维护且登录用户不是超级用户，加上权限控制SQL语句
		//
		if (!isMaintenance && !UserUtil.isSuperUser()) {
			sql.append(getMenuAuthSql());
		}

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 设置SQL参数值
			//
			// 菜单ID
			pstmt.setString(1, menuId);

			//
			// 如果不是菜单维护且登录用户不是超级用户，为权限控制SQL语句设置参数
			//
			if (!isMaintenance && !UserUtil.isSuperUser()) {
				// 设置参数
				pstmt.setString(2, userId);
				pstmt.setString(3, userId);
				pstmt.setString(4, userId);
			}

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				// 取得子菜单数
				childMenuCount = resultSet.getLong("data_count");
			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回子菜单数
		return childMenuCount;
	}

	/**
	 * 取得最顶层菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * 
	 * @return List<TreeModel> 最顶层菜单一览
	 * @throws 无
	 */
	public List<TreeModel> getTopMenus() {

		// 维护用最顶层菜单一览（树形）
		List<TreeModel> topMenus = new ArrayList<TreeModel>();

		// 是否维护菜单
		boolean isMaintenance = true;

		// 取得最顶层菜单一览（树形）
		topMenus = getTopMenus(isMaintenance, null);

		// 返回最顶层菜单一览（树形）
		return topMenus;

	}

	/**
	 * 取得最顶层菜单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param isMaintenance
	 *            是否是维护
	 * @param userId
	 *            用户ID
	 * 
	 * @return List<TreeModel> 最顶层菜单一览
	 * @throws 无
	 */
	public List<TreeModel> getTopMenus(boolean isMaintenance, String userId) {
		// 最顶层菜单一览
		List<TreeModel> topMenus = new ArrayList<TreeModel>();

		// 数据库连接
		Connection connection = this.getConnection();

		StringBuffer sql = new StringBuffer();

		// SQL生成
		sql.append("select ");
		sql.append(" pmm.menu_id,");
		sql.append(" pmm.url,");
		sql.append(" pmm.rel,");
		sql.append(" pmm.contents");
		sql.append(" from");
		sql.append(" p_m_menu pmm");
		sql.append(" where");
		sql.append(" pmm.parent_menu_id is null ");
		sql.append(" or pmm.parent_menu_id = '' ");

		//
		// 如果不是菜单维护且登录用户不是超级用户，加上权限控制SQL语句
		//
		if (!isMaintenance && !UserUtil.isSuperUser()) {
			sql.append(getMenuAuthSql());
		}

		sql.append(" order by pmm.sort_number asc, pmm.menu_id asc");

		try {
			// SQL执行准备
			pstmt = connection.prepareStatement(sql.toString());

			//
			// 如果不是菜单维护且登录用户不是超级用户，为权限控制SQL语句设置参数
			//
			if (!isMaintenance && !UserUtil.isSuperUser()) {
				// 设置参数
				pstmt.setString(1, userId);
				pstmt.setString(2, userId);
				pstmt.setString(3, userId);
			}

			// 执行查询处理
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// 菜单对象
				TreeModel treeModel = new TreeModel();
				String modelKey = "";
				//
				// 取得查询结果
				//
				// 菜单ID
				modelKey = resultSet.getString("menu_id");
				// 链接URL
				modelKey = modelKey + "," + resultSet.getString("url");
				// 标签
				modelKey = modelKey + "," + resultSet.getString("rel");

				treeModel.setModelKey(modelKey);
				// 菜单名称
				treeModel.setModelName(resultSet.getString("contents"));

				// 加入到最顶层菜单一览
				topMenus.add(treeModel);

			}
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}

		// 关闭相关连接
		coles();

		// 返回最顶层菜单一览
		return topMenus;
	}

	/**
	 * 生成权限SQL
	 * 
	 * @return String 权限SQL
	 */
	private String getMenuAuthSql() {

		// 权限SQL
		StringBuffer sql = new StringBuffer();

		// 生成权限SQL
		sql.append(" and (exists( select ");
		sql.append("     pmmr.menu_id");
		sql.append(" from");
		sql.append("     p_m_menu_role pmmr,");
		sql.append("     p_m_user_role pmur,");
		sql.append("     p_m_user pmu");
		sql.append(" where");
		sql.append("     pmmr.role_id = pmur.role_id");
		sql.append("  and pmur.user_id = pmu.user_id");
		sql.append("  and pmm.menu_id = pmmr.menu_id");
		sql.append("  and pmu.user_id = ?)");
		sql.append(" or exists( select ");
		sql.append("     pmmp.menu_id");
		sql.append(" from");
		sql.append("     p_m_menu_post pmmp,");
		sql.append("     p_m_user_post pmup,");
		sql.append("     p_m_user pmu");
		sql.append(" where");
		sql.append("     pmmp.company_id = pmup.company_id");
		sql.append("  and pmmp.post_id = pmup.post_id");
		sql.append("  and pmup.user_id = pmu.user_id");
		sql.append("  and pmm.menu_id = pmmp.menu_id");
		sql.append("  and pmu.user_id = ?)");
		sql.append(" or exists( select ");
		sql.append("     pmmd.menu_id");
		sql.append(" from");
		sql.append("     p_m_menu_department pmmd,");
		sql.append("     p_m_user_department pmud,");
		sql.append("     p_m_user pmu");
		sql.append(" where");
		sql.append("     pmmd.company_id = pmud.company_id");
		sql.append("  and pmmd.department_id = pmud.department_id");
		sql.append("  and pmud.user_id = pmu.user_id");
		sql.append("  and pmm.menu_id = pmmd.menu_id");
		sql.append("  and pmu.user_id = ?))");

		// 返回权限SQL
		return sql.toString();

	}
}
