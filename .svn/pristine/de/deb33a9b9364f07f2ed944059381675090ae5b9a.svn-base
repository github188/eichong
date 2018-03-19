package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblPureBusiness;

/**
 * 纯商家
 * @author xiay
 * 数据访问接口
 *
 */
public interface CmsPureBusinessMapper {
	
	/**
	 * 根据纯商家ID取得纯商家信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblPureBusiness findBusiness(String businessId);
	/**
	 * 根据纯商家爱充网账号取得纯商家信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblPureBusiness findBusinessByLoveId(String loveLogin);

	/**
	 * 添加纯商家
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int saveBusiness(TblPureBusiness tblPureBusiness);

	/**
	 * 编辑纯商家
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void modifyBusiness(TblPureBusiness tblPureBusiness);

	/**
	 * 取得纯商家一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblPureBusiness> getBusinessList();

	/**
	 * 查询纯商家个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchBusinessCount(TblPureBusiness tblPureBusiness);

	/**
	 * 查询纯商家一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblPureBusiness> searchBusinessList(TblPureBusiness tblPureBusiness);
	/**
	 * 校验爱充网账号唯一性
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int checkLoveAccount(TblPureBusiness tblPureBusiness);
	/**
	 * 根据登陆账号获取纯商家信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblPureBusiness findBusinessByUserId(String loveLogin);
	
	/**
	 * 根据公司id获取纯商家数量
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int findByCompanyId(String companyId);
}
