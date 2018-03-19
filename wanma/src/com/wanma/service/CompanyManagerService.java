package com.wanma.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.model.TblCompany;

/**
 * 预约业务处理器
 * 
 *
 */
public interface CompanyManagerService {
	
	public List<TblCompany> getCompanyList(TblCompany tblCompany);
	public long getCompanyCount(TblCompany tblCompany);
	public void addCompany(TblCompany tblCompany,MultipartFile[] IdUnitCardImage,MultipartFile[] LicenseImage,
			MultipartFile[] AffairsImage,MultipartFile[] AccreditImage);
	public TblCompany getCompanyById(int pkCompanyid);
	public int countByCompanyName(String companyName);
	public void modifyCompany(TblCompany tblCompany,MultipartFile[] IdUnitCardImage,MultipartFile[] LicenseImage,
			MultipartFile[] AffairsImage,MultipartFile[] AccreditImage);
	public void deleteCompany(String pkCompanyid);
	public List<TblCompany> getUnSelectCompanyList(TblCompany company);
	public void modifyCompanyFlag(TblCompany tblCompany);
	public void deleteFlag(String ids);
	public boolean isFlagBeUsed(String ids);
	public List<TblCompany> findCompanyFlagList(TblCompany tblCompany);
	public long findCompanyFlagListCount(TblCompany tblCompany);
	public boolean checkCpyCompanyNumber(TblCompany tblCompany);
	
}
