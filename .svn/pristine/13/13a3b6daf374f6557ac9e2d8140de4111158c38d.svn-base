package com.wanma.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanma.model.TblRateinformation;

public interface CmsRateInfoMapper {
	
	
	/**
	 * 根据用户id获取(超级管理员、管理员、纯商家)费率列表信息
	 * @return
	 */
	public List<TblRateinformation> getRateInfoListByUserId(TblRateinformation tblRateInfo);
	/**
	 * 根据用户id获取同一个公司(该公司纯商家、子商家)的费率列表信息
	 * @param tblRateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoListByUserId2(TblRateinformation tblRateInfo);
	
	/**
	 * 根据用户id，获取超级管理员、管理员、纯商家设置的费率总数
	 * @param tblRateinfo
	 * @return
	 */
	public int getRateInfoNumByUserId(TblRateinformation tblRateinfo);
	
	/**
	 * 根据用户id，获取公司设置的费率总数
	 * @param userId
	 * @return
	 */
	public int getRateInfoNumByUserId2(@Param("userId") String userId);
	
	
	
	public int insertRateInfo(TblRateinformation tblRateInfo);
	
	public void insertRateHistoryInfo(TblRateinformation tblRateInfo);
	
	public void delRateInfo(int id);
	
	/**
	 * 根据主键获取费率信息
	 * @param id
	 * @return
	 */
	public TblRateinformation getRateInfoById(int id);
	
	public void updateRateInfo(TblRateinformation tblRateInfo);
	

	
	/**
	 * 个体商家 根据登陆用户id和地区获取费率信息 
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoByUser(TblRateinformation rateInfo);
	
	/**
	 * 纯商家 根据登陆用户id所属公司和地区获取费率信息
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoByCompany(TblRateinformation rateInfo);
	
	/**
	 * 万马账户 根据地区获取万马新增费率 
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoByWM(TblRateinformation rateInfo); 
	
	/**
	 * 判断传入ID的费率是否已经绑定了桩
	 * @param id
	 * @return
	 */
	public List<TblRateinformation> getRateAndElectricSend(int id);

	/**
	 * 根据省份记录修改费率信息
	 * @param params
	 */
	public void updateRateInfoByProvince(TblRateinformation params);
	
	
	public List<TblRateinformation> getRateinfoList(TblRateinformation rateinfo);
	 	
}
