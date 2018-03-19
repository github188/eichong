package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 分账配置历史
 * 
 * @author bingo
 * @date 2017-11-27
 */
public class FinAccountSplitConfigHistoryDO extends BasicModel {

	private static final long serialVersionUID = 1432784845695432123L;

	/** 分账配置历史ID */
	private Long pkId;

	/** 分账方式（0：服务费&电费分成） */
	private Integer splitMode;

	/** 资产公司id */
	private Long cpyId;

	/** 电桩id */
	private Long electricPileId;

	/** 分账规则（json格式：｛公司id，服务费比例值，电费比例值｝）{"data":[{"cpyId":"","servicesRatio":"","electricityRatio":""}]} */
	private String splitRules;

	/** 关联的分账配置ID */
	private Long refId;

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

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}
}
