package com.wanma.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblApplyPartner;
import com.wanma.web.dao.WebApplyPartnerMapper;
import com.wanma.web.service.WebApplyPartnerService;
@Service
public class WebApplyPartnerServiceImpl implements WebApplyPartnerService {
	
	
			@Autowired	
			WebApplyPartnerMapper webApplyPartnerMapper;
			
			@Override
			public void insertWebApplyPartner(TblApplyPartner tblApplyPartner) {
				webApplyPartnerMapper.insertWebApplyPartner(tblApplyPartner);
				if (ObjectUtil.isNotEmpty(tblApplyPartner.getAllMultiFile())){
					MultipartFileUtil.saveAllMulti(tblApplyPartner,
							WanmaConstants.MULTI_TYPE_PARTNER_APPLY_IMG,
							tblApplyPartner.getPkApplyPartner() + "");
				}		
			}	
			
			@Override
			public void insertCompanyApplyPartner(TblApplyPartner tblApplyPartner) {
				webApplyPartnerMapper.insertWebApplyPartner(tblApplyPartner);		
			}		


}
