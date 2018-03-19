package com.wanma.app.dao;

import java.util.List;
import java.util.Map;


public interface CouponMapper {
	/**
	 * 获取优惠券列表
	 * @return
	 */
	public List<Map<String, Object>> getCouponList(Map<String, Object> params);
	
	/**
	 * 兑换码换券
	 * @param params
	 * 			uId 用户id，code 兑换码，beginDate 优惠券兑换时间，endDate 优惠券过期时间
	 */
	public void code2Voucher(Map<String,Object> params);
	
	/**
	 * 根据兑换码获取优惠券对应活动的生效期
	 * @param code
	 * @return
	 */
	public Map<String, Object> getActiTimeByCode(String code);
	
	/**
	 * 从优惠券品种表获取优惠券的有效天数
	 * @param code
	 * @return
	 */
	public Map<String, Object> getValidDaysByCode(String code);
	
	/**
	 * 检查优惠券表中未兑换的优惠券里是否存在该兑换码
	 * @param code
	 * @return
	 */
	public int codeUnexchangeNum(String code);
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
	
	/**
	 * 根据用户id获取用户有效优惠券数
	 * @param uId
	 * @return
	 */
	public int getValidCPByUid(int uId);

	public List<Map<String, Object>> getMyCoupon(Map<String, Object> params);

	public long getCouponListCount(Map<String, Object> params);
	
	public Map<String, Object> getMyCouponOne(Map<String, Object> params);
}
