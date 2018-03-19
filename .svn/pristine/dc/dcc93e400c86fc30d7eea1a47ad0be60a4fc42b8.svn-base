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

import com.wanma.dubbox.dao.TblUserNormalMapper;
import com.wanma.dubbox.model.TblUserNormal;
import com.wanma.dubbox.service.TblUserNormalService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;


/**
 * 普通用户数据查询接口
 * @author lhy
 *
 */
@Service
public class TblUserNormalServiceImpl implements TblUserNormalService{
	@Autowired
	private TblUserNormalMapper tblUserNormalMapper;

	@CacheDelete(clazz=TblUserNormal.class,key="#model.id")
	@Override
	public int delete(TblUserNormal model) {
		return tblUserNormalMapper.delete(model);
	}

	@CachePut(clazz = TblUserNormal.class, key = "#model.id", value = "#model")
	@Override
	public int insert(TblUserNormal model) {
		return tblUserNormalMapper.insert(model);
	}

	@CacheGet(clazz = TblUserNormal.class, key = "#model.id")
	@Override
	public TblUserNormal selectOne(TblUserNormal model) {
		return tblUserNormalMapper.selectOne(model);
	}

	@CacheDelete(clazz=TblUserNormal.class,key="#model.id")
	@Override
	public int update(TblUserNormal model) {
		return tblUserNormalMapper.update(model);
	}

	@Override
	public int getCount(TblUserNormal model) {
		return tblUserNormalMapper.getCount(model);
	}

	@Override
	public List<TblUserNormal> getList(TblUserNormal model) {
		return tblUserNormalMapper.getList(model);
	}
	
	
	
}
