package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblChargingOrder;
import com.wanma.page.Page;

/**
 * @Description: 充电消费订单
 * @author songjf
 * @createTime：2015-4-10 下午06:52:38
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface TblChargingorderMapper {

	public TblChargingOrder get(java.lang.Integer pkChargingorder);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkChargingorder);

	public <T, K, V> List<T> find(Map<K, V> params);

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
	public <K, V> Map<K, V> selectChargeData(Map<K, V> params);

	/**
	 * @Title: updateStatus
	 * @Description: 更新订单状态
	 * @param params
	 * @return
	 */
	public int updateStatus(TblChargingOrder tblChargingorder);

	public int update(TblChargingOrder tblChargingorder);

	public int delete(java.lang.Integer pkChargingorder);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public int getUnpayOrder(int userId);

}
