package com.wanma.ims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.ims.common.domain.AppLoginRecord;
import com.wanma.ims.mapper.AppLoginRecordMapper;
import com.wanma.ims.service.AppLoginRecordService;

@Service("appLoginRecordService")
public class AppLoginRecordServiceImpl implements AppLoginRecordService{
	@Autowired
	private AppLoginRecordMapper appLoginRecordMapper;
	
	@Override
	public long getAppLoginRecordCount(AppLoginRecord appLoginRecord) {
		return appLoginRecordMapper.getAppLoginRecordCount(appLoginRecord);
	}

	@Override
	public List<AppLoginRecord> getAppLoginRecordList(
			AppLoginRecord appLoginRecord) {
		return appLoginRecordMapper.getAppLoginRecordList(appLoginRecord);
	}


}
