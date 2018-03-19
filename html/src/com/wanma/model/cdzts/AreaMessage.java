package com.wanma.model.cdzts;
/**
 * 省、市、县区编码以及名称的信息表
 * @author lyh
 *
 */
public class AreaMessage {
 private String provinceCode; //省编码
 private String provinceName; //省名称
 private String cityCode; //市编码
 private String cityName; //市名称
 private String areaCode; //县区编码
 private String areaName; //县区名称
 
public String getProvinceCode() {
	return provinceCode;
}
public void setProvinceCode(String provinceCode) {
	this.provinceCode = provinceCode;
}
public String getProvinceName() {
	return provinceName;
}
public void setProvinceName(String provinceName) {
	this.provinceName = provinceName;
}
public String getCityCode() {
	return cityCode;
}
public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
public String getAreaCode() {
	return areaCode;
}
public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
}
public String getAreaName() {
	return areaName;
}
public void setAreaName(String areaName) {
	this.areaName = areaName;
}
@Override
public String toString() {
	return "AreaMessage [provinceCode=" + provinceCode + ", provinceName="
			+ provinceName + ", cityCode=" + cityCode + ", cityName="
			+ cityName + ", areaCode=" + areaCode + ", areaName=" + areaName
			+ "]";
}
 
 
}
