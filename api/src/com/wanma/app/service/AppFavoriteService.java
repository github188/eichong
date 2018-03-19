package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.FavoritesList;
import com.wanma.model.TblFavorite;
import com.wanma.model.TblUsercollect;

/**
 * @Description: 收藏夹业务处理接口
 * @author songjf
 * @createTime：2015-3-15 下午07:50:57
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppFavoriteService {

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
	public List<FavoritesList> getFavoriteList(TblFavorite tblFavorite);
	
	public List<Map<String, Object>> getFavoriteListN(TblUsercollect c);


}
