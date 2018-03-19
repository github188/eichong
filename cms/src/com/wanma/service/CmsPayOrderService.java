package com.wanma.service;

import java.util.List;
import com.wanma.model.TblPayOrder;

/**
 * 充值订单处理器
 * 
 * @author xiay
 *
 */
public interface CmsPayOrderService {

	/**
	 * 根据充值订单ID取得充值订单信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            充值订单ID
	 * @return 无
	 * @throws 无
	 */
	public TblPayOrder findPay(String pkPayOrder);

	/**
	 * 取得充值订单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<UserModel> 充值订单一览
	 * @throws 无
	 */
	public List<TblPayOrder> getPayList();

	/**
	 * 查询充值订单个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 *            充值订单信息
	 * @return List<UserModel> 充值订单一览
	 * @throws 无
	 */
	public long searchPayCount(TblPayOrder tblPayOrder);

	/**
	 * 查询充值订单一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 *            充值订单信息
	 * @return List<UserModel> 充值订单一览
	 * @throws 无
	 */
	public List<TblPayOrder> searchPayList(TblPayOrder tblPayOrder);
	
	public int deletePay(String pkPayOrder);
	
}
