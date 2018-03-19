package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblCompany;


/**
 * 数据访问接口
 *
 */
public interface TblCompanyMapper {    

	int delete(TblCompany model);

    int insert(TblCompany model);

    TblCompany selectOne(TblCompany model);

    int update(TblCompany model);

	List<TblCompany> getList(TblCompany model);

	int getCount(TblCompany model);
	
}


