package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblCarCompany;


public interface TblCarCompanyService {

    int delete(TblCarCompany model);

    int insert(TblCarCompany model);

    TblCarCompany selectOne(TblCarCompany model);

    int update(TblCarCompany model);
    
    int getCount(TblCarCompany model);
    
    List<TblCarCompany> getList(TblCarCompany model);
}