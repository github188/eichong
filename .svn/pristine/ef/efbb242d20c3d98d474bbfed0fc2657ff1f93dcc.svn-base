package com.wanma.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblEquipmentrepairMapper;
import com.wanma.app.service.TblEquipmentrepairService;
import com.wanma.model.TblEquipmentrepair;

/** 
* @ClassName: TblEquipmentrepairServiceImpl 
* @Description: 设备维修服务层接口实现类
* @author chenb
* @date 2015年3月15日 下午4:47:14  
*/
@Service
public class TblEquipmentrepairServiceImpl implements TblEquipmentrepairService {
	
	/** 设备维修业务操作DAO */
	@Autowired
	private TblEquipmentrepairMapper tblEquipmentrepairMapper;
	
	@Override
	public void addTblEquipmentrepair(TblEquipmentrepair tblEquipmentrepair) {
		tblEquipmentrepairMapper.insert(tblEquipmentrepair);	
	}

}
