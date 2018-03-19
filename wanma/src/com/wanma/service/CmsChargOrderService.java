package com.wanma.service;

import java.util.List;
import java.util.Map;
import com.wanma.model.TblChargingOrder;

/**
 * 充电消费业务处理器
 * 
 * @author xiay
 *
 */
public interface CmsChargOrderService {
	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @return
	 */
	public TblChargingOrder findCharge(String pkChargingOrder);

	/**
	 * 编辑充电消费
	 * 
	 * @return 
	 */
	public void modifyCharge(TblChargingOrder tblChargingOrder);

	/**
	 * 取得充电消费一览
	 * 
	 * @return 
	 */
	public List<TblChargingOrder> getChargeList();

	/**
	 * 查询充电消费个数
	 * 
	 * @return 
	 */
	public long searchChargeCount(TblChargingOrder tblChargingOrder);

	/**
	 * 查询充电消费一览
	 * 
	 * @return 
	 */
	public List<TblChargingOrder> searchChargeList(TblChargingOrder tblChargingOrder);
	
	/**
	 * 删除充电消费
	 * 
	 * @param pkChargingOrder
	 * @return
	 */
	public int deleteCharge(String pkChargingOrder);
	
	
	/**
	 * 根据充电消费ID取得充电消费信息--个体商家充电收益
	 * 
	 * @return
	 */
	public TblChargingOrder findUnitCharge(String pkChargingOrder);

	/**
	 * 取得充电消费一览--个体商家充电收益
	 * 
	 * 
	 * @return
	 */
	public List<TblChargingOrder> getUnitChargeList();

	/**
	 * 查询充电消费个数--个体商家充电收益
	 * 
	 * 
	 * @return
	 */
	public long searchUnitChargeCount(TblChargingOrder tblChargingOrder);

	/**
	 * 查询充电消费一览--个体商家充电收益
	 * 
	 * 
	 * @return
	 */
	public List<TblChargingOrder> searchUnitChargeList(TblChargingOrder tblChargingOrder);
	
	
	/**
	 * 根据充电消费ID取得充电消费信息--个体商家充电收益
	 * 
	 * @return
	 */
	public TblChargingOrder findFirmCharge(String pkChargingOrder);

	/**
	 * 取得充电消费一览--个体商家充电收益
	 * 
	 * 
	 * @return
	 */
	public List<TblChargingOrder> getFirmChargeList();

	/**
	 * 查询充电消费个数--个体商家充电收益
	 * 
	 * 
	 * @return
	 */
	public long searchFirmChargeCount(TblChargingOrder tblChargingOrder);

	/**
	 * 查询充电消费一览--个体商家充电收益
	 * 
	 * 
	 * @return
	 */
	public List<TblChargingOrder> searchFirmChargeList(TblChargingOrder tblChargingOrder);
	
	/**
	 * 查询充电消费个数--合作商充电消费订单
	 * 
	 * 
	 * @return
	 */
	public long searchPartnerChargeCount(TblChargingOrder tblChargingOrder);
	/**
	 * 查询充电消费一览--合作商充电收益
	 * 
	 * 
	 * @return
	 */
	public List<TblChargingOrder> searchPartnerChargeList(
			TblChargingOrder tblChargingOrder);

	public TblChargingOrder getSettleInfo(Map<String, String> params);

	public String updateSettle(Map<String, String> params);
	

	
}
