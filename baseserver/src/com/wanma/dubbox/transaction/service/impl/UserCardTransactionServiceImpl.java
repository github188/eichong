package com.wanma.dubbox.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblCardapplicationform;
import com.wanma.dubbox.model.TblUserCard;
import com.wanma.dubbox.model.TblUserNormal;
import com.wanma.dubbox.service.TblCardapplicationformService;
import com.wanma.dubbox.service.TblUserCardService;
import com.wanma.dubbox.service.TblUserNormalService;
import com.wanma.dubbox.transaction.service.UserCardTransactionService;

/**
 * 充电卡管理事务控制接口
 * 
 * @author lhy
 *
 */
public class UserCardTransactionServiceImpl implements
		UserCardTransactionService {
	@Autowired
	private TblUserCardService tblUserCardService;
	@Autowired
	private TblUserNormalService userNormalService;
	@Autowired
	private TblCardapplicationformService cafService;

	@Override
	public void bind(TblUserCard cModel,TblCardapplicationform cafModel) throws Exception {
		tblUserCardService.update(cModel);
		TblUserNormal nmuModel = new TblUserNormal();
		nmuModel.setId(cModel.getUid());
		nmuModel.setAplyCd(new Short("2"));
		userNormalService.update(nmuModel);
		if(cafModel != null && cafModel.getId() != null){
			TblUserCard tempCard=tblUserCardService.selectOne(cModel);
			cafModel.setCdNum(tempCard.getExtNum());
			cafModel.setSts(1);
			cafService.update(cafModel);
		}
	}
}
