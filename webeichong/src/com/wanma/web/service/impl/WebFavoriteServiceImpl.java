package com.wanma.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.JudgeNullUtils;
import com.wanma.model.Favorites;
import com.wanma.model.FavoritesList;
import com.wanma.model.TblFavorite;
import com.wanma.web.dao.WebFavoriteMapper;
import com.wanma.web.service.WebFavoriteService;

/**
 * @Description: 收藏夹业务处理类
 * @author songjf
 * @createTime：2015-3-15 下午07:50:57
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebFavoriteServiceImpl implements WebFavoriteService {

	/** 用户信息业务操作DAO */
	@Autowired
	private WebFavoriteMapper appFavoriteMapper;

	/**
	 * @Title: insertFavorite
	 * @Description: 新增收藏
	 * @param tblFavorite
	 * @return
	 */
	@Override
	public int insertFavorite(TblFavorite tblFavorite) {
		// 收藏时间
		tblFavorite.setFavoCreatetime(new Date());
		tblFavorite.setFavoCreatedate(new Date());
		tblFavorite.setFavoUpdatedate(new Date());
		return appFavoriteMapper.insert(tblFavorite);
	}

	@Override
	public void removeFavoritesList(String favoriteId) {
		// TODO Auto-generated method stub
		if(favoriteId.indexOf(",")>0){
			String [] collectIds=favoriteId.split(",");
			for (int i = 0; i < collectIds.length; i++) {
				appFavoriteMapper.delete(JudgeNullUtils.nvlInteget(collectIds[i]));
			}
		}else{
			appFavoriteMapper.delete(JudgeNullUtils.nvlInteget(favoriteId));
		}
	}

	@Override
	public List<Map<String, Object>> getFavoriteList(Map<String, Object> params) {
		return appFavoriteMapper.findFavoriteByUserId(params);
	}
	@Override
	public long countFavorite(Map<String, Object> params) {
		return appFavoriteMapper.countFavorite(params);
	}

}
