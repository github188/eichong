package com.wanma.app.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.dao.AppPublishEpMapper;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblPublishEp;

@Service
public class AppPublishEpServiceImpl {
	
	/**
	 * 添加分享
	 * @return
	 */
	public void addShareEp(TblPublishEp tblPublishEp){
		appPublishEpMapper.addPublishEp(tblPublishEp);
		
		if (ObjectUtil.isNotEmpty(tblPublishEp.getAllMultiFile())){
			MultipartFileUtil.saveAllMulti(tblPublishEp,
					WanmaConstants.MULTI_TYPE_ELECTRICT_PUBLISH_IMG,
					tblPublishEp.getId() + "");
		}
	}
	
	@Autowired
	AppPublishEpMapper appPublishEpMapper;
}
