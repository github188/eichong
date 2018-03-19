/** 
 * FileName AreaMapper.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblArea;


/**
 * FileName AreaMapper.java
 * 
 * Version 1.0
 * 
 * 区县表操作用DAO接口Mapper
 */
public interface TblAreaMapper {

	List<TblArea> getList(TblArea model);
	
}
