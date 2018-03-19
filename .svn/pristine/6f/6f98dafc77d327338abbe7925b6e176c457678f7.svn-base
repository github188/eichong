package com.wanma.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblRecruit;
import com.wanma.web.dao.WebTblRecruitMapper;
import com.wanma.web.service.WebTblRecruitService;

@Service
public class WebTblRecruitServiceImpl implements WebTblRecruitService {
	
	@Autowired
	WebTblRecruitMapper tblRecruitMapper;

	@Override
	public List<TblRecruit> findSome() {
		// TODO Auto-generated method stub
		return tblRecruitMapper.findSome();
	}

//	@Override
//	public List<WebList> findByType(String releType) {
//	/*	Map map=new HashMap();
//		map.put("releType", releType);*/
//		// TODO Auto-generated method stub
//		return listMapper.findByType(releType);
//	}

	@Override
	public TblRecruit findByPk(int pkRecruit) {
		// TODO Auto-generated method stub
		return tblRecruitMapper.findByPk(pkRecruit);
	}

	@Override
	public List<TblRecruit> getAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tblRecruitMapper.getAll(params);
	}

	@Override
	public long countRecruit(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tblRecruitMapper.countRecruit(params);
	}

}
