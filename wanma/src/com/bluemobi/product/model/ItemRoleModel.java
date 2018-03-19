/**
 * FileName:ItemRoleModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 数据库字段角色权限实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class ItemRoleModel extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 6704452993998425498L;

	/** 表物理名 */
	private String tableId;

	/** 字段物理名 */
	private String columnId;

	/** 角色ID */
	private String roleId;

	/** 包括子角色 */
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
	 * 角色ID的取得。
	 * 
	 * @return 角色ID
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 角色ID的设定。
	 * 
	 * @param pRoleId
	 *            角色ID
	 */
	public void setRoleId(String pRoleId) {
		this.roleId = pRoleId;
	}

	/**
	 * 包括子角色的取得。
	 * 
	 * @return 包括子角色
	 */
	public String getChildAccessable() {
		return childAccessable;
	}

	/**
	 * 包括子角色的设定。
	 * 
	 * @param pChildAccessable
	 *            包括子角色
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
