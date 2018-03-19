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

import com.wanma.dubbox.dao.TblUserBusinessMapper;
import com.wanma.dubbox.model.TblUserBusiness;
import com.wanma.dubbox.service.TblUserBusinessService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;


/**
 * 普通用户数据查询接口
 * @author lhy
 *
 */
@Service
public class TblUserBusinessServiceImpl implements TblUserBusinessService{
	@Autowired
	private TblUserBusinessMapper TblUserBusinessMapper;

	@CacheDelete(clazz=TblUserBusiness.class,key="#model.id")
	@Override
	public int delete(TblUserBusiness model) {
		return TblUserBusinessMapper.delete(model);
	}

	@CachePut(clazz = TblUserBusiness.class, key = "#model.id", value = "#model")
	@Override
	public int insert(TblUserBusiness model) {
		return TblUserBusinessMapper.insert(model);
	}

	@CacheGet(clazz = TblUserBusiness.class, key = "#model.id")
	@Override
	public TblUserBusiness selectOne(TblUserBusiness model) {
		return TblUserBusinessMapper.selectOne(model);
	}

	@CacheDelete(clazz=TblUserBusiness.class,key="#model.id")
	@Override
	public int update(TblUserBusiness model) {
		return TblUserBusinessMapper.update(model);
	}

	@Override
	public int getCount(TblUserBusiness model) {
		return TblUserBusinessMapper.getCount(model);
	}

	@Override
	public List<TblUserBusiness> getList(TblUserBusiness model) {
		return TblUserBusinessMapper.getList(model);
	}
	
	
	
}
