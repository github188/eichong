package com.wanma.model;

import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblPureBusiness extends BasicListAndMutiFile{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String businessId;			//主键
	private String groupId;				//分组ID
	private String companyName;			//企业名称
	private String nickName;			//平台昵称
	private String companyAddress;		//企业地址
	private String companyEmail;		//企业邮箱
	private String postCode;			//邮编
	private String scopeBusiness;		//经营范围
	private String authorizedName;		//授权人名称
	private String authorizedPhone;		//授权人联系电话
	private String authorizedCard;		//前后劝人身份证
	private String businessLicence;		//营业执照
	private String organizationCode;	//组织机构代码
	private String torontoHospital;		//税务登记证
	private String authorization;		//授权证明
	private String mailingAddress;		//邮寄地址
	private String pureBusinessStatus;	//状态
	private String loveLogin;			//爱宠网账号
	private String lovePassword;		//登录密码
	private Date createdate;			//创建时间
	private Date updatedate;			//修改时间
	private String puBuPicType;			//修改时间
	
	public String getPuBuPicType() {
		return puBuPicType;
	}
	public void setPuBuPicType(String puBuPicType) {
		this.puBuPicType = puBuPicType;
	}
	//非数据库字段
	private String groupna;
	
	public String getGroupna() {
		return groupna;
	}
	public void setGroupna(String groupna) {
		this.groupna = groupna;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getScopeBusiness() {
		return scopeBusiness;
	}
	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
	}
	public String getAuthorizedName() {
		return authorizedName;
	}
	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}
	public String getAuthorizedPhone() {
		return authorizedPhone;
	}
	public void setAuthorizedPhone(String authorizedPhone) {
		this.authorizedPhone = authorizedPhone;
	}
	public String getAuthorizedCard() {
		return authorizedCard;
	}
	public void setAuthorizedCard(String authorizedCard) {
		this.authorizedCard = authorizedCard;
	}
	public String getBusinessLicence() {
		return businessLicence;
	}
	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getTorontoHospital() {
		return torontoHospital;
	}
	public void setTorontoHospital(String torontoHospital) {
		this.torontoHospital = torontoHospital;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getPureBusinessStatus() {
		return pureBusinessStatus;
	}
	public void setPureBusinessStatus(String pureBusinessStatus) {
		this.pureBusinessStatus = pureBusinessStatus;
	}
	public String getLoveLogin() {
		return loveLogin;
	}
	public void setLoveLogin(String loveLogin) {
		this.loveLogin = loveLogin;
	}
	public String getLovePassword() {
		return lovePassword;
	}
	public void setLovePassword(String lovePassword) {
		this.lovePassword = lovePassword;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
}
