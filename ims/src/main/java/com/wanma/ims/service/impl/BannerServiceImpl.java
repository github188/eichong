package com.wanma.ims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.BannerDO;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.BannerMapper;
import com.wanma.ims.mapper.InitialMapper;
import com.wanma.ims.service.BannerService;
import com.wanma.ims.service.MultipartFileService;

@Service("bannerService")
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerMapper bannerMapper;
	@Autowired
	private InitialMapper initialMapper;
	@Autowired
	private MultipartFileService multipartFileService;
	
	@Override
	public long selectBannerCount(BannerDO banner) {
		return bannerMapper.selectBannerCount(banner);
	}

	@Override
	public List<BannerDO> selectBannerList(BannerDO banner) {
		List<BannerDO> bannerList = bannerMapper.selectBannerList(banner);
		List<CityDO>  cityList  =  initialMapper.selectCityList(null,null);
		for(int j=0;j<bannerList.size();j++){
			if(bannerList.get(j).getBannerCityCode().isEmpty()){
				bannerList.get(j).setBannerRegion("全国");
			}else{
				for(int i=0;i<cityList.size();i++){
					if(cityList.get(i).getCityId().equals(bannerList.get(j).getBannerCityCode())){
						bannerList.get(j).setBannerRegion(cityList.get(i).getCityName().toString());
					}	
				}
			}
		}
		return bannerList;
	}

	@Override
	@Transactional
	public BaseResultDTO insertBanner(BannerDO banner, MultipartFile[] file, UserDO loginUser) throws Exception {
		ResultDTO<String> result = new ResultDTO<>();
		if(!(bannerMapper.insertBanner(banner)>0)){
			return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增banner失败");
		  }
		String referenceId =""+banner.getPkBannerId();
		String results = multipartFileService.saveMultiFile(file, WanmaConstants.BANNER_PIC, referenceId, false, loginUser);
		if (!WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
			  throw new Exception("图片上传失败");
		  }
		return result;
	}

	@Override
	@Transactional
	public BaseResultDTO updateBanner(BannerDO banner, MultipartFile[] file, UserDO loginUser) throws Exception {
		ResultDTO<String> result = new ResultDTO<>();
		String referenceId =""+banner.getPkBannerId();
		//先删除原先的
       if (file.length>0) {
    	   List<String> oldFileUrlList = multipartFileService.getAllMultiUrl(WanmaConstants.BANNER_PIC, referenceId);
    	   multipartFileService.deleteMulti(oldFileUrlList, WanmaConstants.BANNER_PIC, referenceId, loginUser);
           String results = multipartFileService.saveMultiFile(file, WanmaConstants.BANNER_PIC, referenceId, false, loginUser);
           if (WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
   			  if(!(bannerMapper.updateBanner(banner)>0)){
   				return new BaseResultDTO(false, ResultCodeConstants.FAILED, "编辑banner失败");
   			  }
   		  }else{
   			  throw new Exception("图片上传失败");
   		  }
       }else {
    	   if(!(bannerMapper.updateBanner(banner)>0)){
    		   return new BaseResultDTO(false, ResultCodeConstants.FAILED, "编辑banner失败");
			  }
	}
		return result;
	}

	@Override
	public BannerDO getBannerById(BannerDO banner) {
		return bannerMapper.getBannerById(banner);
	}

	@Override
	@Transactional
	public void offShelfBannerById(BannerDO banner) {
		bannerMapper.offShelfBannerById(banner);
	}

	@Override
	@Transactional
	public boolean deleteBannerById(BannerDO banner) {
		return bannerMapper.deleteBannerById(banner)>0;
	}

	@Override
	@Transactional
	public boolean changeBannerSort(BannerDO banner) {
		return bannerMapper.changeBannerSort(banner)>0;
	}

}
