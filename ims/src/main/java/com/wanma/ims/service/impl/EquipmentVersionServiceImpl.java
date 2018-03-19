package com.wanma.ims.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.ims.common.domain.EquipmentVersionDO;
import com.wanma.ims.mapper.EquipmentVersionMapper;
import com.wanma.ims.service.EquipmentVersionService;
import org.springframework.transaction.annotation.Transactional;

@Service("equipmentVersionService")
public class EquipmentVersionServiceImpl implements EquipmentVersionService{
	@Autowired
	private EquipmentVersionMapper equipmentVersionMapper;
	@Override
	public EquipmentVersionDO getBomById(String pkBomListId) {
		return equipmentVersionMapper.getBomById(pkBomListId);
	}

	@Override
	@Transactional
	public void deleteByProductID(String evProductID) {
		equipmentVersionMapper.deleteByProductID(evProductID);
	}

	@Override
	@Transactional
	public void insertEpVersion(EquipmentVersionDO epVersion) {
		equipmentVersionMapper.insertEpVersion(epVersion);
	}

	
	
	
}
