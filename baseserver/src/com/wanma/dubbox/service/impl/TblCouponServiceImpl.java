package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCouponMapper;
import com.wanma.dubbox.model.TblCoupon;
import com.wanma.dubbox.service.TblCouponService;

/**
 * 城市数据查询接口
 * 
 * @author gx
 *
 */
@Service
public class TblCouponServiceImpl implements TblCouponService {

	@Autowired
	TblCouponMapper tblCouponMapper;

	@Override
	public TblCoupon selectOne(TblCoupon model) {
		return tblCouponMapper.selectOne(model);
	}

	@Override
	public int delete(TblCoupon record) {
		return tblCouponMapper.delete(record);
	}

	/**
	 * 获取数据条数
	 * 
	 * @throws Exception
	 */
	@Override
	public int getCount(TblCoupon model) {
		return tblCouponMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblCoupon> getList(TblCoupon model) {
		return tblCouponMapper.getList(model);
	}

}