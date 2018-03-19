package com.wanma.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.PartnerDO;
import com.wanma.ims.mapper.PartnerMapper;
import com.wanma.ims.service.PartnerService;


@Service
public class PartnerServiceImpl implements PartnerService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public List<PartnerDO> getPartnerList(PartnerDO partner) {

        return partnerMapper.getPartnerList(partner);
    }

    @Override
    public long getPartnerListCount(PartnerDO partner) {

        return partnerMapper.getPartnerListCount(partner);
    }

    @Override
    @Transactional
    public int addPartner(PartnerDO partner) {
        return partnerMapper.addPartner(partner);

    }

    @Override
    @Transactional
    public void updatePartnerKeyById(PartnerDO partner) {
        partnerMapper.updatePartnerKeyById(partner);
    }

    @Override
    public PartnerDO selectPartnerNameById(int partnerId) {
        return partnerMapper.selectPartnerNameById(partnerId);
    }

    @Override
    public int checkPartnerName(String partnerKey) {
        return partnerMapper.checkPartnerName(partnerKey);
    }

    @Override
    @Transactional
    public void deletePartnerById(String partnerId) {
        partnerMapper.deletePartnerById(partnerId);
    }
}