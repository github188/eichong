/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.wanma.service;

import java.util.List;

import com.wanma.model.TblUserCard;

/**
 * FileName UserService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 充电卡用户业务处理接口
 */
public interface ChargeCardService {

	List<TblUserCard> getCardUserList(TblUserCard user);

	public long getCardCount(TblUserCard user);

	public void bindCard(TblUserCard userCard);

	public String getCardInfoByCardNo(String cardNo);

	public void updateUserard(TblUserCard userCard);

	TblUserCard getUsercard(TblUserCard userCard);

}
