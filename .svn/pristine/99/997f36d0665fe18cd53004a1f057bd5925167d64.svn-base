/**     
 * @Title:  TblUserServiceImpl.java   
 * @Package com.wanma.service.impl   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年4月28日 下午4:05:44   
 * @version V1.0     
 */  
package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import com.wanma.dao.TblPurchasehistoryMapper;
import com.wanma.dao.TblUserMapper;
import com.wanma.model.ChargeGive;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;
import com.wanma.service.TblUserService;

/**
 * @author bc
 *
 */
@Service
public class TblUserServiceImpl implements TblUserService{
	@Autowired
	private TblUserMapper userDao;
	
	@Autowired
	private TblPurchasehistoryMapper tblPurchasehistoryMapper;
	
	@Override
	public TblUser getNormUserByAccount(String userAccount) {
		return userDao.getByAccount(userAccount);
	}
	
	@Override
	public void insertUser(TblUser user) throws Exception {
		try {
			// 调用DAO执行用户添加处理
			userDao.insert(user);
			userDao.insertNormalUser(user);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	//更新tbl_user_normal表
	public int updateNormalUser(TblUser tblUser){
		return userDao.updateNormalUser(tblUser);
	}

	@Override
	public void addCharge(ChargeGive chargegive, TblUser user) {
		
		try {
			//充值记录新增
        TblPurchasehistory purchase = new TblPurchasehistory();
			purchase.setPuhiUserid(user.getUserId().intValue());
			purchase.setPuhiType(4);
			purchase.setPuhiMonetary(new BigDecimal(chargegive.getCharge()));
			purchase.setPuhiConsumerremark(chargegive.getRemark());
			Date d = new Date();
			purchase.setPuhiPurchasehistorytime(d);
			purchase.setPuhiCreatedate(d);
			purchase.setPuhiUpdatedate(d);
			purchase.setPuhiChargeType(7);
			purchase.setPuhiPurchasecontent("人工充值");
			tblPurchasehistoryMapper.insert(purchase);
			//赠送记录新增
			if(new Double(chargegive.getGift())!=0){
				TblPurchasehistory p = new TblPurchasehistory();
				p.setPuhiUserid(user.getUserId().intValue());
				p.setPuhiType(4);
				p.setPuhiMonetary(new BigDecimal(chargegive.getGift()));
				p.setPuhiConsumerremark(chargegive.getRemark());
				p.setPuhiPurchasehistorytime(d);
				p.setPuhiCreatedate(d);
				p.setPuhiUpdatedate(d);
				p.setPuhiChargeType(6);
				p.setPuhiPurchasecontent("充值送");
				tblPurchasehistoryMapper.insert(p);
				}	
			//用户表账户金额数量修改
				user.setNormAccountBalance(user.getNormAccountBalance().add(
						purchase.getPuhiMonetary()).add(new BigDecimal(chargegive.getGift())));
				userDao.updateNormalUser(user);
			
		} catch (Exception e) {
			throw e;
		}
		
		
	}

	
	
	
}
