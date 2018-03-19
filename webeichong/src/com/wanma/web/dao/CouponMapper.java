package com.wanma.web.dao;

import java.util.List;
import java.util.Map;


public interface CouponMapper {
	/**
	 * 获取需要生成的优惠券列表
	 * @return
	 */
	public List<Map<String, Object>> getGenerateCouponList(Map<String, Object> params);
	
	/**
	 * 生成优惠券
	 * @param params
	 */
	public void generateCode(List<Map<String, Object>> list);

	public Double getPersonChargeSum(Map<String, Object> params);
	
}
