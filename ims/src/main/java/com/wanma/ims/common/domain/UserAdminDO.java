package com.wanma.ims.common.domain;

/**
 * 管理员DO
 */
public class UserAdminDO extends UserDO {

	private static final long serialVersionUID = 9169026234851229569L;
	// 主键
	private Long userId;
	private String adminName;
	private String adminPhone;
	private String isCpyEp; // 渠道的桩设置给该管理员 0.否 1.是
	private String roleId; // 角色ID
	private String roleName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getIsCpyEp() {
		return isCpyEp;
	}

	public void setIsCpyEp(String isCpyEp) {
		this.isCpyEp = isCpyEp;
	}

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
}
