package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblInstalldetail;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface WebInstalldetailMapper {
	public final static String PREFIX = WebInstalldetailMapper.class.getName();

	public TblInstalldetail get(java.lang.Integer pkInstalldetail);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkInstalldetail);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblInstalldetail tblInstalldetail);

	public int update(TblInstalldetail tblInstalldetail);

	public int delete(java.lang.Integer pkInstalldetail);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}
