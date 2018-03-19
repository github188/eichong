package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblProductcommentMapper;
import com.wanma.dubbox.model.TblProductcomment;
import com.wanma.dubbox.service.TblProductcommentService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblProductcommentServiceImpl implements TblProductcommentService {

	@Autowired
	TblProductcommentMapper tblProductcommentMapper;

	@Override
	public int delete(TblProductcomment model) {
		return tblProductcommentMapper.delete(model);
	}

	@Override
	public int insert(TblProductcomment model) {
		return tblProductcommentMapper.insert(model);
	}

	@Override
	public int update(TblProductcomment model) {
		return tblProductcommentMapper.update(model);
	}

	@Override
	public List<TblProductcomment> getList(TblProductcomment model) {
		return tblProductcommentMapper.getList(model);
	}

	@Override
	public int getCount(TblProductcomment model) {
		return tblProductcommentMapper.getCount(model);
	}

	
}