package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblParaconfig;

/**
 * @Description: 其他配置参数设置表操作dao
 * @author songjf
 * @createTime：2015-3-25 下午06:15:23
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppParaconfigMapper {
	public final static String PREFIX = AppParaconfigMapper.class.getName();

	public TblParaconfig get(java.lang.Integer pkParaconfig);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkParaconfig);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblParaconfig tblParaconfig);

	public int update(TblParaconfig tblParaconfig);

	public int delete(java.lang.Integer pkParaconfig);

}
