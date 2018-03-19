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
 * 用户职位实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class UserPostModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 7464217632110716239L;

	/** 用户ID */
	private String userId;

	/** 公司ID */
	private String companyId;

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
