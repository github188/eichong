package com.wanma.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsEquipmentrepairMapper;
import com.wanma.model.TblEquipmentrepair;
import com.wanma.service.CmsEquipmentrepairService;

@Service
public class CmsEquipmentrepairServiceImpl implements CmsEquipmentrepairService{
	
	/** 调用Dao处理 */
	@Autowired
	private CmsEquipmentrepairMapper tblEquipmentrepairDao;

	@Override
	public List<TblEquipmentrepair> getEquipList() {
		// 设备报修表一览
		List<TblEquipmentrepair> listEquip;
				
		//取得设备报修表一览
		listEquip = tblEquipmentrepairDao.getEquipList();
				
		//返回设备报修表一览
		return listEquip;
	}

	@Override
	public long searchEquipCount(TblEquipmentrepair tblEquipmentrepair) {
		// 设备报修个数
		long dataCount;

		// 取得设备报修个数
		dataCount = tblEquipmentrepairDao.searchEquipCount(tblEquipmentrepair);

		// 返回设备报修个数
		return dataCount;
	}

	@Override
	public List<TblEquipmentrepair> searchEquipList(
			TblEquipmentrepair tblEquipmentrepair) {
		
		// 设备报修表一览
		List<TblEquipmentrepair> listEquip;
								
		//取得设备报修表一览
		listEquip = tblEquipmentrepairDao.searchEquipList(tblEquipmentrepair);
								
		//返回设备报修表一览
		return listEquip;
	}

	@SystemControllerLog(description = "添加设备报修")
	@Override
	public void addEquip(TblEquipmentrepair tblEquipmentrepair) {
		tblEquipmentrepairDao.insertEquip(tblEquipmentrepair);
	}
	
	/**
	 * 更新设备报修状态
	 *
	 * @return
	 * @throws 无
	 */
	@SystemControllerLog(description = "更新设备报修状态")
	@Override
	public int updateEquipStage(TblEquipmentrepair record) {
		return tblEquipmentrepairDao.updateEquipStage(record);
	}


}
