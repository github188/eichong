package com.wanma.model;

import java.util.List;

/**
 *  确认订单
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-24 上午11:28:06 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class AffirmOrder {

	private String userId;//用户名称
	private String receiveName;//收货人
	private String receiveTel;//收货人联系方式
	private String receiveAddress;//收货地址
	
	
	private List<AffirmOrderList> affirmOrderList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AffirmOrderList> getAffirmOrderList() {
		return affirmOrderList;
	}

	public void setAffirmOrderList(List<AffirmOrderList> affirmOrderList) {
		this.affirmOrderList = affirmOrderList;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	 
	
}
