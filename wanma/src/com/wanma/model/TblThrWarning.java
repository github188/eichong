package com.wanma.model;

import java.io.Serializable;
import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblThrWarning extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 945678568282441295L;
	/**
	 * 
	 */
	//主键
	private Integer thwPkId;
	// 商家用户ID
	private Integer thwUserId;
	// 商家公司名称
	private String thwBusiName;
	//企业账号
	private String userAccount;
	// 阀值设置 默认0
	private String thwThreshold;
	// 运营人员通知手机号
	private String thwCellphone;
	// 是否删除 默认0 不删除 1 删除
	private Short thwDeletetag;
	// 创建时间
	private Date createDate;
	// 更新时间
	private Date updateDate;
	
	private String pkCompanyId;
	//大客户预警通知手机号
	private String thwCustomerPhone;

	
	public String getThwCustomerPhone() {
		return thwCustomerPhone;
	}

	public void setThwCustomerPhone(String thwCustomerPhone) {
		this.thwCustomerPhone = thwCustomerPhone;
	}

	public String getPkCompanyId() {
		return pkCompanyId;
	}

	public void setPkCompanyId(String pkCompanyId) {
		this.pkCompanyId = pkCompanyId;
	}

	public Integer getThwPkId() {
		return thwPkId;
	}

	public void setThwPkId(Integer thwPkId) {
		this.thwPkId = thwPkId;
	}

	public Integer getThwUserId() {
		return thwUserId;
	}

	public void setThwUserId(Integer thwUserId) {
		this.thwUserId = thwUserId;
	}

	public String getThwBusiName() {
		return thwBusiName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setThwBusiName(String thwBusiName) {
		this.thwBusiName = thwBusiName == null ? null : thwBusiName.trim();
	}

	

	public String getThwThreshold() {
		return thwThreshold;
	}

	public void setThwThreshold(String thwThreshold) {
		this.thwThreshold = thwThreshold;
	}

	public String getThwCellphone() {
		return thwCellphone;
	}

	public void setThwCellphone(String thwCellphone) {
		this.thwCellphone = thwCellphone;
	}

	public Short getThwDeletetag() {
		return thwDeletetag;
	}

	public void setThwDeletetag(Short thwDeletetag) {
		this.thwDeletetag = thwDeletetag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}