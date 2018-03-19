package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsApplyStationMapper;
import com.wanma.model.TblApplyStation;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsApplyStationService;
@Service
public class CmsApplyStationServiceImpl implements CmsApplyStationService {
	
	
			@Autowired	
			CmsApplyStationMapper cmsApplyStationMapper;
			
			@Override
			public void insertCmsApplyStation(TblApplyStation tblApplyStation) {
				cmsApplyStationMapper.insertCmsApplyStation(tblApplyStation);
		
			}
		
			@Override
			public List<TblApplyStation> getCmsApplyStationList(
					TblApplyStation tblApplyStation) {
				  
				return cmsApplyStationMapper.getCmsApplyStationList(tblApplyStation);
			}
		
			@Override
			public long getCmsApplyStationCount(TblApplyStation tblApplyStation) {
				  
				return cmsApplyStationMapper.getCmsApplyStationCount(tblApplyStation);
			}
		
			@Override
			public TblApplyStation getCmsApplyStationById(
					Integer pkApplyStation) {				  
				return cmsApplyStationMapper.getCmsApplyStationById(pkApplyStation);
			}
		
			@Override
			public void updateApplyStationState(Map<String, Object> params) {
				cmsApplyStationMapper.updateApplyStationState(params);
					
			}
		
			@Override
			public void updateApplyStationReason(Map<String, Object> params) {
				  
				cmsApplyStationMapper.updateApplyStationReason(params);
			}

			@Override
			public void deleteApplyStationById(Integer pkApplyStation) {
				cmsApplyStationMapper.deleteApplyStationById(pkApplyStation);				
			}

			@Override
			public void deleteApplyStationByIdS(String pkApplyStations) {
				String[] applyStationIds = pkApplyStations.split(",");
				for (int i = 0; i < applyStationIds.length; i++) {
					Integer pkApplyStation = Integer.parseInt(applyStationIds[i]);
					cmsApplyStationMapper.deleteApplyStationById(pkApplyStation);
				}
				
			}

}
