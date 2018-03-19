package com.wanma.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblCarinfo;
import com.wanma.model.TblParaconfig;
import com.wanma.web.dao.WebCarCompanyMapper;
import com.wanma.web.dao.WebCarinfoMapper;
import com.wanma.web.dao.WebParaconfigMapper;
import com.wanma.web.service.WebParaconfigService;

/**
 * @Description: 其他配置参数业务处理类
 * @author songjf
 * @createTime：2015-3-13 下午05:04:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebParaconfigServiceImpl implements WebParaconfigService {

	/** 其他配置信息业务操作DAO */
	@Autowired
	private WebParaconfigMapper paraconfigMapper;

	@Autowired
	private WebCarinfoMapper carinfoMapper;	
	@Autowired
	private WebCarCompanyMapper  carCompanyMapper;

	/**
	 * @Title: findParaconfig
	 * @Description: 获取配置信息
	 * @param params
	 *            查询条件
	 * @return
	 */
	@Override
	public List<TblParaconfig> findParaconfigList(Map<String, Object> params) {

		return paraconfigMapper.find(params);
	}

	/**
	 * @Title: findCarinfoList
	 * @Description: 获取配置信息
	 * @param params
	 *            查询条件
	 * @return
	 */
	@Override
	public List<TblCarinfo> findCarinfoList(Map<String, Object> params) {
		return carinfoMapper.find(params);
	}
	
	/**
	 * @Title: findCarCompanyList
	 * @Description: 获取汽车厂家信息
	 * @param params
	 *            查询条件
	 * @return
	 */
	public List<HashMap<String, Object>> findCarCompanyList(Map<String, Object> params){
		return carCompanyMapper.findCarCompanyList(params);
	}


}
