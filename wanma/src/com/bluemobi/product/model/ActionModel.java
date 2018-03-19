/**
 * FileName:ActionModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import java.util.List;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 画面功能定义实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class ActionModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 4816804159876594599L;

	/** 功能ID */
	private String actionId;

	/** 画面ID */
	private String pageId;

	/** 画面名称 */
	private String pageName;

	/** 功能名称 */
	private String actionName;

	/** 功能职位权限列表 */
	private List<ActionPostModel> actionPostList;

	/** 功能角色权限列表 */
	private List<ActionRoleModel> actionRoleList;

	/** 功能部门权限列表 */
	private List<ActionDepartmentModel> actionDeptList;

	/** 功能职位权限列表 */
	private List<CompanyPostModel> postList;

	/** 功能角色权限列表 */
	private List<RoleModel> roleList;

	/** 功能部门权限列表 */
	private List<DepartmentModel> deptList;

	/**
	 * 功能ID的取得。
	 * 
	 * @return 功能ID
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * 功能ID的设定。
	 * 
	 * @param pActionId
	 *            功能ID
	 */
	public void setActionId(String pActionId) {
		this.actionId = pActionId;
	}

	/**
	 * 画面ID的取得。
	 * 
	 * @return 画面ID
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * 画面ID的设定。
	 * 
	 * @param pPageId
	 *            画面ID
	 */
	public void setPageId(String pPageId) {
		this.pageId = pPageId;
	}

	/**
	 * 画面名称的取得。
	 * 
	 * @return 画面名称
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * 画面名称的设定。
	 * 
	 * @param pPageName
	 *            画面名称
	 */
	public void setPageName(String pPageName) {
		this.pageName = pPageName;
	}

	/**
	 * 功能名称的取得。
	 * 
	 * @return 功能名称
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * 功能名称的设定。
	 * 
	 * @param pActionName
	 *            功能名称
	 */
	public void setActionName(String pActionName) {
		this.actionName = pActionName;
	}

	/**
	 * @return the actionPostList
	 */
	public List<ActionPostModel> getActionPostList() {
		return actionPostList;
	}

	/**
	 * @param actionPostList
	 *            the actionPostList to set
	 */
	public void setActionPostList(List<ActionPostModel> actionPostList) {
		this.actionPostList = actionPostList;
	}

	/**
	 * @return the actionRoleList
	 */
	public List<ActionRoleModel> getActionRoleList() {
		return actionRoleList;
	}

	/**
	 * @param actionRoleList
	 *            the actionRoleList to set
	 */
	public void setActionRoleList(List<ActionRoleModel> actionRoleList) {
		this.actionRoleList = actionRoleList;
	}

	/**
	 * @return the actionDeptList
	 */
	public List<ActionDepartmentModel> getActionDeptList() {
		return actionDeptList;
	}

	/**
	 * @param actionDeptList
	 *            the actionDeptList to set
	 */
	public void setActionDeptList(List<ActionDepartmentModel> actionDeptList) {
		this.actionDeptList = actionDeptList;
	}

	/**
	 * @param deptList
	 *            the deptList to set
	 */
	public void setDeptList(List<DepartmentModel> deptList) {
		this.deptList = deptList;
	}

	/**
	 * @return the postList
	 */
	public List<CompanyPostModel> getPostList() {
		return postList;
	}

	/**
	 * @param postList
	 *            the postList to set
	 */
	public void setPostList(List<CompanyPostModel> postList) {
		this.postList = postList;
	}

	/**
	 * @return the roleList
	 */
	public List<RoleModel> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the deptList
	 */
	public List<DepartmentModel> getDeptList() {
		return deptList;
	}

}
