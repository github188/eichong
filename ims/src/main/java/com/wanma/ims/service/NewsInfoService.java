package com.wanma.ims.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.NewsInfoDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;

public interface NewsInfoService {

	public long selectNewsInfoCount(NewsInfoDO newsInfoDO);

	public List<NewsInfoDO> selectNewsInfoList(NewsInfoDO newsInfoDO);

	public BaseResultDTO addNewsInfo(NewsInfoDO newsInfoDO, MultipartFile[] file, UserDO userDO) throws Exception;

	public BaseResultDTO updateNewsInfo(NewsInfoDO newsInfoDO, MultipartFile[] file, UserDO userDO) throws Exception;

	public boolean deleteNewsInfo(NewsInfoDO newsInfoDO);

	public boolean downNewsInfoById(NewsInfoDO newsInfoDO);

	public NewsInfoDO getNewsInfoById(NewsInfoDO newsInfoDO);

}
