/**
 * FileName:UserDepartmentModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 用户部门实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class UserDepartmentModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 7464217632110716239L;

	/** 用户ID */
	private String userId;

	/** 用户名称 */
	private String userName;

	/** 公司ID */
	private String companyId;

	/** 部门ID */
	private String departmentId;

	/** 职位ID */
	private String postId;

	/** 追加删除标识 */
	private String prcessFlg;

	/**
	 * 用户ID的取得。
	 * 
	 * @return 用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户ID的设定。
	 * 
	 * @param pUserId
	 *            用户ID
	 */
	public void setUserId(String pUserId) {
		this.userId = pUserId;
	}

	/**
	 * 用户名称的取得。
	 * 
	 * @return 用户名称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户名称的设定。
	 * 
	 * @param pUserName
	 *            用户名称
	 */
	public void setUserName(String pUserName) {
		this.userName = pUserName;
	}

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
	 * 部门ID的取得。
	 * 
	 * @return 部门ID
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * 部门ID的设定。
	 * 
	 * @param pDepartmentId
	 *            部门ID
	 */
	public void setDepartmentId(String pDepartmentId) {
		this.departmentId = pDepartmentId;
	}

	/**
	 * 职位ID的取得。
	 * 
	 * @return 职位ID
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * 职位ID的设定。
	 * 
	 * @param pPostId
	 *            职位ID
	 */
	public void setPostId(String pPostId) {
		this.postId = pPostId;
	}

	/**
	 * 追加删除标识的取得。
	 * 
	 * @return 追加删除标识
	 */
	public String getPrcessFlg() {
		return prcessFlg;
	}

	/**
	 * 追加删除标识的设定。
	 * 
	 * @param pPrcessFlg
	 *            追加删除标识
	 */
	public void setPrcessFlg(String pPrcessFlg) {
		this.prcessFlg = pPrcessFlg;
	}
}
