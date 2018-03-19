package com.wanma.service;

import java.util.List;

import com.wanma.model.TblVersion;

/**
 * 版本处理接口
 * 
 * @version V1.0
 * @author wubc
 * @date 2015年5月28日
 */
public interface CmsVersionService {

	/**
	 * 获取所有版本
	 * 
	 * @return
	 */
	public List<TblVersion> getAll(TblVersion version);

	public int getCount(TblVersion version);

	public TblVersion getOne(TblVersion version);

	public int insert(TblVersion version);

	public int delete(java.lang.Integer id);

	public int deleteBatch(String ids);

}
