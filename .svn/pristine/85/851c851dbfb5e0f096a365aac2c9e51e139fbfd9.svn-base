package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblElectricpilecommentMapper;
import com.wanma.dubbox.model.TblElectricpilecomment;
import com.wanma.dubbox.service.TblElectricpilecommentService;
/**
 * 桩评论数据查询接口
 * @author lhy
 *
 */
@Service
public class TblElectricpilecommentServiceImpl implements TblElectricpilecommentService {

	@Autowired
	TblElectricpilecommentMapper tblElectricpilecommentMapper;

	@Override
	public int delete(TblElectricpilecomment model) {
		return tblElectricpilecommentMapper.delete(model);
	}

	@Override
	public int insert(TblElectricpilecomment model) {
		return tblElectricpilecommentMapper.insert(model);
	}

	@Override
	public int update(TblElectricpilecomment model) {
		return tblElectricpilecommentMapper.update(model);
	}

	@Override
	public List<TblElectricpilecomment> getList(TblElectricpilecomment model) {
		return tblElectricpilecommentMapper.getList(model);
	}

	@Override
	public int getCount(TblElectricpilecomment model) {
		return tblElectricpilecommentMapper.getCount(model);
	}

	
}