package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblUsermessage extends BasicListAndMutiFile implements
		Serializable {
	private static final long serialVersionUID = -3918328901600849216L;
	private java.lang.Integer id;
	private java.lang.Integer userId;
	private java.lang.Integer fromUserid;
	private java.lang.String fromUsername;
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.Integer status;
	private java.util.Date editTime;
	private java.util.Date createTime;

	//表单数据
	private String userName;
	private String userPhone;
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getFromUserid() {
		return fromUserid;
	}

	public void setFromUserid(java.lang.Integer fromUserid) {
		this.fromUserid = fromUserid;
	}

	public java.lang.String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(java.lang.String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.util.Date getEditTime() {
		return editTime;
	}

	public void setEditTime(java.util.Date editTime) {
		this.editTime = editTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "TblUsermessage [id=" + id + ", userId=" + userId
				+ ", fromUserid=" + fromUserid + ", fromUsername="
				+ fromUsername + ", title=" + title + ", content=" + content
				+ ", status=" + status + ", editTime=" + editTime
				+ ", createTime=" + createTime + ", userName=" + userName + "]";
	}

	

}
