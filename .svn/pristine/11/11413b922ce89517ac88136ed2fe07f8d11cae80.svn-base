package com.wanma.service.impl;

import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsSimCardMapper;
import com.wanma.model.TblSimCard;
import com.wanma.service.CmsSimCardService;

@Service
public class CmsSimCardServiceImpl implements CmsSimCardService{
	
	@Autowired
	private CmsSimCardMapper cmsSimCardMapper;

	@Override
	public long getSimCardListCount(TblSimCard simCard) {
		return cmsSimCardMapper.getSimCardListCount(simCard);
	}

	@Override
	public List<Map<String, Object>> getSimCardList(TblSimCard simCard) {
		return cmsSimCardMapper.getSimCardList(simCard);
	}

	@Override
	public int findSimCardCount(String elpiElectricpilecode) {
		return cmsSimCardMapper.findSimCardCount(elpiElectricpilecode);
	}

	@Override
	public int findSimOperatorCount(String elpiElectricpilecode) {
		return cmsSimCardMapper.findSimOperatorCount(elpiElectricpilecode);
	}

	@Override
	public void insertSimCard(TblSimCard simCard) {
		cmsSimCardMapper.insertSimCard(simCard);
	}

	@Override
	public void insertSimOperator(TblSimCard simCard) {
		cmsSimCardMapper.insertSimOperator(simCard);
	}

	@Override
	public void updateSimCard(TblSimCard simCard) {
		cmsSimCardMapper.updateSimCard(simCard);
	}


	

}
