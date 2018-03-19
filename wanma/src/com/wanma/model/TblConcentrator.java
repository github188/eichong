package com.wanma.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_concentrator表
 * 
 * @author lhy
 * 
 */
public class TblConcentrator extends BasicListAndMutiFile implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pkConcentratorID; // 主键
	private String coctConcentratorName; // 集中器名称
	private String coctSIMMAC; // SIM卡号
	private String coctSIMCODE; // SIM卡编号
	private Integer coctSIMTYPE; // SIM来源(1：联通,2：电信,3：移动)
	private Integer coctState; // 集中器状态(0：离线,2：上线)
	private Long coctUserId; // 所属用户ID
	private String coctUserName; // 用户姓名
	private Date coctCreatedate; // 创建时间
	private Date coctUpdatedate; // 修改时间
	private String postEleids;  //绑定集中器电桩ID序列
	
	private List<TblElectricpile> pileList;//绑定电桩列表
	
	private String userLevel;//用户级别

	public java.lang.Integer getPkConcentratorID() {
		return pkConcentratorID;
	}

	public void setPkConcentratorID(java.lang.Integer pkConcentratorID) {
		this.pkConcentratorID = pkConcentratorID;
	}

	public java.lang.String getCoctConcentratorName() {
		return coctConcentratorName;
	}

	public void setCoctConcentratorName(java.lang.String coctConcentratorName) {
		this.coctConcentratorName = coctConcentratorName;
	}

	public java.lang.String getCoctSIMMAC() {
		return coctSIMMAC;
	}

	public void setCoctSIMMAC(java.lang.String coctSIMMAC) {
		this.coctSIMMAC = coctSIMMAC;
	}

	public java.lang.String getCoctSIMCODE() {
		return coctSIMCODE;
	}

	public void setCoctSIMCODE(java.lang.String coctSIMCODE) {
		this.coctSIMCODE = coctSIMCODE;
	}

	public java.lang.Integer getCoctSIMTYPE() {
		return coctSIMTYPE;
	}

	public void setCoctSIMTYPE(java.lang.Integer coctSIMTYPE) {
		this.coctSIMTYPE = coctSIMTYPE;
	}

	public java.lang.Integer getCoctState() {
		return coctState;
	}

	public void setCoctState(java.lang.Integer coctState) {
		this.coctState = coctState;
	}

	public Long getCoctUserId() {
		return coctUserId;
	}

	public void setCoctUserId(Long coctUserId) {
		this.coctUserId = coctUserId;
	}

	public String getCoctUserName() {
		return coctUserName;
	}

	public void setCoctUserName(String coctUserName) {
		this.coctUserName = coctUserName;
	}

	public Date getCoctCreatedate() {
		return coctCreatedate;
	}

	public void setCoctCreatedate(Date coctCreatedate) {
		this.coctCreatedate = coctCreatedate;
	}

	public Date getCoctUpdatedate() {
		return coctUpdatedate;
	}

	public void setCoctUpdatedate(Date coctUpdatedate) {
		this.coctUpdatedate = coctUpdatedate;
	}

	public List<TblElectricpile> getPileList() {
		return pileList;
	}

	public void setPileList(List<TblElectricpile> list) {
		this.pileList = list;
	}
	
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getPostEleids() {
		return postEleids;
	}

	public void setPostEleids(String postEleids) {
		this.postEleids = postEleids;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblConcentrator");
		sb.append("{pkConcentratorID=").append(pkConcentratorID);
		sb.append(", coctConcentratorName=").append(coctConcentratorName);
		sb.append(", coctSIMMAC=").append(coctSIMMAC);
		sb.append(", coctSIMCODE=").append(coctSIMCODE);
		sb.append(", coctSIMTYPE=").append(coctSIMTYPE);
		sb.append(", elPiUserId=").append(coctUserId);
		sb.append(", elPiCreatedate=").append(coctCreatedate);
		sb.append(", elPiUpdatedate=").append(coctUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}