package com.wanma.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblActivity extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3112793700180198885L;

	private Integer pkActivity;

	private String actActivityname;

	private Integer actType;

	private Integer actChanneltype;

	private String actStatus;

	private Integer actActivityrule;

	private String actCreateuserid;

	private String actUpdateuserid;

	private String actRemark;

	private Date actBegindate;

	private Date actEnddate;
	
	private Date actCouponEndDate;

	private Date actCreatedate;

	private Date actUpdatedate;

	private List<TblActivity> headList;

	private Integer num;

	private String actsType;
	private Integer pkCouponVariety;

	private String prizeName;

	private String actBegindates;

	private String actEnddates;

	private String actCouponEndDates;
	private String first;// 1:代表首次进入该页面
	
	private Date currentDate;
	
	private Integer pkCompanyId;//下拉列表公司标识id
	
	private String cpyCompanyName;//下拉列表公司名称
	
	private String singelMoney;
	
	private  String actTopMoney;
	
	

	public String getActTopMoney() {
		return actTopMoney;
	}

	public void setActTopMoney(String actTopMoney) {
		this.actTopMoney = actTopMoney;
	}

	public String getSingelMoney() {
		return singelMoney;
	}

	public void setSingelMoney(String singelMoney) {
		this.singelMoney = singelMoney;
	}

	public Integer getPkCompanyId() {
		return pkCompanyId;
	}

	public void setPkCompanyId(Integer pkCompanyId) {
		this.pkCompanyId = pkCompanyId;
	}

	public String getCpyCompanyName() {
		return cpyCompanyName;
	}

	public void setCpyCompanyName(String cpyCompanyName) {
		this.cpyCompanyName = cpyCompanyName;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	
	public Date getActBegindate() {
		return actBegindate;
	}

	public void setActBegindate(Date actBegindate) {
		this.actBegindate = actBegindate;
	}

	public String getActBegindates() {
		return actBegindates;
	}

	public void setActBegindates(String actBegindates) {
		this.actBegindates = actBegindates;
	}

	public String getActEnddates() {
		return actEnddates;
	}

	public void setActEnddates(String actEnddates) {
		this.actEnddates = actEnddates;
	}

	public String getPrizeName() {
		
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getActsType() {
		return actsType;
	}

	public void setActsType(String actsType) {
		this.actsType = actsType;
	}

	public Integer getPkCouponVariety() {
		return pkCouponVariety;
	}

	public void setPkCouponVariety(Integer pkCouponVariety) {
		this.pkCouponVariety = pkCouponVariety;
	}

	public List<TblActivity> getHeadList() {
		return headList;
	}

	public void setHeadList(List<TblActivity> headList) {
		this.headList = headList;
	}

	public Integer getPkActivity() {
		return pkActivity;
	}

	public void setPkActivity(Integer pkActivity) {
		this.pkActivity = pkActivity;
	}

	public String getActActivityname() {
		return actActivityname;
	}

	public void setActActivityname(String actActivityname) {
		this.actActivityname = actActivityname == null ? null : actActivityname
				.trim();
	}

	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}

	public Integer getActChanneltype() {
		return actChanneltype;
	}

	public void setActChanneltype(Integer actChanneltype) {
		this.actChanneltype = actChanneltype;
	}

	public String getActStatus() {
		return actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Integer getActActivityrule() {
		return actActivityrule;
	}

	public void setActActivityrule(Integer actActivityrule) {
		this.actActivityrule = actActivityrule;
	}

	public String getActCreateuserid() {
		return actCreateuserid;
	}

	public void setActCreateuserid(String actCreateuserid) {
		this.actCreateuserid = actCreateuserid == null ? null : actCreateuserid
				.trim();
	}

	public String getActUpdateuserid() {
		return actUpdateuserid;
	}

	public void setActUpdateuserid(String actUpdateuserid) {
		this.actUpdateuserid = actUpdateuserid == null ? null : actUpdateuserid
				.trim();
	}

	public String getActRemark() {
		return actRemark;
	}

	public void setActRemark(String actRemark) {
		this.actRemark = actRemark == null ? null : actRemark.trim();
	}

	
	public Date getActEnddate() {
		return actEnddate;
	}

	public void setActEnddate(Date actEnddate) {
		this.actEnddate = actEnddate;
	}

	public Date getActCreatedate() {
		return actCreatedate;
	}

	public void setActCreatedate(Date actCreatedate) {
		this.actCreatedate = actCreatedate;
	}

	public Date getActUpdatedate() {
		return actUpdatedate;
	}

	public void setActUpdatedate(Date actUpdatedate) {
		this.actUpdatedate = actUpdatedate;
	}

	public Date getActCouponEndDate() {
		return actCouponEndDate;
	}

	public void setActCouponEndDate(Date actCouponEndDate) {
		this.actCouponEndDate = actCouponEndDate;
	}

	public String getActCouponEndDates() {
		return actCouponEndDates;
	}

	public void setActCouponEndDates(String actCouponEndDates) {
		this.actCouponEndDates = actCouponEndDates;
	}
	
	
}