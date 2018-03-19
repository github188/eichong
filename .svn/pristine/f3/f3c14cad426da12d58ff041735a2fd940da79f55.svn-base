package com.wanma.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsMessageInfoMapper;
import com.wanma.model.TblMessageInfo;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsMessageInfoService;


@Service
public class CmsMessageInfoServiceImpl implements CmsMessageInfoService {
	@Autowired 
	private CmsMessageInfoMapper cmsMessageInfoMapper;
	@Autowired
	CmsCommitLogService commitLogService;
	@Override
	public List<TblMessageInfo> getMessageInfoList(TblMessageInfo messageInfo) {
		return cmsMessageInfoMapper.getMessageInfoList(messageInfo);
	}
	@Override
	public long getMessageInfoListCount(TblMessageInfo messageInfo) {
		return cmsMessageInfoMapper.getMessageInfoListCount(messageInfo);
	}
	@Override
	public List<Map<String, Object>> getCityName() {
		return cmsMessageInfoMapper.getCityName();
	}
	@Override
	public List<Object> getpowerstation(Map<String, String> params) {
		return cmsMessageInfoMapper.getpowerstation(params);
	}
	@Override
	public void insertMessageInfo(TblMessageInfo messageInfo,
			String[] pkPowerstation) {
		if(messageInfo.getMessageInfoType()==2){//新建消息，结束时间为发布日的00:00之后 7天
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH,7);
			SimpleDateFormat   sdf   =   new   SimpleDateFormat( "yyyy-MM-dd 00:00:00 "); 
			String time = sdf.format(c.getTime());
			try {
				messageInfo.setMessageInfoEndtime(sdf.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			SimpleDateFormat   sdf   =   new   SimpleDateFormat( "yyyy-MM-dd 00:00:00 "); 
			try {
				messageInfo.setMessageInfoEndtime(sdf.parse("2030-01-01 00:00:00 "));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		cmsMessageInfoMapper.insertMessageInfo(messageInfo);
		commitLogService.insert("新增首页消息，首页消息主键：["
				+ messageInfo.getPkMessageInfoId() + "]");
		if(pkPowerstation!=null){
			for(int i=0;i<pkPowerstation.length;i++){
				int powerstationId = Integer.parseInt(pkPowerstation[i]);
				messageInfo.setPkPowerstation(powerstationId);
				messageInfo.setMprName(cmsMessageInfoMapper.getMprName(powerstationId));
				cmsMessageInfoMapper.relationPowerStation(messageInfo);
			}
		}
		
		
	}
	@Override
	public TblMessageInfo getMessageInfoById(int pkMessageInfoId) {
		return cmsMessageInfoMapper.getMessageInfoById(pkMessageInfoId);
		
	}
	@Override
	public List<Map<String, Object>> getPowerstationById(int pkMessageInfoId) {
		return cmsMessageInfoMapper.getPowerstationById(pkMessageInfoId);
	}
	@Override
	public void relationPowerStation(TblMessageInfo messageInfo) {
		cmsMessageInfoMapper.relationPowerStation(messageInfo);
		
	}
	@Override
	public void removeRelationPowerStation(TblMessageInfo messageInfo) {
		cmsMessageInfoMapper.removeRelationPowerStation(messageInfo);
		
	}
	@Override
	public void updateMessageInfo(TblMessageInfo messageInfo) {
		cmsMessageInfoMapper.updateMessageInfo(messageInfo);	
		commitLogService.insert("编辑首页消息，首页消息主键：["
				+ messageInfo.getPkMessageInfoId() + "]");
	}
	@Override
	public String getMprNameByPkPowerstation(String pkPowerstation) {
		return cmsMessageInfoMapper.getMprNameByPkPowerstation(pkPowerstation);
	}
	@Override
	public void closeMessageInfoById(int pkMessageInfoId) {
		cmsMessageInfoMapper.closeMessageInfoById(pkMessageInfoId);
	}
	@Override
	public void deleteMessageInfoById(int pkMessageInfoId) {
		cmsMessageInfoMapper.deleteMessageInfoById(pkMessageInfoId);
		
	}
	@Override
	public void deleteMessageInfoPointById(int pkMessageInfoId) {
		cmsMessageInfoMapper.deleteMessageInfoPointById(pkMessageInfoId);
		
	}
	

	


	
}
