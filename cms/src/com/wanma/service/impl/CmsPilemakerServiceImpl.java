package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsPilemakerMapper;
import com.wanma.dao.TblElectricpileMapper;
import com.wanma.model.TblPilemaker;
import com.wanma.service.CmsPilemakerService;

@Service
public class CmsPilemakerServiceImpl implements CmsPilemakerService {
	@Autowired
	CmsPilemakerMapper cmsPilemakerMapper;
	
	@Autowired
	TblElectricpileMapper tblElectricpileMapper;

	@Override
	public List<TblPilemaker> getAll(TblPilemaker carmaker) {
		return cmsPilemakerMapper.getAll(carmaker);
	}

	@Override
	public int getCount(TblPilemaker carmaker) {
		return cmsPilemakerMapper.getCount(carmaker);
	}
	
	@Override
	public TblPilemaker getOne(TblPilemaker carmaker) {
		return cmsPilemakerMapper.getOne(carmaker);
	}
	
	@Override
	public int insert(TblPilemaker tblCarMaker) {
		return cmsPilemakerMapper.insert(tblCarMaker);
	}

	@Override
	public int update(TblPilemaker tblCarMaker) {
		return cmsPilemakerMapper.update(tblCarMaker);
	}

	@Override
	public int delete(Integer pkCarmaker) {
		return cmsPilemakerMapper.delete(pkCarmaker);
	}

	@Override
	public int deleteBatch(String pkCarmakers) {
		return cmsPilemakerMapper.deleteBatch(pkCarmakers);
	}

	@Override
	public List<TblPilemaker> getByProperty(TblPilemaker carmaker) {
		return  cmsPilemakerMapper.getByProperty(carmaker);
	}

	@Override
	public boolean isBondWithElectricPile(Integer pkCarmaker){
		List list = tblElectricpileMapper.getElectricPileByMaker(pkCarmaker);
		return list!= null && list.size() > 0;
	}

}
