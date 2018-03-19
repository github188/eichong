package com.wanma.dubbox.transaction.service;


import com.wanma.dubbox.model.TblConcentrator;
import com.wanma.dubbox.model.TblElectricPile;

public interface ConcentratorTransactionService {

	void insertConcentrator(TblConcentrator cModel);

	void updateConcentrator(TblConcentrator cModel);

	void deleteConcentrator(TblConcentrator cModel);
}
