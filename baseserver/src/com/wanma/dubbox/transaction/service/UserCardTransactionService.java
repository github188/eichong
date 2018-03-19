package com.wanma.dubbox.transaction.service;

import com.wanma.dubbox.model.TblCardapplicationform;
import com.wanma.dubbox.model.TblUserCard;

public interface UserCardTransactionService {

	void bind(TblUserCard cModel, TblCardapplicationform cafModel)
			throws Exception;
}
