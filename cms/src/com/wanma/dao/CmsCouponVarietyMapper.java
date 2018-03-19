package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblCouponVariety;

/**
 * 现金劵品种
 * @author Administrator
 *
 */
public interface CmsCouponVarietyMapper {

	public List<TblCouponVariety> getCouponVarietyList(TblCouponVariety tblCouponVariety);

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
