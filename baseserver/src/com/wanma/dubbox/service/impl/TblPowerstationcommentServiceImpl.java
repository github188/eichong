package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblPowerstationcommentMapper;
import com.wanma.dubbox.model.TblPowerstationcomment;
import com.wanma.dubbox.service.TblPowerstationcommentService;
/**
 * 桩评论数据查询接口
 * @author lhy
 *
 */
@Service
public class TblPowerstationcommentServiceImpl implements TblPowerstationcommentService {

	@Autowired
	TblPowerstationcommentMapper TblPowerstationcommentMapper;

	@Override
	public int delete(TblPowerstationcomment model) {
		return TblPowerstationcommentMapper.delete(model);
	}

	@Override
	public int insert(TblPowerstationcomment model) {
		return TblPowerstationcommentMapper.insert(model);
	}

	@Override
	public int update(TblPowerstationcomment model) {
		return TblPowerstationcommentMapper.update(model);
	}

	@Override
	public List<TblPowerstationcomment> getList(TblPowerstationcomment model) {
		return TblPowerstationcommentMapper.getList(model);
	}

	@Override
	public int getCount(TblPowerstationcomment model) {
		return TblPowerstationcommentMapper.getCount(model);
	}

	
}