package com.wanma.ims.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.AdvertisementDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.AdvertisementMapper;
import com.wanma.ims.service.AdvertisementService;
import com.wanma.ims.service.MultipartFileService;

@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService{
	@Autowired
	private AdvertisementMapper advertisementMapper;
	@Autowired
	private MultipartFileService multipartFileService;

	@Override
	public int getLimitCount(AdvertisementDO advertisementDO) {
		return advertisementMapper.getLimitCount(advertisementDO);
	}

	@Override
	@Transactional
	public BaseResultDTO addAdvertisement(AdvertisementDO advertisementDO, 
			MultipartFile[] file,MultipartFile[] picListFile, UserDO loginUser) throws Exception {
		String results1="";
		String results2="";
		if(!advertisementMapper.insertAdvertisement(advertisementDO)){
			return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增失败！");
	   }
		String referenceId =""+advertisementDO.getPkAdId();
		if (file.length>0) {
			results1 = multipartFileService.saveMultiFile(file, WanmaConstants.ADVERTISEMENT_PIC, referenceId, false, loginUser);
			if (!WanmaConstants.PROCESS_STATUS_OK.equals(results1)) {
				throw new Exception("图片上传失败");
			  }
		}
		if (picListFile.length>0) {
			results2 = multipartFileService.saveMultiFile(picListFile, WanmaConstants.ADVERTISEMENT_LIST_PIC, referenceId, false, loginUser);
			if (!WanmaConstants.PROCESS_STATUS_OK.equals(results2)) {
				throw new Exception("动态列表图片上传失败");
			  }
		}
		return new BaseResultDTO();
	}

	@Override
	public AdvertisementDO getAdvertisementById(AdvertisementDO advertisementDO) {
		return advertisementMapper.getAdvertisementById(advertisementDO);
	}

	@Override
	@Transactional
	public ResultDTO<String> updateAdvertisement(AdvertisementDO advertisementDO, MultipartFile[] file, MultipartFile[] picListFile, UserDO loginUser) {
		ResultDTO<String> result = new ResultDTO<>();
		String referenceId =""+advertisementDO.getPkAdId();
		//先删除原先的
       if (file.length>0) {
    	   List<String> oldFileUrlList = multipartFileService.getAllMultiUrl(WanmaConstants.ADVERTISEMENT_PIC, referenceId);
    	   multipartFileService.deleteMulti(oldFileUrlList, WanmaConstants.ADVERTISEMENT_PIC, referenceId, loginUser);
           String results = multipartFileService.saveMultiFile(file, WanmaConstants.ADVERTISEMENT_PIC, referenceId, false, loginUser);
           if (!WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
        	 result.setSuccess(false);
             result.setResultCode(ResultCodeConstants.FAILED);
             result.setErrorDetail("图片上传失败！");
             return result;
   		  }
       }
       if (picListFile.length>0) {
    	   List<String> oldFileUrlList = multipartFileService.getAllMultiUrl(WanmaConstants.ADVERTISEMENT_LIST_PIC, referenceId);
    	   multipartFileService.deleteMulti(oldFileUrlList, WanmaConstants.ADVERTISEMENT_LIST_PIC, referenceId, loginUser);
           String results = multipartFileService.saveMultiFile(file, WanmaConstants.ADVERTISEMENT_LIST_PIC, referenceId, false, loginUser);
           if (!WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
        	 result.setSuccess(false);
             result.setResultCode(ResultCodeConstants.FAILED);
             result.setErrorDetail("修改图片失败！");
             return result;
   		  }
       }
       if(!advertisementMapper.updateAdvertisement(advertisementDO)){
			  	result.setSuccess(false);
	            result.setResultCode(ResultCodeConstants.FAILED);
	            result.setErrorDetail("修改失败！");
	            return result;
		  }
		return result;
	}

	@Override
	public long selectAdvertisementCount(AdvertisementDO advertisementDO) {
		return advertisementMapper.selectAdvertisementCount(advertisementDO);
	}

	@Override
	public List<AdvertisementDO> selectAdvertisementList(
			AdvertisementDO advertisementDO) {
		return advertisementMapper.selectAdvertisementList(advertisementDO);
	}

	@Override
	@Transactional
	public boolean downAdvertisement(AdvertisementDO advertisementDO) {
		return advertisementMapper.updateAdvertisement(advertisementDO);
	}


	

	

}
