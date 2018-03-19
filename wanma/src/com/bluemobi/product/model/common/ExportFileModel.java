/**
 * ExportFileModel
 * Author: Administrator
 * Create: 2014年7月8日
 * Last Modified: 2014年7月8日
 * Version: V1.0 
 */
package com.bluemobi.product.model.common;

import java.io.Serializable;

/**
 * 数据导出设置用数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月8日
 */
public class ExportFileModel implements Serializable {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 3779624540557760158L;

	/** 字段 */
	private String property;

	/** 名字 */
	private String name;

	/** 格式 */
	private String formart;

	/**
	 * 字段的取得。
	 * 
	 * @return 字段
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * 字段的设定。。
	 * 
	 * @param pProperty
	 *            字段
	 */
	public void setProperty(String pProperty) {
		this.property = pProperty;
	}

	/**
	 * 名字的取得。
	 * 
	 * @return 名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名字的设定。。
	 * 
	 * @param pName
	 *            名字
	 */
	public void setName(String pName) {
		this.name = pName;
	}

	/**
	 * 格式的取得。
	 * 
	 * @return 格式
	 */
	public String getFormart() {
		return formart;
	}

	/**
	 * 格式的设定。。
	 * 
	 * @param pFormart
	 *            格式
	 */
	public void setFormart(String pFormart) {
		this.formart = pFormart;
	}

}
