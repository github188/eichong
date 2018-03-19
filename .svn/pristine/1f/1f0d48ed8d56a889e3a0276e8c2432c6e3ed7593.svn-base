package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblPureBusinessMapper;
import com.wanma.dubbox.model.TblPureBusiness;
import com.wanma.dubbox.service.TblPureBusinessService;
/**
 * 企业信息数据查询接口
 * @author lhy
 *
 */
@Service
public class TblPureBusinessServiceImpl implements TblPureBusinessService {

	@Autowired
	TblPureBusinessMapper TblPureBusinessMapper;

	@Override
	public TblPureBusiness selectOne(TblPureBusiness model) {
		return TblPureBusinessMapper.selectOne(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblPureBusiness> getList(TblPureBusiness model) {
		return TblPureBusinessMapper.getList(model);
	}
	
}