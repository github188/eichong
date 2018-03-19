/**
 * FileName:RoleModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import java.util.List;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.model.common.BasicListModel;

/**
 * 角色实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class RoleModel extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 4896055021710137409L;

	/** 角色ID */
	private String roleId;

	/** 角色名称 */
	private String roleName;

	/** 角色分类 1:超级管理员，2：普通管理员，3：纯商家，4子商家，5个体商家 */
	private String category;

	/** 备注 */
	private String notes;

	/** 是否有子角色 */
	private boolean hasChild;

	/** 父角色ID */
	private String parentRoleId;
    private String createUser;
    private String menuId;
    private String userLevel;
    private String createUserAccount;
	/** 子角色列表 */
	private List<RoleModel> childRoles;

	/**
	 * 角色ID的取得。
	 * 
	 * @return 角色ID
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 角色ID的设定。
	 * 
	 * @param pRoleId
	 *            角色ID
	 */
	public void setRoleId(String pRoleId) {
		this.roleId = pRoleId;
	}

	/**
	 * 角色名称的取得。
	 * 
	 * @return 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名称的设定。
	 * 
	 * @param pRoleName
	 *            角色名称
	 */
	public void setRoleName(String pRoleName) {
		this.roleName = pRoleName;
	}

	/**
	 * 角色分类的取得。
	 * 
	 * @return 角色分类
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 角色分类的设定。
	 * 
	 * @param pCategory
	 *            角色分类
	 */
	public void setCategory(String pCategory) {
		this.category = pCategory;
	}

	/**
	 * 备注的取得。
	 * 
	 * @return 备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 备注的设定。
	 * 
	 * @param pNotes
	 *            备注
	 */
	public void setNotes(String pNotes) {
		this.notes = pNotes;
	}

	/**
	 * 是否有子角色的取得。
	 * 
	 * @return 是否有子角色
	 */
	public boolean getHasChild() {
		return hasChild;
	}

	/**
	 * 是否有子角色的设定。
	 * 
	 * @param pHasChild
	 *            是否有子角色
	 */
	public void setHasChild(boolean pHasChild) {
		this.hasChild = pHasChild;
	}

	/**
	 * 父角色ID的取得。
	 * 
	 * @return 父角色ID
	 */
	public String getParentRoleId() {
		return parentRoleId;
	}

	/**
	 * 父角色ID的设定。
	 * 
	 * @param pParentRoleId
	 *            父角色ID
	 */
	public void setParentRoleId(String pParentRoleId) {
		this.parentRoleId = pParentRoleId;
	}

	/**
	 * 子角色列表的取得。
	 * 
	 * @return 子角色列表
	 */
	public List<RoleModel> getChildRoles() {
		return childRoles;
	}

	/**
	 * 子角色列表的设定。
	 * 
	 * @param pChildRoles
	 *            子角色列表
	 */
	public void setChildRoles(List<RoleModel> pChildRoles) {
		this.childRoles = pChildRoles;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	

	public String getCreateUserAccount() {
		return createUserAccount;
	}

	public void setCreateUserAccount(String createUserAccount) {
		this.createUserAccount = createUserAccount;
	}

	
	
	@Override
	public String toString() {
		return "RoleModel [roleId=" + roleId + ", roleName=" + roleName
				+ ", category=" + category + ", notes=" + notes + ", hasChild="
				+ hasChild + ", parentRoleId=" + parentRoleId + ", createUser="
				+ createUser + ", menuId=" + menuId + ", userLevel="
				+ userLevel + ", createUserAccount=" + createUserAccount
				+ ", childRoles=" + childRoles + "]";
	}

	/**
	 * 从角色列表中删除指定角色对象
	 * 
	 * @param targetRoleList
	 *            角色列表
	 * @param targetRoleModel
	 *            指定角色对象
	 * @return 无
	 * @throws 无
	 */
	public static void removeRole(List<RoleModel> targetRoleList,
			RoleModel targetRoleModel) {
		if (targetRoleList == null || targetRoleList.size() == 0
				|| targetRoleModel == null
				|| targetRoleModel.getRoleId() == null) {
			return;
		}

		for (RoleModel roleModel : targetRoleList) {
			if (StringUtils.equals(roleModel.getRoleId(),
					targetRoleModel.getRoleId())) {
				targetRoleList.remove(roleModel);
				break;
			}
		}
	}
}
