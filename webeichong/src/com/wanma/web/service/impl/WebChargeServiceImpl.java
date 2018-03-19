/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ChargeOrderInfo;
import com.wanma.model.TblChargeinfo;
import com.wanma.web.dao.WebChargeMapper;
import com.wanma.web.service.WebChargeService;

/***
 *
 *   充电展示
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class WebChargeServiceImpl implements WebChargeService {

	@Autowired
	WebChargeMapper chargeMapper;

	@Override
	public List<TblChargeinfo> getChangeInfo(TblChargeinfo tblChargeinfo) {
		// TODO Auto-generated method stub
		return chargeMapper.find(tblChargeinfo);
	}
	@Override
	public List<ChargeOrderInfo> finishCharge(TblChargeinfo tblChargeinfo) {
		// TODO Auto-generated method stub
		List<ChargeOrderInfo> ChargeOrderList=new ArrayList<ChargeOrderInfo>();
		ChargeOrderInfo chargeOrderInfo=new ChargeOrderInfo();
		chargeOrderInfo.setElectricityCount(JudgeNullUtils.nvlStr("200"));
		chargeOrderInfo.setElectricityMoney(JudgeNullUtils.nvlStr("300.00"));
		chargeOrderInfo.setElectricityTime(JudgeNullUtils.nvlStr("3"));
		chargeOrderInfo.setElectricityServiceMoney(JudgeNullUtils.nvlStr("11.00"));
		chargeOrderInfo.setElectricityState(JudgeNullUtils.nvlStr("已充满"));
		chargeOrderInfo.setElectricityQuantity(JudgeNullUtils.nvlStr("100%"));
		chargeOrderInfo.setElectricityTotalMoney(JudgeNullUtils.nvlStr("41"));
		
		ChargeOrderList.add(chargeOrderInfo);
		return ChargeOrderList;
	}
	/**
	 * 判断是否结束充电正常
	 */
	@Override
	public boolean getChangeState(String machineCode) {
		// TODO Auto-generated method stub
		TblChargeinfo tblChargeinfo =new TblChargeinfo();
		tblChargeinfo.setChinUsingmachinecode(JudgeNullUtils.nvlStr(machineCode));
		List<TblChargeinfo> chargeinfoList=chargeMapper.find(tblChargeinfo);
		for (TblChargeinfo tblChargeinfo2 : chargeinfoList) {
			Integer linkeState=tblChargeinfo2.getChinLinkedstatus();
			if(linkeState==0){//电桩关闭状态
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 更改电桩状态
	 */
	@Override
	public void changeElectricState(TblChargeinfo tblChargeinfo) {
		// TODO Auto-generated method stub
		chargeMapper.update(tblChargeinfo);
	}
}
