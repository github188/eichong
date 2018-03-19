package com.wanma.dao;


import java.util.List;
import java.util.Map;

import com.wanma.model.TblCompany;


/**
 * 数据访问接口
 *
 */
public interface TblCompanyMapper {    
    
    public TblCompany get(java.lang.Integer pkCompanyid);
	
    public int countByCompanyName(String companyName);
    
	public  TblCompany findOne(TblCompany tblCompany);
	
	public List<TblCompany> find(TblCompany tblCompany);
	public long findCount(TblCompany tblCompany);
	
	public int insert(TblCompany tblCompany);
	
	public int update(TblCompany tblCompany);
	
	public int delete(java.lang.Integer pkCompanyid );

	public List<TblCompany> getUnSelectCompanyList(TblCompany company);

	public void updateFlag(TblCompany tblCompany);

	public void deleteFlag(Map<String, String[]> makeIdsMap);

	public List<TblCompany> findCompanyFlagList(TblCompany tblCompany);

	public long findCompanyFlagListCount(TblCompany tblCompany);

	public long getUsedCompanyFlagCount(Map<String, String[]> makeIdsMap);

	public long findCompanyFlagListCountByCompanyNumber(TblCompany tblCompany);


}


