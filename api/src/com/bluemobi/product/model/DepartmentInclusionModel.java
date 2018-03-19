/**
 * FileName:DepartmentInclusionModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 部门关系实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class DepartmentInclusionModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -7728459559920960136L;

	/** 公司ID */
	private String companyId;

	/** 父部门ID */
	private String parentDepartmentId;

	/** 子部门ID */
	private String departmentId;

	/** 深度 */
	private long depth;

	/**
	 * 公司ID的取得。
	 * 
	 * @return 公司ID
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 公司ID的设定。
	 * 
	 * @param pCompanyId
	 *            公司ID
	 */
	public void setCompanyId(String pCompanyId) {
		this.companyId = pCompanyId;
	}

	/**
	 * 父部门ID的取得。
	 * 
	 * @return 父部门ID
	 */
	public String getParentDepartmentId() {
		return parentDepartmentId;
	}

	/**
	 * 父部门ID的设定。
	 * 
	 * @param pParentDepartmentId
	 *            父部门ID
	 */
	public void setParentDepartmentId(String pParentDepartmentId) {
		this.parentDepartmentId = pParentDepartmentId;
	}

	/**
	 * 子部门ID的取得。
	 * 
	 * @return 子部门ID
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 子部门ID的设定。
	 * 
	 * @param pDepartmentId
	 *            子部门ID
	 */
	public void setDepartmentId(String pDepartmentId) {
		this.departmentId = pDepartmentId;
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
