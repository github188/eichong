package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.PowerstationPushMapper;
import com.wanma.model.cdzts.PowerstationPush;
import com.wanma.service.CmsPowerstationService;

/**
 * @Description: 充电点管理业务处理实现类
 * @author lyh
 * @createTime：
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class CmsPowerstationServiceImpl implements CmsPowerstationService{
	@Autowired
	private PowerstationPushMapper powerstationMapper;	
	
	@Override
	public int getPowerCount(PowerstationPush model) {
		return powerstationMapper.getPowerCount(model);
	}
	
	/**
	 * @Title: deleteById
	 * @Description: 通过ID获取充电点信息
	 * @param params
	 * @return
	 */
	@Override
	public PowerstationPush getPowerById(String powerStationId){
		PowerstationPush tblPowerstation=powerstationMapper.get(powerStationId);
		/*//获取电桩列表图片
		List<String> listImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE,
				tblPowerstation.getPkPowerstation() + "");
		tblPowerstation.setPostPicUrl(listImage);*/
		/*//获取电桩详情图片
		List<String> detailImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_POWER_DETAIL_IMAGE,
				tblPowerstation.getPkPowerstation() + "");
		if(listImage.size()>0 && !listImage.isEmpty()){
			tblPowerstation.setPostPic(JudgeNullUtils.nvlStr(listImage.get(0)));
		}
		if(detailImage.size()>0 && !detailImage.isEmpty()){
			tblPowerstation.setPostDetailpic(JudgeNullUtils.nvlStr(detailImage.get(0)));
		}*/	
			
		return tblPowerstation;
	}


}
