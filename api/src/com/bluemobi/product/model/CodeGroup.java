/** 
 * FileName CodeGroup.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.model;

import java.util.List;

import com.bluemobi.product.model.common.BasicListModel;

/**
 * FileName CodeGroup.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 共有code组bean类
 */
public class CodeGroup extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/** CODE组 */
	private String codeGroupId;

	/** CODE组名称 */
	private String codeGroupName;

	/** CODE详细 */
	private List<CodeDetail> codeDetail;

	/**
	 * CODE组取得
	 * 
	 * @return CODE组
	 */
	public String getCodeGroupId() {
		return codeGroupId;
	}

	/**
	 * CODE组设定。
	 * 
	 * @param pCodeGroupId
	 *            CODE组
	 */
	public void setCodeGroupId(String pCodeGroupId) {
		this.codeGroupId = pCodeGroupId;
	}

	/**
	 * CODE组名称取得
	 * 
	 * @return CODE组名称
	 */
	public String getCodeGroupName() {
		return codeGroupName;
	}

	/**
	 * CODE组名称设定。
	 * 
	 * @param pCodeGroupName
	 *            CODE组名称
	 */
	public void setCodeGroupName(String pCodeGroupName) {
		this.codeGroupName = pCodeGroupName;
	}

	/**
	 * CODE详细取得
	 * 
	 * @return CODE详细
	 */
	public List<CodeDetail> getCodeDetail() {
		return codeDetail;
	}

	/**
	 * CODE详细设定。
	 * 
	 * @param pCodeDetail
	 *            CODE详细
	 */
	public void setCodeDetail(List<CodeDetail> pCodeDetail) {
		this.codeDetail = pCodeDetail;
	}

}
