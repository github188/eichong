package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCommitLogMapper;
import com.wanma.dubbox.model.TblCommitLog;
import com.wanma.dubbox.service.TblCommitLogService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblCommitLogServiceImpl implements TblCommitLogService {

	@Autowired
	TblCommitLogMapper TblCommitLogMapper;

	@Override
	public int delete(TblCommitLog record) {
		return TblCommitLogMapper.delete(record);
	}

	@Override
	public int insert(TblCommitLog record) {
		// TODO Auto-generated method stub
		return TblCommitLogMapper.insert(record);
	}

	@Override
	public TblCommitLog selectOne(TblCommitLog model) {
		return TblCommitLogMapper.selectOne(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblCommitLog> getList(TblCommitLog model) {
		return TblCommitLogMapper.getList(model);
	}

	@Override
	public int getCount(TblCommitLog model) {
		return TblCommitLogMapper.getCount(model);
	}
	
	
}