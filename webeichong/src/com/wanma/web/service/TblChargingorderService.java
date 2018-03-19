package com.wanma.web.service;

import java.util.Map;

import com.wanma.model.TblChargingOrder;

/**
 * @Description: 充电消费订单业务处理接口
 * @author songjf
 * @createTime：2015-4-10 下午06:56:10
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface TblChargingorderService {

	/**
	 * @Title: insertChargeOrder
	 * @Description: 新增充电消费订单
	 * @param params
	 * @return
	 */
	public int insertChargeOrder(TblChargingOrder tblChargingorder);

	/**
	 * @Title: selectChargeData
	 * @Description: 获取充电电度，金额，服务费，总费用，开始时间，结束时间
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectChargeData(Map<String, Object> params);

}
