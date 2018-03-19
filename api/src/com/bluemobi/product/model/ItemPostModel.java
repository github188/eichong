/**
 * FileName:ItemPostModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 数据库字段职位权限实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class ItemPostModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -8950492806448332834L;

	/** 表物理名 */
	private String tableId;

	/** 字段物理名 */
	private String columnId;

	/** 公司ID */
	private String companyId;

	/** 职位ID */
	private String postId;

	/**
	 * 表物理名的取得。
	 * 
	 * @return 表物理名
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * 表物理名的设定。
	 * 
	 * @param pTableId
	 *            表物理名
	 */
	public void setTableId(String pTableId) {
		this.tableId = pTableId;
	}

	/**
	 * 字段物理名的取得。
	 * 
	 * @return 字段物理名
	 */
	public String getColumnId() {
		return columnId;
	}

	/**
	 * 字段物理名的设定。
	 * 
	 * @param pColumnId
	 *            字段物理名
	 */
	public void setColumnId(String pColumnId) {
		this.columnId = pColumnId;
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

}
