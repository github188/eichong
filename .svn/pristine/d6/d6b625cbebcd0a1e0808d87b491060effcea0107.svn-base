package com.wanma.dubbox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblElectricpileMapper;
import com.wanma.dubbox.model.TblElectricpile;
import com.wanma.dubbox.service.ElecttricpileService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class ElecttricpileServiceImpl implements ElecttricpileService {

	@Autowired
	TblElectricpileMapper tblElectricpileMapper;
	/**
	 * 通过电桩ID获取电桩数据
	 */
	@Override
	public TblElectricpile getElectricpileById(String eId) {
		TblElectricpile tblElectricpile = tblElectricpileMapper.get(eId);
		return tblElectricpile;
	}
}