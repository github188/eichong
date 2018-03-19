package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblAdvertisementMapper;
import com.wanma.dubbox.model.TblAdvertisement;
import com.wanma.dubbox.service.TblAdvertisementService;
/**
 * 广告屏数据查询接口
 * @author lhy
 *
 */
@Service
public class TblAdvertisementServiceImpl implements TblAdvertisementService {

	@Autowired
	TblAdvertisementMapper tblAdvertisementMapper;
	@Override
	public int insert(TblAdvertisement record) {
		return tblAdvertisementMapper.insert(record);
	}

	@Override
	public TblAdvertisement selectOne(TblAdvertisement model) {
		return tblAdvertisementMapper.selectOne(model);
	}

	@Override
	public int update(TblAdvertisement record) {
		return tblAdvertisementMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblAdvertisement model) {
		return tblAdvertisementMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblAdvertisement> getList(TblAdvertisement model) {
		return tblAdvertisementMapper.getList(model);
	}

	@Override
	public int getLimitCount(TblAdvertisement model) {
		return tblAdvertisementMapper.getLimitCount(model);
	}
	
}