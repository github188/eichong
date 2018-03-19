package com.wanma.model;

import java.io.Serializable;
import java.util.List;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_ProductComment表
 * 
 * @author songjf
 * 
 */
public class TblProductcomment extends BasicListAndMutiFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8530134350066229713L;
	private java.lang.Integer pkProductcomment; //
	private java.lang.Integer prcoProductid; // 产品Id(tbl_Product表中获取)
	private java.lang.Integer prcoUserid; // 用户Id(tbl_UserInfo表中获取)
	private java.lang.String prcoUsername; // 用户名
	private java.util.Date prcoCreatetime; // 评论时间
	private java.lang.Double prcoCommentstart; // 星级
	private java.lang.String prcoCommentpic; // 评论图片
	private java.lang.Integer prcoOrderid; // 订单号
	private java.lang.Integer prcoCommentType; // 1：桩体评价 2：电站评价 3：商城商品评价:
	private java.util.Date prcoCreatedate; // 创建时间
	private java.util.Date prcoUpdatedate; // 修改时间
	private java.lang.Integer prcoUtatus; // 0：显示 -1 已删除
	private java.lang.String prcoContent; // 评论内容
	
	// 以下字段不予数据库对应
	private String start_create_date;
		
	private String end_create_date;
	
	private String phone;
	
	private String proCode;
	
	private String proCodeName;
	
	private String ordCode;
	
	private String chooseProduct;
	
	private String choevaluate;

	public String getChoevaluate() {
		return choevaluate;
	}

	public void setChoevaluate(String choevaluate) {
		this.choevaluate = choevaluate;
	}

	public String getChooseProduct() {
		return chooseProduct;
	}

	public void setChooseProduct(String chooseProduct) {
		this.chooseProduct = chooseProduct;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getOrdCode() {
		return ordCode;
	}

	public void setOrdCode(String ordCode) {
		this.ordCode = ordCode;
	}

	public String getStart_create_date() {
		return start_create_date;
	}

	public void setStart_create_date(String start_create_date) {
		this.start_create_date = start_create_date;
	}

	public String getEnd_create_date() {
		return end_create_date;
	}

	public void setEnd_create_date(String end_create_date) {
		this.end_create_date = end_create_date;
	}

	// 以下字段不予数据库对应
	private java.lang.String userImage;// 用户头像
	private List<String> commPicList;// 评论图片

	/**
	 * 获取属性
	 * 
	 * @return pkProductcomment
	 */
	public java.lang.Integer getPkProductcomment() {
		return pkProductcomment;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkProductcomment
	 */
	public void setPkProductcomment(java.lang.Integer pkProductcomment) {
		this.pkProductcomment = pkProductcomment;
	}

	/**
	 * 获取产品Id(tbl_Product表中获取)属性
	 * 
	 * @return prcoProductid
	 */
	public java.lang.Integer getPrcoProductid() {
		return prcoProductid;
	}

	/**
	 * 设置产品Id(tbl_Product表中获取)属性
	 * 
	 * @param prcoProductid
	 */
	public void setPrcoProductid(java.lang.Integer prcoProductid) {
		this.prcoProductid = prcoProductid;
	}

	/**
	 * 获取用户Id(tbl_UserInfo表中获取)属性
	 * 
	 * @return prcoUserid
	 */
	public java.lang.Integer getPrcoUserid() {
		return prcoUserid;
	}

	/**
	 * 设置用户Id(tbl_UserInfo表中获取)属性
	 * 
	 * @param prcoUserid
	 */
	public void setPrcoUserid(java.lang.Integer prcoUserid) {
		this.prcoUserid = prcoUserid;
	}

	/**
	 * 获取用户名属性
	 * 
	 * @return prcoUsername
	 */
	public java.lang.String getPrcoUsername() {
		return prcoUsername;
	}

	/**
	 * 设置用户名属性
	 * 
	 * @param prcoUsername
	 */
	public void setPrcoUsername(java.lang.String prcoUsername) {
		this.prcoUsername = prcoUsername;
	}

	/**
	 * 获取评论时间属性
	 * 
	 * @return prcoCreatetime
	 */
	public java.util.Date getPrcoCreatetime() {
		return prcoCreatetime;
	}

	/**
	 * 设置评论时间属性
	 * 
	 * @param prcoCreatetime
	 */
	public void setPrcoCreatetime(java.util.Date prcoCreatetime) {
		this.prcoCreatetime = prcoCreatetime;
	}

	/**
	 * 获取星级属性
	 * 
	 * @return prcoCommentstart
	 */
	public java.lang.Double getPrcoCommentstart() {
		return prcoCommentstart;
	}

	/**
	 * 设置星级属性
	 * 
	 * @param prcoCommentstart
	 */
	public void setPrcoCommentstart(java.lang.Double prcoCommentstart) {
		this.prcoCommentstart = prcoCommentstart;
	}

	/**
	 * 获取评论图片属性
	 * 
	 * @return prcoCommentpic
	 */
	public java.lang.String getPrcoCommentpic() {
		return prcoCommentpic;
	}

	/**
	 * 设置评论图片属性
	 * 
	 * @param prcoCommentpic
	 */
	public void setPrcoCommentpic(java.lang.String prcoCommentpic) {
		this.prcoCommentpic = prcoCommentpic;
	}

	/**
	 * 获取订单号属性
	 * 
	 * @return prcoOrderid
	 */
	public java.lang.Integer getPrcoOrderid() {
		return prcoOrderid;
	}

	/**
	 * 设置订单号属性
	 * 
	 * @param prcoOrderid
	 */
	public void setPrcoOrderid(java.lang.Integer prcoOrderid) {
		this.prcoOrderid = prcoOrderid;
	}

	/**
	 * 获取1：桩体评价 2：商城商品评价 3：充值评价属性
	 * 
	 * @return prcoCommentType
	 */
	public java.lang.Integer getPrcoCommentType() {
		return prcoCommentType;
	}

	/**
	 * 设置1：桩体评价 2：商城商品评价 3：充值评价属性
	 * 
	 * @param prcoCommentType
	 */
	public void setPrcoCommentType(java.lang.Integer prcoCommentType) {
		this.prcoCommentType = prcoCommentType;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return prcoCreatedate
	 */
	public java.util.Date getPrcoCreatedate() {
		return prcoCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param prcoCreatedate
	 */
	public void setPrcoCreatedate(java.util.Date prcoCreatedate) {
		this.prcoCreatedate = prcoCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return prcoUpdatedate
	 */
	public java.util.Date getPrcoUpdatedate() {
		return prcoUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param prcoUpdatedate
	 */
	public void setPrcoUpdatedate(java.util.Date prcoUpdatedate) {
		this.prcoUpdatedate = prcoUpdatedate;
	}

	/**
	 * 获取0：显示 -1 已删除属性
	 * 
	 * @return prcoUtatus
	 */
	public java.lang.Integer getPrcoUtatus() {
		return prcoUtatus;
	}

	/**
	 * 设置0：显示 -1 已删除属性
	 * 
	 * @param prcoUtatus
	 */
	public void setPrcoUtatus(java.lang.Integer prcoUtatus) {
		this.prcoUtatus = prcoUtatus;
	}

	/**
	 * 获取评论内容属性
	 * 
	 * @return prcoContent
	 */
	public java.lang.String getPrcoContent() {
		return prcoContent;
	}

	/**
	 * 设置评论内容属性
	 * 
	 * @param prcoContent
	 */
	public void setPrcoContent(java.lang.String prcoContent) {
		this.prcoContent = prcoContent;
	}

	/**
	 * 获取用户头像属性
	 * 
	 * @param userImage
	 */
	public java.lang.String getUserImage() {
		return userImage;
	}

	/**
	 * 设置用户头像属性
	 * 
	 * @param userImage
	 */
	public void setUserImage(java.lang.String userImage) {
		this.userImage = userImage;
	}

	public List<String> getCommPicList() {
		return commPicList;
	}

	public void setCommPicList(List<String> commPicList) {
		this.commPicList = commPicList;
	}

	public String getProCodeName() {
		return proCodeName;
	}

	public void setProCodeName(String proCodeName) {
		this.proCodeName = proCodeName;
	}

	@Override
	public String toString() {
		return "TblProductcomment [pkProductcomment=" + pkProductcomment
				+ ", prcoProductid=" + prcoProductid + ", prcoUserid="
				+ prcoUserid + ", prcoUsername=" + prcoUsername
				+ ", prcoCreatetime=" + prcoCreatetime + ", prcoCommentstart="
				+ prcoCommentstart + ", prcoCommentpic=" + prcoCommentpic
				+ ", prcoOrderid=" + prcoOrderid + ", prcoCommentType="
				+ prcoCommentType + ", prcoCreatedate=" + prcoCreatedate
				+ ", prcoUpdatedate=" + prcoUpdatedate + ", prcoUtatus="
				+ prcoUtatus + ", prcoContent=" + prcoContent
				+ ", start_create_date=" + start_create_date
				+ ", end_create_date=" + end_create_date + ", phone=" + phone
				+ ", proCode=" + proCode + ", proCodeName=" + proCodeName
				+ ", ordCode=" + ordCode + ", chooseProduct=" + chooseProduct
				+ ", choevaluate=" + choevaluate + ", userImage=" + userImage
				+ ", commPicList=" + commPicList + "]";
	}


}