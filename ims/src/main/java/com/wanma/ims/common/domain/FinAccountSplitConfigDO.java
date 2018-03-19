package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 分账配置
 * 
 * @author bingo
 * @date 2017-11-27
 */
public class FinAccountSplitConfigDO extends BasicModel {

	private static final long serialVersionUID = 6272720633428191998L;

	/** 分账配置ID */
	private Long pkId;

	/** 分账方式（0：服务费&电费分成；1：电量*单价） */
	private Integer splitMode;

	/** 资产公司id */
	private Long cpyId;

	/** 电桩id */
	private Long electricPileId;

	/** 分账规则（json格式：如果分账方式是0，则格式为｛公司id，服务费比例值，电费比例值｝； 如果分账方式是1，则格式为｛资产公司id，0｝｛公司id，分账价格｝；）
	 * {"data":[{"cpyId":"","servicesRatio":"","electricityRatio":""}或{"cpyId":"","price":""}]} */
	private String splitRules;

	private Double servicesRatio;//服务费比例值

	private Double electricityRatio;//电费比例值

	private Double price;//分账价格

	private String electricPileIds;//电桩Ids(桩id以‘，’拼接)

	private String splitModeName;//分账方式名称

	private Integer forListFlag = 1;//区分是否为展示列表(0:是；1：不是)

	private String cpyName;//资产公司名称
	private String strCpyName;//多个公司的名字拼接
	private String strServicesRatio;//多个公司的服务费比例值拼接
	private String strElectricityRatio;//多个公司的电费比例值拼接
	private String strPrice;//多个公司的分账价格值拼接

	private String addElectricPileIds;//电桩Ids(桩id以‘，’拼接)
	private String updElectricPileIds;//电桩Ids(桩id以‘，’拼接)
	private String delElectricPileIds;//电桩Ids(桩id以‘，’拼接)

	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public Integer getSplitMode() {
		return splitMode;
	}

	public void setSplitMode(Integer splitMode) {
		this.splitMode = splitMode;
	}

	public Long getCpyId() {
		return cpyId;
	}

	public void setCpyId(Long cpyId) {
		this.cpyId = cpyId;
	}

	public Long getElectricPileId() {
		return electricPileId;
	}

	public void setElectricPileId(Long electricPileId) {
		this.electricPileId = electricPileId;
	}

	public String getSplitRules() {
		return splitRules;
	}

	public void setSplitRules(String splitRules) {
		this.splitRules = splitRules;
	}

	public Double getServicesRatio() {
		return servicesRatio;
	}

	public void setServicesRatio(Double servicesRatio) {
		this.servicesRatio = servicesRatio;
	}

	public Double getElectricityRatio() {
		return electricityRatio;
	}

	public void setElectricityRatio(Double electricityRatio) {
		this.electricityRatio = electricityRatio;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getElectricPileIds() {
		return electricPileIds;
	}

	public void setElectricPileIds(String electricPileIds) {
		this.electricPileIds = electricPileIds;
	}

	public String getSplitModeName() {
		return splitModeName;
	}

	public void setSplitModeName(String splitModeName) {
		this.splitModeName = splitModeName;
	}

	public Integer getForListFlag() {
		return forListFlag;
	}

	public void setForListFlag(Integer forListFlag) {
		this.forListFlag = forListFlag;
	}

	public String getCpyName() {
		return cpyName;
	}

	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}

	public String getStrCpyName() {
		return strCpyName;
	}

	public void setStrCpyName(String strCpyName) {
		this.strCpyName = strCpyName;
	}

	public String getStrServicesRatio() {
		return strServicesRatio;
	}

	public void setStrServicesRatio(String strServicesRatio) {
		this.strServicesRatio = strServicesRatio;
	}

	public String getStrElectricityRatio() {
		return strElectricityRatio;
	}

	public void setStrElectricityRatio(String strElectricityRatio) {
		this.strElectricityRatio = strElectricityRatio;
	}

	public String getStrPrice() {
		return strPrice;
	}

	public void setStrPrice(String strPrice) {
		this.strPrice = strPrice;
	}

	public String getAddElectricPileIds() {
		return addElectricPileIds;
	}

	public void setAddElectricPileIds(String addElectricPileIds) {
		this.addElectricPileIds = addElectricPileIds;
	}

	public String getUpdElectricPileIds() {
		return updElectricPileIds;
	}

	public void setUpdElectricPileIds(String updElectricPileIds) {
		this.updElectricPileIds = updElectricPileIds;
	}

	public String getDelElectricPileIds() {
		return delElectricPileIds;
	}

	public void setDelElectricPileIds(String delElectricPileIds) {
		this.delElectricPileIds = delElectricPileIds;
	}
}
