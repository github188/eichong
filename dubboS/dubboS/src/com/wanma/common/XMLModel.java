/**
 * XMLModel
 * Author: Administrator
 * Create: 2014年7月8日
 * Last Modified: 2014年7月8日
 * Version: V1.0 
 */
package com.wanma.common;

import java.io.Serializable;
import java.util.List;

/**
 * XML存储用数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月8日
 */
public class XMLModel implements Serializable {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -4899916798982785300L;

	/** 关键字 */
	private String key;

	/** 值 */
	private String value;

	/** 是否有子节点 */
	private boolean hasChildFlg = false;

	/** 子节点列表 */
	private List<XMLModel> childList;

	/**
	 * 关键字的取得。
	 * 
	 * @return 关键字
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 关键字的设定。
	 * 
	 * @param pKey
	 *            关键字
	 */
	public void setKey(String pKey) {
		this.key = pKey;
	}

	/**
	 * 值的取得。
	 * 
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 值的设定。
	 * 
	 * @param pValue
	 *            值
	 */
	public void setValue(String pValue) {
		this.value = pValue;
	}

	/**
	 * 是否有子节点的取得。
	 * 
	 * @return 是否有子节点
	 */
	public boolean getHasChildFlg() {
		return hasChildFlg;
	}

	/**
	 * 是否有子节点的设定。
	 * 
	 * @param pHasChildFlg
	 *            是否有子节点
	 */
	public void setHasChildFlg(boolean pHasChildFlg) {
		this.hasChildFlg = pHasChildFlg;
	}

	/**
	 * 子节点列表的取得。
	 * 
	 * @return 子节点列表
	 */
	public List<XMLModel> getChildList() {
		return childList;
	}

	/**
	 * 子节点列表的设定。
	 * 
	 * @param pChildList
	 *            子节点列表
	 */
	public void setChildList(List<XMLModel> pChildList) {
		this.childList = pChildList;
	}

}
