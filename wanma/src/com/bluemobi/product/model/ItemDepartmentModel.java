/**
 * FileName:ItemDepartmentModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 数据库字段部门权限实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class ItemDepartmentModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -3648833285905221029L;

	/** 表物理名 */
	private String tableId;

	/** 字段物理名 */
	private String columnId;

	/** 公司ID */
	private String companyId;

	/** 部门ID */
	private String departmentId;

	/** 包括子部门 */
	private String childAccessable;

	/** 操作级别 */
	private String processLevel;

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
	 * 包括子部门的取得。
	 * 
	 * @return 包括子部门
	 */
	public String getChildAccessable() {
		return childAccessable;
	}

	/**
	 * 包括子部门的设定。
	 * 
	 * @param pChildAccessable
	 *            包括子部门
	 */
	public void setChildAccessable(String pChildAccessable) {
		this.childAccessable = pChildAccessable;
	}

	/**
	 * 操作级别的取得。
	 * 
	 * @return 操作级别
	 */
	public String getProcessLevel() {
		return processLevel;
	}

	/**
	 * 操作级别的设定。
	 * 
	 * @param pProcessLevel
	 *            操作级别
	 */
	public void setProcessLevel(String pProcessLevel) {
		this.processLevel = pProcessLevel;
	}

}
