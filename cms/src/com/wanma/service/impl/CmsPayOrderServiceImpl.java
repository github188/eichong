package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsPayOrderMapper;
import com.wanma.model.TblPayOrder;
import com.wanma.service.CmsPayOrderService;

@Service
public class CmsPayOrderServiceImpl implements CmsPayOrderService {

	@Autowired
	private CmsPayOrderMapper tblPayOrderDao;
	
	/**
	 * 根据充值订单ID取得充值订单信息
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param PayId
	 *            充值订单ID
	 * @return TblPayOrder 登录充值订单信息
	 * @throws 无
	 */
	public TblPayOrder findPay(String pkPayOrder) {

		// 充值订单信息
		TblPayOrder pay;

		// 取得充值订单信息
		pay = tblPayOrderDao.findPay(pkPayOrder);

		// 返回充值订单一览
		return pay;
	}
	
	/**
	 * 删除充值订单
	 * 
	 */
	@Override
	public int deletePay(String pkPayOrder) {
		return tblPayOrderDao.deletePay(pkPayOrder);
	}

	
	/**
	 * 取得充值订单一览
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @return List<TblPayOrder> 充值订单一览
	 * @throws 无
	 */
	public List<TblPayOrder> getPayList() {
		// 充值订单一览
		List<TblPayOrder> listPay;

		// 取得充值订单一览
		listPay = tblPayOrderDao.getPayList();

		// 返回充值订单一览
		return listPay;
	}

	/**
	 * 查询充值订单个数
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblPayOrder
	 *            充值订单信息
	 * @return List<TblPayOrder> 充值订单一览
	 * @throws 无
	 */
	public long searchPayCount(TblPayOrder tblPayOrder) {
		// 充值订单个数
		long dataCount;

		// 取得充值订单个数
		dataCount = tblPayOrderDao.searchPayCount(tblPayOrder);

		// 返回充值订单个数
		return dataCount;

	}

	/**
	 * 查询充值订单一览
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblPayOrder
	 * @return List<TblPayOrder> 充值订单一览
	 * @throws 无
	 */
	public List<TblPayOrder> searchPayList(TblPayOrder tblPayOrder) {
		// 充值订单一览
		List<TblPayOrder> listPay;

		// 取得充值订单一览
		listPay = tblPayOrderDao.searchPayList(tblPayOrder);

		// 返回充值订单一览
		return listPay;

	}
	
	
}
