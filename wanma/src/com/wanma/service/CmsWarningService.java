package com.wanma.service;

import java.util.List;

import com.wanma.model.TblThrWarning;


public interface CmsWarningService {

	long getWarningCount(TblThrWarning tblWarning);

	List<TblThrWarning> getWarningList(TblThrWarning tblWarning);

	List<TblThrWarning> getCompanyType();

	void insertWarning(TblThrWarning tblWarning);

	//long getAccountNumber(TblThrWarning tblWarning);

	long getWarningCout(TblThrWarning tblWarning);

	void updateWarning(TblThrWarning tblWarning);

	void updateWarningFlag(String id);
}
