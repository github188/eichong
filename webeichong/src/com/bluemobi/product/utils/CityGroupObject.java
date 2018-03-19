/**
 * FileName:CityGroupObject.java
 * Author: Administrator
 * Create: 2014年8月14日
 * Last Modified: 2014年8月14日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.List;

import com.bluemobi.product.model.City;


/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月14日
 */
public class CityGroupObject implements java.io.Serializable {

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1769740866708650555L;

	/** 分组ID */
	private String keyWord;

	/** 城市一览 */
	private List<City> cityList = new ArrayList<City>();

	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyWord
	 *            the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the cityList
	 */
	public List<City> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList
	 *            the cityList to set
	 */
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

}
