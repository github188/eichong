package com.wanma.model;


public class TblPartner {
   //组织机构编码
   private String operatorID;
   //内部公司标识
   private String orgNo;
   //公司id
   private String cpyId;
   //万马token获取密匙
   private String wmTokenSecret;
   //是否有充电流程
   private String valid;
   //第三方token获取地址
   private String thirdTokenUrl;
   //第三方token获取密匙
   private String thirdTokenSecret;
   //加密密匙
   private String aesSecret;
   //初始化向量
   private String aesIv;
   //签名密匙
   private String sigSecret;
   //推送订单对账结果url
   private String pushOrderCheckUrl;
   //订单推送间隔，
   private String timeInterval;
   //推送订单URL
   private String pushOrderUrl;
   
   private String secret;
   
public String getOperatorID() {
	return operatorID;
}
public void setOperatorID(String operatorID) {
	this.operatorID = operatorID;
}
public String getOrgNo() {
	return orgNo;
}
public void setOrgNo(String orgNo) {
	this.orgNo = orgNo;
}
public String getCpyId() {
	return cpyId;
}
public void setCpyId(String cpyId) {
	this.cpyId = cpyId;
}
public String getWmTokenSecret() {
	return wmTokenSecret;
}
public void setWmTokenSecret(String wmTokenSecret) {
	this.wmTokenSecret = wmTokenSecret;
}
public String getValid() {
	return valid;
}
public void setValid(String valid) {
	this.valid = valid;
}
public String getThirdTokenUrl() {
	return thirdTokenUrl;
}
public void setThirdTokenUrl(String thirdTokenUrl) {
	this.thirdTokenUrl = thirdTokenUrl;
}
public String getThirdTokenSecret() {
	return thirdTokenSecret;
}
public void setThirdTokenSecret(String thirdTokenSecret) {
	this.thirdTokenSecret = thirdTokenSecret;
}
public String getAesSecret() {
	return aesSecret;
}
public void setAesSecret(String aesSecret) {
	this.aesSecret = aesSecret;
}
public String getAesIv() {
	return aesIv;
}
public void setAesIv(String aesIv) {
	this.aesIv = aesIv;
}
public String getSigSecret() {
	return sigSecret;
}
public void setSigSecret(String sigSecret) {
	this.sigSecret = sigSecret;
}
public String getPushOrderCheckUrl() {
	return pushOrderCheckUrl;
}
public void setPushOrderCheckUrl(String pushOrderCheckUrl) {
	this.pushOrderCheckUrl = pushOrderCheckUrl;
}
public String getTimeInterval() {
	return timeInterval;
}
public void setTimeInterval(String timeInterval) {
	this.timeInterval = timeInterval;
}
public String getSecret() {
	return secret;
}
public void setSecret(String secret) {
	this.secret = secret;
}
public String getPushOrderUrl() {
	return pushOrderUrl;
}
public void setPushOrderUrl(String pushOrderUrl) {
	this.pushOrderUrl = pushOrderUrl;
}
   
   
}