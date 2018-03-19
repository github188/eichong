package com.wanma.model;



import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_FeedBack表
 * @author songjf
 *
 */
public class TblFeedback extends BasicListAndMutiFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.Integer pkFeedback; // 主键
	private java.lang.String febaContent; // 反馈内容
	private java.lang.Integer febaUserid; // 用户ID tbl_UserInfo中获取
	private java.util.Date febaCreatedate; // 创建时间
	private java.util.Date febaUpdatedate; // 修改时间
	private java.lang.Integer   processStatus; //处理状态
	private java.lang.String  reason; //处理原因


	

	public java.lang.Integer getPkFeedback() {
		return pkFeedback;
	}




	public void setPkFeedback(java.lang.Integer pkFeedback) {
		this.pkFeedback = pkFeedback;
	}




	public java.lang.String getFebaContent() {
		return febaContent;
	}




	public void setFebaContent(java.lang.String febaContent) {
		this.febaContent = febaContent;
	}




	public java.lang.Integer getFebaUserid() {
		return febaUserid;
	}




	public void setFebaUserid(java.lang.Integer febaUserid) {
		this.febaUserid = febaUserid;
	}




	public java.util.Date getFebaCreatedate() {
		return febaCreatedate;
	}




	public void setFebaCreatedate(java.util.Date febaCreatedate) {
		this.febaCreatedate = febaCreatedate;
	}




	public java.util.Date getFebaUpdatedate() {
		return febaUpdatedate;
	}




	public void setFebaUpdatedate(java.util.Date febaUpdatedate) {
		this.febaUpdatedate = febaUpdatedate;
	}




	public java.lang.Integer getProcessStatus() {
		return processStatus;
	}




	public void setProcessStatus(java.lang.Integer processStatus) {
		this.processStatus = processStatus;
	}




	public java.lang.String getReason() {
		return reason;
	}




	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TblFeedback [pkFeedback=" + pkFeedback + ", febaContent="
				+ febaContent + ", febaUserid=" + febaUserid
				+ ", febaCreatedate=" + febaCreatedate + ", febaUpdatedate="
				+ febaUpdatedate + ", processStatus=" + processStatus
				+ ", reason=" + reason + "]";
	}
}