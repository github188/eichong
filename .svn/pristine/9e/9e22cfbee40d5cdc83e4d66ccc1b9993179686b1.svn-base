package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.Favorites;
import com.wanma.model.TblFavorite;

/**
 * @Description: 收藏夹业务处理接口
 * @author songjf
 * @createTime：2015-3-15 下午07:50:57
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebFavoriteService {

	/**
	 * @Title: insertFavorite
	 * @Description: 新增收藏
	 * @param tblFavorite
	 * @return
	 */
	public int insertFavorite(TblFavorite tblFavorite);
	/**
	 * @Title: insertFavorite
	 * @Description: 删除收藏
	 * @param tblFavorite
	 * @return
	 */
	public void removeFavoritesList(String favoriteId);
	/**
	 * @Title: insertFavorite
	 * @Description: 获取收藏列表
	 * @param tblFavorite
	 * @return
	 */
	public List<Map<String, Object>> getFavoriteList(Map<String, Object> params);
	public long countFavorite(Map<String, Object> params);

}
