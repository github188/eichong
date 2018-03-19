/** 
 * FileName CodeDetail.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicListModel;

/**
 * FileName CodeDetail.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 共有code详细bean类
 */
public class CodeDetail extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/** CODE组 */
	private String codeGroupId;

	/** CODE */
	private String codeId;

	/** CODE名称 */
	private String codeName;

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
	 * CODE取得
	 * 
	 * @return CODE
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * CODE设定。
	 * 
	 * @param pCodeId
	 *            CODE
	 */
	public void setCodeId(String pCodeId) {
		this.codeId = pCodeId;
	}

	/**
	 * CODE名称取得
	 * 
	 * @return CODE名称
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * CODE名称设定。
	 * 
	 * @param pCodeName
	 *            CODE名称
	 */
	public void setCodeName(String pCodeName) {
		this.codeName = pCodeName;
	}

}
