package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblConfigparameterMapper;
import com.wanma.dubbox.model.TblConfigparameter;
import com.wanma.dubbox.service.TblConfigparameterService;
/**
 * 参数配置数据查询接口
 * @author lhy
 *
 */
@Service
public class TblConfigparameterServiceImpl implements TblConfigparameterService {

	@Autowired
	TblConfigparameterMapper tblConfigparameterMapper;

	@Override
	public int delete(TblConfigparameter model) {
		return tblConfigparameterMapper.delete(model);
	}

	@Override
	public int insert(TblConfigparameter model) {
		// TODO Auto-generated method stub
		return tblConfigparameterMapper.insert(model);
	}

	@Override
	public TblConfigparameter selectOne(TblConfigparameter model) {
		return tblConfigparameterMapper.selectOne(model);
	}

	@Override
	public int getCount(TblConfigparameter model) {
		return tblConfigparameterMapper.getCount(model);
	}
	

	@Override
	public int update(TblConfigparameter model) {
		return tblConfigparameterMapper.update(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblConfigparameter> getList(TblConfigparameter model) {
		return tblConfigparameterMapper.getList(model);
	}
	
}