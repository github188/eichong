/** 
 * FileName CommonListModel.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.model.common;

/**
 * FileName CommonListModel.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 基础数据模型类（分页显示用）
 */

public class BasicListModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -3768281254272946233L;

	/** 分页用对象 */
	private DwzPagerMySQL pager;

	/**
	 * 分页用对象取得
	 * 
	 * @return 分页用对象
	 */
	public DwzPagerMySQL getPager() {
		return pager;
	}

	/**
	 * 分页用对象设定
	 * 
	 * @param pPager
	 *            分页用对象
	 */
	public void setPager(DwzPagerMySQL pPager) {
		this.pager = pPager;
	}

}
