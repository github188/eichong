package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblPurchasehistoryMapper;
import com.wanma.dubbox.model.TblPurchasehistory;
import com.wanma.dubbox.service.TblPurchasehistoryService;
/**
 * 消费历史数据查询接口
 * @author lhy
 * @param <T>
 *
 */
@Service
public class TblPurchasehistoryServiceImpl implements TblPurchasehistoryService {

	@Autowired
	TblPurchasehistoryMapper tblPurchasehistoryMapper;

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblPurchasehistory model) {
		return tblPurchasehistoryMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblPurchasehistory> getList(TblPurchasehistory model) {
		return tblPurchasehistoryMapper.getList(model);
	}

	@Override
	public int update(TblPurchasehistory model) {
		return tblPurchasehistoryMapper.update(model);
	}
	
}