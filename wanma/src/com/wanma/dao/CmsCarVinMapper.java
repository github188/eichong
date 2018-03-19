package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblCarVin;

public interface CmsCarVinMapper {
  

	long getCarvinCount(TblCarVin tblCarVin);

	List<TblCarVin> getCarvinList(TblCarVin tblCarVin);

	void deteleCarvin(String id);

	void addCarVin(TblCarVin pile);

	List<TblCarVin> getCarVinList_export(TblCarVin tblCarVin);
	long checkVinCode(String vincode);
	
	void updateRepeatVinCode(TblCarVin tblCarVin);

	TblCarVin findOne(TblCarVin tblCarVin);
}