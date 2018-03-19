package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.NewsInfoDO;


public interface NewsInfoMapper {

	long selectNewsInfoCount(NewsInfoDO newsInfoDO);

	List<NewsInfoDO> selectNewsInfoList(NewsInfoDO newsInfoDO);

	int insertNewsInfo(NewsInfoDO newsInfoDO);

	int updateNewsInfo(NewsInfoDO newsInfoDO);

	int deleteNewsInfo(NewsInfoDO newsInfoDO);

	boolean downNewsInfoById(NewsInfoDO newsInfoDO);

	NewsInfoDO getNewsInfoById(NewsInfoDO newsInfoDO);

	



}
