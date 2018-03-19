package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.dao.MultipartFileDao;
import com.wanma.common.WanmaConstants;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsPublishEpMapper;
import com.wanma.model.TblCarmaker;
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

	@SystemControllerLog(description = "更新桩体分享")
	@Override
	public int update(TblPublishEp tblPublishEp) {
		return cmsPublishEpMapper.update(tblPublishEp);
	}

	@SystemControllerLog(description = "删除桩体分享")
	@Override
	public int delete(Integer pk) {
		return cmsPublishEpMapper.delete(pk);
	}

	@SystemControllerLog(description = "批量删除桩体分享")
	@Override
	public int deleteBatch(String pks) {
		return cmsPublishEpMapper.deleteBatch(pks);
	}

	public List<String> getImages(TblPublishEp tblPublishEp) {
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		return multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_ELECTRICT_PUBLISH_IMG, String.valueOf(tblPublishEp.getId()));
	}
	

}
