package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblBomListMapper;
import com.wanma.dubbox.model.TblBomList;
import com.wanma.dubbox.service.TblBomListService;
/**
 * bom清单数据查询接口
 * @author lhy
 *
 */
@Service
public class TblBomListServiceImpl implements TblBomListService {

	@Autowired
	TblBomListMapper TblBomListMapper;
	@Override
	public int insert(TblBomList record) {
		return TblBomListMapper.insert(record);
	}

	@Override
	public int update(TblBomList record) {
		return TblBomListMapper.update(record);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblBomList> getList(TblBomList model) {
		return TblBomListMapper.getList(model);
	}
	
}