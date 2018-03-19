package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblCouponVariety;


public interface TblCouponVarietyMapper {
	
    TblCouponVariety selectOne(TblCouponVariety model);

    int delete(TblCouponVariety model);

	List<TblCouponVariety> getList(TblCouponVariety model);

	int getCount(TblCouponVariety model);
}
