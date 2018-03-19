package com.wanma.app.service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.controller.ApplyEpController;
import com.wanma.app.dao.AppPublishEpMapper;
import com.wanma.app.dao.ApplyEpMapper;
import com.wanma.app.service.ApplyEpService;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblPublishEp;

@Service
public class ApplyEpServiceImpl implements ApplyEpService{
	
	public void saveApplyEp(Map<String, String> params){
		applyEpMapper.saveApplyEp(params);
	}
	
	public List<Map<String, String>> getApplyEpList(int uid){
		return applyEpMapper.getApplyEpList(uid);
	}
	
	@Autowired
	ApplyEpMapper applyEpMapper;
}
