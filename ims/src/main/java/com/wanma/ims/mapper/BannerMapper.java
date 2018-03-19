package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.BannerDO;

public interface BannerMapper {

	long selectBannerCount(BannerDO banner);

	List<BannerDO> selectBannerList(BannerDO banner);

	int insertBanner(BannerDO banner);

	int updateBanner(BannerDO banner);

	BannerDO getBannerById(BannerDO banner);

	void offShelfBannerById(BannerDO banner);

	int deleteBannerById(BannerDO banner);

	int changeBannerSort(BannerDO banner);
	
	
}
