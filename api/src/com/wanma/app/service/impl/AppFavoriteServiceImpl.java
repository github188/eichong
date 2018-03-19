package com.wanma.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.app.dao.AppFavoriteMapper;
import com.wanma.app.dao.UsercollectMapper;
import com.wanma.app.service.AppFavoriteService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.Favorites;
import com.wanma.model.FavoritesList;
import com.wanma.model.TblFavorite;
import com.wanma.model.TblUsercollect;

/**
 * @Description: 收藏夹业务处理类
 * @author songjf
 * @createTime：2015-3-15 下午07:50:57
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppFavoriteServiceImpl implements AppFavoriteService {

	/** 用户信息业务操作DAO */
	@Autowired
	private AppFavoriteMapper appFavoriteMapper;

	@Autowired
	private UsercollectMapper usercollectMapper;
	
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
		/*if(favoriteId.indexOf(",")>0){
			String [] collectIds=favoriteId.split(",");
			for (int i = 0; i < collectIds.length; i++) {
				if(collecType.equals("3")){//收藏类型 3-商城收藏  1-电桩 2-电站
					appFavoriteMapper.delete(JudgeNullUtils.nvlInteget(collectIds[i]));
				}else{
					TblUsercollect tblUsercollect=new TblUsercollect();
					tblUsercollect.setPkUsercollect(JudgeNullUtils.nvlInteget(collectIds[i]));
					usercollectMapper.delete(tblUsercollect);
				}
			}
		}else{
			if(collecType.equals("3")){//收藏类型  3-商城收藏  1-电桩 2-电站
				appFavoriteMapper.delete(JudgeNullUtils.nvlInteget(favoriteId));
			}else{*/
				TblUsercollect tblUsercollect=new TblUsercollect();
				tblUsercollect.setPkUsercollect(JudgeNullUtils.nvlInteget(favoriteId));
				usercollectMapper.delete(tblUsercollect);
			/*}
		}*/
	}

	@Override
	public List<FavoritesList> getFavoriteList(TblFavorite tblFavorite) {
		// TODO Auto-generated method stub
		List<FavoritesList> favoritesList=new ArrayList<FavoritesList>();
		List<TblFavorite> tblFavoriteList=appFavoriteMapper.findFavoriteByUserId(tblFavorite);
		
		//01:获取商城收藏列表
		FavoritesList favoriteList=new FavoritesList();
		List<Favorites> favorites=new ArrayList<Favorites>();
		for (int i = 0; i < tblFavoriteList.size(); i++) {
			Favorites favorite=new Favorites();
			Map<String,Object> tblFavoriteMap=(Map<String,Object>)tblFavoriteList.get(i);
			favorite.setFavoriteId(JudgeNullUtils.nvlStr(tblFavoriteMap.get("pkFavorite")));
			favorite.setProductId(JudgeNullUtils.nvlStr(tblFavoriteMap.get("pk_Product")));
			favorite.setFavoriteImage(JudgeNullUtils.nvlStr(tblFavoriteMap.get("prod_ProductImage")));
			favorite.setFavoriteName(JudgeNullUtils.nvlStr(tblFavoriteMap.get("prod_ProductName")));
			favorite.setFavoriteProductPrice(JudgeNullUtils.nvlStr(tblFavoriteMap.get("prod_ProductPrice")));
			favorite.setFavoriteMarketPrice(JudgeNullUtils.nvlStr(tblFavoriteMap.get("prod_MarketPrice")));
			favorite.setFavoriteStockQuantity(JudgeNullUtils.nvlStr(tblFavoriteMap.get("prod_StockQuantity")));
			favorite.setElPi_PowerUser(0);
			favorite.setFavoriteProductTag("");
			favorite.setFavoriteType("1");
			favorites.add(favorite);
		}
		
		//02:获取电桩收藏列表
		TblUsercollect tblUsercollect=new TblUsercollect();
		tblUsercollect.setUscoUserid(tblFavorite.getFavoUserid());
		List<?> usercollects=usercollectMapper.findUserCollectByUserId(tblUsercollect);
		
		for (int i = 0; i < usercollects.size(); i++) {
			Favorites favorite=new Favorites();
			Map<String,Object> usercollectsMap=(Map<String,Object>)usercollects.get(i);
			favorite.setFavoriteId(JudgeNullUtils.nvlStr(usercollectsMap.get("pkUsercollect")));
			favorite.setProductId(JudgeNullUtils.nvlStr(usercollectsMap.get("uscoObjectid")));
			favorite.setFavoriteImage(getEleImage(JudgeNullUtils.nvlStr(usercollectsMap.get("uscoType")),JudgeNullUtils.nvlStr(usercollectsMap.get("uscoObjectid"))));
			favorite.setFavoriteName(JudgeNullUtils.nvlStr(usercollectsMap.get("elPi_ElectricPileName")));
			favorite.setFavoriteType(getEleFavoriteType(JudgeNullUtils.nvlStr(usercollectsMap.get("uscoType"))));
			favorite.setElPi_PowerUser(JudgeNullUtils.nvlInteget(usercollectsMap.get("elPi_PowerUser")));
			favorite.setFavoriteProductPrice("");
			favorite.setFavoriteMarketPrice("");
			favorite.setFavoriteStockQuantity("");
			favorite.setFavoriteProductTag("");
			favorites.add(favorite);
		}
		
		//电站收藏
		
		favoriteList.setFavorites(favorites);//收藏列表
		int dataSum=tblFavoriteList.size()+usercollects.size();
		favoriteList.setFavoriteTotal(""+ dataSum);//收藏总数
		favoritesList.add(favoriteList);//组装数据
		
		return favoritesList;
	}

	public List<Map<String, Object>> getFavoriteListN(TblUsercollect c) {
		List<Map<String, Object>> usercollects=usercollectMapper.findUserCollectByUserIdN(c);
		
		return usercollects;//favoritesList;
	}
	
	/**
	 * 获取电桩/电站图片
	 * @param EleId
	 * @return
	 */
	private String getEleFavoriteType(String eleId){
		
		if(eleId.equals("1")){//电桩
			 return "2";
		}else if(eleId.equals("2")){//电站
			 return "3";
		}
		return "";
	}
	/**
	 * 获取电桩/电站图片
	 * @param EleId
	 * @return
	 */
	private String getEleImage(String eleId,String objectid){
		
		if(eleId.equals("1")){//电桩
			//获取电桩列表图片
			List<String> listImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
					objectid + "");
			if(listImage.size()>0 && !listImage.isEmpty()){
				return JudgeNullUtils.nvlStr(listImage.get(0));
			}
		}else if(eleId.equals("2")){//电站
			//获取电桩详情图片
			List<String> detailImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE,
					objectid + "");
			if(detailImage.size()>0 && !detailImage.isEmpty()){
				return JudgeNullUtils.nvlStr(detailImage.get(0));
			}
		}
		MessageManager messageManager = MessageManager.getMessageManager();
		String relFilePath = messageManager.getSystemProperties(CommonConsts.DEPLOY_URL);
		return ""+relFilePath+"/res/bluemobi/img/default.png'";//无图片返回默认
	}
}
