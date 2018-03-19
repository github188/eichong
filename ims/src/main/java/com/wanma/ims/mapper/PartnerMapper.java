package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.PartnerDO;

import java.util.List;


public interface PartnerMapper {

    public List<PartnerDO> getPartnerList(PartnerDO partner);

    public long getPartnerListCount(PartnerDO partner);

    public int addPartner(PartnerDO partner);

    public void updatePartnerKeyById(PartnerDO partner);

    public PartnerDO selectPartnerNameById(int partnerId);

    public int checkPartnerName(String partnerName);

    public void deletePartnerById(String partnerId);
}
