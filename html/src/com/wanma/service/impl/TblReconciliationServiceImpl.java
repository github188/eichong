package com.wanma.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sgcc.utils.DateUtil;
import com.wanma.dao.TblReconciliationMapper;
import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblPartner;
import com.wanma.model.TblReconciliation;
import com.wanma.service.CommonService;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TblPowerstationService;
import com.wanma.service.TblReconciliationService;
import com.wanma.service.TcbPartnerService;
import com.wanma.support.common.MessageManager;
import com.wanma.support.common.RedisService;
import com.wanma.support.utils.CecPost;
import com.wanma.support.utils.RandomUtil;

@Service
public class TblReconciliationServiceImpl implements TblReconciliationService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TblReconciliationServiceImpl.class);
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static final SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	@Autowired
	private TblReconciliationMapper tblreconciliation;
	@Autowired
	private TblChargingOrderService ordService;
	@Autowired
	private TblPowerstationService psService;
	@Autowired
	private TcbPartnerService partnerService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private CommonService comService;

	@Override
	public void PushChargeOrder(String cpyNumber, Date beginTime, Date endTime) {
		LOGGER.info("~~~~~~~~~~~~~~推送对账订单begin~~~~~~~~~~~~~~~~~");
		try {
			TblReconciliation tblReconciliation = new TblReconciliation();
			beginTime = DateUtil.getDailyStartTime(beginTime);
			endTime = DateUtil.getDailyEndTime(endTime);
			Map<String, Object> params = new HashMap<>();
			params.put("cpyNumber", cpyNumber);
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			List<TblChargingOrder> orderList = ordService.findChargeOrder(params);
			Map<String, Object> orderSummary = ordService.fandChargeOrderSummary(params);
			Map<String, String> map = new HashMap<>();
			map.put("cpyNumber", cpyNumber);
			Map<String, String> map1 = psService.getcpyNumberById(map);
			TblPartner partnerInfo = partnerService.PartnerInfo(map1.get("partnerKey"));
			LOGGER.info("~~~~~~~~~~~~~~~~~开始获取token~~~~~~~~~~~~~~~~~~~~~");
			Map<String, String> maps = comService.getCecToken(partnerInfo.getOperatorID());
			String token = maps.get("AccessToken");
			Date datas = new Date();
			long time = format.parse(format.format(datas).toString()).getTime();
			String checkOrderSeq = "321895837" + RandomUtil.createData(5)+ time;
			tblReconciliation.setReconciliationNo(checkOrderSeq);
			tblReconciliation.setCheckBeginTime(fmt.format(beginTime));
			tblReconciliation.setCheckEndTime(fmt.format(endTime));
			if (Integer.valueOf(orderSummary.get("OrderCount").toString()) == 0) {
				tblReconciliation.setTotalOrderPower(new BigDecimal("0.0000"));
				tblReconciliation.setTotalOrderMoney(new BigDecimal("0.00"));
			} else {
				tblReconciliation.setTotalOrderPower(new BigDecimal(String.valueOf(orderSummary.get("TotalOrderPower"))));
				tblReconciliation.setTotalOrderMoney(new BigDecimal(String.valueOf(orderSummary.get("TotalOrderMoney"))));
			}
			tblReconciliation.setGmtCreate(fmt.format(datas));
			tblReconciliation.setCpyId(Integer.valueOf(String.valueOf(partnerInfo.getCpyId())));
			tblReconciliation.setCheckStatus(0);
			LOGGER.info("~~~~~~~~~~~~~~添加对账流水信息~~~~~~~~~~~~~~~~~");
			ordService.insert(tblReconciliation);
			int Id = tblReconciliation.getReconciliationId();
			Map<String, Object> data = new HashMap<>();
			data.put("CheckOrderSeq", checkOrderSeq);
			data.put("StartTime", fmt.format(beginTime));
			data.put("EndTime", fmt.format(endTime));
			data.put("OrderCount",
					Integer.valueOf(orderSummary.get("OrderCount").toString()));
			if (Integer.valueOf(orderSummary.get("OrderCount").toString()) == 0) {
				data.put("TotalOrderPower", String.format("%.4f", 0.000));
				data.put("TotalOrderMoney", String.format("%.2f", 0.00));
			} else {
				data.put("TotalOrderPower", orderSummary.get("TotalOrderPower"));
				data.put("TotalOrderMoney", orderSummary.get("TotalOrderMoney"));
			}
			List<Map<String, Object>> list = new ArrayList<>();
			for (TblChargingOrder TblChargingOrder : orderList) {
				Map<String, Object> mapss = new HashMap<>();
				mapss.put("StartChargeSeq",TblChargingOrder.getStartChargeSeq());
				mapss.put("TotalPower", TblChargingOrder.getTotalPower());
				mapss.put("TotalMoney", TblChargingOrder.getTatalMoney());
				list.add(mapss);
			}
			data.put("ChargeOrders", list);
			LOGGER.info("~~~~~~~~~~~~~~开始进行推送对账流水~~~~~~~~~~~~~~~~~");
			if (partnerInfo.getOperatorID().equals("MA002TMQX")) {
				String AesSecret = map1.get("compatible").split("\\|")[0];
				String AesIv = map1.get("compatible").split("\\|")[1];
				String SigSecret = map1.get("compatible").split("\\|")[2];
				JSONObject jsonData = CecPost.HttpPost(
						partnerInfo.getPushOrderCheckUrl(), "321895837", token,
						SigSecret, new JSONObject(data).toString(), AesSecret,
						AesIv);
				if (jsonData != null) {
					String TotalDisputeOrder = jsonData.getString("TotalDisputeOrder");
					String TotalDisputePower = jsonData.getString("TotalDisputePower");
					String TotalDisputeMoney = jsonData.getString("TotalDisputeMoney");
					Object Orders = jsonData.get("DisputeOrders");
					JSONArray DisputeOrders = JSONArray.fromObject(jsonData.get("DisputeOrders"));
					Map<String, Object> mmp = new HashMap<>();
					mmp.put("Id", Id);
					mmp.put("TotalDisputeOrder", TotalDisputeOrder);
					mmp.put("TotalDisputePower", TotalDisputePower);
					mmp.put("TotalDisputeMoney", TotalDisputeMoney);
					if (DisputeOrders.size() > 0 && Orders != null) {
						for (TblChargingOrder TblChargingOrder : orderList) {
							Map<String, Object> test = new HashMap<>();
							test.put("Id", Id);
							test.put("StartChargeSeq",TblChargingOrder.getStartChargeSeq());
							test.put("Status", 1);
							ordService.modifyOrderById(test);
						}
						JSONArray array = JSONArray.fromObject(jsonData.get("DisputeOrders"));
						for (int i = 0; i < array.size(); i++) {
							String StartChargeSeq = array.getJSONObject(i).getString("StartChargeSeq");
							Map<String, Object> test = new HashMap<>();
							test.put("Id", Id);
							test.put("StartChargeSeq", StartChargeSeq);
							test.put("Status", 2);
							ordService.modifyOrderById(test);
						}
					} else {
						for (TblChargingOrder TblChargingOrder : orderList) {
							Map<String, Object> mapp = new HashMap<>();
							mapp.put("StartChargeSeq",TblChargingOrder.getStartChargeSeq());
							mapp.put("Id", Id);
							mapp.put("Status", 1);
							ordService.modifyOrderById(mapp);
						}
					}
					if (TotalDisputePower.equals(data.get("TotalOrderPower"))
							|| TotalDisputeMoney.equals(data.get("TotalOrderMoney"))) {
						mmp.put("Status", 1);
					} else {
						mmp.put("Status", 0);
					}
					ordService.modifyStatusById(mmp);
					LOGGER.info("~~~~~~~~~~~~~~推送对账订单end~~~~~~~~~~~~~~~~~");
				} else {
					LOGGER.info("~~~~~~~~~~~~~~推送对账订单失败~~~~~~~~~~~~~~~~~");
				}
			} else {
				JSONObject jsonData = CecPost.HttpPost(
						partnerInfo.getPushOrderCheckUrl(), "321895837", token,
						partnerInfo.getSigSecret(),
						new JSONObject(data).toString(),
						partnerInfo.getAesSecret(), partnerInfo.getAesIv());
				if (jsonData != null) {
					String TotalDisputeOrder = jsonData.getString("TotalDisputeOrder");
					String TotalDisputePower = jsonData.getString("TotalDisputePower");
					String TotalDisputeMoney = jsonData	.getString("TotalDisputeMoney");
					Object Orders = jsonData.get("DisputeOrders");
					JSONArray DisputeOrders = JSONArray.fromObject(jsonData.get("DisputeOrders"));
					Map<String, Object> mmp = new HashMap<>();
					mmp.put("Id", Id);
					mmp.put("TotalDisputeOrder", TotalDisputeOrder);
					mmp.put("TotalDisputePower", TotalDisputePower);
					mmp.put("TotalDisputeMoney", TotalDisputeMoney);
					if (DisputeOrders.size() > 0 && Orders != null) {
						for (TblChargingOrder TblChargingOrder : orderList) {
							Map<String, Object> test = new HashMap<>();
							test.put("Id", Id);
							test.put("StartChargeSeq",TblChargingOrder.getStartChargeSeq());
							test.put("Status", 1);
							ordService.modifyOrderById(test);
						}
						JSONArray array = JSONArray.fromObject(jsonData.get("DisputeOrders"));
						for (int i = 0; i < array.size(); i++) {
							String StartChargeSeq = array.getJSONObject(i).getString("StartChargeSeq");
							Map<String, Object> test = new HashMap<>();
							test.put("Id", Id);
							test.put("StartChargeSeq", StartChargeSeq);
							test.put("Status", 2);
							ordService.modifyOrderById(test);
						}
					} else {
						for (TblChargingOrder TblChargingOrder : orderList) {
							Map<String, Object> mapp = new HashMap<>();
							mapp.put("StartChargeSeq",TblChargingOrder.getStartChargeSeq());
							mapp.put("Id", Id);
							mapp.put("Status", 1);
							ordService.modifyOrderById(mapp);
						}
					}
					if (TotalDisputePower.equals(data.get("TotalOrderPower"))
							|| TotalDisputeMoney.equals(data.get("TotalOrderMoney"))) {
						mmp.put("Status", 1);
					} else {
						mmp.put("Status", 0);
					}
					ordService.modifyStatusById(mmp);
					LOGGER.info("~~~~~~~~~~~~~~推送对账订单end~~~~~~~~~~~~~~~~~");
				} else {
					LOGGER.info("~~~~~~~~~~~~~~推送对账订单失败~~~~~~~~~~~~~~~~~");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("~~~~~~~~~~~~~~推送对账订单失败~~~~~~~~~~~~~~~~~");
		}
	}
}
