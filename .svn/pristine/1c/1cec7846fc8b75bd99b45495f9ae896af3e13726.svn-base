package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsAppButtonMapper;
import com.wanma.model.TblAppButton;
import com.wanma.model.TblBanner;
import com.wanma.service.CmsAppButtonService;
import com.wanma.service.CmsCommitLogService;


@Service
public class CmsAppButtonServiceImpl implements CmsAppButtonService {
	@Autowired
	CmsCommitLogService commitLogService;
	@Autowired
	CmsAppButtonMapper appButtonMapper;

	@Override
	public long getButtonListCount() {
		return appButtonMapper.getButtonListCount();
	}

	@Override
	public List<TblAppButton> getButtonList(TblAppButton appButton) {
		return appButtonMapper.getButtonList(appButton);
	}

	@Override
	public void insertButton(TblAppButton appButton) {
		 appButtonMapper.insertButton(appButton);
		 commitLogService.insert("新增button，button主键：["
					+ appButton.getPkButtonId() + "]");
		 updateImageInfo(appButton);
		
	}
	
	private void updateImageInfo(TblAppButton model) {
		String buttonPicMfRefId = model.getButtonPicFileId();
		if (StringUtils.isNotBlank(buttonPicMfRefId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("referenceId", model.getPkButtonId().toString());
			map.put("fileId", buttonPicMfRefId);
			appButtonMapper.updateImageInfo(map);
		}
	}

	@Override
	public TblAppButton getButtonById(int pkButtonId) {
		return appButtonMapper.getButtonById(pkButtonId);
	}

	@Override
	public void updateButton(TblAppButton appButton) {
		appButtonMapper.updateButton(appButton);
		 commitLogService.insert("编辑button，button主键：["
					+ appButton.getPkButtonId() + "]");
		 updateImageInfo(appButton);
	}

	@Override
	public void deleteButtonById(int pkButtonId) {
		 appButtonMapper.deleteButtonById(pkButtonId);
		
	}

	@Override
	public void downButtonById(int pkButtonId) {
		 appButtonMapper.downButtonById(pkButtonId);
		
	}

	@Override
	public void changeButtonSort(TblAppButton appButton) {
		 appButtonMapper.changeButtonSort(appButton);
		
	}

	
}
