package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsRateInfoMapper;
import com.wanma.model.TblRateinformation;
import com.wanma.model.TblRateinformation;
import com.wanma.service.CmsRateInfoService;


@Service
public class CmsRateInfoServiceImpl implements CmsRateInfoService{

	@Autowired
	private CmsRateInfoMapper cmsRateInfoMapper;
	

	/**
	 * 根据用户id，获取对应超级管理员、管理员、纯商家的费率列表
	 * @return
	 */
	public List<TblRateinformation> getRateInfoListByUserId(TblRateinformation tblRateInfo){
		return cmsRateInfoMapper.getRateInfoListByUserId(tblRateInfo);
	}
	
	/**
	 * 根据用户id，获取某个公司（由该公司的纯商家、子商家）设置的费率列表
	 * @return
	 */
	public List<TblRateinformation> getRateInfoListByUserId2(TblRateinformation tblRateInfo){
		return cmsRateInfoMapper.getRateInfoListByUserId2(tblRateInfo);
	}
	
	/**
	 * 根据用户id，获取对应用户设置的费率总数
	 * @param userId
	 * @return
	 */
	public int getRateInfoNumByUserId(TblRateinformation tblRateInfo){
		return cmsRateInfoMapper.getRateInfoNumByUserId(tblRateInfo);
	}
	
	public int getRateInfoNumByUserId2(String userId){
		return cmsRateInfoMapper.getRateInfoNumByUserId2(userId);
	}
	
	public void insertRateInfo(TblRateinformation tblRateInfo){
		cmsRateInfoMapper.insertRateInfo(tblRateInfo);
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
	public TblRateinformation getRateInfoById(int id){
		return cmsRateInfoMapper.getRateInfoById(id);
	}
	
	/**
	 * 更新费率表
	 * @param tblRateInfo
	 */
	public void updateRateInfo(TblRateinformation tblRateInfo){
		cmsRateInfoMapper.updateRateInfo(tblRateInfo);
		cmsRateInfoMapper.insertRateHistoryInfo(tblRateInfo);
	}
	
	/**
	 * 个体商家  根据登陆用户id和地区获取费率信息 
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoByUser(TblRateinformation rateInfo){
		return cmsRateInfoMapper.getRateInfoByUser(rateInfo);
	}
	
	/**
	 * 纯商家  根据登陆用户id所属公司和地区获取费率信息
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoByCompany(TblRateinformation rateInfo){
		return cmsRateInfoMapper.getRateInfoByCompany(rateInfo);
	}
	
	/**
	 * 万马账户 根据地区获取万马新增费率 
	 * @param rateInfo
	 * @return
	 */
	public List<TblRateinformation> getRateInfoByWM(TblRateinformation rateInfo){
		return cmsRateInfoMapper.getRateInfoByWM(rateInfo);
	}

	public List<TblRateinformation> getRateAndElectricSend(int id){
		return cmsRateInfoMapper.getRateAndElectricSend(id);
	}

	@Override
	public List<TblRateinformation> getRateinfoList(TblRateinformation rateinfo) {
		return cmsRateInfoMapper.getRateinfoList(rateinfo);
	}

	@Override
	public void insertRateHistoryInfo(TblRateinformation tblRateInfo) {
		cmsRateInfoMapper.insertRateHistoryInfo(tblRateInfo);
	}

	@Override
	public void updateRateInfoByProvince(TblRateinformation params) {
		cmsRateInfoMapper.updateRateInfoByProvince(params);
	}
	
	
}
