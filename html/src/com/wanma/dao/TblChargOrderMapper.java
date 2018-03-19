package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblChargingOrder;


/**
 * @Description:充电订单DAO
 * @author lhy
 * @createTime 2015/12/08 12:43
 * @updater 
 * @updateTime    
 * @version v1.0
 */
public interface TblChargOrderMapper {
	public void update(TblChargingOrder model);
	public TblChargingOrder selectOne(TblChargingOrder model);
	public List<TblChargingOrder> getList(TblChargingOrder model);
	public TblChargingOrder getOderByOpenId(String openId);
	public List<Map<String, Object>> getListGroupByPileNumByPsId(
			TblChargingOrder odrModel);
	public List<Map<String, Object>> getListGroupByHeadNoByPileNum(
			TblChargingOrder odrModel);
	/**
	 * 根据第三方订单编号查询订单
	 * @param model
	 * @return
	 */
	public TblChargingOrder selectChargeOrder(TblChargingOrder model);
	/**
	 * 中电联推送(订单汇总)
	 * @param odrModel
	 * @return
	 */
	public Map<String, Object> fandChargeOrderSummary(Map<String, Object> params);
	/**
	 * 中电联推送(查询相关订单)
	 * @param odrModel
	 * @return
	 */
	public List<TblChargingOrder> findChargeOrder(Map<String, Object> params);
	/**
	 * 更改对账信息
	 * @param params
	 */
	public void ChargeOrderById(Map<String, Object> params);
	
	/**
	 * 中电联 - 查询充电订单信息 - 检验充电订单是否存在
	 * @param StartChargeSeq
	 * @return
	 */
	public int checkChargeOrderIsExist(String StartChargeSeq);
	
	/**
	 * 中电联 - 查询充电订单信息 
	 * @param StartChargeSeq
	 * @return
	 */
	public Map<String, Object> getChargeOrderInfo(String StartChargeSeq);
	
	/**
	 * 中电联 - 查询充电状态
	 * @param StartChargeSeq
	 * @return
	 */
	public Map<String, Object> getChargingOrderState(String StartChargeSeq);
	
	/**
	 * 检验第三方对接的公司标识
	 * @return
	 */
	public int checkCpyNum(int org);
	
	/**
	 *  南京南瑞-查询订单信息
	 * @param model
	 * @return
	 */
	public List<Map<String, Object>> getNariChargeOrder(
			Map<String, Object> model);

	/**
	 * 查询所有需要推送的公司
	 * @return
	 */
	public List<Map<String, Object>>selectCpynumber();	
	
	/**
	 * 检验第三方订单号是否重复
	 * @return
	 */
	public int checkChargeOrderNum(String StartChargeSeq);
	
}
