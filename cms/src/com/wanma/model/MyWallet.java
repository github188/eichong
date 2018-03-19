package com.wanma.model;

import java.util.List;

/**
 * 我的钱包
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-23 下午03:22:04 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class MyWallet {

	private String balance;//余额
	private List<WalletRecord> consumeRecord;//消费记录
	private List<WalletRecord> earningsRecord;//收益记录
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public List<WalletRecord> getConsumeRecord() {
		return consumeRecord;
	}
	public void setConsumeRecord(List<WalletRecord> consumeRecord) {
		this.consumeRecord = consumeRecord;
	}
	public List<WalletRecord> getEarningsRecord() {
		return earningsRecord;
	}
	public void setEarningsRecord(List<WalletRecord> earningsRecord) {
		this.earningsRecord = earningsRecord;
	}
	 
	
	
}
