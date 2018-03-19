package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblEquipmentrepairMapper;
import com.wanma.dubbox.model.TblEquipmentrepair;
import com.wanma.dubbox.service.TblEquipmentrepairService;
/**
 * 桩评论数据查询接口
 * @author lhy
 *
 */
@Service
public class TblEquipmentrepairServiceImpl implements TblEquipmentrepairService {

	@Autowired
	TblEquipmentrepairMapper tblEquipmentrepairMapper;

	@Override
	public int delete(TblEquipmentrepair model) {
		return tblEquipmentrepairMapper.delete(model);
	}

	@Override
	public int insert(TblEquipmentrepair model) {
		return tblEquipmentrepairMapper.insert(model);
	}

	@Override
	public int update(TblEquipmentrepair model) {
		return tblEquipmentrepairMapper.update(model);
	}

	@Override
	public List<TblEquipmentrepair> getList(TblEquipmentrepair model) {
		return tblEquipmentrepairMapper.getList(model);
	}

	@Override
	public int getCount(TblEquipmentrepair model) {
		return tblEquipmentrepairMapper.getCount(model);
	}

	
}