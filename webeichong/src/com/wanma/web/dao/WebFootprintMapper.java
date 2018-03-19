package com.wanma.web.dao;

import java.util.List;

import com.wanma.model.TblFootprint;


/**
 * 数据访问接口
 * 
 */
public interface WebFootprintMapper {

	/**
	 * 获取购物订单详情
	 * @return String orderId
	 */
	List<TblFootprint> find(TblFootprint tblFootprint);
}
