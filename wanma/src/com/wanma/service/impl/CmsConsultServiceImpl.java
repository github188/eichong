package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanma.dao.CmsConsultMapper;
import com.wanma.model.Consult;
import com.wanma.service.CmsConsultService;

/**
 * 咨询业务处理器
 * 
 * @author xiay
 *
 */
@Service
public class CmsConsultServiceImpl implements CmsConsultService{

	/** 咨询表作用Dao */
	@Autowired
	private CmsConsultMapper consultDao;

	/**
	 * 取得咨询表一览
	 * 
	 */
	@Override
	public List<Consult> getConsultList() {
		// 咨询表一览
		List<Consult> listConsult;
		
		//取得咨询表一览
		listConsult = consultDao.getConsultList();
		
		//返回咨询表一览
		return listConsult;
	}

	/**
	 * 查询咨询个数
	 * 
	 */
	public long searchConsultCount(Consult consult) {
		// 咨询个数
		long dataCount;

		// 取得咨询个数
		dataCount = consultDao.searchConsultCount(consult);

		// 返回咨询个数
		return dataCount;

	}
	
	/**
	 * 查询咨询表一览
	 * 
	 */
	@Override
	public List<Consult> searchConsultList(Consult consult) {
		// 咨询表一览
		List<Consult> listConsult;
				
		//取得咨询表一览
		listConsult = consultDao.searchConsultList(consult);
				
		//返回咨询表一览
		return listConsult;
	}

	
}
