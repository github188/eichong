package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblVersion;


public interface CmsVersionMapper {

	public List<TblVersion> getAll(TblVersion version);
	
	public int getCount(TblVersion version);

	public TblVersion getOne(TblVersion version);

	public int insert(TblVersion version);

	public int delete(java.lang.Integer id);

	public int deleteBatch(String ids);
	
	
}
