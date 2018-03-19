package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.bo.SimCardBO;


public interface SimCardMapper {

	public long getSimCardListCount(SimCardBO simCard);

	public List<SimCardBO> getSimCardList(SimCardBO simCard);

	public int findSimCardCount(String elpiElectricpilecode);

	public int findSimOperatorCount(String elpiElectricpilecode);

	public void insertSimCard(SimCardBO simCard);

	public void insertSimOperator(SimCardBO simCard);

	public void updateSimCard(SimCardBO simCard);
}
