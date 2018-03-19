package com.wanma.service;

import java.util.List;

import com.wanma.model.TblBespoke;
import com.wanma.model.TblCouponVariety;
import com.wanma.model.TblDynamic;

public interface CmsCouponVarietyService {
	/**
	 * 获取现金劵品种列表
	 */
	public List<TblCouponVariety> getCouponVarietyList(TblCouponVariety TblCouponVariety);

	public long getCouponVarietyCount(TblCouponVariety tblCouponVariety);

	public void addCouponVariety(TblCouponVariety tblCouponVariety);

	public TblCouponVariety getCouponVarietyById(int pkCouponVariety);

	public void changeCouponVariety(TblCouponVariety tblCouponVariety);

	public int checkCouponVariety(int pkCouponVariety);

	public void removeCouponVariety(TblCouponVariety tblCouponVariety);

	public void changeCovaStutas(TblCouponVariety tblCouponVariety);

	public int getCovaStutas(Integer pkCouponVariety);

	public void removeCouponByCouponVarietyId(Integer pkCouponVariety);

	public int judgeCovaActivityName(String covaActivityName);

}
