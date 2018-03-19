/**
 * FileName:TableItemModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 数据库表字段定义实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class TableItemModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 6573955274111085000L;

	/** 表物理名 */
	private String tableId;

	/** 字段物理名 */
	private String columnId;

	/** 字段逻辑名 */
	private String columnName;

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
	 * 字段逻辑名的取得。
	 * 
	 * @return 字段逻辑名
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * 字段逻辑名的设定。
	 * 
	 * @param pColumnName
	 *            字段逻辑名
	 */
	public void setColumnName(String pColumnName) {
		this.columnName = pColumnName;
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
