package com.wanma.ims.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.AdvertisementDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;



/**
 * 闪屏 插屏管理
 */
public interface AdvertisementService {
	/**
	 * 闪屏列表
	 * @param splashDO
	 * @return
	 */
	long selectAdvertisementCount(AdvertisementDO advertisementDO);
	List<AdvertisementDO> selectAdvertisementList(
			AdvertisementDO advertisementDO);

	int getLimitCount(AdvertisementDO advertisementDO);
	/**
	 * 新增插屏闪屏
	 * @param advertisementDO 
	 * @param loginUser 
	 * @param file 
	 * @param picListFile 
	 * @return
	 */
	public BaseResultDTO addAdvertisement(AdvertisementDO advertisementDO, MultipartFile[] file, MultipartFile[] picListFile, UserDO loginUser) throws Exception;
	/**
	 * 根据主键获取插屏 闪屏
	 * @param advertisementDO
	 * @return
	 */
	AdvertisementDO getAdvertisementById(AdvertisementDO advertisementDO);
	/**
	 * 修改插屏闪屏
	 * @param advertisementDO 
	 * @param file 
	 * @param picListFile 
	 * @param loginUser 
	 * @return
	 */
	ResultDTO<String> updateAdvertisement(AdvertisementDO advertisementDO, MultipartFile[] file, MultipartFile[] picListFile, UserDO loginUser);
	/**
	 * 下架
	 * @param advertisementDO
	 * @return
	 */
	boolean downAdvertisement(AdvertisementDO advertisementDO);


	
	
}
