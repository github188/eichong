package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsChargOrderMapper;
import com.wanma.model.TblChargingOrder;
import com.wanma.service.CmsChargOrderService;

@Service
public class CmsChargOrderServiceImpl implements CmsChargOrderService {

	/** 充电消费操作dao */
	@Autowired
	private CmsChargOrderMapper tblChargOrderDao;

	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public TblChargingOrder findCharge(String pkChargingOrder) {

		// 充电消费信息
		TblChargingOrder Charge;

		// 取得充电消费信息
		Charge = tblChargOrderDao.findCharge(pkChargingOrder);

		// 返回充电消费一览
		return Charge;
	}

	/**
	 * 编辑充电消费
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	@SystemControllerLog(description = "编辑充电消费订单")
	public void modifyCharge(TblChargingOrder tblChargingOrder) {

		// 调用DAO执行充电消费更新处理
		tblChargOrderDao.modifyCharge(tblChargingOrder);
	}

	/**
	 * 删除充电消费
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	@SystemControllerLog(description = "删除充电消费订单")
	@Override
	public int deleteCharge(String pkChargingOrder) {
		return tblChargOrderDao.deleteCharge(pkChargingOrder);
	}

	/**
	 * 取得充电消费一览
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public List<TblChargingOrder> getChargeList() {
		// 充电消费一览
		List<TblChargingOrder> Charge;

		// 取得充电消费信息
		Charge = tblChargOrderDao.getChargeList();

		// 返回充电消费一览
		return Charge;
	}

	/**
	 * 查询充电消费个数
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public long searchChargeCount(TblChargingOrder tblChargingOrder) {
		// 充电消费信息
		long dataCount;

		// 取得充电消费信息
		dataCount = tblChargOrderDao.searchChargeCount(tblChargingOrder);

		// 返回充电消费一览
		return dataCount;

	}

	/**
	 * 查询充电消费一览
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public List<TblChargingOrder> searchChargeList(
			TblChargingOrder tblChargingOrder) {
		// 充电消费信息
		return tblChargOrderDao.searchChargeList(tblChargingOrder);
	}

	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public TblChargingOrder findUnitCharge(String pkChargingOrder) {

		// 充电消费信息
		TblChargingOrder UnitCharge;

		// 取得充电消费信息
		UnitCharge = tblChargOrderDao.findUnitCharge(pkChargingOrder);

		// 返回充电消费一览
		return UnitCharge;
	}

	/**
	 * 取得充电消费一览
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public List<TblChargingOrder> getUnitChargeList() {
		// 充电消费一览
		List<TblChargingOrder> UnitCharge;

		// 取得充电消费信息
		UnitCharge = tblChargOrderDao.getUnitChargeList();

		// 返回充电消费一览
		return UnitCharge;
	}

	/**
	 * 查询充电消费个数
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public long searchUnitChargeCount(TblChargingOrder tblChargingOrder) {
		// 充电消费信息
		long dataCount;

		// 取得充电消费信息
		dataCount = tblChargOrderDao.searchUnitChargeCount(tblChargingOrder);

		// 返回充电消费一览
		return dataCount;

	}

	/**
	 * 查询充电消费一览
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public List<TblChargingOrder> searchUnitChargeList(
			TblChargingOrder tblChargingOrder) {
		// 充电消费信息
		List<TblChargingOrder> UnitCharge;

		// 取得充电消费信息
		UnitCharge = tblChargOrderDao.searchUnitChargeList(tblChargingOrder);
		// 返回充电消费一览
		return UnitCharge;

	}

	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public TblChargingOrder findFirmCharge(String pkChargingOrder) {

		// 充电消费信息
		TblChargingOrder FirmCharge;

		// 取得充电消费信息
		FirmCharge = tblChargOrderDao.findFirmCharge(pkChargingOrder);

		// 返回充电消费一览
		return FirmCharge;
	}

	/**
	 * 取得充电消费一览
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public List<TblChargingOrder> getFirmChargeList() {
		// 充电消费一览
		List<TblChargingOrder> FirmCharge;

		// 取得充电消费信息
		FirmCharge = tblChargOrderDao.getFirmChargeList();

		// 返回充电消费一览
		return FirmCharge;
	}

	/**
	 * 查询充电消费个数
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public long searchFirmChargeCount(TblChargingOrder tblChargingOrder) {
		return tblChargOrderDao.searchFirmChargeCount(tblChargingOrder);
	}

	/**
	 * 查询充电消费一览
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */
	public List<TblChargingOrder> searchFirmChargeList(
			TblChargingOrder tblChargingOrder) {
		return tblChargOrderDao.searchFirmChargeList(tblChargingOrder);

	}

	/**
	 * 取得充电消费一览(合作商)
	 * 
	 * @author xiay
	 * @return
	 * @throws
	 */

	@Override
	public long searchPartnerChargeCount(TblChargingOrder tblChargingOrder) {
		return tblChargOrderDao.searchPartnerChargeCount(tblChargingOrder);
	}

	@Override
	public List<TblChargingOrder> searchPartnerChargeList(
			TblChargingOrder tblChargingOrder) {
		return tblChargOrderDao.searchPartnerChargeList(tblChargingOrder);
	}

	@Override
	public TblChargingOrder getSettleInfo(Map<String, String> params) {
		return tblChargOrderDao.getSettleInfo(params);
	}

	/**
	 * 人工结算
	 */
	@Override
	public String updateSettle(Map<String, String> params) {
		String flag = "1";// 初始化修改状态
		try {

			/**
			 * 先付费订单，订单状态由“未支付”修改为“支付成功” 后付费订单，订单状态由“未支付”修改为“完成未结算”
			 */
			if ("1".equals(params.get("chRePayMode"))) {
				params.put("chOrChargingStatus", "2");
				// 先付费模式，订单表状态改为已完成
				tblChargOrderDao.updateChargingOrder(params);
				// 订单record表修改状态
				tblChargOrderDao.updateChargingRecord(params);
				// 消费记录表新增记录
				tblChargOrderDao.insertHistoryLine(params);
			
				// 账号表预冻结金额解除
				tblChargOrderDao.updateAccount(params);

			} else if ("2".equals(params.get("chRePayMode"))) {
				params.put("chOrChargingStatus", "3");
				// 后付费模式，订单表状态改为完成未结算
				tblChargOrderDao.updateChargingOrder(params);
				// 订单record表修改状态
				tblChargOrderDao.updateChargingRecord(params);
			}

		} catch (Exception e) {
			flag = "0";
			throw e;
		}

		return flag;
	}

}
