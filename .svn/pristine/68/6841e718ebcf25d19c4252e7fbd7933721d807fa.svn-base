package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.WanmaConstants;
import com.base.util.MultiFileUtil;
import com.wanma.dao.CmsPublishEpMapper;
import com.wanma.model.TblPublishEp;
import com.wanma.service.CmsPublishEpService;

@Service
public class CmsPublishEpServiceImpl implements CmsPublishEpService {
	@Autowired
	CmsPublishEpMapper cmsPublishEpMapper;

	@Override
	public List<TblPublishEp> getAll(TblPublishEp tblPublishEp) {
		return cmsPublishEpMapper.getAll(tblPublishEp);
	}

	@Override
	public int getCount(TblPublishEp tblPublishEp) {
		return cmsPublishEpMapper.getCount(tblPublishEp);
	}

	@Override
	public TblPublishEp getOne(TblPublishEp tblPublishEp) {
		return cmsPublishEpMapper.getOne(tblPublishEp);
	}

	@Override
	public int update(TblPublishEp tblPublishEp) {
		return cmsPublishEpMapper.update(tblPublishEp);
	}

	@Override
	public int delete(Integer pk) {
		return cmsPublishEpMapper.delete(pk);
	}

	@Override
	public int deleteBatch(String pks) {
		return cmsPublishEpMapper.deleteBatch(pks);
	}

	public List<String> getImages(TblPublishEp tblPublishEp) {
		return MultiFileUtil.getUrls(WanmaConstants.MULTI_TYPE_ELECTRICT_PUBLISH_IMG, String.valueOf(tblPublishEp.getId()));
	}
	

}
