/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblCardapplicationformMapper;
import com.wanma.dao.TblUserCardMapper;
import com.wanma.dao.TblUserMapper;
import com.wanma.model.TblCardapplicationform;
import com.wanma.model.TblUserCard;
import com.wanma.service.ChargeCardService;

/**
 * FileName UserService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 充电卡用户业务处理实现类
 */
@Service
public class ChargeCardServiceImpl implements ChargeCardService {
	
	/** 用户表操作用DAO */
	@Autowired
	private TblUserMapper tblUserMapper;
	
	@Autowired
	private TblUserCardMapper tblUserCardMapper;
	
	@Autowired
	private TblCardapplicationformMapper  cardApllyMapper;
	
	@Override
	public List<TblUserCard> getCardUserList(TblUserCard user) {
		
		return tblUserCardMapper.getCardList(user);
	}

	@Override
	public long getCardCount(TblUserCard userCard) {
		return tblUserCardMapper.getCardListCount(userCard);
	}

	@Override
	public void bindCard(TblUserCard userCard) {
		tblUserCardMapper.bindCard(userCard);
		tblUserCardMapper.bindCardUser(userCard);
		if(userCard.getPkCardapplicationform()!=null){
			TblUserCard tempCard=tblUserCardMapper.getUserCard(userCard);
			TblCardapplicationform card=new TblCardapplicationform();
			card.setPkCardapplicationform(userCard.getPkCardapplicationform());
			card.setCafUsercard(tempCard.getUcExternalCardNumber());
			cardApllyMapper.bindCardApply(card);
		}
		
	}

	@Override
	public String getCardInfoByCardNo(String cardNo) {
		return tblUserMapper.findUnbindCardByCardNo(cardNo);
	}

	@Override
	public void updateUserard(TblUserCard userCard) {
		tblUserCardMapper.updateUserard(userCard);
	}

	@Override
	public TblUserCard getUsercard(TblUserCard userCard) {
		return tblUserCardMapper.getUserCard(userCard);
	}
	
	
}
