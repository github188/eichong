/**
 * FileName:AuthorizedSqlCreater.java
 * Author: Administrator
 * Create: 2014年7月8日
 * Last Modified: 2014年7月8日
 * Version: V1.0 
 */
package com.wanma.common;

import java.util.List;


/**
 * 权限控制SQL生成器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月8日
 */
public class AuthorizedSqlCreater {

	public static String getDataAuthSql(String originalSql,
			List<XMLModel> xmlModelList) {

		if (xmlModelList == null || xmlModelList.size() < 0) {
			return "";
		}

		String originalSqlPre = "";
		String originalSqlBck = "";

		StringBuffer strSql = new StringBuffer();
		StringBuffer newSql = new StringBuffer();

		// 权限对象表
//		String faceTable = DataAuthXmlReader.getValueFromList(xmlModelList,
//				DataAuthXmlReader.XML_TAGERT_AUTH_FACE_TABLE);
//		// 权限分类
//		String authType = DataAuthXmlReader.getValueFromList(xmlModelList,
//				DataAuthXmlReader.XML_TAGERT_AUTH_TYPE);
//		// 是否可以访问下级部门/角色数据
//		String childAccessable = DataAuthXmlReader.getValueFromList(
//				xmlModelList, DataAuthXmlReader.XML_TAGERT_AUTH_FACE_TABLE);
		// 是否有where条件
		String hasWhere = DataAuthXmlReader.getValueFromList(xmlModelList,
				DataAuthXmlReader.XML_TAGERT_HAS_WHERE);
		// 是否有Group By处理
		String hasGroupBy = DataAuthXmlReader.getValueFromList(xmlModelList,
				DataAuthXmlReader.XML_TAGERT_HAS_GROUP_BY);
		// 是否有having子句
		String hasHaving = DataAuthXmlReader.getValueFromList(xmlModelList,
				DataAuthXmlReader.XML_TAGERT_HAS_HAVING);
		// 是否有排序处理
		String hasOrderBy = DataAuthXmlReader.getValueFromList(xmlModelList,
				DataAuthXmlReader.XML_TAGERT_HAS_ORDER_BY);
		// 是否有limit处理
		String hasLimit = DataAuthXmlReader.getValueFromList(xmlModelList,
				DataAuthXmlReader.XML_TAGERT_HAS_LIMIT);

		//
		// SQL有where条件时，追加and关键字
		// SQL无where条件时，追加where关键字
		//
		if ("true".equalsIgnoreCase(hasWhere)) {
			strSql.append(" and ");
		} else {
			strSql.append(" where ");

		}

		// 数据权限SQL取得
//		strSql.append(getDataAuthSqlIdentify(faceTable, authType,
//				childAccessable));

		//
		// 除where外，还有其他限制排序等条件，按照SQL执行顺序，插入数据权限限制SQL
		//
		if ("true".equalsIgnoreCase(hasGroupBy)) {
			originalSqlPre = originalSql.substring(0,
					StringUtil.lastIndexOfIgnoreCase(originalSql, "group "));
			originalSqlBck = originalSql.substring(originalSql
					.lastIndexOf("group "));

		} else if ("true".equalsIgnoreCase(hasHaving)) {
			originalSqlPre = originalSql.substring(0,
					StringUtil.lastIndexOfIgnoreCase(originalSql, "having "));
			originalSqlBck = originalSql.substring(originalSql
					.lastIndexOf("having "));

		} else if ("true".equalsIgnoreCase(hasOrderBy)) {
			originalSqlPre = originalSql.substring(0,
					StringUtil.lastIndexOfIgnoreCase(originalSql, "order "));
			originalSqlBck = originalSql.substring(originalSql
					.lastIndexOf("order "));

		} else if ("true".equalsIgnoreCase(hasLimit)) {
			originalSqlPre = originalSql.substring(0,
					StringUtil.lastIndexOfIgnoreCase(originalSql, " limit "));
			originalSqlBck = originalSql.substring(originalSql
					.lastIndexOf(" limit "));
		} else {

			// 否则sql前半部分数据直接设置成原SQL
			originalSqlPre = originalSql;
		}

		newSql.append(originalSqlPre);
		newSql.append(strSql);
		newSql.append(originalSqlBck);

		return newSql.toString();
	}

	/**
	 * 权限控制SQL取得分析器
	 * 
	 * 
	 * @param faceTable
	 *            权限控制对象表
	 * @param authType
	 *            权限分类
	 * @param childAccessable
	 *            可否访问下级部门数据
	 * @return
	 */
//	private static String getDataAuthSqlIdentify(String faceTable,
//			String authType, String childAccessable) {
//		String strSql = "";
//
//		// 权限分类是按照部门控制的情况
//		if ("department".equalsIgnoreCase(authType)) {
//			strSql = getDataDeptAuthSql(faceTable, childAccessable);
//
//			// 权限分类是按照角色控制的情况
//		} else if ("role".equalsIgnoreCase(authType)) {
//			strSql = getDataRoleAuthSql(faceTable, childAccessable);
//
//			// 权限分类是按照部门和角色共同控制的情况
//		} else if ("deptPost".equalsIgnoreCase(authType)) {
//			strSql = getDataDeptPostAuthSql(faceTable, childAccessable);
//			// 其他情况，只能访问用户自身数据
//		} else {
//			// 登录用户
////			String loginUser = UserUtil.getLoginUserId();
////
////			strSql = faceTable + ".create_user = '" + loginUser + "' ";
//		}
//
//		return strSql;
//	}

