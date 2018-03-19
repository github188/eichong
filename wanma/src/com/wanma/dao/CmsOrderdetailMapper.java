package com.wanma.dao;


import java.util.List;

import com.wanma.model.TblOrderdetail;

/**
 * 商城商品订单详情
 * 
 * @author xiay
 *
 */
public interface CmsOrderdetailMapper {    
    
	/**
	 * 获取已购买商品列表
	 * 
	 * @author xiay
	 * @return 无
	 */
	public  List<TblOrderdetail> findProductList(TblOrderdetail orderdetail);
	
	/**
	 * 获取已购买商品总数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long findProductCount(TblOrderdetail orderdetail);
	
	/**
	 * 根据订单获取订单商品列表
	 * 
	 * @author xiay
	 * @return 无
	 */
	public  List<TblOrderdetail> findByOrderId(TblOrderdetail orderdetail);
	
	/**
	 * 根据订单获取已购买商品总数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int findCountByOrderId(TblOrderdetail orderdetail);


}


