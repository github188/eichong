package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblBanner;

/**
 * @Description: 后台管理banner
 * @author mb
 * @version：V4.0
 */
public interface CmsBannerMapper {

	long getBannerListCount();

	List<TblBanner> getBannerList(TblBanner banner);

	List getCityName();

	void insertBanner(TblBanner banner);

	int updateImageInfo(Map<String, Object> map);

	TblBanner getBannerById(int bannerId);

	void updateBanner(TblBanner banner);

	void deleteBannerById(int bannerId);

	void offShelfBannerById(TblBanner banner);

	void changeBannerSort(TblBanner banner);


	
}
