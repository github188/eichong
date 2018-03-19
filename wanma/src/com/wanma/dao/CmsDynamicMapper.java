package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblDynamic;

public interface CmsDynamicMapper {

	
	public List<TblDynamic> list(TblDynamic dynamic);
	public long count(TblDynamic dynamic);
	public TblDynamic get(Integer pkRelease);
	public int add(TblDynamic dynamic);
	public void update(TblDynamic dynamic);
	public boolean disableDynamic(TblDynamic dynamic);
	public boolean deleteByIds(String ids);
	public String getUploadImgUrl(String busiType, String refId);
	
}
