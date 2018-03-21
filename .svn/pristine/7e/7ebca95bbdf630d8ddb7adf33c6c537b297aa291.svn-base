package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.ActivityDO;
import com.wanma.ims.common.domain.FavCouponDO;

public interface FavCouponMapper {

	int getUserAvailableCoupon(FavCouponDO coupon);

	String getUserDiscountAmount(FavCouponDO coupon);

	void removeCouponByCouponVarietyId(Integer pkCouponVariety);

	long getCouponCount(FavCouponDO coupon);

	List<FavCouponDO> getCouponList(FavCouponDO coupon);

	int getCouponStatus(String id);

	void deleteCoupon(String id);

	List<FavCouponDO> getExportCouponList(FavCouponDO searchModel);

	long getUserCouponCount(FavCouponDO coupon);

	List<FavCouponDO> getUserCouponList(FavCouponDO coupon);

	void changeEndDate(ActivityDO activity);
	
	public int modifyCoupon(FavCouponDO favCouponDO);

	public FavCouponDO getDataForAccountSplitDetailsBatch(FavCouponDO favCouponDO);

	public Long getDataForCouponBatch(FavCouponDO coupon);

	public void deleteCouponForBatch(FavCouponDO favCouponDO);

	public int batchAddCoupon(List<FavCouponDO> couponDOList);

	public int addCoupon(FavCouponDO favCouponDO);
}
