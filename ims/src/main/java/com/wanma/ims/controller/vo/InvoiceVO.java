package com.wanma.ims.controller.vo;

import com.wanma.ims.common.domain.InvoiceDO;

public class InvoiceVO  {
	private Long pkInvoice;
	private Double ivInvoiceAmount;//开票金额
	private Double ivFreightAmount;//运费金额
	private String ivRecipients;//收件人姓名
	private Integer ivInvoiceStatus;//发票申请状态：0-已申请， 1-已开票 ，2-已申请，未支付邮费,3-已拒绝
	private Integer ivPayFreight;//支付类型：0-账户余额支付， 1-支付宝支付，2-微信支付，3-银联支付 ,4-货到付款
	private String ivCreatedate;//创建时间(申请时间)
	private String ivUpdatedate;//更新时间(开票时间)
	private Integer ivReceipType;//发票类型：0-普通发票，1-增值税专用发票
	  /**
     *非持久化字段
     * */
	private String userAccount;
	
	
	public Long getPkInvoice() {
		return pkInvoice;
	}


	public void setPkInvoice(Long pkInvoice) {
		this.pkInvoice = pkInvoice;
	}


	public Double getIvInvoiceAmount() {
		return ivInvoiceAmount;
	}


	public void setIvInvoiceAmount(Double ivInvoiceAmount) {
		this.ivInvoiceAmount = ivInvoiceAmount;
	}


	public Double getIvFreightAmount() {
		return ivFreightAmount;
	}


	public void setIvFreightAmount(Double ivFreightAmount) {
		this.ivFreightAmount = ivFreightAmount;
	}


	public String getIvRecipients() {
		return ivRecipients;
	}


	public void setIvRecipients(String ivRecipients) {
		this.ivRecipients = ivRecipients;
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


	public Integer getIvReceipType() {
		return ivReceipType;
	}


	public void setIvReceipType(Integer ivReceipType) {
		this.ivReceipType = ivReceipType;
	}


	public String getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}


	public static InvoiceVO valueOf(InvoiceDO invoiceDO) {
		InvoiceVO invoiceVO = new InvoiceVO();
		invoiceVO.setIvCreatedate(invoiceDO.getIvCreatedate());
		invoiceVO.setIvUpdatedate(invoiceDO.getIvUpdatedate());
		invoiceVO.setIvFreightAmount(invoiceDO.getIvFreightAmount());
		invoiceVO.setIvInvoiceAmount(invoiceDO.getIvInvoiceAmount());
		invoiceVO.setIvInvoiceStatus(invoiceDO.getIvInvoiceStatus());
		invoiceVO.setIvPayFreight(invoiceDO.getIvPayFreight());
		invoiceVO.setIvReceipType(invoiceDO.getIvReceipType());
		invoiceVO.setIvRecipients(invoiceDO.getIvRecipients());
		invoiceVO.setPkInvoice(invoiceDO.getPkInvoice());
		
		return invoiceVO;
        
    }
	
}
