/**
 * FileName:TableModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 数据库表定义实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class TableModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 5974399460623156419L;

	/** 表物理名 */
	private String tableId;

	/** 表逻辑名 */
	private String tableName;

	/** 备注 */
	private String notes;

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
	 * 表逻辑名的取得。
	 * 
	 * @return 表逻辑名
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 表逻辑名的设定。
	 * 
	 * @param pTableName
	 *            表逻辑名
	 */
	public void setTableName(String pTableName) {
		this.tableName = pTableName;
	}

	/**
	 * 备注的取得。
	 * 
	 * @return 备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 备注的设定。
	 * 
	 * @param pNotes
	 *            备注
	 */
	public void setNotes(String pNotes) {
		this.notes = pNotes;
	}

}
