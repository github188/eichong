package com.wanma.model;


import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_ProductComment表
 * 
 * @author songjf
 * 
 */
public class PowerStationComment extends BasicListAndMutiFile {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8530134350066229713L;
	private java.lang.Integer pk_PsComment; //
	private java.lang.Integer psc_PsId; // 电站id
	private java.lang.Integer psc_UserId; // 用户Id(tbl_UserInfo表中获取)
	private java.lang.String psc_UserName; // 用户名
	private java.util.Date psc_CreateTime; // 评论时间
	private java.lang.String psc_CommentPic; // 评论图片
	private java.util.Date psc_Createdate; // 创建时间
	private java.util.Date psc_Updatedate; // 修改时间
	private java.lang.Integer psc_Utatus; // 0：显示 -1 已删除
	private java.lang.String psc_Content; // 评论内容
	
	// 以下字段不予数据库对应

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblPowerStationComment");
		sb.append("{pk_PsComment=").append(pk_PsComment);
		sb.append(", psc_PsId=").append(psc_PsId);
		sb.append(", psc_UserId=").append(psc_UserId);
		sb.append(", psc_UserName=").append(psc_UserName);
		sb.append(", psc_CreateTime=").append(psc_CreateTime);
		sb.append(", psc_CommentPic=").append(psc_CommentPic);
		sb.append(", psc_Createdate=").append(psc_Createdate);
		sb.append(", psc_Updatedate=").append(psc_Updatedate);
		sb.append(", psc_Utatus=").append(psc_Utatus);
		sb.append(", psc_Content=").append(psc_Content);
		sb.append('}');
		return sb.toString();
	}

	public java.lang.Integer getPk_PsComment() {
		return pk_PsComment;
	}

	public void setPk_PsComment(java.lang.Integer pk_PsComment) {
		this.pk_PsComment = pk_PsComment;
	}

	public java.lang.Integer getPsc_PsId() {
		return psc_PsId;
	}

	public void setPsc_PsId(java.lang.Integer psc_PsId) {
		this.psc_PsId = psc_PsId;
	}

	public java.lang.Integer getPsc_UserId() {
		return psc_UserId;
	}

	public void setPsc_UserId(java.lang.Integer psc_UserId) {
		this.psc_UserId = psc_UserId;
	}

	public java.lang.String getPsc_UserName() {
		return psc_UserName;
	}

	public void setPsc_UserName(java.lang.String psc_UserName) {
		this.psc_UserName = psc_UserName;
	}

	public java.util.Date getPsc_CreateTime() {
		return psc_CreateTime;
	}

	public void setPsc_CreateTime(java.util.Date psc_CreateTime) {
		this.psc_CreateTime = psc_CreateTime;
	}

	public java.lang.String getPsc_CommentPic() {
		return psc_CommentPic;
	}

	public void setPsc_CommentPic(java.lang.String psc_CommentPic) {
		this.psc_CommentPic = psc_CommentPic;
	}

	public java.util.Date getPsc_Createdate() {
		return psc_Createdate;
	}

	public void setPsc_Createdate(java.util.Date psc_Createdate) {
		this.psc_Createdate = psc_Createdate;
	}

	public java.util.Date getPsc_Updatedate() {
		return psc_Updatedate;
	}

	public void setPsc_Updatedate(java.util.Date psc_Updatedate) {
		this.psc_Updatedate = psc_Updatedate;
	}

	public java.lang.Integer getPsc_Utatus() {
		return psc_Utatus;
	}

	public void setPsc_Utatus(java.lang.Integer psc_Utatus) {
		this.psc_Utatus = psc_Utatus;
	}

	public java.lang.String getPsc_Content() {
		return psc_Content;
	}

	public void setPsc_Content(java.lang.String psc_Content) {
		this.psc_Content = psc_Content;
	}
}