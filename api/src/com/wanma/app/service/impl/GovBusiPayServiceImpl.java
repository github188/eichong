package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanma.app.dao.WebTblUserMapper;
import com.wanma.app.dao.WebPurchasehistoryMapper;
import com.wanma.app.service.GovBusiPayService;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;

/**
 * @author gx
 * @Description: 用户信息业务处理类
 * @createTime：2017-10-14 上午10:31:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class GovBusiPayServiceImpl implements GovBusiPayService {
	private  Log log = LogFactory.getLog(this.getClass());
	/**
	 * 用户信息业务操作DAO
	 */
	@Autowired
	private WebTblUserMapper userDao;
	@Autowired
	private WebPurchasehistoryMapper tblPurchasehistoryMapper;


	public Boolean addReChargeRecord(HttpServletRequest request) {
		log.error("addGovReChargeRecord start...");
		String tradeNo = request.getParameter("out_trade_no");
		String totalFee = request.getParameter("total_fee");
		//body参数格式:userId_充值类型_订单id
		String[] extArray = request.getParameter("body").split("_");
		String userId="",puhiType="";
		if(extArray.length==1){
			userId=extArray[0];
			puhiType="4";
			
		}else{
			userId=extArray[0];
			puhiType=extArray[1];
			
		}
		log.error("addGovReChargeRecord2 1");
		
		String puhiPurchasecontent="支付宝充值";
		
		TblPurchasehistory purchase = new TblPurchasehistory();
		purchase.setPuhiUserid(new Integer(userId));
		purchase.setPuhiType(new Integer(puhiType));
		purchase.setPuhiMonetary(new BigDecimal(totalFee));
		purchase.setPuhiPaymentNumber(tradeNo);
		purchase.setPuhiConsumerremark(tradeNo);
		Date d = new Date();
		purchase.setPuhiPurchasehistorytime(d);
		purchase.setPuhiCreatedate(d);
		purchase.setPuhiUpdatedate(d);
		purchase.setPuhiPurchasecontent(puhiPurchasecontent);
		purchase.setPuhiChargeType(1);
		log.error("addGovReChargeRecord2 2");
		return userRecharge(purchase, request);
	}

	public Boolean addReChargeRecordForTenpay(Map<String, Object> map,
			HttpServletRequest request) {
		log.error("addGovReChargeRecordForTenpay2 start...");
		String tradeNo = (String) map.get("out_trade_no");
		String totalFee = (String) map.get("total_fee");
		log.error("addGovReChargeRecordForTenpay2 step1");
		BigDecimal fee = new BigDecimal(totalFee).divide(new BigDecimal("100"),
				2, RoundingMode.UP);
		log.error("addGovReChargeRecordForTenpay2 step2");
		String extString = tradeNo.split("ech")[1];
		String[] extArray = extString.split("_");
		String userId="",puhiType="";
		if(extArray.length==1){
			userId=extArray[0];
			puhiType="4";
			
		}else{
			userId=extArray[0];
			puhiType=extArray[1];
			
		}
		String puhiPurchasecontent="微信充值";
		
		log.error("addGovReChargeRecordForTenpay2 step2");
		
		TblPurchasehistory purchase = new TblPurchasehistory();
		purchase.setPuhiUserid(new Integer(userId));
		purchase.setPuhiType(new Integer(puhiType));
		purchase.setPuhiMonetary(fee);
		purchase.setPuhiPaymentNumber(tradeNo);
		purchase.setPuhiConsumerremark(tradeNo);
		
		Date d = new Date();
		purchase.setPuhiPurchasehistorytime(d);
		purchase.setPuhiCreatedate(d);
		purchase.setPuhiUpdatedate(d);
		purchase.setPuhiChargeType(2);
		purchase.setPuhiPurchasecontent(puhiPurchasecontent);
		log.error("addGovReChargeRecordForTenpay2 step3");
		
		return userRecharge(purchase, request);

	}

	private Boolean userRecharge(TblPurchasehistory purchase,
			HttpServletRequest request) {
		log.error("govUserRecharge start...");
		// 用户账户添加余额
		TblUser user = new TblUser();
		user.setUserId(purchase.getPuhiUserid().longValue());
		user = userDao.getGovBusiUser(user);
		user.setNormAccountBalance(user.getNormAccountBalance().add(
				purchase.getPuhiMonetary()));
		if (user.getNormAccountBalance().intValue() > 1000000) {
			return false;
		}
		// 添加充值记录
	
		long count = tblPurchasehistoryMapper.getPurchaseHistory(purchase);
		if (count == 0&&purchase.getPuhiType()==4) {
		
			// 添加充值记录
			tblPurchasehistoryMapper.insertCompany(purchase);
            //更新资金账户余额			
			userDao.updateGovFinAccount(user);
		
			log.error("govUserRecharge end...");
			return true;
		} else {
			log.error("govUserRecharge error...");
			return false;
		}
	}

	
	
	
}
