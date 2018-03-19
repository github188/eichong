package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsCarmakerMapper;
import com.wanma.model.TblCarmaker;
import com.wanma.service.CmsCarmakerService;

@Service
public class CmsCarmakerServiceImpl implements CmsCarmakerService {
	@Autowired
	CmsCarmakerMapper cmsCarmakerMapper;
	
	@Autowired
	TblElectricpileMapper tblElectricpileMapper;

	@Override
	public List<TblCarmaker> getAll(TblCarmaker carmaker) {
		return cmsCarmakerMapper.getAll(carmaker);
	}

	@Override
	public int getCount(TblCarmaker carmaker) {
		return cmsCarmakerMapper.getCount(carmaker);
	}
	
	@Override
	public TblCarmaker getOne(TblCarmaker carmaker) {
		return cmsCarmakerMapper.getOne(carmaker);
	}
	
	@SystemControllerLog(description = "新增制造厂商")
	@Override
	public int insert(TblCarmaker tblCarMaker) {
		return cmsCarmakerMapper.insert(tblCarMaker);
	}

	@SystemControllerLog(description = "更新制造厂商")
	@Override
	public int update(TblCarmaker tblCarMaker) {
		return cmsCarmakerMapper.update(tblCarMaker);
	}

	@SystemControllerLog(description = "删除制造厂商")
	@Override
	public int delete(Integer pkCarmaker) {
		return cmsCarmakerMapper.delete(pkCarmaker);
	}

	@SystemControllerLog(description = "批量删除制造厂商")
	@Override
	public int deleteBatch(String pkCarmakers) {
		return cmsCarmakerMapper.deleteBatch(pkCarmakers);
	}

	@Override
	public List<TblCarmaker> getByProperty(TblCarmaker carmaker) {
		return  cmsCarmakerMapper.getByProperty(carmaker);
	}

	@Override
	public boolean isBondWithElectricPile(Integer pkCarmaker){
		List list = tblElectricpileMapper.getElectricPileByMaker(pkCarmaker);
		return list!= null && list.size() > 0;
	}

}
