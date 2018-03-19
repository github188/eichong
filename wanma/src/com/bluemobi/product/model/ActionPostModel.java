/**
 * FileName:ActionPostModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 画面功能职位权限实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class ActionPostModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -5639958125506326352L;

	/** 功能ID */
	private String actionId;

	/** 公司ID */
	private String companyId;

	/** 职位ID */
	private String postId;

	/** 追加删除标识 */
	private String prcessFlg;

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
