package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsRateInfoMapper;
import com.wanma.dao.CmsTwoDiCodeMapper;
import com.wanma.service.CmsTwoDiCodeService;
@Service
public class CmsTwoDiCodeServiceImpl implements CmsTwoDiCodeService {
	
	@Autowired
	CmsTwoDiCodeMapper twoDiCodeMapper;
	
	
	@Override
	public List<HashMap<String, Object>> getTwoDiCodeList(
			Map<String, Object> params) {		
		return twoDiCodeMapper.getTwoDiCodeList(params);
	}

	@Override
	public long getTwoDiCodeListCount(Map<String, Object> params) {
		
		return twoDiCodeMapper.getTwoDiCodeListCount(params);
	}

}
