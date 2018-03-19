package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblWebReleaseMapper;
import com.wanma.dubbox.model.TblWebRelease;
import com.wanma.dubbox.service.TblWebReleaseService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblWebReleaseServiceImpl implements TblWebReleaseService {

	@Autowired
	TblWebReleaseMapper TblWebReleaseMapper;

	@Override
	public int delete(TblWebRelease record) {
		return TblWebReleaseMapper.delete(record);
	}

	@Override
	public int insert(TblWebRelease record) {
		// TODO Auto-generated method stub
		return TblWebReleaseMapper.insert(record);
	}

	@Override
	public TblWebRelease selectOne(TblWebRelease model) {
		return TblWebReleaseMapper.selectOne(model);
	}

	@Override
	public int update(TblWebRelease record) {
		return TblWebReleaseMapper.update(record);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblWebRelease> getList(TblWebRelease model) {
		return TblWebReleaseMapper.getList(model);
	}

	@Override
	public int getCount(TblWebRelease model) {
		return TblWebReleaseMapper.getCount(model);
	}
	
	
}