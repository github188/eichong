package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblPublishEpMapper;
import com.wanma.dubbox.model.TblPublishEp;
import com.wanma.dubbox.service.TblPublishEpService;
/**
 * 电桩分享数据查询接口
 * @author lhy
 *
 */
@Service
public class TblPublishEpServiceImpl implements TblPublishEpService {

	@Autowired
	TblPublishEpMapper tblPublishEpMapper;

	@Override
	public int delete(TblPublishEp record) {
		return tblPublishEpMapper.delete(record);
	}

	@Override
	public int insert(TblPublishEp record) {
		// TODO Auto-generated method stub
		return tblPublishEpMapper.insert(record);
	}

	@Override
	public TblPublishEp selectOne(TblPublishEp model) {
		return tblPublishEpMapper.selectOne(model);
	}

	@Override
	public int update(TblPublishEp record) {
		return tblPublishEpMapper.update(record);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblPublishEp> getList(TblPublishEp model) {
		return tblPublishEpMapper.getList(model);
	}

	@Override
	public int getCount(TblPublishEp model) {
		return tblPublishEpMapper.getCount(model);
	}
	
	
}