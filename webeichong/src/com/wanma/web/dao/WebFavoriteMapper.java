package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblFavorite;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface WebFavoriteMapper {
	public final static String PREFIX = WebFavoriteMapper.class.getName();

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
	
	/**
	 * @Title: insert
	 * @Description: 我的收藏
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findFavoriteByUserId(Map<K, V> params);
	/**我的收藏分页使用*/
	public <K, V> long countFavorite(Map<K, V> params);
}
