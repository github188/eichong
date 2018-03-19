/**
 * FileName:MenuTreeUtil.java
 * Author: Administrator
 * Create: 2014年7月17日
 * Last Modified: 2014年7月17日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.bluemobi.product.common.dao.MenuDao;
import com.bluemobi.product.model.common.TreeModel;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月17日
 */
public class MenuTreeUtil extends TreeUtil {

	/**
	 * 取得维护用菜单一览（树形）
	 * 
	 * @return List<TreeModel> 维护用菜单一览（树形）
	 */
	public List<TreeModel> getMenuTreeData() {

		// 维护用菜单一览（树形）
		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 是否维护菜单
		boolean isMaintenance = true;

		// 取得维护用菜单一览（树形）
		treeDataList = getMenuTreeData(isMaintenance);

		// 返回维护用菜单一览（树形）
		return treeDataList;

	}

	/**
	 * 取得菜单数据（树形）
	 * 
	 * @param isMaintenance
	 *            是否是维护
	 * 
	 * @return List<TreeModel> 菜单数据（树形）
	 */
	public List<TreeModel> getMenuTreeData(boolean isMaintenance) {

		// 登录用户Id（默认为guest）
		String userLogin = "guest";

		// 取得登录用户Id
		userLogin = UserUtil.getLoginUserId();

		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 菜单DAO
		MenuDao menuDao = new MenuDao();

		// 最顶层菜单一览
		List<TreeModel> topDataList = null;

		if (isMaintenance) {
			// 取得最顶层菜单一览
			topDataList = menuDao.getTopMenus();
		} else {
			// 取得登录用户拥有权限的最顶层菜单一览
			topDataList = menuDao.getTopMenus(false, userLogin);
		}

		//
		// 如果最顶层菜单一览为空时，返回
		//
		if (topDataList == null || topDataList.size() < 1) {
			return treeDataList;
		}

		//
		//
		//
		for (TreeModel treeModel : topDataList) {
			addMenuChildData(menuDao, treeModel, isMaintenance);
		}

		treeDataList = topDataList;

		return treeDataList;

	}

	/**
	 * 追加子菜单数据
	 * 
	 * @param menuDao
	 *            菜单dao
	 * @param parentModel
	 *            父菜单对象
	 * @param isMaintenance
	 *            是否是维护
	 */
	public void addMenuChildData(MenuDao menuDao, TreeModel parentModel,
			boolean isMaintenance) {
		String modelKey = parentModel.getModelKey();
		String[] keys = modelKey.split(",");
		String menuId = keys[0];

		if (menuDao.getChildMenuCount(menuId) > 0) {

			List<TreeModel> childModelList = null;
			if (isMaintenance) {
				childModelList = menuDao.getRealChildList(menuId);

			} else {
				childModelList = menuDao.getRealChildList(menuId, false,
						UserUtil.getLoginUserId());
			}
			parentModel.setChildTreeModels(childModelList);
			parentModel.setHasChild(true);

			for (TreeModel childModel : childModelList) {
				String modelKey1 = childModel.getModelKey();
				String[] keys1 = modelKey1.split(",");
				String menuId1 = keys1[0];
				if (menuDao.getChildMenuCount(menuId1) > 0) {
					addMenuChildData(menuDao, childModel, isMaintenance);
				}
			}
		}

	}

	/**
	 * 
	 * @param treeList
	 * @return
	 */
	public String writeMenuTree(List<TreeModel> treeList) {
		StringBuffer strHtml = new StringBuffer("");

		if (treeList == null || treeList.size() == 0) {
			return strHtml.toString();
		}

		for (TreeModel treeModel : treeList) {
			if (treeModel.getHasChild()) {
				strHtml.append(getULStartString(true, treeModel));
				writeMenuChildTree(treeModel, strHtml, true);
				strHtml.append(getULEndString());
			} else {
				strHtml.append(getULStartString(true, treeModel));
				strHtml.append(getLIStartString());
				strHtml.append(getMenuNodeLink(treeModel));
				strHtml.append(getLIEndString());
				strHtml.append(getULEndString());
			}
		}

		return strHtml.toString();
	}

	public void writeMenuChildTree(TreeModel treeModel, StringBuffer strHtml,
			boolean isTop) {
		if (treeModel.getHasChild()) {
			strHtml.append(getLIStartString());
			strHtml.append(getMenuNodeLink(treeModel));
			strHtml.append(getULStartString(false, treeModel));
			List<TreeModel> childList = treeModel.getChildTreeModels();
			for (TreeModel childModel : childList) {
				writeMenuChildTree(childModel, strHtml, false);
			}
			strHtml.append(getULEndString());
			strHtml.append(getLIEndString());
		} else {
			strHtml.append(getLIStartString());
			strHtml.append(getMenuNodeLink(treeModel));
			strHtml.append(getLIEndString());

		}
	}

	public String getMenuNodeLink(TreeModel treeModel) {
		StringBuffer nodeLinkString = new StringBuffer("");
		nodeLinkString.append("<a");

		// 菜单链接
		String menuLink = "";
		// 菜单链接
		String linkRel = "";
		// 菜单ID
		String[] menuIds = treeModel.getModelKey().split(",");

		if (menuIds.length == 3) {
			menuLink = menuIds[1];
			linkRel = menuIds[2];
			if (StringUtils.isEmpty(linkRel)) {
				linkRel = menuLink.substring(menuLink.lastIndexOf("/") + 1,
						menuLink.lastIndexOf(".do"));
			}
			if (menuLink.startsWith("/")) {
				menuLink = HttpServletRequestUtil.getHttpRequest()
						.getContextPath() + menuLink;
			}
		}
		nodeLinkString.append(" href=\"");
		if (!StringUtils.isEmpty(menuLink)) {
			nodeLinkString.append(menuLink);
			nodeLinkString.append("\"");
			nodeLinkString.append(" target=\"navTab\"");

			if (!StringUtils.isEmpty(linkRel)) {
				nodeLinkString.append(" rel=\"" + linkRel + "\"");
			}
		} else {
			nodeLinkString.append("javascript:void(0);");
			nodeLinkString.append("\"");

		}

		nodeLinkString.append(">");
		nodeLinkString.append(treeModel.getModelName());
		nodeLinkString.append("</a>");

		return nodeLinkString.toString();

	}

	public static void main(String[] args) {
		new MenuTreeUtil().testMenuTreeUtil();
	}

	public void testMenuTreeUtil() {
		List<TreeModel> testList = this.getMenuTreeData();
		System.out.println(writeTree(testList));
		for (TreeModel treeModel : testList) {
			if (treeModel.getHasChild()) {
				testPrintTree(treeModel);
			} else {
				System.out.println(treeModel.getModelName());
			}
		}
	}

	public void testPrintTree(TreeModel treeModel) {
		System.out.println(treeModel.getModelName());
		if (treeModel.getHasChild()) {
			List<TreeModel> childList = treeModel.getChildTreeModels();
			for (TreeModel childModel : childList) {
				testPrintTree(childModel);
			}
		}
	}

}
