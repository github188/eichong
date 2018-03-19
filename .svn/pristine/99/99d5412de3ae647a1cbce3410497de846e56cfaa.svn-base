package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsBannerMapper;
import com.wanma.model.TblAdvertisement;
import com.wanma.model.TblBanner;
import com.wanma.service.CmsBannerService;
import com.wanma.service.CmsCommitLogService;


@Service
public class CmsBannerServiceImpl implements CmsBannerService {
	@Autowired 
	private CmsBannerMapper cmsBannerMapper;
	@Autowired
	CmsCommitLogService commitLogService;
	
	@Override
	public long getBannerListCount() {
		return cmsBannerMapper.getBannerListCount();
	}

	@Override
	public List<TblBanner> getBannerList(TblBanner banner) {
		return cmsBannerMapper.getBannerList(banner);
	}

	@Override
	public List getCityName() {
		return cmsBannerMapper.getCityName();
	}

	@Override
	public void insertBanner(TblBanner banner) {
		 cmsBannerMapper.insertBanner(banner);
		 commitLogService.insert("新增banner，banner主键：["
					+ banner.getPkBannerId() + "]");
		 updateImageInfo(banner);
		
	}
	private void updateImageInfo(TblBanner model) {
		String bannerPicMfRefId = model.getBannerPicFileId();
		if (StringUtils.isNotBlank(bannerPicMfRefId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("referenceId", model.getPkBannerId().toString());
			map.put("fileId", bannerPicMfRefId);
			cmsBannerMapper.updateImageInfo(map);
		}
	}

	@Override
	public TblBanner getBannerById(int bannerId) {
		return cmsBannerMapper.getBannerById(bannerId);
	}

	@Override
	public void updateBanner(TblBanner banner) {
		cmsBannerMapper.updateBanner(banner);
		commitLogService.insert("修改banner，banner主键：["
				+ banner.getPkBannerId() + "]");
		updateImageInfo(banner);
		
	}

	@Override
	public void deleteBannerById(int bannerId) {
		cmsBannerMapper.deleteBannerById(bannerId);
	}

	@Override
	public void offShelfBannerById(TblBanner banner) {
		cmsBannerMapper.offShelfBannerById(banner);
	}

	@Override
	public void changeBannerSort(TblBanner banner) {
		cmsBannerMapper.changeBannerSort(banner);
		
	}


	
}
