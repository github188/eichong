/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanma.dao.CmsAdvertisementMapper;
import com.wanma.model.TblAdvertisement;
import com.wanma.service.CmsAdvertisementService;

/**
 * 广告管理业务实现类
 * 
 * @author lhy
 *
 */
@Service
public class CmsAdvertisementServiceImpl implements CmsAdvertisementService {
	@Autowired
	CmsAdvertisementMapper advMapper;

	@Override
	public List<TblAdvertisement> getList(TblAdvertisement model) {
		return advMapper.getList(model);
	}

	@Override
	public long getCount(TblAdvertisement model) {
		return advMapper.getCount(model);
	}

	@Override
	public void insert(TblAdvertisement model) {
		advMapper.insert(model);
		updateImageInfo(model);
	}

	@Override
	public void update(TblAdvertisement model) {
		advMapper.update(model);
		updateImageInfo(model);
	}

	@Override
	public int getLimitCount(TblAdvertisement model) {
		return advMapper.getLimitCount(model);
	}

	private void updateImageInfo(TblAdvertisement model) {
		String advPicMfRefId = model.getAdvPicFileId();
		if (StringUtils.isNotBlank(advPicMfRefId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("referenceId", model.getPkAdId());
			map.put("fileId", advPicMfRefId);
			advMapper.updateImageInfo(map);
		}
		String advListPicMfRefId = model.getAdvListFileId();
		if (StringUtils.isNotBlank(advListPicMfRefId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("referenceId", model.getPkAdId());
			map.put("fileId", advListPicMfRefId);
			advMapper.updateImageInfo(map);
		}
	}

	@Override
	public TblAdvertisement selectOne(TblAdvertisement advModel) {
		return advMapper.selectOne(advModel);
	}

}
