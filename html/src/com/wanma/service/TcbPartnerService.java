package com.wanma.service;

import com.wanma.model.TblPartner;

public interface TcbPartnerService {
	public String getKey(int comNum);

	TblPartner PartnerInfo(String OperatorID);
}
