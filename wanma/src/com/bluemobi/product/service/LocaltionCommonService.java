/** 
 * FileName PeaceLiveCommonService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/11/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.bluemobi.product.service;

import java.util.Map;

import org.springframework.ui.Model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * FileName PeaceLiveCommonService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/11/9
 * 
 * 乐居公共业务处理接口
 */
public interface LocaltionCommonService {
	
	/**
	 * 设置省份、城市、区县、社区等信息到画面显示对象中
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param basicModel
	 *            基础对象
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	//public void setAllCommonDataToModel(BasicModel basicModel, Model model);
	
	/**
	 * 设置省份、城市、区县、社区等信息到画面显示对象中
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param basicModel
	 *            基础对象
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	//public void setAllCommonDataToModelMap(Map<String, Object> basicModel, Model model);

	/**
	 * 设置省份、城市、区县等信息到画面显示对象中
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param basicModel
	 *            基础对象
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	//public void setAllAreaDataToModel(BasicModel basicModel, Model model);
}
