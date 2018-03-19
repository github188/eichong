package com.wanma.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.simple.SimpleChargeOrder;
import com.wanma.service.SimpleOrderInfoService;
import com.wanma.service.SimpleOrderService;
import com.wanma.support.common.AccessSuccessResult;
@Service
public class SimpleOrderServiceImpl implements SimpleOrderService{
	@Autowired
	private SimpleOrderInfoService scService;
	
	
	/**
	 * 订单查询
	 */
	@Override
	public String getSimpleInfo(String orderId) {
		SimpleChargeOrder tc=new SimpleChargeOrder();
		tc.setChorCode(orderId);
		String chorCode=tc.getChorCode();
		tc=scService.getSimpleOrderList(chorCode);		
		// 获取日期及格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String timestamp = sdf.format(now);
		String isSuccess = null;
		String errorMSG = null;
		String pkChargingorder=tc.getPkChargingorder();
		if(pkChargingorder!=null){
			isSuccess = "0";
			errorMSG = "";
		}else {
			isSuccess = "1";
			errorMSG = "订单查询失败!";
		}	
		// 获取充电开始、结束时间，格式
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		Date beginChargetime = tc.getBeginChargetime();
		Date endChargetime = tc.getEndChargetime();
		String startTime = s.format(beginChargetime);
		String endTime = s.format(endChargetime);

		// 充电量
		BigDecimal power = tc.getChorQuantityelectricity();
		// soc
		int soc = tc.getEndSoc();
		// 充电金额
		BigDecimal amount = tc.getChorMoeny();
		double chargeBalance = tc.getChargeBalance();
		double debtAmount = 0;
		if (chargeBalance > 0) {
			debtAmount = chargeBalance;
		}
		int chargeState = tc.getChorChargingstatus();
		String state = null;
		if (chargeState == 1) {
			state = "0";
		} else if (chargeState == 2) {
			state = "1";
		} else {
			state = "";
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("timestamp", timestamp);
		data.put("isSuccess", isSuccess);
		data.put("errorMSG", errorMSG);
		data.put("startTime", startTime);
		data.put("endTime",endTime );
		data.put("power",power );
		data.put("soc",soc );
		data.put("amount", amount);
		data.put("debtAmount",debtAmount );
		data.put("state",state );
		data.put("endType", "4");
		
		return new AccessSuccessResult(data).toString();
	}


}
