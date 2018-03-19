/**
 * FileName:RoleInclusionModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 角色关系实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class RoleInclusionModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 19690778141384547L;

	/** 父角色ID */
	private String parentRoleId;

	/** 角色ID */
	private String roleId;

	/** 深度 */
	private long depth;

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
	 * 子角色ID的取得。
	 * 
	 * @return 角色ID
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 子角色ID的设定。
	 * 
	 * @param pRoleId
	 *            角色ID
	 */
	public void setRoleId(String pRoleId) {
		this.roleId = pRoleId;
	}

	/**
	 * 深度的取得。
	 * 
	 * @return 深度
	 */
	public long getDepth() {
		return depth;
	}

	/**
	 * 深度的设定。
	 * 
	 * @param pDepth
	 *            深度
	 */
	public void setDepth(long pDepth) {
		this.depth = pDepth;
	}

}
