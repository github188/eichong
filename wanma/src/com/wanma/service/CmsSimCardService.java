package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblSimCard;


public interface CmsSimCardService {

	public List<Map<String, Object>> getSimCardList(TblSimCard simCard);

	public long getSimCardListCount(TblSimCard simCard);

	public int findSimCardCount(String elpiElectricpilecode);

	public int findSimOperatorCount(String elpiElectricpilecode);

	public void insertSimCard(TblSimCard simCard);

	public void insertSimOperator(TblSimCard simCard);

	public void updateSimCard(TblSimCard simCard);



}
