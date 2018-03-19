package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.SimCardBO;
import com.wanma.ims.common.dto.BaseResultDTO;


public interface SimCardService {

	public List<SimCardBO> getSimCardList(SimCardBO simCard);

	public long getSimCardListCount(SimCardBO simCard);

	public int findSimCardCount(String elpiElectricpilecode);

	public int findSimOperatorCount(String elpiElectricpilecode);

	public void insertSimCard(SimCardBO simCard);

	public void insertSimOperator(SimCardBO simCard);

	public void updateSimCard(SimCardBO simCard);
	
	public BaseResultDTO modifySimCard(SimCardBO simCard, UserDO loginUser) throws Exception;
}
