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

import com.wanma.dubbox.dao.TblUserMapper;
import com.wanma.dubbox.model.TblUser;
import com.wanma.dubbox.service.TblUserService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;



/**
 * 用户主数据查询接口
 * @author lhy
 *
 */
@Service
public class TblUserServiceImpl implements TblUserService{
	@Autowired
	private TblUserMapper tblUserMapper;

	@CacheDelete(clazz=TblUser.class,key="#model.id")
	@Override
	public int delete(TblUser model) {
		return tblUserMapper.delete(model);
	}

	@CachePut(clazz = TblUser.class, key = "#model.id", value = "#model")
	@Override
	public int insert(TblUser model) {
		return tblUserMapper.insert(model);
	}
	
	@CacheDelete(clazz=TblUser.class,key="#model.id")
	@Override
	public int update(TblUser model) {
		return tblUserMapper.update(model);
	}

	@CacheGet(clazz = TblUser.class, key = "#model.id")
	@Override
	public TblUser selectOne(TblUser model) {
		return tblUserMapper.selectOne(model);
	}

	@Override
	public List<TblUser> getList(TblUser model) {
		return tblUserMapper.getList(model);
	}

	@Override
	public int getCount(TblUser model) {
		return tblUserMapper.getCount(model);
	}


	
}
