package com.wanma.web.dao;

import java.util.List;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblCarinfo;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface WebCarinfoMapper {
	public final static String PREFIX = WebCarinfoMapper.class.getName();

	public TblCarinfo get(java.lang.Integer pkCarinfo);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkCarinfo);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblCarinfo tblCarinfo);

	public int update(TblCarinfo tblCarinfo);

	public int delete(java.lang.Integer pkCarinfo);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}
