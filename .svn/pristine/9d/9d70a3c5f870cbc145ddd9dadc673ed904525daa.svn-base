package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblVersion;

/**
 * @Description: 版本管理表操作dao
 * @author songjf
 * @createTime：2015-3-26 下午02:09:49
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebVersionMapper {
	public final static String PREFIX = WebVersionMapper.class.getName();

	/**
	 * @Title: get
	 * @Description: 获取版本信息
	 * @param params
	 * @return
	 */
	public TblVersion get(java.lang.Integer versType);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkVersion);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblVersion tblVersion);

	public int update(TblVersion tblVersion);

	public int delete(java.lang.Integer pkVersion);
}
