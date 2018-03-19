package com.wanma.service;

import com.wanma.model.cdzts.PowerstationPush;

public interface CmsPowerstationService {

	int getPowerCount(PowerstationPush model);

	PowerstationPush getPowerById(String powerStationId);

}
