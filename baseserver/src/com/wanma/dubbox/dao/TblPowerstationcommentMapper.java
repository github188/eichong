package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblPowerstationcomment;

public interface TblPowerstationcommentMapper {

    int insert(TblPowerstationcomment model);

    int update(TblPowerstationcomment model);

	List<TblPowerstationcomment> getList(TblPowerstationcomment model);

	int delete(TblPowerstationcomment model);

	int getCount(TblPowerstationcomment model);
}