package com.wanma.model;

import java.util.List;

/**
 * 充电点详情
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-16 下午02:18:16
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class PowerStationDetail {

	// 电桩详情
	private String powerStationId;// 充电点ID
	private String powerStationName;// 电桩名称
	private String powerStationImage;// 电桩图片
	private String powerStationState;// 电桩状态
	private String powerElectricpileSum;// 电桩数量
	private String powerStationAddress;// 充电点地址
	private String powerStationTell;// 充电点电话
	private String powerStationRemark;//充电点备注
	private String onlineTime; //在线时间
	// 电桩列表
	private List<PowerStationElictric> powerElectricpileList;// 充电点电桩列表
	private String powerCommentSum;// 电桩评论总数
	private String powerCommentUser;// 电桩评论用户名
	private String powerCommentStar;// 电桩评论星级
	private String powerCommentContent;// 电桩评论内容
	private String isCollect;// 是否收藏 0未收藏 大于0收藏

	private List<TblProductcomment> commentList;// 充电点评价列表

	private String postLongitude; // 经度
	private String postLatitude; // 纬度
	private String distance;
	private String poStOnlineTime; //开放时间
	private String totalChargeDl;//累计充电度量
	private String totalChargeTime;//累计充电时间，单位秒
	private String totalChargeAmt;//累计充电费用 
	
	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getPowerStationRemark() {
		return powerStationRemark;
	}

	public void setPowerStationRemark(String powerStationRemark) {
		this.powerStationRemark = powerStationRemark;
	}

	public String getPowerStationId() {
		return powerStationId;
	}

	public void setPowerStationId(String powerStationId) {
		this.powerStationId = powerStationId;
	}

	public String getPowerStationName() {
		return powerStationName;
	}

	public void setPowerStationName(String powerStationName) {
		this.powerStationName = powerStationName;
	}

	public String getPowerStationImage() {
		return powerStationImage;
	}

	public void setPowerStationImage(String powerStationImage) {
		this.powerStationImage = powerStationImage;
	}

	public String getPowerStationState() {
		return powerStationState;
	}

	public void setPowerStationState(String powerStationState) {
		this.powerStationState = powerStationState;
	}

	public String getPowerElectricpileSum() {
		return powerElectricpileSum;
	}

	public void setPowerElectricpileSum(String powerElectricpileSum) {
		this.powerElectricpileSum = powerElectricpileSum;
	}

	public String getPowerStationAddress() {
		return powerStationAddress;
	}

	public void setPowerStationAddress(String powerStationAddress) {
		this.powerStationAddress = powerStationAddress;
	}

	public String getPowerStationTell() {
		return powerStationTell;
	}

	public void setPowerStationTell(String powerStationTell) {
		this.powerStationTell = powerStationTell;
	}

	public List<PowerStationElictric> getPowerElectricpileList() {
		return powerElectricpileList;
	}

	public void setPowerElectricpileList(
			List<PowerStationElictric> powerElectricpileList) {
		this.powerElectricpileList = powerElectricpileList;
	}

	public String getPowerCommentSum() {
		return powerCommentSum;
	}

	public void setPowerCommentSum(String powerCommentSum) {
		this.powerCommentSum = powerCommentSum;
	}

	public String getPowerCommentUser() {
		return powerCommentUser;
	}

	public void setPowerCommentUser(String powerCommentUser) {
		this.powerCommentUser = powerCommentUser;
	}

	public String getPowerCommentStar() {
		return powerCommentStar;
	}

	public void setPowerCommentStar(String powerCommentStar) {
		this.powerCommentStar = powerCommentStar;
	}

	public String getPowerCommentContent() {
		return powerCommentContent;
	}

	public void setPowerCommentContent(String powerCommentContent) {
		this.powerCommentContent = powerCommentContent;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public List<TblProductcomment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<TblProductcomment> commentList) {
		this.commentList = commentList;
	}

	public String getPostLongitude() {
		return postLongitude;
	}

	public void setPostLongitude(String postLongitude) {
		this.postLongitude = postLongitude;
	}

	public String getPostLatitude() {
		return postLatitude;
	}

	public void setPostLatitude(String postLatitude) {
		this.postLatitude = postLatitude;
	}

	public String getPoStOnlineTime() {
		return poStOnlineTime;
	}

	public void setPoStOnlineTime(String poStOnlineTime) {
		this.poStOnlineTime = poStOnlineTime;
	}

	public String getTotalChargeDl() {
		return totalChargeDl;
	}

	public void setTotalChargeDl(String totalChargeDl) {
		this.totalChargeDl = totalChargeDl;
	}

	public String getTotalChargeTime() {
		return totalChargeTime;
	}

	public void setTotalChargeTime(String totalChargeTime) {
		this.totalChargeTime = totalChargeTime;
	}

	public String getTotalChargeAmt() {
		return totalChargeAmt;
	}

	public void setTotalChargeAmt(String totalChargeAmt) {
		this.totalChargeAmt = totalChargeAmt;
	}

	@Override
	public String toString() {
		return "PowerStationDetail [powerStationId=" + powerStationId
				+ ", powerStationName=" + powerStationName
				+ ", powerStationImage=" + powerStationImage
				+ ", powerStationState=" + powerStationState
				+ ", powerElectricpileSum=" + powerElectricpileSum
				+ ", powerStationAddress=" + powerStationAddress
				+ ", powerStationTell=" + powerStationTell
				+ ", powerStationRemark=" + powerStationRemark
				+ ", onlineTime=" + onlineTime + ", powerElectricpileList="
				+ powerElectricpileList + ", powerCommentSum="
				+ powerCommentSum + ", powerCommentUser=" + powerCommentUser
				+ ", powerCommentStar=" + powerCommentStar
				+ ", powerCommentContent=" + powerCommentContent
				+ ", isCollect=" + isCollect + ", commentList=" + commentList
				+ ", postLongitude=" + postLongitude + ", postLatitude="
				+ postLatitude + ", distance=" + distance + "]";
	}
	
	
}
