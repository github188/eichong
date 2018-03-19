package com.wanma.ims.service;

import com.wanma.ims.common.domain.PartnerDO;

import java.util.List;

/**
 * 第三方接口用户身份管理
 */
public interface PartnerService {


	public List<PartnerDO> getPartnerList(PartnerDO partner);

	public long getPartnerListCount(PartnerDO partner);

	public int addPartner(PartnerDO partner);

	public void updatePartnerKeyById(PartnerDO partner);

	public PartnerDO selectPartnerNameById(int partnerId);

	public int checkPartnerName(String partnerKey);

	public void deletePartnerById(String partnerId);
}
