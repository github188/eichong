/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppChargedeductionrecordsMapper;
import com.wanma.app.dao.AppChargingrecordMapper;
import com.wanma.app.dao.AppUserinfoMapper;
import com.wanma.app.dao.ChargeMapper;
import com.wanma.app.dao.TblChargingorderMapper;
import com.wanma.app.dao.TblPurchasehistoryMapper;
import com.wanma.app.service.ChargeService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.ChargeOrderInfo;
import com.wanma.model.TblChargedeductionrecords;
import com.wanma.model.TblChargeinfo;
import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblChargingrecord;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUserinfo;

/***
 * 
 * 充电展示
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-13 下午04:51:34
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class ChargeServiceImpl implements ChargeService {

	@Autowired
	ChargeMapper chargeMapper;
	/* 用户操作dao */
	@Autowired
	AppUserinfoMapper userinfoMapper;
	/* 充电订单操作dao */
	@Autowired
	TblChargingorderMapper orderMapper;
	/* 充电记录操作dao */
	@Autowired
	AppChargingrecordMapper recordMapper;
	/* 充电扣款记录操作dao */
	@Autowired
	AppChargedeductionrecordsMapper deductionrecordsMapper;
	@Autowired
	TblPurchasehistoryMapper tblPhMapper;
	@Autowired
	AppChargingrecordMapper chargingrecordMapper;
	@Override
	public TblChargeinfo getChangeInfo(TblChargeinfo tblChargeinfo) {
		return chargeMapper.get(tblChargeinfo);
	}

	/**
	 * 新的实时充电接口，从直流和交流实时表获取充电实时信息
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChangeInfoN(int userId){
		List<Map<String, Object>> list = chargeMapper.getChangeInfoFromAC(userId);
		if(null == list || list.size() == 0){
			list = chargeMapper.getChangeInfoFromDC(userId);
		}
		
		return list == null ? new ArrayList<Map<String,Object>>() : list;
	}
	
	@Override
	public List<ChargeOrderInfo> finishCharge(TblChargeinfo tblChargeinfo) {
		List<ChargeOrderInfo> ChargeOrderList = new ArrayList<ChargeOrderInfo>();
		ChargeOrderInfo chargeOrderInfo = new ChargeOrderInfo();
		chargeOrderInfo.setElectricityCount(JudgeNullUtils.nvlStr("200"));
		chargeOrderInfo.setElectricityMoney(JudgeNullUtils.nvlStr("300.00"));
		chargeOrderInfo.setElectricityTime(JudgeNullUtils.nvlStr("3"));
		chargeOrderInfo.setElectricityServiceMoney(JudgeNullUtils
				.nvlStr("11.00"));
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
		TblChargeinfo tblChargeinfo = new TblChargeinfo();
		tblChargeinfo.setChinUsingmachinecode(machineCode);
		List<TblChargeinfo> chargeinfoList = chargeMapper.find(tblChargeinfo);
		for (TblChargeinfo tblChargeinfo2 : chargeinfoList) {
			Integer linkeState = tblChargeinfo2.getChinLinkedstatus();
			if (linkeState == 0) {// 电桩关闭状态
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
		chargeMapper.update(tblChargeinfo);
	}

	/**
	 * @Title: selectMoney
	 * @Description: 获取用户余额，冻结金额，电桩最小充电金额
	 * @param params
	 * @return
	 */
	@Override
	public Boolean selectIsEnoughMoney(Map<String, Object> params) {
		Map<String, Object> map = userinfoMapper.selectMoney(params);
		// 用户账户余额
		BigDecimal accountBalance = (BigDecimal) map.get("accountBalance");
		// 冻结金额
		BigDecimal frozenAmt = (BigDecimal) map.get("frozenAmt");
		// 电桩最小充电金额
		BigDecimal minFreezingMoney = (BigDecimal) map.get("minFreezingMoney");
		if (minFreezingMoney == null) {
			minFreezingMoney = new BigDecimal(0);
		}

		if (frozenAmt != null) {
			accountBalance = accountBalance.subtract(frozenAmt);
		}
		// 比较用户账户余额和电桩最小冻结金额
		Boolean result = accountBalance.compareTo(minFreezingMoney) >= 0 ? true
				: false;

		return result;
	}

	/**
	 * 根据用户id获取该用户未支付的订单数
	 * @param userId
	 * @return
	 */
	public int getUnpayOrder(int userId){
		return orderMapper.getUnpayOrder(userId);
	}
	
	/**
	 * @Title: updateAccountbalance
	 * @Description: 更新用户账户余额,充电订单状态为支付成功
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> updateAccountbalance(Map<String, Object> params) {
		Boolean flag = true;
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取用户信息
		/*Map<String, Object> userinfo = userinfoMapper.findOne(Integer
				.parseInt((String) params.get("pkUserinfo")));*/
		TblUserinfo userinfo = userinfoMapper.get(Integer.parseInt(params.get("pkUserinfo").toString()));
		// 账户余额
		BigDecimal usinAccountbalance = (BigDecimal) userinfo
				.getUsinAccountbalance();
		// 充电总费用
		BigDecimal frozenamt = new BigDecimal((String) params.get("totalMoney"));
		// 账户余额和充电总费用比较
		int num = usinAccountbalance.compareTo(frozenamt);
		if (num == -1) {
			flag = false;
			map.put("flag", flag);
			return map;
		}

		BigDecimal balance = usinAccountbalance.subtract(frozenamt);

		params.put("usinAccountbalance", balance);
		// 更新用户账户余额
		userinfoMapper.updateAccountbalance(params);
		// 更新订单状态为支付成功
		TblChargingOrder order = new TblChargingOrder();
		order.setPkChargingorder((String) params.get("pkChargingorder"));
		order.setChorChargingstatus(WanmaConstants.ORDER_STATUS_SUCCESS + "");
		orderMapper.updateStatus(order);
		// 新增充电扣款记录
		TblChargingrecord record = recordMapper
				.selectByChreCode((String) params.get("pkChargingorder"));
		TblChargedeductionrecords ductionrecords = new TblChargedeductionrecords();
		ductionrecords.setCdreElectricpileid(record.getChreElectricpileid());
		ductionrecords
				.setCdreUsingmachinecode(record.getChreUsingmachinecode());
		ductionrecords.setCdreCharginginterfaceidentifier(record
				.getChreChargingnumber());
		ductionrecords.setCdreDebitamount(frozenamt);
		ductionrecords.setCdreBalance(balance);
		ductionrecords.setCdreSuccess("1");
		ductionrecords.setCdreCode(record.getChreCode());
		ductionrecords.setCdreTransactionnumber(record
				.getChreTransactionnumber());
		deductionrecordsMapper.insert(ductionrecords);

		TblPurchasehistory ph = new TblPurchasehistory();
		ph.setPuhiType(1);
		ph.setPuhiPurchasehistorytime(new Date());
		ph.setPuhiMonetary(new BigDecimal(params.get("totalMoney").toString()));
		ph.setPuhiConsumerremark(record.getChreTransactionnumber());
		ph.setPuhiCreatedate(new Date());
		ph.setPuhiPurchasecontent("充电消费");
		ph.setPuhiUserid(Integer.parseInt(params.get("pkUserinfo").toString()));
		tblPhMapper.insert(ph);
		
		map.put("flag", flag);
		map.put("balance", balance);

		return map;
	}
	
	/**
	 * 根据用户id 查询充电订单列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChargeOrderList(Map<String, Object> params){
		return userinfoMapper.getChargeOrderListByUid(params);
	}
	
	/**
	 * 获取直流桩在充电前的自检状态
	 * @param params
	 * 			epCode 电桩编号 ，ephCode 枪口编号
	 * @return
	 */
	public int getDCSelfCheckStatus(Map<String, String> params){
		return chargeMapper.getDCSelfCheckStatus(params);
	}

	@Override
	public Map<String, Object> chargeOrderDetail(int coId) {
		return chargingrecordMapper.findDetail(coId);
	}
}
