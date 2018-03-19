package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblCompany;

public interface CmsOfflineChargeMapper {

	long getCompanyCount(TblCompany company);

	List<TblCompany> getCompanyList(TblCompany company);

	List<TblCompany> getUnsetCompanyList();

	String getCpyCompanyNumberById(int pkCompanyid);

	void insertOfflineCharge(TblCompany company);

	TblCompany getCompanyById(int pkCompanyid);

	void deleteOfflineCharge(int pkCompanyid);

}
