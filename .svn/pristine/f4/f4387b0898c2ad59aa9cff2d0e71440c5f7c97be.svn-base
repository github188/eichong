package com.wanma.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblCarmaker;
import com.wanma.web.dao.WebCarmakerMapper;
import com.wanma.web.service.WebCarmakerService;

@Service
public class WebCarmakerServiceImpl implements WebCarmakerService{
	@Autowired
	WebCarmakerMapper webCarmakerMapper;
	 
	@Override
	public List<TblCarmaker> getAll(Map<String, String> param) {
		return webCarmakerMapper.getAll(param);
	}

	@Override
	public TblCarmaker getOne(Map<String, String> param) {
		return webCarmakerMapper.getOne(param);
	}

	@Override
	public int insert(TblCarmaker tblCarMaker) {
		return webCarmakerMapper.insert(tblCarMaker);
	}

	@Override
	public int update(TblCarmaker tblCarMaker) {
		return webCarmakerMapper.update(tblCarMaker);
	}

	@Override
	public int delete(Integer pkCarmaker) {
		return webCarmakerMapper.delete(pkCarmaker);
	}

}
