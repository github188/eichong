package com.wanma.service;

import java.util.List;

import com.wanma.model.TblPureBusiness;
import com.wanma.model.TblUser;

/**
 * FileName TblPureBusinessService.java
 * 
 * Version 1.0
 * 
 * Create by xiay
 * 
 * 纯商家处理器
 */
public interface CmsPureBusinessService {
	
	/**
	 * 根据纯商家ID取得纯商家信息
	 * 
	 * @return
	 */
	public TblPureBusiness findBusiness(String businessId);
	public TblPureBusiness findBusinessByLoveId(String loveLogin);
	/**
	 * 添加纯商家
	 *
	 * @return
	 */
	public void addBusiness(TblPureBusiness tblPureBusiness);

	/**
	 * 编辑纯商家
	 * 
	 * @return 
	 */
	public void modifyBusiness(TblPureBusiness tblPureBusiness );

	/**
	 * 取得纯商家一览
	 * 
	 * @return
	 */
	public List<TblPureBusiness> getBusinessList(TblUser loginUser);

	/**
	 * 查询纯商家个数
	 * 
	 * @return 
	 */
	public long searchBusinessCount(TblPureBusiness tblPureBusiness,TblUser loginUser);
	
	/**
	 * 查询纯商家一览
	 * 
	 * @return 
	 */
	public List<TblPureBusiness> searchBusinessList(TblPureBusiness tblPureBusiness,TblUser loginUser);
	public String checkLoveAccount(String loveAccount);
	
	/**
	 * 根据登陆账号获取纯商家信息
	 * 
	 * @author xiay
	 * @return TblPureBusiness
	 */
	public TblPureBusiness findBusinessByUserId(String loveLogin);
	
	/**
	 * 根据公司id获取纯商家数量
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int findByCompanyId(String companyId);
	
	/**
	 * 删除纯商家
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void removeUser(TblUser TblUser);
}
