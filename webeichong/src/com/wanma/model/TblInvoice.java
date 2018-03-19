package com.wanma.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * Tbl_Invoice表
 * 
 * @author mew
 * 
 */
public class TblInvoice extends BasicListAndMutiFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8080314299776030419L;
	private Long pkInvoice;
	private String ivTaxpayerNumber;// 纳税人识别号
	private String ivCompanyName;// 公司抬头
	private String ivCompanyAddress;// 公司地址
	private String ivPhoneNumber;// 电话
	private String ivBankName;// 开户银行名称
	private String ivBankAccount;// 开户银行账号
	private Double ivInvoiceAmount;// 开票金额
	private String ivInvoiceNumber;// 发票号码
	private String ivInvoiceContent;// 发票内容
	private String ivTrackNumber;// 快递单号
	private String ivRecipients;// 收件人姓名
	private String ivConsigneeAddress;// 收件人地址
	private String ivRecipientsNumber;// 收件人手机号码
	private Integer ivInvoiceType;// 开票类型：0-个人开票 ，1-公司开票
	private Integer ivInvoiceStatus;// 发票申请状态：0-已申请， 1-已开票
	private Integer ivPayFreight;// 支付类型：0-账户余额支付， 1-微信支付，2-支付宝支付，3-货到付款
	private BigDecimal ivFreightAmount;//运费金额
	private String ivOwnProvinceCode;// 所属省份代码
	private String ivOwnCityCode;// 所属城市代码
	private String ivOwnCountyCode;// 所属区县代码
	private Long ivUserID;// 用户ID
	private String ivCreatedate;//
	private String ivUpdatedate;//
	public Long getPkInvoice() {
		return pkInvoice;
	}
	public void setPkInvoice(Long pkInvoice) {
		this.pkInvoice = pkInvoice;
	}
	public String getIvTaxpayerNumber() {
		return ivTaxpayerNumber;
	}
	public void setIvTaxpayerNumber(String ivTaxpayerNumber) {
		this.ivTaxpayerNumber = ivTaxpayerNumber;
	}
	public String getIvCompanyName() {
		return ivCompanyName;
	}
	public void setIvCompanyName(String ivCompanyName) {
		this.ivCompanyName = ivCompanyName;
	}
	public String getIvCompanyAddress() {
		return ivCompanyAddress;
	}
	public void setIvCompanyAddress(String ivCompanyAddress) {
		this.ivCompanyAddress = ivCompanyAddress;
	}
	public String getIvPhoneNumber() {
		return ivPhoneNumber;
	}
	public void setIvPhoneNumber(String ivPhoneNumber) {
		this.ivPhoneNumber = ivPhoneNumber;
	}
	public String getIvBankName() {
		return ivBankName;
	}
	public void setIvBankName(String ivBankName) {
		this.ivBankName = ivBankName;
	}
	public String getIvBankAccount() {
		return ivBankAccount;
	}
	public void setIvBankAccount(String ivBankAccount) {
		this.ivBankAccount = ivBankAccount;
	}
	public Double getIvInvoiceAmount() {
		return ivInvoiceAmount;
	}
	public void setIvInvoiceAmount(Double ivInvoiceAmount) {
		this.ivInvoiceAmount = ivInvoiceAmount;
	}
	public String getIvInvoiceNumber() {
		return ivInvoiceNumber;
	}
	public void setIvInvoiceNumber(String ivInvoiceNumber) {
		this.ivInvoiceNumber = ivInvoiceNumber;
	}
	public String getIvInvoiceContent() {
		return ivInvoiceContent;
	}
	public void setIvInvoiceContent(String ivInvoiceContent) {
		this.ivInvoiceContent = ivInvoiceContent;
	}
	public String getIvTrackNumber() {
		return ivTrackNumber;
	}
	public void setIvTrackNumber(String ivTrackNumber) {
		this.ivTrackNumber = ivTrackNumber;
	}
	public String getIvRecipients() {
		return ivRecipients;
	}
	public void setIvRecipients(String ivRecipients) {
		this.ivRecipients = ivRecipients;
	}
	public String getIvConsigneeAddress() {
		return ivConsigneeAddress;
	}
	public void setIvConsigneeAddress(String ivConsigneeAddress) {
		this.ivConsigneeAddress = ivConsigneeAddress;
	}
	public String getIvRecipientsNumber() {
		return ivRecipientsNumber;
	}
	public void setIvRecipientsNumber(String ivRecipientsNumber) {
		this.ivRecipientsNumber = ivRecipientsNumber;
	}
	public Integer getIvInvoiceType() {
		return ivInvoiceType;
	}
	public void setIvInvoiceType(Integer ivInvoiceType) {
		this.ivInvoiceType = ivInvoiceType;
	}
	public Integer getIvInvoiceStatus() {
		return ivInvoiceStatus;
	}
	public void setIvInvoiceStatus(Integer ivInvoiceStatus) {
		this.ivInvoiceStatus = ivInvoiceStatus;
	}
	public Integer getIvPayFreight() {
		return ivPayFreight;
	}
	public void setIvPayFreight(Integer ivPayFreight) {
		this.ivPayFreight = ivPayFreight;
	}
	public String getIvOwnProvinceCode() {
		return ivOwnProvinceCode;
	}
	public void setIvOwnProvinceCode(String ivOwnProvinceCode) {
		this.ivOwnProvinceCode = ivOwnProvinceCode;
	}
	public String getIvOwnCityCode() {
		return ivOwnCityCode;
	}
	public void setIvOwnCityCode(String ivOwnCityCode) {
		this.ivOwnCityCode = ivOwnCityCode;
	}
	public String getIvOwnCountyCode() {
		return ivOwnCountyCode;
	}
	public void setIvOwnCountyCode(String ivOwnCountyCode) {
		this.ivOwnCountyCode = ivOwnCountyCode;
	}
	public Long getIvUserID() {
		return ivUserID;
	}
	public void setIvUserID(Long ivUserID) {
		this.ivUserID = ivUserID;
	}
	public String getIvCreatedate() {
		return ivCreatedate;
	}
	public void setIvCreatedate(String ivCreatedate) {
		this.ivCreatedate = ivCreatedate;
	}
	public String getIvUpdatedate() {
		return ivUpdatedate;
	}
	public void setIvUpdatedate(String ivUpdatedate) {
		this.ivUpdatedate = ivUpdatedate;
	}
	
	
	
	public BigDecimal getIvFreightAmount() {
		return ivFreightAmount;
	}
	public void setIvFreightAmount(BigDecimal ivFreightAmount) {
		this.ivFreightAmount = ivFreightAmount;
	}
	@Override
	public String toString() {
		return "TblInvoice [pkInvoice=" + pkInvoice + ", ivTaxpayerNumber="
				+ ivTaxpayerNumber + ", ivCompanyName=" + ivCompanyName
				+ ", ivCompanyAddress=" + ivCompanyAddress + ", ivPhoneNumber="
				+ ivPhoneNumber + ", ivBankName=" + ivBankName
				+ ", ivBankAccount=" + ivBankAccount + ", ivInvoiceAmount="
				+ ivInvoiceAmount + ", ivInvoiceNumber=" + ivInvoiceNumber
				+ ", ivInvoiceContent=" + ivInvoiceContent + ", ivTrackNumber="
				+ ivTrackNumber + ", ivRecipients=" + ivRecipients
				+ ", ivConsigneeAddress=" + ivConsigneeAddress
				+ ", ivRecipientsNumber=" + ivRecipientsNumber
				+ ", ivInvoiceType=" + ivInvoiceType + ", ivInvoiceStatus="
				+ ivInvoiceStatus + ", ivPayFreight=" + ivPayFreight
				+ ", ivOwnProvinceCode=" + ivOwnProvinceCode
				+ ", ivOwnCityCode=" + ivOwnCityCode + ", ivOwnCountyCode="
				+ ivOwnCountyCode + ", ivUserID=" + ivUserID
				+ ", ivCreatedate=" + ivCreatedate + ", ivUpdatedate="
				+ ivUpdatedate + "]";
	}
	
	

}