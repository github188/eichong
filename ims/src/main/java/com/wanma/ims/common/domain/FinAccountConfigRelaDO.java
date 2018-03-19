package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;


/**
 * 账务配置
 * @author bingo
 * @date 2017-06-13
 */
public class FinAccountConfigRelaDO extends BasicModel{
	
	private static final long serialVersionUID = 8310558559568944392L;

	/** 主键 */
	private Long pkId;
	
	/** 渠道ID  */
	private Long cpyId;
	
	/** 账单科目ID   */
	private Long billAccountId;
	
	/** 付费策略 1.优先扣大账户 2. 扣自己  3.为小账户分配额度 */
	private Integer paymentRule;
	
	/** 是否删除 0.否 1.是 */
	private Integer isDel;
	
	private String accountNO;//资金账户号
	
	private String paymentRuleName;//付费策略名称

	private String billAccountName;//账单科目名称
	
	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public Long getCpyId() {
		return cpyId;
	}

	public void setCpyId(Long cpyId) {
		this.cpyId = cpyId;
	}

	public Long getBillAccountId() {
		return billAccountId;
	}

	public void setBillAccountId(Long billAccountId) {
		this.billAccountId = billAccountId;
	}

	public Integer getPaymentRule() {
		return paymentRule;
	}

	public void setPaymentRule(Integer paymentRule) {
		this.paymentRule = paymentRule;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getAccountNO() {
		return accountNO;
	}

	public void setAccountNO(String accountNO) {
		this.accountNO = accountNO;
	}

	public String getPaymentRuleName() {
		return paymentRuleName;
	}

	public void setPaymentRuleName(String paymentRuleName) {
		this.paymentRuleName = paymentRuleName;
	}

	public String getBillAccountName() {
		return billAccountName;
	}

	public void setBillAccountName(String billAccountName) {
		this.billAccountName = billAccountName;
	}
}