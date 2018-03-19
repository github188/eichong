package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsApplyBuildElecPileMapper;
import com.wanma.model.TblApplyBuildElecPile;
import com.wanma.service.CmsApplyBuildElecPileService;
@Service
public class CmsApplyBuildElecPileServiceImpl implements CmsApplyBuildElecPileService {
	
	
			@Autowired	
			CmsApplyBuildElecPileMapper cmsApplyElecPileMapper;
			

		
			@Override
			public List<TblApplyBuildElecPile> getCmsApplyBuildElecPileList(
					TblApplyBuildElecPile tblApplyElecPile) {
				  
				return cmsApplyElecPileMapper.getCmsApplyBuildElecPileList(tblApplyElecPile);
			}
		
			@Override
			public long getCmsApplyBuildElecPileCount(TblApplyBuildElecPile tblApplyElecPile) {
				  
				return cmsApplyElecPileMapper.getCmsApplyBuildElecPileCount(tblApplyElecPile);
			}

			@Override
			public void updateApplyState(
					TblApplyBuildElecPile tblApplyBuildElecPile) {
				cmsApplyElecPileMapper.updateApplyState(tblApplyBuildElecPile);
			}


}
