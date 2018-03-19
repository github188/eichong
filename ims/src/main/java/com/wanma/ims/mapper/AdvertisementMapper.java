package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.AdvertisementDO;
import com.wanma.ims.common.domain.AppButtonDO;



public interface AdvertisementMapper {

	int getLimitCount(AdvertisementDO advertisementDO);

	boolean insertAdvertisement(AdvertisementDO advertisementDO);

	AdvertisementDO getAdvertisementById(AdvertisementDO advertisementDO);

	boolean updateAdvertisement(AdvertisementDO advertisementDO);

	long selectAdvertisementCount(AdvertisementDO advertisementDO);

	List<AdvertisementDO> selectAdvertisementList(
			AdvertisementDO advertisementDO);

	

	
	
	
}
