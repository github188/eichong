package com.wanma.model.simple;


/**
 * 充电桩表
 * @author lyh
 *
 */
public class PowerStation {	
	private java.lang.Integer pkPowerstation; // 主键(充电站编码)
	private java.lang.String postName; // 充电站名称
	private java.lang.Integer postStatus; // 充电站状态（0草稿，5提交审核，3已驳回，10离线，15上线）
	private java.lang.String  postOwnProvinceCode;//所属省份编码
	private java.lang.String  postOwnCityCode;//所属城市编码
	private java.lang.String  postOwnCountyCode;//所属地区代码编码
	private java.lang.Integer postAreacode; // 充电站所属区域代码，根据省、市、区代码表关联
	private java.lang.String provinceName;//省份名称
	private java.lang.String cityName;//城市名称
	private java.lang.String areaName;//地区名称
	private java.lang.String postAddress; // 地址
	private java.lang.String postPhone; // 联系电话
	private java.lang.String poStOnlineTime; // 开放时间
	private java.math.BigDecimal postLongitude; // 经度
	private java.math.BigDecimal postLatitude; // 纬度
	private java.lang.String postRemark; // 备注
	private java.util.Date postCreatedate; // 创建时间
	private java.util.Date postUpdatedate; // 修改时间
	
	private java.lang.String postPic; // 充电站图片
	private java.lang.String postDetailpic; // 充电站详情图片
	private java.lang.String postEleids; // 充电站绑定相关电桩，电桩id用逗号隔开
	private java.lang.Integer postPoweruser; // 电桩用途
	private java.lang.Integer postIsappoint; // 电桩是否支持预约
	private java.lang.String rejectionReason; // 驳回原因
    private String poStUserName;//所属用户
    private String poStCreateUserId;//所属用户ID
    private java.lang.Integer cpyCompanyNumber;//第三方g公司标识
    
	
	public java.lang.Integer getPkPowerstation() {
		return pkPowerstation;
	}
	public void setPkPowerstation(java.lang.Integer pkPowerstation) {
		this.pkPowerstation = pkPowerstation;
	}
	public java.lang.String getPostName() {
		return postName;
	}
	public void setPostName(java.lang.String postName) {
		this.postName = postName;
	}
	public java.lang.Integer getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(java.lang.Integer postStatus) {
		this.postStatus = postStatus;
	}
	public java.lang.String getPostOwnProvinceCode() {
		return postOwnProvinceCode;
	}
	public void setPostOwnProvinceCode(java.lang.String postOwnProvinceCode) {
		this.postOwnProvinceCode = postOwnProvinceCode;
	}
	public java.lang.String getPostOwnCityCode() {
		return postOwnCityCode;
	}
	public void setPostOwnCityCode(java.lang.String postOwnCityCode) {
		this.postOwnCityCode = postOwnCityCode;
	}
	public java.lang.String getPostOwnCountyCode() {
		return postOwnCountyCode;
	}
	public void setPostOwnCountyCode(java.lang.String postOwnCountyCode) {
		this.postOwnCountyCode = postOwnCountyCode;
	}
	public java.lang.Integer getPostAreacode() {
		return postAreacode;
	}
	public void setPostAreacode(java.lang.Integer postAreacode) {
		this.postAreacode = postAreacode;
	}
	public java.lang.String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(java.lang.String provinceName) {
		this.provinceName = provinceName;
	}
	public java.lang.String getCityName() {
		return cityName;
	}
	public void setCityName(java.lang.String cityName) {
		this.cityName = cityName;
	}
	public java.lang.String getAreaName() {
		return areaName;
	}
	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}
	public java.lang.String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(java.lang.String postAddress) {
		this.postAddress = postAddress;
	}
	public java.lang.String getPostPhone() {
		return postPhone;
	}
	public void setPostPhone(java.lang.String postPhone) {
		this.postPhone = postPhone;
	}
	public java.lang.String getPoStOnlineTime() {
		return poStOnlineTime;
	}
	public void setPoStOnlineTime(java.lang.String poStOnlineTime) {
		this.poStOnlineTime = poStOnlineTime;
	}
	public java.math.BigDecimal getPostLongitude() {
		return postLongitude;
	}
	public void setPostLongitude(java.math.BigDecimal postLongitude) {
		this.postLongitude = postLongitude;
	}
	public java.math.BigDecimal getPostLatitude() {
		return postLatitude;
	}
	public void setPostLatitude(java.math.BigDecimal postLatitude) {
		this.postLatitude = postLatitude;
	}
	public java.lang.String getPostRemark() {
		return postRemark;
	}
	public void setPostRemark(java.lang.String postRemark) {
		this.postRemark = postRemark;
	}
	public java.util.Date getPostCreatedate() {
		return postCreatedate;
	}
	public void setPostCreatedate(java.util.Date postCreatedate) {
		this.postCreatedate = postCreatedate;
	}
	public java.util.Date getPostUpdatedate() {
		return postUpdatedate;
	}
	public void setPostUpdatedate(java.util.Date postUpdatedate) {
		this.postUpdatedate = postUpdatedate;
	}
	public java.lang.String getPostPic() {
		return postPic;
	}
	public void setPostPic(java.lang.String postPic) {
		this.postPic = postPic;
	}
	public java.lang.String getPostDetailpic() {
		return postDetailpic;
	}
	public void setPostDetailpic(java.lang.String postDetailpic) {
		this.postDetailpic = postDetailpic;
	}
	public java.lang.String getPostEleids() {
		return postEleids;
	}
	public void setPostEleids(java.lang.String postEleids) {
		this.postEleids = postEleids;
	}
	public java.lang.Integer getPostPoweruser() {
		return postPoweruser;
	}
	public void setPostPoweruser(java.lang.Integer postPoweruser) {
		this.postPoweruser = postPoweruser;
	}
	public java.lang.Integer getPostIsappoint() {
		return postIsappoint;
	}
	public void setPostIsappoint(java.lang.Integer postIsappoint) {
		this.postIsappoint = postIsappoint;
	}
	public java.lang.String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(java.lang.String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getPoStUserName() {
		return poStUserName;
	}
	public void setPoStUserName(String poStUserName) {
		this.poStUserName = poStUserName;
	}
	public String getPoStCreateUserId() {
		return poStCreateUserId;
	}
	public void setPoStCreateUserId(String poStCreateUserId) {
		this.poStCreateUserId = poStCreateUserId;
	}
	public java.lang.Integer getCpyCompanyNumber() {
		return cpyCompanyNumber;
	}
	public void setCpyCompanyNumber(java.lang.Integer cpyCompanyNumber) {
		this.cpyCompanyNumber = cpyCompanyNumber;
	}
	
}