	/**
	 * 取得基于部门控制的数据权限SQL
	 * 
	 * @param faceTable
	 *            权限控制对象表
	 * @param childAccessable
	 *            可否访问下级部门数据
	 * @return 基于部门控制的数据权限SQL
	 */
//	private static String getDataDeptAuthSql(String faceTable,
//			String childAccessable) {
//		// 登录用户
//		String loginUser = UserUtil.getLoginUserId();
//
//		StringBuffer strSql = new StringBuffer();
//
//		strSql.append(" (exists( select ");
//		strSql.append(" pmud.user_id");
//		strSql.append(" from");
//		strSql.append(" p_m_user_department pmud");
//		strSql.append(" where");
//		strSql.append(" pmud.user_id = " + faceTable + ".create_user");
//		strSql.append(" and pmud.department_id in");
//		strSql.append(" ( select ");
//		strSql.append(" pmdi.department_id");
//		strSql.append(" from");
//		strSql.append(" p_m_department_inclusion pmdi");
//		strSql.append(" where");
//		strSql.append(" pmdi.company_id = pmud.company_id");
//
//		//
//		// 如果不能访问下属部门
//		//
//		if ("false".equals(childAccessable)) {
//			strSql.append(" and pmdi.depth = 0");
//		}
//
//		strSql.append(" and pmdi.parent_department_id in (select ");
//		strSql.append(" pmud1.department_id");
//		strSql.append(" from");
//		strSql.append(" p_m_user_department pmud1");
//		strSql.append(" where");
//		strSql.append(" pmud1.company_id = pmud.company_id");
//		strSql.append(" and pmud1.user_id = '" + loginUser + "')))");
//		strSql.append(" or " + faceTable + ".create_user = '" + loginUser
//				+ "')");
//
//		// 返回基于部门控制的数据权限SQL
//		return strSql.toString();
//	}

	/**
	 * 取得基于部门职位控制的数据权限SQL
	 * 
	 * @param faceTable
	 *            权限控制对象表
	 * @param childAccessable
	 *            可否访问下级部门以及职位的数据
	 * @return 基于部门职位控制的数据权限SQL
	 */
//	private static String getDataDeptPostAuthSql(String faceTable,
//			String childAccessable) {
//		// 登录用户
//		String loginUser = UserUtil.getLoginUserId();
//
//		StringBuffer strSql = new StringBuffer();
//
//		strSql.append(" (exists( select ");
//		strSql.append(" pmud.user_id");
//		strSql.append(" from");
//		strSql.append(" p_m_user_department pmud");
//		strSql.append(" where");
//		strSql.append(" pmud.user_id = " + faceTable + ".create_user");
//		strSql.append(" and pmud.department_id in");
//		strSql.append(" ( select ");
//		strSql.append(" pmdi.department_id");
//		strSql.append(" from");
//		strSql.append(" p_m_department_inclusion pmdi");
//		strSql.append(" where");
//		strSql.append(" pmdi.company_id = pmud.company_id");
//
//		//
//		// 如果不能访问下属部门
//		//
//		if ("false".equals(childAccessable)) {
//			strSql.append(" and pmdi.depth = 0");
//		}
//
//		strSql.append(" and pmdi.parent_department_id in (select ");
//		strSql.append(" pmud1.department_id");
//		strSql.append(" from");
//		strSql.append(" p_m_user_department pmud1");
//		strSql.append(" where");
//		strSql.append(" pmud1.company_id = pmud.company_id");
//		strSql.append(" and pmud1.user_id = '" + loginUser + "')))");
//		strSql.append(" or " + faceTable + ".create_user = '" + loginUser
//				+ "')");
//
//		// 基于部门职位控制的数据权限SQL
//		return strSql.toString();
//	}

	/**
	 * 取得基于角色控制的数据权限SQL
	 * 
	 * @param faceTable
	 *            权限控制对象表
	 * @param childAccessable
	 *            可否访问下级角色数据
	 * @return 基于角色控制的数据权限SQL
	 */
//	private static String getDataRoleAuthSql(String faceTable,
//			String childAccessable) {
//		// 登录用户
//		String loginUser = UserUtil.getLoginUserId();
//
//		StringBuffer strSql = new StringBuffer();
//
//		strSql.append(" (exists( select ");
//		strSql.append(" pmur.user_id");
//		strSql.append(" from");
//		strSql.append(" p_m_user_role pmur");
//		strSql.append(" where");
//		strSql.append(" pmur.user_id = " + faceTable + ".create_user");
//		strSql.append(" and pmur.role_id in");
//		strSql.append(" ( select ");
//		strSql.append(" pmri.child_role_id");
//		strSql.append(" from");
//		strSql.append(" p_m_role_inclusion pmri");
//		strSql.append(" where");
//
//		//
//		// 如果不能访问下属角色
//		//
//		if ("false".equals(childAccessable)) {
//			strSql.append(" pmri.depth = 0 and ");
//		}
//
//		strSql.append(" pmri.parent_role_id in (select ");
//		strSql.append(" pmur1.role_id");
//		strSql.append(" from");
//		strSql.append(" p_m_user_role pmur1");
//		strSql.append(" where");
//		strSql.append(" pmur1.user_id = '" + loginUser + "')))");
//		strSql.append(" or " + faceTable + ".create_user = '" + loginUser
//				+ "')");
//
//		// 返回基于角色控制的数据权限SQL
//		return strSql.toString();
//	}
}
