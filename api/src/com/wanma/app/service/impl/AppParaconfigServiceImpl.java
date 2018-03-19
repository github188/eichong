package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppCarinfoMapper;
import com.wanma.app.dao.AppParaconfigMapper;
import com.wanma.app.service.AppParaconfigService;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblParaconfig;

/**
 * @Description: 其他配置参数业务处理类
 * @author songjf
 * @createTime：2015-3-13 下午05:04:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppParaconfigServiceImpl implements AppParaconfigService {

	/** 其他配置信息业务操作DAO */
	@Autowired
	private AppParaconfigMapper paraconfigMapper;

	@Autowired
	private AppCarinfoMapper carinfoMapper;

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

}
