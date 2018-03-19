package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblFeedbackMapper;
import com.wanma.dubbox.model.TblFeedback;
import com.wanma.dubbox.service.TblFeedbackService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblFeedbackServiceImpl implements TblFeedbackService {

	@Autowired
	TblFeedbackMapper tblFeedbackMapper;

	@Override
	public int delete(TblFeedback record) {
		return tblFeedbackMapper.delete(record);
	}

	@Override
	public int insert(TblFeedback record) {
		// TODO Auto-generated method stub
		return tblFeedbackMapper.insert(record);
	}

	@Override
	public TblFeedback selectOne(TblFeedback model) {
		return tblFeedbackMapper.selectOne(model);
	}

	@Override
	public int update(TblFeedback record) {
		return tblFeedbackMapper.update(record);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblFeedback> getList(TblFeedback model) {
		return tblFeedbackMapper.getList(model);
	}

	@Override
	public int getCount(TblFeedback model) {
		return tblFeedbackMapper.getCount(model);
	}
	
	
}