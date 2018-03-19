package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsRateInfoMapper;
import com.wanma.model.TblRateInfo;


@Service
public class CmsRateInfoServiceImpl{

	@Autowired
	private CmsRateInfoMapper cmsRateInfoMapper;
	
	/**
	 * 根据用户id，获取对应用户的费率
	 * @return
	 */
	public Map<String, String> getRateInfoByUserId() {
		return cmsRateInfoMapper.getRateInfoByUserId();
	}

	/**
	 * 根据用户id，获取对应超级管理员、管理员、纯商家的费率列表
	 * @return
	 */
	public List<Map<String, Object>> getRateInfoListByUserId(TblRateInfo tblRateInfo){
		return cmsRateInfoMapper.getRateInfoListByUserId(tblRateInfo);
	}
	
	/**
	 * 根据用户id，获取某个公司（由该公司的纯商家、子商家）设置的费率列表
	 * @return
	 */
	public List<Map<String, Object>> getRateInfoListByUserId2(TblRateInfo tblRateInfo){
		return cmsRateInfoMapper.getRateInfoListByUserId2(tblRateInfo);
	}
	
	/**
	 * 根据用户id，获取对应用户设置的费率总数
	 * @param userId
	 * @return
	 */
	public int getRateInfoNumByUserId(TblRateInfo tblRateInfo){
		return cmsRateInfoMapper.getRateInfoNumByUserId(tblRateInfo);
	}
	
	public int getRateInfoNumByUserId2(String userId){
		return cmsRateInfoMapper.getRateInfoNumByUserId2(userId);
	}
	
	public void insertRateInfo(TblRateInfo tblRateInfo){
		cmsRateInfoMapper.insertRateInfo(tblRateInfo);
		cmsRateInfoMapper.insertRateHistoryInfo(tblRateInfo);
	}
	
	private void insertRateHistoryInfo(TblRateInfo tblRateInfo){
		cmsRateInfoMapper.insertRateHistoryInfo(tblRateInfo);
	}
	
	
	public void delRateInfo(int id){
		cmsRateInfoMapper.delRateInfo(id);
	}
	
	/**
	 * 根据id获取费率信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> getRateInfoById(int id){
		return cmsRateInfoMapper.getRateInfoById(id);
	}
	
	/**
	 * 更新费率表
	 * @param tblRateInfo
	 */
	@SystemControllerLog(description = "更新费率")
	public void updateRateInfo(TblRateInfo tblRateInfo){
		cmsRateInfoMapper.updateRateInfo(tblRateInfo);
		cmsRateInfoMapper.insertRateHistoryInfo(tblRateInfo);
	}
	/**
	 * 省市区三级信息
	 * @param params
	 * @return
	 */
	public List<HashMap<String,Object>>  searchProvinceList(Map<String,Object> params){
		return cmsRateInfoMapper.searchProvinceList(params);
	}
	public List<HashMap<String,Object>>  searchCityList(Map<String,Object> params){
		return cmsRateInfoMapper.searchCityList(params);
	}
	public List<HashMap<String,Object>>  searchAreaList(Map<String,Object> params){
		return cmsRateInfoMapper.searchAreaList(params);
	}
	
	/**
	 * 个体商家  根据登陆用户id和地区获取费率信息 
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateInfo> getRateInfoByUser(TblRateInfo rateInfo){
		return cmsRateInfoMapper.getRateInfoByUser(rateInfo);
	}
	
	/**
	 * 纯商家  根据登陆用户id所属公司和地区获取费率信息
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateInfo> getRateInfoByCompany(TblRateInfo rateInfo){
		return cmsRateInfoMapper.getRateInfoByCompany(rateInfo);
	}
	
	/**
	 * 万马账户 根据地区获取万马新增费率 
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateInfo> getRateInfoByWM(TblRateInfo rateInfo){
		return cmsRateInfoMapper.getRateInfoByWM(rateInfo);
	}

	public List<HashMap<String,Object>> getRateAndElectricSend(int id){
		
		return cmsRateInfoMapper.getRateAndElectricSend(id);
	}

	public Double selectMinPriceByPsId(Integer pkPowerstation) {
		return cmsRateInfoMapper.selectMinPriceByPsId(pkPowerstation);
	}
	
	/**
	 * 当前用户下的费率
	 * @param rateInfo
	 * @return
	 */
	public int getRateInfoList(TblRateInfo rateInfo){
		return cmsRateInfoMapper.getRateInfoList(rateInfo);
		
	}
	
}
