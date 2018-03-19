package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TcbPartnerMapper;
import com.wanma.model.Cache;
import com.wanma.model.TblPartner;
import com.wanma.service.TblPowerstationService;
import com.wanma.service.TcbPartnerService;

@Service
public class TcbPartnerServiceImpl implements TcbPartnerService {
	@Autowired
	private TcbPartnerMapper partnerMapper;

	@Autowired
	private TblPowerstationService psService;

	@Override
	public String getKey(int comNum) {
		return partnerMapper.getKey(comNum);
	}

	@Override
	public TblPartner PartnerInfo(String OperatorID) {

		TblPartner partner = new TblPartner();
		Cache cache = CacheServiceImpl.getCacheInfo(OperatorID);
		if (null == cache) {
			partner = psService.getPartnerList(OperatorID);
			if(partner.getSecret()!=null&&!"".equals(partner.getSecret())){
			partner.setAesSecret(partner.getSecret().split("\\|")[0]);
			partner.setAesIv(partner.getSecret().split("\\|")[1]);
			partner.setSigSecret(partner.getSecret().split("\\|")[2]);
			}
			partner.setWmTokenSecret(partner.getWmTokenSecret());
			partner.setCpyId(partner.getCpyId());
			partner.setValid(partner.getValid());
			partner.setThirdTokenUrl(partner.getThirdTokenUrl());
			partner.setPushOrderCheckUrl(partner.getPushOrderCheckUrl());
			partner.setTimeInterval(partner.getTimeInterval());
			cache = new Cache();
			cache.setKey(OperatorID);
			cache.setValue(partner);
			CacheServiceImpl.putCache(OperatorID, cache);

		} else {
			partner = (TblPartner) cache.getValue();
		}

		return partner;
	}

}
