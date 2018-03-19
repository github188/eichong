package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_ParaConfig表
 * 
 * @author mew
 * 
 */
public class TblParaconfig extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2420862090774073526L;
	private java.lang.Integer pkParaconfig; //
	private java.lang.String paraName; // 配置名称
	private java.lang.Integer paraType; // 配置类型 1-车品牌 2-车型号 3-电桩搜索半径 4-设备报修类型
										// 5-所属用户类型
	private java.util.Date paraCreatedate; // 创建时间
	private java.util.Date paraUpdatedate; // 修改时间

	/**
	 * 获取属性
	 * 
	 * @return pkParaconfig
	 */
	public java.lang.Integer getPkParaconfig() {
		return pkParaconfig;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkParaconfig
	 */
	public void setPkParaconfig(java.lang.Integer pkParaconfig) {
		this.pkParaconfig = pkParaconfig;
	}

	/**
	 * 获取配置名称属性
	 * 
	 * @return paraName
	 */
	public java.lang.String getParaName() {
		return paraName;
	}

	/**
	 * 设置配置名称属性
	 * 
	 * @param paraName
	 */
	public void setParaName(java.lang.String paraName) {
		this.paraName = paraName;
	}

	/**
	 * 获取配置类型 1-车品牌 2-车型号 3-电桩搜索半径 4-设备报修类型 5-所属用户类型属性
	 * 
	 * @return paraType
	 */
	public java.lang.Integer getParaType() {
		return paraType;
	}

	/**
	 * 设置配置类型 1-车品牌 2-车型号 3-电桩搜索半径 4-设备报修类型 5-所属用户类型属性
	 * 
	 * @param paraType
	 */
	public void setParaType(java.lang.Integer paraType) {
		this.paraType = paraType;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return paraCreatedate
	 */
	public java.util.Date getParaCreatedate() {
		return paraCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param paraCreatedate
	 */
	public void setParaCreatedate(java.util.Date paraCreatedate) {
		this.paraCreatedate = paraCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return paraUpdatedate
	 */
	public java.util.Date getParaUpdatedate() {
		return paraUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param paraUpdatedate
	 */
	public void setParaUpdatedate(java.util.Date paraUpdatedate) {
		this.paraUpdatedate = paraUpdatedate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblParaconfig");
		sb.append("{pkParaconfig=").append(pkParaconfig);
		sb.append(", paraName=").append(paraName);
		sb.append(", paraType=").append(paraType);
		sb.append(", paraCreatedate=").append(paraCreatedate);
		sb.append(", paraUpdatedate=").append(paraUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}