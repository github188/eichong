package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblPayOrder;

/**
 * 充值订单
 * @author xiay
 * 数据访问接口
 *
 */
public interface CmsPayOrderMapper {

	/**
	 * 根据充值订单ID取得充值订单信息
	 * 
	 * @author xiay
	 * @return
	 */
	public TblPayOrder findPay(String pkPayOrder);

	/**
	 * 取得充值订单一览
	 * 
	 * @author xiay
	 * @return
	 */
	public List<TblPayOrder> getPayList();

	/**
	 * 查询充值订单个数
	 * 
	 * @author xiay
	 * @return
	 */
	public long searchPayCount(TblPayOrder tblPayOrder);

	/**
	 * 查询充值订单一览
	 * 
	 * @author xiay
	 * @return
	 */
	public List<TblPayOrder> searchPayList(TblPayOrder tblPayOrder);
	
	/**
	 * 删除充值订单一览
	 * 
	 * @author xiay
	 * @return
	 */
	public int deletePay(String pkPayOrder);
	
}
