package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblDynamicMapper;
import com.wanma.model.TblDynamic;
import com.wanma.service.TblDynamicService;

@Service
public class TblDynamicServiceImpl implements TblDynamicService{
	@Autowired
	private TblDynamicMapper dynamicMapper;
	
	@Override
	public List<TblDynamic> list(TblDynamic dynamic) {
		return dynamicMapper.list(dynamic);
	}

	@Override
	public long count(TblDynamic dynamic) {
		return dynamicMapper.count(dynamic);
	}
	
	@Override
	public TblDynamic get(Integer pkRelease) {
		return dynamicMapper.get(pkRelease);
	}
	

	@Override
	public boolean disableDynamic(TblDynamic dynamic) {
		return dynamicMapper.disableDynamic(dynamic);
	}

	@Override
	public boolean deleteByIds(String ids) {
		return dynamicMapper.deleteByIds(ids);
	}

	@Override
	public String getUploadImgUrl(String busiType, String refId) {
		return dynamicMapper.getUploadImgUrl(busiType,refId);
	}

	

}
