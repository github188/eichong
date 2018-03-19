package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

import com.wanma.ims.common.domain.FavCouponVarietyDO;;

public interface FavCouponVarietyService {
	/**
	 * 获取现金券品种数量
	 * @author mb
	 * @param couponVariety
	 * @return
	 */
	long getCouponVarietyCount(FavCouponVarietyDO couponVariety);
	/**
	 * 获取现金券品种列表
	 * @author mb
	 * @param couponVariety
	 * @return
	 */
	List<FavCouponVarietyDO> getCouponVarietyList(
			FavCouponVarietyDO couponVariety);
	/**
	 * 判断现金券名称是否重复
	 * @author mb
	 * @param covaActivityName
	 * @return
	 */
	int judgeCovaActivityName(String covaActivityName);
	/**
	 * 新增现金券品种
	 * @author mb
	 * @param couponVariety
	 */
	boolean addCouponVariety(FavCouponVarietyDO couponVariety);
	/**
	 * 根据主键查询现金券品种
	 * @author mb
	 * @param pkCouponVariety
	 * @return FavCouponVarietyDO
	 */
	FavCouponVarietyDO getCouponVarietyById(Integer pkCouponVariety);
	/**
	 * 修改现金券品种
	 * @author mb
	 * @param couponVariety
	 */
	void changeCouponVariety(FavCouponVarietyDO couponVariety);
	/**
	 * 优惠券明细列表下拉栏品种
	 * @author mb
	 * @param couponVariety 
	 * @return
	 */
	List<FavCouponVarietyDO> getCouponVarietyForList(FavCouponVarietyDO couponVariety);
	/**
	 * 筛选框 城市通用 范围
	 * @author mb
	 */
	List<Map<String, String>> getCityScope();

	



}
