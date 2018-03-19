package com.wanma.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.model.TblPartner;
import com.wanma.service.CommonService;
import com.wanma.service.NariChargeOrderPushService;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TcbPartnerService;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.CecPost;

@Service
public class NariChargeOrderPushServiceImpl implements NariChargeOrderPushService{
	private static final Logger LOGGER = LoggerFactory.getLogger(NariChargeOrderPushServiceImpl.class);
	@Autowired
	private TblChargingOrderService ordService;
	@Autowired
	private TcbPartnerService partnerService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private RedisService redisService;
	
	@Override
	public int nariChargeOrderPush(String startTime, String endTime) throws Exception{
		try {
			LOGGER.info("...........南京南瑞订单信息变化推送-begin............");
			//获取充电订单信息
	    	Map<String,Object> model = new HashMap<>();
	    	model.put("startTime", startTime);
	    	model.put("endTime", endTime); 
	    	List<Map<String,Object>> list = ordService.getNariChargeOrder(model);
	    	//查询结果为空
	    	if(list.isEmpty()){	    		
	        	return  1;			
	    	} 
	    	
	    	for (int i = 0; i < list.size(); i++) {
				Map<String,Object> map = list.get(i);
				 //桩体编码
				String epCode = map.get("epCode").toString();
				//枪头编号
				int ehId = Integer.parseInt(map.get("ehId").toString());
				//充电设备接口编码--桩体编码   + 枪头编号（两位）
				String ConnectorID = null;
				if(ehId < 10){
					ConnectorID = epCode + "0" +ehId;
				}else{
					ConnectorID = epCode + ehId;
				}
				String startChargeSeq = map.get("startChargeSeq").toString();
				String userPhone = map.get("userPhone").toString();
				//累计> 充电量、电费、服务费、总金额
				String totalPower = map.get("totalPower").toString();
				String elecMoney = map.get("elecMoney").toString();
				String serviceMoney = map.get("serviceMoney").toString();
				String totalMoney = map.get("totalMoney").toString();
				//尖峰平谷各时段电量
				String cuspElect = map.get("cuspElect").toString();
				String peakElect = map.get("peakElect").toString();
				String flatElect = map.get("flatElect").toString();
				String valleyElect = map.get("valleyElect").toString();
				//充电开始、结束时间
				String beginTime = map.get("startTime").toString();
				String lastTime = map.get("endTime").toString();
				Map<String,Object> nr = new HashMap<>();
				nr.put("OperatorID", "321895837");
				nr.put("ConnectorID", ConnectorID);
				nr.put("StartChargeSeq", startChargeSeq);
				nr.put("UserChargeType", 3);
				if("0".equals(userPhone)){
					nr.put("MobileNumber", null);
				}else{
					nr.put("MobileNumber", userPhone);
				}
				nr.put("Money", new BigDecimal(totalMoney));
				nr.put("ElectMoney", new BigDecimal(elecMoney));
				nr.put("ServiceMoney", new BigDecimal(serviceMoney));
				nr.put("Elect", new BigDecimal(totalPower));
				//尖阶段信息
				nr.put("CuspElect",new BigDecimal(cuspElect));
				nr.put("CuspElectPrice", 0);
				nr.put("CuspServicePrice", 0);
				nr.put("CuspMoney", 0);
				nr.put("CuspElectMoney", 0);
				nr.put("CuspServiceMoney", 0);
				//峰阶段信息
				nr.put("PeakElect", new BigDecimal(peakElect));
				nr.put("PeakElectPrice", 0);
				nr.put("PeakServicePrice", 0);
				nr.put("PeakMoney", 0);
				nr.put("PeakElectMoney", 0);
				nr.put("PeakServiceMoney", 0);
				//平阶段信息
				nr.put("FlatElect", new BigDecimal(flatElect));
				nr.put("FlatElectPrice", 0);
				nr.put("FlatServicePrice", 0);
				nr.put("FlatMoney", 0);
				nr.put("FlatElectMoney", 0);
				nr.put("FlatServiceMoney", 0);
				//谷阶段信息
				nr.put("ValleyElect", new BigDecimal(valleyElect));
				nr.put("ValleyElectPrice", 0);
				nr.put("ValleyServicePrice", 0);
				nr.put("ValleyMoney", 0);
				nr.put("ValleyElectMoney", 0);
				nr.put("ValleyServiceMoney", 0);
				nr.put("StartTime", beginTime);
				nr.put("EndTime", lastTime);
				nr.put("PaymentAmount", 0);
				nr.put("PayTime", null);
				nr.put("DiscountInfo", null);
				Map<String,Object> data = new HashMap<>();
		    	data.put("OrderInfo", nr);
		    	//获取token，推送
		    	String OperatorID = "01294771X";
				TblPartner tblPartner = partnerService.PartnerInfo(OperatorID);
				//订单信息推送URL
				String pushOrderUrl = tblPartner.getPushOrderUrl();
				LOGGER.info(".....南京南瑞订单信息变化推送，获取token-begin.........");
				Map<String, String> mmp = commonService.getCecToken(OperatorID);	
				String token = mmp.get("AccessToken");
				LOGGER.info(".....南京南瑞订单信息变化推送，获取token-end.........");
				String nariDate = new JSONObject(data).toString();
		    	LOGGER.debug("南京南瑞订单信息变化推送URL:{}{}{}{}{}" , pushOrderUrl, ",token:" , token , ",data:" , nariDate);
				CecPost.HttpPost(pushOrderUrl, "321895837",token, tblPartner.getSigSecret(), 
						nariDate,tblPartner.getAesSecret(), tblPartner.getAesIv());
				LOGGER.info("......南京南瑞订单信息变化推送-end..........");
			}
		} catch (Exception e) {
			return 1;
		}
		String time = redisService.strGet(WanmaConstants.NARI_ORDER_TIME);
		if(time ==null){
			redisService.strSet(WanmaConstants.NARI_ORDER_TIME, endTime);
		}else{
			redisService.strRemove(WanmaConstants.NARI_ORDER_TIME);
			redisService.strSet(WanmaConstants.NARI_ORDER_TIME, endTime);
		}
		return 0;
	}

}
