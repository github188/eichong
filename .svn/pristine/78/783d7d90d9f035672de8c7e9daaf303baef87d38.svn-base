package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblMenuMapper;
import com.wanma.dubbox.model.TblMenu;
import com.wanma.dubbox.service.TblMenuService;
/**
 * 菜单数据查询接口
 * @author lhy
 *
 */
@Service
public class TblMenuServiceImpl implements TblMenuService {

	@Autowired
	TblMenuMapper TblMenuMapper;

	@Override
	public int delete(TblMenu record) throws Exception {
		if(record.getPkIds() == null)
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		return TblMenuMapper.delete(record);
	}

	@Override
	public int insert(TblMenu record) {
		return TblMenuMapper.insert(record);
	}

	@Override
	public TblMenu selectOne(TblMenu model) {
		return TblMenuMapper.selectOne(model);
	}

	@Override
	public int update(TblMenu record) {
		return TblMenuMapper.update(record);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblMenu> getList(TblMenu model) {
		return TblMenuMapper.getList(model);
	}

	@Override
	public int getCount(TblMenu model) {
		return TblMenuMapper.getCount(model);
	}
	
	
}