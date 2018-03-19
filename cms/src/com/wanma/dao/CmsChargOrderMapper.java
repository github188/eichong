package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblChargingOrder;

/**
 * 充电消费订单
 * 
 * @author xiay
 *
 */
public interface CmsChargOrderMapper {
	
	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @return
	 */
	public TblChargingOrder findCharge(String pkChargingOrder);

	/**
	 * 添加充电消费
	 * 
	 * @return
	 */
	public int saveCharge(TblChargingOrder tblChargingOrder);

	/**
	 * 编辑充电消费
	 * 
	 * 
	 * @return
	 */
	public void modifyCharge(TblChargingOrder tblChargingOrder);

	/**
	 * 取得充电消费一览
	 * 
	 * 
	 * @return
	 */
	public List<TblChargingOrder> getChargeList();

	/**
	 * 查询充电消费个数
	 * 
	 * 
	 * @return
	 */
	public long searchChargeCount(TblChargingOrder tblChargingOrder);

	/**
	 * 查询充电消费一览
	 * 
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
	

}
