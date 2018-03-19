package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.dao.MultipartFileDao;
import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsApplyPartnerMapper;
import com.wanma.model.TblApplyPartner;
import com.wanma.model.TblPublishEp;
import com.wanma.service.CmsApplyPartnerService;
@Service
public class CmsApplyPartnerServiceImpl implements CmsApplyPartnerService {
	
	
			@Autowired	
			CmsApplyPartnerMapper cmsApplyPartnerMapper;
			
			@Override
			public void insertCmsApplyPartner(TblApplyPartner tblApplyPartner) {
				cmsApplyPartnerMapper.insertCmsApplyPartner(tblApplyPartner);
		
			}
		
			@Override
			public List<TblApplyPartner> getCmsApplyPartnerList(
					TblApplyPartner tblApplyPartner) {
				  
				return cmsApplyPartnerMapper.getCmsApplyPartnerList(tblApplyPartner);
			}
		
			@Override
			public long getCmsApplyPartnerCount(TblApplyPartner tblApplyPartner) {
				  
				return cmsApplyPartnerMapper.getCmsApplyPartnerCount(tblApplyPartner);
			}
		
			@Override
			public TblApplyPartner getCmsApplyPartnerById(
					Integer pkApplyPartner) {				  
				return cmsApplyPartnerMapper.getCmsApplyPartnerById(pkApplyPartner);
			}
		
			@Override
			public void updateApplyPartnerState(Map<String, Object> params) {
				cmsApplyPartnerMapper.updateApplyPartnerState(params);
					
			}
		
			@Override
			public void updateApplyPartnerReason(Map<String, Object> params) {
				  
				cmsApplyPartnerMapper.updateApplyPartnerReason(params);
			}

			@Override
			public void deleteApplyPartnerById(Integer pkApplyPartner) {
				cmsApplyPartnerMapper.deleteApplyPartnerById(pkApplyPartner);				
			}

			@Override
			public void deleteApplyPartnerByIdS(String pkApplyPartners) {
				String[] applyPartnerIds = pkApplyPartners.split(",");
				for (int i = 0; i < applyPartnerIds.length; i++) {
					Integer pkApplyPartner = Integer.parseInt(applyPartnerIds[i]);
					cmsApplyPartnerMapper.deleteApplyPartnerById(pkApplyPartner);
				}
				
			}
			@Override
			public List<String> getImages(TblApplyPartner tblApplyPartner) {
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				return multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_PARTNER_APPLY_IMG, String.valueOf(tblApplyPartner.getPkApplyPartner()));
			}
}
