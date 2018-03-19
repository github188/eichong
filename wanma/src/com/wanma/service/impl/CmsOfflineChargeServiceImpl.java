package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsOfflineChargeMapper;
import com.wanma.model.TblCompany;
import com.wanma.service.CmsOfflineChargeService;
@Service
public class CmsOfflineChargeServiceImpl implements CmsOfflineChargeService {
	@Autowired
	private CmsOfflineChargeMapper offlineChargeMapper;

	@Override
	public long getCompanyCount(TblCompany company) {
		return offlineChargeMapper.getCompanyCount(company);
	}

	@Override
	public List<TblCompany> getCompanyList(TblCompany company) {
		return offlineChargeMapper.getCompanyList(company);
	}

	@Override
	public List<TblCompany> getUnsetCompanyList() {
		return offlineChargeMapper.getUnsetCompanyList();
	}

	
	@Override
	public String getCpyCompanyNumberById(int pkCompanyid) {
		return offlineChargeMapper.getCpyCompanyNumberById(pkCompanyid);
	}

	@Override
	public void insertOfflineCharge(TblCompany company) {
		offlineChargeMapper.insertOfflineCharge(company);
		
	}

	@Override
	public TblCompany getCompanyById(int pkCompanyid) {
		return offlineChargeMapper.getCompanyById(pkCompanyid);
	}

	@Override
	public void deleteOfflineCharge(int pkCompanyid) {
		 offlineChargeMapper.deleteOfflineCharge(pkCompanyid);
		
	}
	
			

}
