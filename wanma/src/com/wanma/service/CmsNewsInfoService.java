package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblNewsInfo;


/**
 * banner业务处理器
 * @author mb
 * 
 */
public interface CmsNewsInfoService {

	long getNewsInfoListCount(TblNewsInfo newsInfo);

	List<TblNewsInfo> getNewsInfoList(TblNewsInfo newsInfo);

	void insertNewsInfo(TblNewsInfo newsInfo);

	TblNewsInfo getNewsInfoById(int newsInfoId);

	void updateNewsInfo(TblNewsInfo newsInfo);

	void deleteNewsInfoById(int newsInfoId);

	void downNewsInfoById(int newsInfoId);
		


}
