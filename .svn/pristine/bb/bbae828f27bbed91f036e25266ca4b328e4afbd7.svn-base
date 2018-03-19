package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblFavorite;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface AppFavoriteMapper {
	public final static String PREFIX = AppFavoriteMapper.class.getName();

	public TblFavorite get(java.lang.Integer pkFavorite);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkFavorite);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 新增收藏
	 * @param params
	 * @return
	 */
	public int insert(TblFavorite tblFavorite);

	public int update(TblFavorite tblFavorite);

	public int delete(java.lang.Integer pkFavorite);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	public  List<TblFavorite> findFavoriteByUserId(TblFavorite tblFavorite);
}
