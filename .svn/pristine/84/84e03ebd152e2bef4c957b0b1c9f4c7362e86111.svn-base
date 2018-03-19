package com.pub.model;

import java.util.List;

import com.alibaba.druid.util.StringUtils;

/**
 * 角色实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class RoleModel extends Entity {

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean pHasChild) {
		this.hasChild = pHasChild;
	}

	public String getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(String pParentRoleId) {
		this.parentRoleId = pParentRoleId;
	}

	public List<RoleModel> getChildRoles() {
		return childRoles;
	}

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
