package com.wanma.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblApplyElecPile;
import com.wanma.web.dao.WebApplyElecPileMapper;
import com.wanma.web.service.WebApplyElecPileService;
@Service
public class WebApplyElecPileServiceImpl implements WebApplyElecPileService {
	
	
			@Autowired	
			WebApplyElecPileMapper webApplyElecPileMapper;
			
			@Override
			public void insertWebApplyElecPile(TblApplyElecPile tblApplyElecPile) {
				webApplyElecPileMapper.insertWebApplyElecPile(tblApplyElecPile);
		
			}
		
			
}
