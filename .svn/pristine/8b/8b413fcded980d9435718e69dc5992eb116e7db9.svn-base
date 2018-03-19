package com.wanma.app.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblChargingorderMapper;
import com.wanma.app.service.TblChargingorderService;
import com.wanma.app.util.DateUtil;
import com.wanma.model.TblChargingOrder;

/**
 * @Description: 充电消费订单业务处理接口
 * @author songjf
 * @createTime：2015-4-10 下午06:56:10
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service("chargingorderService")
public class TblChargingorderServiceImpl implements TblChargingorderService {

	// 充电消费操作dao
	@Autowired
	private TblChargingorderMapper chargingorderMapper;

	/**
	 * @Title: insertChargeOrder
	 * @Description: 新增充电消费订单
	 * @param params
	 * @return
	 */
	@Override
	public int insertChargeOrder(TblChargingOrder tblChargingorder) {
		return chargingorderMapper.insertChargeOrder(tblChargingorder);
	}

	/**
	 * @Title: selectChargeData
	 * @Description: 获取充电电度，金额，服务费，总费用，开始时间，结束时间
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectChargeData(Map<String, Object> params) {
		Map<String, Object> map = chargingorderMapper.selectChargeData(params);
		if(null == map) {
			map = new HashMap<String, Object>();
			return map;
		}
		//String time = DateUtil.getDistanceTime(map.get("startDate").toString(),map.get("endDate").toString());
		/*String cfreFaultcause = (String) map.get("cfreFaultcause");
		if(cfreFaultcause == null){
			cfreFaultcause = "";
		}
		
		long faultStatus = (Long) map.get("faultStatus");
		if(faultStatus>0){
			faultStatus =  1;
		}
		
		//map.put("chargeTime", time);
		map.put("cfreFaultcause", cfreFaultcause);
		map.put("faultStatus", faultStatus);*/
		return map;
	}

}
