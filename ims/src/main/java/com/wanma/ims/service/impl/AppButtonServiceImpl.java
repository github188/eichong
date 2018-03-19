package com.wanma.ims.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.AppButtonDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.AppButtonMapper;
import com.wanma.ims.service.AppButtonService;
import com.wanma.ims.service.MultipartFileService;

@Service("appButtonService")
public class AppButtonServiceImpl implements AppButtonService{
	@Autowired
	private AppButtonMapper appButtonMapper;
	@Autowired
	private MultipartFileService multipartFileService;
	
	@Override
	public long selectAppButtonCount(AppButtonDO appButtonDO) {
		return appButtonMapper.selectAppButtonCount(appButtonDO);
	}

	@Override
	public List<AppButtonDO> selectAppButtonList(AppButtonDO appButtonDO) {
		return appButtonMapper.selectAppButtonList(appButtonDO);
	}

	@Override
	@Transactional
	public BaseResultDTO addAppButton(AppButtonDO appButtonDO, MultipartFile[] file,UserDO loginUser) throws Exception {
		ResultDTO<String> result = new ResultDTO<>();
		 if(!(appButtonMapper.insertAppButton(appButtonDO)>0)){
			 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增appButton失败");
		  }
		String referenceId =""+appButtonDO.getPkButtonId();
		String results = multipartFileService.saveMultiFile(file, WanmaConstants.APP_BUTTON_PIC, referenceId, false, loginUser);
		if (!WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
			throw new Exception("图片上传失败");
		  }
		return result;
	}

	@Override
	public AppButtonDO getAppButtonById(AppButtonDO appButtonDO) {
		return appButtonMapper.getAppButtonById(appButtonDO);
	}

	@Override
	@Transactional
	public BaseResultDTO updateButton(AppButtonDO appButtonDO,MultipartFile[] file, UserDO loginUser) throws Exception{
		ResultDTO<String> result = new ResultDTO<>();
		String referenceId =""+appButtonDO.getPkButtonId();
		if (file.length>0) {
			//先删除原先的
			List<String> oldFileUrlList = multipartFileService.getAllMultiUrl(WanmaConstants.APP_BUTTON_PIC, referenceId);
	        multipartFileService.deleteMulti(oldFileUrlList, WanmaConstants.APP_BUTTON_PIC, referenceId, loginUser);
	        String results = multipartFileService.saveMultiFile(file, WanmaConstants.APP_BUTTON_PIC, referenceId, false, loginUser);
	        if (WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
				  if(!(appButtonMapper.updateButton(appButtonDO)>0)){
					  return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改appButton失败！");
				  }
			  }else{
				  throw new Exception("图片上传失败");
			  }
		}else {
			 if(!(appButtonMapper.updateButton(appButtonDO)>0)){
				  return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改appButton失败！");
			  }
		}
		return result;
	}

	@Override
	@Transactional
	public boolean deleteAppButton(AppButtonDO appButtonDO) {
		return appButtonMapper.deleteAppButton(appButtonDO)>0;
	}

	@Override
	@Transactional
	public boolean downAppButton(AppButtonDO appButtonDO) {
		return appButtonMapper.downAppButton(appButtonDO);
	}

	@Override
	@Transactional
	public boolean changeButtonSort(AppButtonDO appButtonDO) {
		return appButtonMapper.changeButtonSort(appButtonDO);
	}

	

}
