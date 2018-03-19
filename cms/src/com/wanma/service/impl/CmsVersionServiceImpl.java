package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsVersionMapper;
import com.wanma.model.TblVersion;
import com.wanma.service.CmsVersionService;

@Service
public class CmsVersionServiceImpl implements CmsVersionService{
	@Autowired
	CmsVersionMapper cmsVersionMapper;
	
	@Override
	public List<TblVersion> getAll(TblVersion version) {
		return cmsVersionMapper.getAll(version);
	}

	@Override
	public int getCount(TblVersion version) {
		return cmsVersionMapper.getCount(version);
	}

	@Override
	public TblVersion getOne(TblVersion version) {
		return cmsVersionMapper.getOne(version);
	}

	@Override
	public int insert(TblVersion version) {
		version.setVersCreatedate(new Date());
		version.setVersUpdatedate(new Date());
		return cmsVersionMapper.insert(version);
	}

	@Override
	public int delete(Integer id) {
		return cmsVersionMapper.delete(id);
	}

	@Override
	public int deleteBatch(String ids) {
		return cmsVersionMapper.deleteBatch(ids);
	}

}
