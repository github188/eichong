/**     
 * @Title:  TblUserServiceImpl.java   
 * @Package com.wanma.service.impl   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年7月13日 下午4:05:44   
 * @version V3.0     
 */  
package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblHomepageMapper;
import com.wanma.dubbox.model.TblHomepage;
import com.wanma.dubbox.service.TblHomepageService;


/**
 * 普通用户数据查询接口
 * @author lhy
 *
 */
@Service
public class TblHomepageServiceImpl implements TblHomepageService{
	@Autowired
	private TblHomepageMapper TblHomepageMapper;

	@Override
	public int insert(TblHomepage record) {
		return TblHomepageMapper.insert(record);
	}

	@Override
	public TblHomepage selectOne(TblHomepage record) {
		return TblHomepageMapper.selectOne(record);
	}

	@Override
	public int update(TblHomepage record) {
		return TblHomepageMapper.update(record);
	}

	@Override
	public int getCount(TblHomepage record) {
		return TblHomepageMapper.getCount(record);
	}

	@Override
	public List<TblHomepage> getList(TblHomepage record) {
		return TblHomepageMapper.getList(record);
	}
	
	
	
}
