/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.dao.TblUsershareelecMapper;
import com.wanma.app.service.TblUsershareelecService;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblUsershareelec;

/***
 *
 *   电桩查找地图模式，分享
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class UsercollectServiceImpl implements TblUsershareelecService {

	@Autowired
	TblUsershareelecMapper tblUsershareelecMapper;
	 
	@Override
	public void userShareElec(TblUsershareelec tblUsershareelec) {
		// TODO Auto-generated method stub
		tblUsershareelecMapper.insert(tblUsershareelec);
		if (ObjectUtil.isNotEmpty(tblUsershareelec.getAllMultiFile())){
			MultipartFileUtil.saveAllMulti(tblUsershareelec,
					WanmaConstants.MULTI_TYPE_PRODUCT_COMMENT_IMAGE,
					tblUsershareelec.getPkUsershareelec() + "");
		}
	}
	

}
