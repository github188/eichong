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

import com.wanma.dubbox.dao.TblUserAdminMapper;
import com.wanma.dubbox.model.TblUserAdmin;
import com.wanma.dubbox.service.TblUserAdminService;
import com.wanma.redis.CacheDelete;
import com.wanma.redis.CacheGet;
import com.wanma.redis.CachePut;


/**
 * 普通用户数据查询接口
 * @author lhy
 *
 */
@Service
public class TblUserAdminServiceImpl implements TblUserAdminService{
	@Autowired
	private TblUserAdminMapper tblUserAdminMapper;

	@CachePut(clazz = TblUserAdmin.class, key = "#model.id", value = "#model")
	@Override
	public int insert(TblUserAdmin model) {
		return tblUserAdminMapper.insert(model);
	}

	@CacheGet(clazz = TblUserAdmin.class, key = "#model.id")
	@Override
	public TblUserAdmin selectOne(TblUserAdmin model) {
		return tblUserAdminMapper.selectOne(model);
	}

	@CacheDelete(clazz=TblUserAdmin.class,key="#model.id")
	@Override
	public int update(TblUserAdmin model) {
		return tblUserAdminMapper.update(model);
	}

	@Override
	public int getCount(TblUserAdmin model) {
		return tblUserAdminMapper.getCount(model);
	}

	@Override
	public List<TblUserAdmin> getList(TblUserAdmin model) {
		return tblUserAdminMapper.getList(model);
	}
	
	
}
