package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblElectricpilecomment;


public interface TblElectricpilecommentService {

    int delete(TblElectricpilecomment model);

    int insert(TblElectricpilecomment model);

    int update(TblElectricpilecomment model);
    
    int getCount(TblElectricpilecomment model);
    
    List<TblElectricpilecomment> getList(TblElectricpilecomment model);
}