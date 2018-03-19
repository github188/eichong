package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblAreaMapper;
import com.wanma.dubbox.model.TblArea;
import com.wanma.dubbox.service.TblAreaService;
/**
 * 区县数据查询接口
 * @author lhy
 *
 */
@Service
public class TblAreaServiceImpl implements TblAreaService {

	@Autowired
	TblAreaMapper tblAreaMapper;
	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblArea> getList(TblArea model) {
		return tblAreaMapper.getList(model);
	}
	
}