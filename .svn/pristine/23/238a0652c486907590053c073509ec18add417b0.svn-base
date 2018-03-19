package com.wanma.app.service.impl;



import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.ChargeCardMapper;
import com.wanma.app.dao.TblPurchasehistoryMapper;
import com.wanma.app.service.ChargeCardService;
import com.wanma.app.util.RandomUtil;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;

@Service
public class ChargeCardServiceImpl implements ChargeCardService{

	@Override
	public void initCard(Map<String, String> params) throws Exception {
		
		TblUser user =new TblUser();
		String account = RandomUtil.createData(11);	
		user.setUserAccount(account);
		chargeCardMapper.addUser(user);
		params.put("userId", user.getUserId().toString());
		chargeCardMapper.initCard(params);
	

		
	}
	
	@Override
	public int countCardByOutNum(String outNum) throws Exception{
		return chargeCardMapper.countCardByOutNum(outNum);
	}
	
	@Override
	public List<HashMap<String, String>> getCompMarkList() throws Exception {
		return chargeCardMapper.getCompMarkList();
	}

	@Override
	public Map<String, Object> getCardInfoByInNum(String inNum) throws Exception {
		return chargeCardMapper.getCardInfoByInNum(inNum);
	}
	
	@Override
	public int countCardByOutAndInNum(Map<String, Object> params) throws Exception {
		return chargeCardMapper.countCardByOutAndInNum(params);
	}
	
	@Override
	public void addPurchaseHis(String sum,String userPhone,String outNum,String account,int userId) throws Exception {
		TblPurchasehistory ph = new TblPurchasehistory();
		ph.setPuhiType(4);
		ph.setChargeType(4);
		ph.setPuhiPurchasehistorytime(new Date());
		ph.setPuhiMonetary(new BigDecimal(sum));
		ph.setPuhiConsumerremark("充值账户：" + userPhone + ",充电卡外卡号：" + outNum + ",操作员账户：" + account);
		ph.setPuhiCreatedate(new Date());
		ph.setPuhiPurchasecontent("现金充值");
		ph.setPuhiUserid(userId);
		tblPhMapper.insert(ph);
		
	}
	
	@Override
	public List<Map<String, String>> cardListByUid(long uId) throws Exception {
		return chargeCardMapper.cardListByUid(uId);
	}
	
	@Override
	public Map<String,Object> getcardByUid(long userId) throws Exception {
		
		return chargeCardMapper.getcardByUid(userId);
	}
	
	
	@Override//	<select id ="myApplyInfo" parameterType="long" resultType="map">
	public Map<String,Object>myApplyInfo(long userId) throws Exception {
		
		return chargeCardMapper.myApplyInfo(userId);
	}
	
	@Override
	public void updateStatusByUidAndOutNum(Map<String, String> params)	throws Exception {
		chargeCardMapper.updateStatusByUidAndOutNum(params);
	}
	
	@Autowired
	private ChargeCardMapper chargeCardMapper;
	@Autowired
	private TblPurchasehistoryMapper tblPhMapper;
	
	

	

}
