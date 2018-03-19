package com.wanma.model;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_electricpilecomment表
 * 
 * @author songjf
 * 
 */
public class ElectricPileComment extends BasicListAndMutiFile {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8530134350066229713L;
	private java.lang.Integer pk_EpcComment; //
	private java.lang.Integer epc_EpId; // 电站id
	private java.lang.Integer epc_UserId; // 用户Id(tbl_UserInfo表中获取)
	private java.lang.String epc_UserName; // 用户名
	private java.lang.String epc_CommentPic; // 评论图片
	private java.util.Date epc_Createdate; // 创建时间
	private java.util.Date epc_Updatedate; // 修改时间
	private java.lang.Integer epc_Utatus; // 0：显示 -1 已删除
	private java.lang.String epc_Content; // 评论内容
	private java.lang.String up_commentId; // 评论内容

	// 以下字段不予数据库对应

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("tblElectricpilecomment");
		sb.append("{pk_EpcComment=").append(pk_EpcComment);
		sb.append(", epc_EpId=").append(epc_EpId);
		sb.append(", epc_UserId=").append(epc_UserId);
		sb.append(", epc_UserName=").append(epc_UserName);
		sb.append(", epc_CommentPic=").append(epc_CommentPic);
		sb.append(", epc_Createdate=").append(epc_Createdate);
		sb.append(", epc_Updatedate=").append(epc_Updatedate);
		sb.append(", epc_Utatus=").append(epc_Utatus);
		sb.append(", epc_Content=").append(epc_Content);
		sb.append('}');
		return sb.toString();
	}

	public java.lang.Integer getPk_EpcComment() {
		return pk_EpcComment;
	}

	public void setPk_EpcComment(java.lang.Integer pk_EpcComment) {
		this.pk_EpcComment = pk_EpcComment;
	}

	public java.lang.Integer getEpc_EpId() {
		return epc_EpId;
	}

	public void setEpc_EpId(java.lang.Integer epc_EpId) {
		this.epc_EpId = epc_EpId;
	}

	public java.lang.Integer getEpc_UserId() {
		return epc_UserId;
	}

	public void setEpc_UserId(java.lang.Integer epc_UserId) {
		this.epc_UserId = epc_UserId;
	}

	public java.lang.String getEpc_UserName() {
		return epc_UserName;
	}

	public void setEpc_UserName(java.lang.String epc_UserName) {
		this.epc_UserName = epc_UserName;
	}

	public java.lang.String getEpc_CommentPic() {
		return epc_CommentPic;
	}

	public void setEpc_CommentPic(java.lang.String epc_CommentPic) {
		this.epc_CommentPic = epc_CommentPic;
	}

	public java.util.Date getEpc_Createdate() {
		return epc_Createdate;
	}

	public void setEpc_Createdate(java.util.Date epc_Createdate) {
		this.epc_Createdate = epc_Createdate;
	}

	public java.util.Date getEpc_Updatedate() {
		return epc_Updatedate;
	}

	public void setEpc_Updatedate(java.util.Date epc_Updatedate) {
		this.epc_Updatedate = epc_Updatedate;
	}

	public java.lang.Integer getEpc_Utatus() {
		return epc_Utatus;
	}

	public void setEpc_Utatus(java.lang.Integer epc_Utatus) {
		this.epc_Utatus = epc_Utatus;
	}

	public java.lang.String getEpc_Content() {
		return epc_Content;
	}

	public void setEpc_Content(java.lang.String epc_Content) {
		this.epc_Content = epc_Content;
	}

	public java.lang.String getUp_commentId() {
		return up_commentId;
	}

	public void setUp_commentId(java.lang.String up_commentId) {
		this.up_commentId = up_commentId;
	}
}