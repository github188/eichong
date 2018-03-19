package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblConfigContentMapper;
import com.wanma.dubbox.model.TblConfigContent;
import com.wanma.dubbox.service.TblConfigContentService;

/**
 * 参数配置数据查询接口
 * @author lhy
 *
 */
@Service
public class TblConfigContentServiceImpl implements TblConfigContentService {

	@Autowired
	TblConfigContentMapper tblConfigContentMapper;

	@Override
	public int insert(TblConfigContent model) {
		return tblConfigContentMapper.insert(model);
	}

	@Override
	public int update(TblConfigContent model) {
		return tblConfigContentMapper.update(model);
	}

	@Override
	public int delete(TblConfigContent model) {
		
		return tblConfigContentMapper.delete(model);
	}

	@Override
	public TblConfigContent selectOne(TblConfigContent model) {
		return tblConfigContentMapper.selectOne(model);
	}

	@Override
	public int getCount(TblConfigContent model) {
		return tblConfigContentMapper.getCount(model);
	}

	@Override
	public List<TblConfigContent> getList(TblConfigContent model) {
		return tblConfigContentMapper.getList(model);
	}

}