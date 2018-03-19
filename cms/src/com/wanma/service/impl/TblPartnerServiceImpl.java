package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblPartnerMapper;
import com.wanma.model.TblPartner;
import com.wanma.service.TblPartnerService;


@Service
public class TblPartnerServiceImpl implements TblPartnerService {
@Autowired
private TblPartnerMapper tblPartnerMapper;

	@Override
	public List<TblPartner> getPartnerList() {
		
		return tblPartnerMapper.getPartnerList();
	}

	@Override
	public long getPartnerListCount() {
		
		return tblPartnerMapper.getPartnerListCount();
	}

	@Override
	public int addPartner(TblPartner partner) {
		return tblPartnerMapper.addPartner(partner);
		
	}

	@Override
	public void updatePartnerKeyById(TblPartner partner) {
		tblPartnerMapper.updatePartnerKeyById(partner);
	}

	@Override
	public TblPartner selectPartnerNameById(int partnerId) {
		
		return tblPartnerMapper.selectPartnerNameById(partnerId);
	}

	@Override
	public int checkPartnerName(String partnerKey) {
		
		return tblPartnerMapper.checkPartnerName(partnerKey);
	}

	@Override
	public void deletePartnerById(String partnerId) {
		tblPartnerMapper.deletePartnerById(partnerId);
	}
	
	
}
