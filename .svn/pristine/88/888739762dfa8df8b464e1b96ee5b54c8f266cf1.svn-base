package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblUsermessageMapper;
import com.wanma.dubbox.model.TblUsermessage;
import com.wanma.dubbox.service.TblUsermessageService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblUsermessageServiceImpl implements TblUsermessageService {

	@Autowired
	TblUsermessageMapper TblUsermessageMapper;

	@Override
	public int delete(TblUsermessage record) {
		return TblUsermessageMapper.delete(record);
	}

	@Override
	public int insert(TblUsermessage record) {
		// TODO Auto-generated method stub
		return TblUsermessageMapper.insert(record);
	}

	@Override
	public TblUsermessage selectOne(TblUsermessage model) {
		return TblUsermessageMapper.selectOne(model);
	}

	@Override
	public int update(TblUsermessage record) {
		return TblUsermessageMapper.update(record);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblUsermessage> getList(TblUsermessage model) {
		return TblUsermessageMapper.getList(model);
	}

	@Override
	public int getCount(TblUsermessage model) {
		return TblUsermessageMapper.getCount(model);
	}
	
	
}