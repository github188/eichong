/**
 * FileName:TreeUtil.java
 * Author: Administrator
 * Create: 2014年7月3日
 * Last Modified: 2014年7月3日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.List;

import com.bluemobi.product.common.dao.CompanyDao;
import com.bluemobi.product.common.dao.CompanyPostDao;
import com.bluemobi.product.common.dao.DepartmentDao;
import com.bluemobi.product.common.dao.PageActionDao;
import com.bluemobi.product.common.dao.RoleDao;
import com.bluemobi.product.model.common.TreeModel;
import com.wanma.model.TblUser;

/**
 * 树结构工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
public class TreeUtil {

	public String FOLDER_CLASS = "tree treeFolder";
	public String PRE = "tree treeFolder";

	/** 文件夹节点是否可以链接 */
	private boolean isFolderLinkable = true;

	/** 各节点是否可以链接 */
	private boolean isLinkable = true;

	/** 是否根据Key进行链接 */
	private boolean isLinkByKey = false;

	/** 是否统一链接 */
	private boolean isSameLink = true;

	/** 统一链接 */
	private boolean sameLink = true;

	/** 是否需要传递参数 */
	private boolean isPutParameter = false;

	/** 是否弹出画面 */
	private boolean isPop = false;

	/** 链接 */
	private String href;

	/** 显示目标标签 */
	private String target;

	/** 关联组件 */
	private String rel;

	/**
	 * 取得部门数据（树形）
	 * 
	 * @return List<TreeModel> 部门数据（树形）
	 */
	public List<TreeModel> getDeptTreeData() {

		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 部门DAO
		DepartmentDao departmentDao = new DepartmentDao();

		// 最顶层部门一览
		List<TreeModel> topDataList = null;
		// 部门关系中的最大深度
		long maxDepth = 0;

		// 取得最顶层部门一览
		topDataList = departmentDao.getTopDepartments();

		//
		// 如果最顶层部门一览为空时，返回
		//
		if (topDataList == null || topDataList.size() < 1) {
			return treeDataList;
		}

		// 取得最大深度
		maxDepth = departmentDao.getMaxDepth();

		//
		// 如果最最大深度小于1时，返回
		//
		if (maxDepth < 1) {
			return topDataList;
		}

		//
		//
		//
		for (TreeModel treeModel : topDataList) {
			addDeptChildData(departmentDao, treeModel);
		}

		treeDataList = topDataList;

		return treeDataList;

	}

	/**
	 * 追加子部门数据
	 * 
	 * @param departmentDao
	 *            部门dao
	 * @param parentModel
	 *            父部门对象
	 */
	public void addDeptChildData(DepartmentDao departmentDao,
			TreeModel parentModel) {
		String modelKey = parentModel.getModelKey();
		String[] keys = modelKey.split(",");
		String companyId = keys[0];
		String departmentId = keys[1];

		if (departmentDao.getChildDeptCount(companyId, departmentId) > 0) {
			List<TreeModel> childModelList = departmentDao.getRealChildList(
					companyId, departmentId);
			parentModel.setChildTreeModels(childModelList);
			parentModel.setHasChild(true);
			for (TreeModel childModel : childModelList) {
				String modelKey1 = childModel.getModelKey();
				String[] keys1 = modelKey1.split(",");
				String companyId1 = keys1[0];
				String departmentId1 = keys1[1];
				if (departmentDao.getChildDeptCount(companyId1, departmentId1) > 0) {
					addDeptChildData(departmentDao, childModel);
				}
			}
		}

	}

	/**
	 * 取得角色数据（树形）
	 * 
	 * @return List<TreeModel> 角色数据（树形）
	 */
	public List<TreeModel> getRoleTreeData(TblUser loginUser) {

		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 角色DAO
		RoleDao roleDao = new RoleDao();

		// 最顶层角色一览
		List<TreeModel> topDataList = null;
		// 角色关系中的最大深度
		long maxDepth = 0;

		// 取得最顶层角色一览
		topDataList = roleDao.getTopRoles(loginUser);

		//
		// 如果最顶层角色一览为空时，返回
		//
		if (topDataList == null || topDataList.size() < 1) {
			return treeDataList;
		}

		// 取得最大深度
		maxDepth = roleDao.getMaxDepth();

		//
		// 如果最最大深度小于1时，返回
		//
		if (maxDepth < 1) {
			return topDataList;
		}

		//
		//
		//
		for (TreeModel treeModel : topDataList) {
			addRoleChildData(roleDao, treeModel,loginUser);
		}

		treeDataList = topDataList;

		return treeDataList;

	}

	/**
	 * 追加子角色数据
	 * 
	 * @param roleDao
	 *            角色dao
	 * @param parentModel
	 *            父角色对象
	 */
	public void addRoleChildData(RoleDao roleDao, TreeModel parentModel,TblUser loginUser) {
		String modelKey = parentModel.getModelKey();
		String roleId = modelKey;

		if (roleDao.getChildRoleCount(roleId) > 0) {
			List<TreeModel> childModelList = roleDao.getRealChildList(roleId,loginUser);
			parentModel.setChildTreeModels(childModelList);
			parentModel.setHasChild(true);
			for (TreeModel childModel : childModelList) {
				String modelKey1 = childModel.getModelKey();
				String roleId1 = modelKey1;
				if (roleDao.getChildRoleCount(roleId1) > 0) {
					addRoleChildData(roleDao, childModel,loginUser);
				}
			}
		}

	}

	/**
	 * 取得公司数据（树形）
	 * 
	 * @return 公司数据（树形）
	 */
	public List<TreeModel> getCompanyTreeData() {

		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 角色DAO
		DepartmentDao departmentDao = new DepartmentDao();

		// 最顶层角色一览
		List<TreeModel> topDataList = null;
		// 取得最顶层角色一览
		topDataList = departmentDao.getTopDepartments();

		treeDataList = topDataList;

		return treeDataList;
	}

	/**
	 * 取得公司职位数据（树形）
	 * 
	 * @return 公司职位数据（树形）
	 */
	public List<TreeModel> getPostTreeData() {

		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 公司DAO
		CompanyDao companyDao = new CompanyDao();
		// 公司职位DAO
		CompanyPostDao companyPostDao = new CompanyPostDao();

		// 公司一览
		List<TreeModel> companyDataList = null;
		// 取得司一览
		companyDataList = companyDao.getCompanyList();

		if (companyDataList == null || companyDataList.size() == 0) {
			return treeDataList;
		}

		for (TreeModel treeModel : companyDataList) {
			// 公司ID
			String companyId = "";
			// key
			String[] keys = treeModel.getModelKey().split(",");

			if (keys.length > 0) {
				companyId = keys[0];
			}

			List<TreeModel> postDataList = companyPostDao
					.getCompanyPostList(companyId);
			if (postDataList != null && postDataList.size() > 0) {
				treeModel.setHasChild(true);
				treeModel.setChildTreeModels(postDataList);
			}
		}

		treeDataList = companyDataList;

		return treeDataList;
	}

	/**
	 * 取得页面功能数据（树形）
	 * 
	 * @return List<TreeModel> 页面功能数据（树形）
	 */
	/*public List<TreeModel> getPageActionTreeData() {

		List<TreeModel> treeDataList = new ArrayList<TreeModel>();

		// 页面功能DAO
		PageActionDao pageActionDao = new PageActionDao();

		// 页面功能一览
		List<TreeModel> pageList = null;

		// 取得页面一览
		pageList = pageActionDao.getPages();

		//
		// 如果页面一览为空时，返回
		//
		if (pageList == null || pageList.size() < 1) {
			return treeDataList;
		}

		//
		// 追加页面功能一览
		//
		for (TreeModel treeModel : pageList) {
			addPageActionData(pageActionDao, treeModel);
		}

		treeDataList = pageList;

		return treeDataList;

	}*/

	/**
	 * 追加页面功能一览
	 * 
	 * @param pageActionDao
	 *            页面功能dao
	 * @param pageModel
	 *            页面对象
	 */
	/*public void addPageActionData(PageActionDao pageActionDao,
			TreeModel pageModel) {
		String modelKey = pageModel.getModelKey();
		String pageId = modelKey;

		if (pageActionDao.getPageActionCount(pageId) > 0) {
			List<TreeModel> pageActionlList = pageActionDao
					.getPageActionList(pageId);
			pageModel.setChildTreeModels(pageActionlList);
			pageModel.setHasChild(true);
		}

	}*/

	/**
	 * 
	 * @param treeList
	 * @return
	 */
	public String writeTree(List<TreeModel> treeList) {
		StringBuffer strHtml = new StringBuffer("");

		if (treeList == null || treeList.size() == 0) {
			return strHtml.toString();
		}

		for (TreeModel treeModel : treeList) {
			if (treeModel.getHasChild()) {
				strHtml.append(getULStartString(true, treeModel));
				writeChildTree(treeModel, strHtml, true);
				strHtml.append(getULEndString());
			} else {
				strHtml.append(getULStartString(true, treeModel));
				strHtml.append(getLIStartString());
				strHtml.append(getNodeLink(treeModel));
				strHtml.append(getLIEndString());
				strHtml.append(getULEndString());
			}
		}

		return strHtml.toString();
	}

	public void writeChildTree(TreeModel treeModel, StringBuffer strHtml,
			boolean isTop) {
		if (treeModel.getHasChild()) {
			strHtml.append(getLIStartString());
			strHtml.append(getNodeLink(treeModel));
			strHtml.append(getULStartString(false, treeModel));
			List<TreeModel> childList = treeModel.getChildTreeModels();
			for (TreeModel childModel : childList) {
				writeChildTree(childModel, strHtml, false);
			}
			strHtml.append(getULEndString());
			strHtml.append(getLIEndString());
		} else {
			strHtml.append(getLIStartString());
			strHtml.append(getNodeLink(treeModel));
			strHtml.append(getLIEndString());

		}
	}

	public String getULStartString(boolean isTop, TreeModel treeModel) {
		StringBuffer ulStartString = new StringBuffer("");
		if (isTop) {
			ulStartString.append("<ul class=\"tree treeFolder collapse\">");
		} else {
			ulStartString.append("<ul>");

		}
		return ulStartString.toString();
	}

	public String getULEndString() {
		StringBuffer ulEndString = new StringBuffer("");

		ulEndString.append("</ul>");

		return ulEndString.toString();
	}

	public String getLIStartString() {
		StringBuffer ulStartString = new StringBuffer("");

		ulStartString.append("<li>");

		return ulStartString.toString();
	}

	public String getNodeLink(TreeModel treeModel) {
		StringBuffer nodeLinkString = new StringBuffer("");
		nodeLinkString.append("<a");
		if (StringUtil.isNotEmpty(href)) {
			nodeLinkString.append(" href=\"" + href);
			if (this.isPutParameter) {
				nodeLinkString.append("?modelKey=" + treeModel.getModelKey());
			}
			nodeLinkString.append("\"");
		}
		if (StringUtil.isNotEmpty(target)) {
			nodeLinkString.append(" target=\"" + target + "\"");
		}
		if (StringUtil.isNotEmpty(rel)) {
			nodeLinkString.append(" rel=\"" + rel + "\"");
		}
		nodeLinkString.append(">");
		nodeLinkString.append(treeModel.getModelName());
		nodeLinkString.append("</a>");

		return nodeLinkString.toString();

	}

	public String getLIEndString() {
		StringBuffer liEndString = new StringBuffer("");

		liEndString.append("</li>");

		return liEndString.toString();
	}

	// public void main(String[] args) {
	// List<TreeModel> testList = this.getDeptTreeData();
	// System.out.println(writeTree(testList));
	// for (TreeModel treeModel : testList) {
	// if (treeModel.getHasChild()) {
	// testPrintTree(treeModel);
	// } else {
	// System.out.println(treeModel.getModelName());
	// }
	// }
	// }
	//
	// public void testPrintTree(TreeModel treeModel) {
	// System.out.println(treeModel.getModelName());
	// if (treeModel.getHasChild()) {
	// List<TreeModel> childList = treeModel.getChildTreeModels();
	// for (TreeModel childModel : childList) {
	// testPrintTree(childModel);
	// }
	// }
	// }

	/**
	 * 文件夹节点是否可以链接的取得。
	 * 
	 * @return 文件夹节点是否可以链接
	 */
	public boolean getIsFolderLinkable() {
		return isFolderLinkable;
	}

	/**
	 * 文件夹节点是否可以链接的设定。
	 * 
	 * @param pIsFolderLinkable
	 *            文件夹节点是否可以链接
	 */
	public void setIsFolderLinkable(boolean pIsFolderLinkable) {
		this.isFolderLinkable = pIsFolderLinkable;
	}

	/**
	 * 是否需要传递参数的取得。
	 * 
	 * @return 是否需要传递参数
	 */
	public boolean getIsPutParameter() {
		return isPutParameter;
	}

	/**
	 * 是否需要传递参数的设定。
	 * 
	 * @param pIsPutParameter
	 *            是否需要传递参数
	 */
	public void setIsPutParameter(boolean pIsPutParameter) {
		this.isPutParameter = pIsPutParameter;
	}

	/**
	 * 是否根据Key进行链接的取得。
	 * 
	 * @return 是否根据Key进行链接
	 */
	public boolean getIsLinkByKey() {
		return isLinkByKey;
	}

	/**
	 * 是否根据Key进行链接的设定。
	 * 
	 * @param pIsLinkByKey
	 *            是否根据Key进行链接
	 */
	public void setIsLinkByKey(boolean pIsLinkByKey) {
		this.isLinkByKey = pIsLinkByKey;
	}

	/**
	 * 是否统一链接的取得。
	 * 
	 * @return 是否统一链接
	 */
	public boolean getIsSameLink() {
		return isSameLink;
	}

	/**
	 * 是否统一链接的设定。
	 * 
	 * @param pIsSameLink
	 *            是否统一链接
	 */
	public void setIsSameLink(boolean pIsSameLink) {
		this.isSameLink = pIsSameLink;
	}

	/**
	 * 统一链接的取得。
	 * 
	 * @return 统一链接
	 */
	public boolean getSameLink() {
		return sameLink;
	}

	/**
	 * 统一链接的设定。
	 * 
	 * @param pSameLink
	 *            统一链接
	 */
	public void setSameLink(boolean pSameLink) {
		this.sameLink = pSameLink;
	}

	/**
	 * 各节点是否可以链接的取得。
	 * 
	 * @return 各节点是否可以链接
	 */
	public boolean getIsLinkable() {
		return isLinkable;
	}

	/**
	 * 各节点是否可以链接的设定。
	 * 
	 * @param pIsLinkable
	 *            各节点是否可以链接
	 */
	public void setIsLinkable(boolean pIsLinkable) {
		this.isLinkable = pIsLinkable;
	}

	/**
	 * 链接的取得。
	 * 
	 * @return 链接
	 */
	public String getHref() {
		return href;
	}

	/**
	 * 链接的设定。
	 * 
	 * @param pHref
	 *            链接
	 */
	public void setHref(String pHref) {
		this.href = pHref;
	}

	/**
	 * 显示目标标签的取得。
	 * 
	 * @return 显示目标标签
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 显示目标标签的设定。
	 * 
	 * @param pTarget
	 *            显示目标标签
	 */
	public void setTarget(String pTarget) {
		this.target = pTarget;
	}

	/**
	 * 关联组件的取得。
	 * 
	 * @return 关联组件
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * 关联组件的设定。
	 * 
	 * @param pRel
	 *            关联组件
	 */
	public void setRel(String pRel) {
		this.rel = pRel;
	}

	/**
	 * 是否弹出画面的取得。
	 * 
	 * @return 是否弹出画面
	 */
	public boolean getIsPop() {
		return isPop;
	}

	/**
	 * 是否弹出画面的设定。
	 * 
	 * @param pIsPop
	 *            是否弹出画面
	 */
	public void setIsPop(boolean pIsPop) {
		this.isPop = pIsPop;
	}

}
