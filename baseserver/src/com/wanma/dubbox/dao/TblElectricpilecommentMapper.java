package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblElectricpilecomment;

public interface TblElectricpilecommentMapper {

    int insert(TblElectricpilecomment model);

    int update(TblElectricpilecomment model);

	List<TblElectricpilecomment> getList(TblElectricpilecomment model);

	int delete(TblElectricpilecomment model);

	int getCount(TblElectricpilecomment model);
}