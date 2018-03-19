/** 
 * FileName Area.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/11/12
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicListModel;

/**
 * FileName ArayacakPosition.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/11/12
 * 
 * 区县bean类
 */
public class Area extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 7398585829782696583L;
	/** 区县ID */
	private String areaId;
	/** 省份ID */
	private String provinceId;
	/** 城市ID */
	private String cityId;
	/** 区县名称 */
	private String areaName;
	/** 热门城市标志 */
	private String hotFlag;
	/** 分组ID */
	private String keyWord;

	//
	// 扩展参数
	//
	/** 省分名称 */
	private String provinceName;
	/** 城市名称 */
	private String cityName;

	/**
	 * 区县ID的取得。
	 * 
	 * @return 区县ID
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 区县ID的设定。
	 * 
	 * @param pAreaId
	 *            区县ID
	 */
	public void setAreaId(String pAreaId) {
		this.areaId = pAreaId;
	}

	/**
	 * 省份ID的取得。
	 * 
	 * @return 省份ID
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 省份ID的设定。
	 * 
	 * @param pProvinceId
	 *            省份ID
	 */
	public void setProvinceId(String pProvinceId) {
		this.provinceId = pProvinceId;
	}

	/**
	 * 城市ID的取得。
	 * 
	 * @return 城市ID
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 城市ID的设定。
	 * 
	 * @param pCityId
	 *            城市ID
	 */
	public void setCityId(String pCityId) {
		this.cityId = pCityId;
	}

	/**
	 * 区县名称的取得。
	 * 
	 * @return 区县名称
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 区县名称的设定。
	 * 
	 * @param pAreaName
	 *            区县名称
	 */
	public void setAreaName(String pAreaName) {
		this.areaName = pAreaName;
	}

	/**
	 * 热门城市标志的取得。
	 * 
	 * @return 热门城市标志
	 */
	public String getHotFlag() {
		return hotFlag;
	}

	/**
	 * 热门城市标志的设定。
	 * 
	 * @param pHotFlag
	 *            热门城市标志
	 */
	public void setHotFlag(String pHotFlag) {
		this.hotFlag = pHotFlag;
	}

	/**
	 * 分组ID的取得。
	 * 
	 * @return 分组ID
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * 分组ID的设定。
	 * 
	 * @param pKeyWord
	 *            分组ID
	 */
	public void setKeyWord(String pKeyWord) {
		this.keyWord = pKeyWord;
	}

	/**
	 * 省分名称的取得。
	 * 
	 * @return 省分名称
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * 省分名称的设定。
	 * 
	 * @param pProvinceName
	 *            省分名称
	 */
	public void setProvinceName(String pProvinceName) {
		this.provinceName = pProvinceName;
	}

	/**
	 * 城市名称的取得。
	 * 
	 * @return 城市名称
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 城市名称的设定。
	 * 
	 * @param pCityName
	 *            城市名称
	 */
	public void setCityName(String pCityName) {
		this.cityName = pCityName;
	}

}
