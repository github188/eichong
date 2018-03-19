package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

public class AppButtonDO extends BasicModel {
	private java.lang.Integer pkButtonId;//主键
	private java.lang.String buttonDesc;//备注
	private java.lang.String buttonName;//名称
	private java.lang.String buttonUrl;//外部链接url
	private java.lang.Integer buttonStatus;//状态默认0: 1 开启 2 删除 3 下架关闭
	private java.lang.Integer buttonSort;//播放顺序
	private java.lang.Integer buttonType;//连接类型  默认0:1 内部 2外部连接
	private java.lang.Integer buttonAction;//连接类型  默认0:
	private java.util.Date buttonCreatedate;//创建时间
	private java.util.Date buttonUpdatedate;//更新时间
	//非数据库字段
	private java.lang.String buttonPicUrl;//button图片地址
	private java.lang.String buttonPicFileId;// button图片关键词
	public java.lang.Integer getPkButtonId() {
		return pkButtonId;
	}
	public void setPkButtonId(java.lang.Integer pkButtonId) {
		this.pkButtonId = pkButtonId;
	}
	public java.lang.String getButtonDesc() {
		return buttonDesc;
	}
	public void setButtonDesc(java.lang.String buttonDesc) {
		this.buttonDesc = buttonDesc;
	}
	public java.lang.String getButtonName() {
		return buttonName;
	}
	public void setButtonName(java.lang.String buttonName) {
		this.buttonName = buttonName;
	}
	public java.lang.String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(java.lang.String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	public java.lang.Integer getButtonStatus() {
		return buttonStatus;
	}
	public void setButtonStatus(java.lang.Integer buttonStatus) {
		this.buttonStatus = buttonStatus;
	}
	public java.lang.Integer getButtonSort() {
		return buttonSort;
	}
	public void setButtonSort(java.lang.Integer buttonSort) {
		this.buttonSort = buttonSort;
	}
	public java.lang.Integer getButtonType() {
		return buttonType;
	}
	public void setButtonType(java.lang.Integer buttonType) {
		this.buttonType = buttonType;
	}
	public java.lang.Integer getButtonAction() {
		return buttonAction;
	}
	public void setButtonAction(java.lang.Integer buttonAction) {
		this.buttonAction = buttonAction;
	}
	public java.util.Date getButtonCreatedate() {
		return buttonCreatedate;
	}
	public void setButtonCreatedate(java.util.Date buttonCreatedate) {
		this.buttonCreatedate = buttonCreatedate;
	}
	public java.util.Date getButtonUpdatedate() {
		return buttonUpdatedate;
	}
	public void setButtonUpdatedate(java.util.Date buttonUpdatedate) {
		this.buttonUpdatedate = buttonUpdatedate;
	}
	public java.lang.String getButtonPicUrl() {
		return buttonPicUrl;
	}
	public void setButtonPicUrl(java.lang.String buttonPicUrl) {
		this.buttonPicUrl = buttonPicUrl;
	}
	public java.lang.String getButtonPicFileId() {
		return buttonPicFileId;
	}
	public void setButtonPicFileId(java.lang.String buttonPicFileId) {
		this.buttonPicFileId = buttonPicFileId;
	}
	@Override
	public String toString() {
		return "AppButtonDO [pkButtonId=" + pkButtonId + ", buttonDesc="
				+ buttonDesc + ", buttonName=" + buttonName + ", buttonUrl="
				+ buttonUrl + ", buttonStatus=" + buttonStatus
				+ ", buttonSort=" + buttonSort + ", buttonType=" + buttonType
				+ ", buttonAction=" + buttonAction + ", buttonCreatedate="
				+ buttonCreatedate + ", buttonUpdatedate=" + buttonUpdatedate
				+ ", buttonPicUrl=" + buttonPicUrl + ", buttonPicFileId="
				+ buttonPicFileId + "]";
	}
	
	
	
}