/** 
 * FileName GroupDetailServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.dao.GroupDetailMapper;
import com.bluemobi.product.model.CodeDetail;
import com.bluemobi.product.service.GroupDetailService;

/**
 * FileName GroupDetailServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 共有Code相关业务处理
 */
@Service
public class GroupDetailServiceImpl implements GroupDetailService {

	@Autowired
	private GroupDetailMapper groupDetailMapper;

	/**
	 * 根据共有Code组ID取得详细一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param codeGroupId
	 *            共有Code组ID
	 * @return List<CodeDetail> 详细一览
	 * @throws 无
	 */
	public List<CodeDetail> getCodeDetailList(String codeGroupId) {
		// 调用Mapper取得共有Code详细一览并返回
		return groupDetailMapper.getCodeDetailList(codeGroupId);
	}

	/**
	 * 根据共有Code组ID和详细ID取得名称
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param codeGroupId
	 *            共有Code组ID
	 * @param codeId
	 *            共有Code详细ID
	 * @return String code名称
	 * @throws 无
	 */
	public String getCodeDetailName(String codeGroupId, String codeId) {
		// 调用Mapper取得共有Code名称并返回
		return groupDetailMapper.getCodeDetailName(codeGroupId, codeId);

	}
}
