package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsApplyElecPileMapper;
import com.wanma.model.TblApplyElecPile;
import com.wanma.service.CmsApplyElecPileService;
@Service
public class CmsApplyElecPileServiceImpl implements CmsApplyElecPileService {
	
	
			@Autowired	
			CmsApplyElecPileMapper cmsApplyElecPileMapper;
			

		
			@Override
			public List<TblApplyElecPile> getCmsApplyElecPileList(
					TblApplyElecPile tblApplyElecPile) {
				  
				return cmsApplyElecPileMapper.getCmsApplyElecPileList(tblApplyElecPile);
			}
		
			@Override
			public long getCmsApplyElecPileCount(TblApplyElecPile tblApplyElecPile) {
				  
				return cmsApplyElecPileMapper.getCmsApplyElecPileCount(tblApplyElecPile);
			}
		
			@Override
			public TblApplyElecPile getCmsApplyElecPileById(
					Integer pkApplyElecPile) {				  
				return cmsApplyElecPileMapper.getCmsApplyElecPileById(pkApplyElecPile);
			}
		
			@Override
			public void updateApplyElecPileState(Map<String, Object> params) {
				cmsApplyElecPileMapper.updateApplyElecPileState(params);
					
			}
		
			@Override
			public void updateApplyElecPileReason(Map<String, Object> params) {
				  
				cmsApplyElecPileMapper.updateApplyElecPileReason(params);
			}

			@Override
			public void deleteApplyElecPileById(Integer pkApplyElecPile) {
				cmsApplyElecPileMapper.deleteApplyElecPileById(pkApplyElecPile);				
			}

			@Override
			public void deleteApplyElecPileByIdS(String pkApplyElecPiles) {
				String[] applyElecPileIds = pkApplyElecPiles.split(",");
				for (int i = 0; i < applyElecPileIds.length; i++) {
					Integer pkApplyElecPile = Integer.parseInt(applyElecPileIds[i]);
					cmsApplyElecPileMapper.deleteApplyElecPileById(pkApplyElecPile);
				}
				
			}


}
