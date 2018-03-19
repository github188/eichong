package com.wanma.service;

import java.util.List;

import com.wanma.model.TblBanner;

/**
 * banner业务处理器
 * @author mb
 * 
 */
public interface CmsBannerService {


	long getBannerListCount();

	List<TblBanner> getBannerList(TblBanner banner);

	List getCityName();

	void insertBanner(TblBanner banner);

	TblBanner getBannerById(int bannerId);

	void updateBanner(TblBanner banner);

	void deleteBannerById(int bannerId);

	void offShelfBannerById(TblBanner banner);

	void changeBannerSort(TblBanner banner);

}
