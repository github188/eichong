package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 城市表
 * 
 * @author
 * 
 */
public class City extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -8133986455137161404L;

	/** 省份ID */
	private String provinceId;

	/** 城市ID */
	private String cityId;

	/** 城市名称 */
	private String cityName;

	/** 热门城市标志 */
	private String hotFlag;
	
	/** 分组ID */
	private String keyWord;

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
	 * 省份ID
	 * 
	 * @return 省份ID
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 省份ID
	 * 
	 * @param pProvinceId
	 *            省份ID
	 */
	public void setProvinceId(String pProvinceId) {
		this.provinceId = pProvinceId;
	}

	/**
	 * 城市ID
	 * 
	 * @return 城市ID
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 城市ID
	 * 
	 * @param pCityId
	 *            城市ID
	 */
	public void setCityId(String pCityId) {
		this.cityId = pCityId;
	}

	/**
	 * 城市名称
	 * 
	 * @return 城市名称
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 城市名称
	 * 
	 * @param pCityName
	 *            城市名称
	 */
	public void setCityName(String pCityName) {
		this.cityName = pCityName;
	}

	/**
	 * 热门城市标志
	 * 
	 * @return 热门城市标志
	 */
	public String getHotFlag() {
		return hotFlag;
	}

	/**
	 * 热门城市标志
	 * 
	 * @param pHotFlag
	 *            热门城市标志
	 */
	public void setHotFlag(String pHotFlag) {
		this.hotFlag = pHotFlag;
	}

}
