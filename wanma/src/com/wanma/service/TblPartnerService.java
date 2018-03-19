/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.wanma.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.model.TblPartner;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblUser;


/**
 * 
 * 第三方接口用户身份管理
 */
public interface TblPartnerService {
	public List<TblPartner> getPartnerList();
	public long getPartnerListCount();
	public int addPartner(TblPartner partner);
	public  void updatePartnerKeyById(TblPartner partner);
	public TblPartner selectPartnerNameById(int partnerId);
	public int checkPartnerName(String partnerKey);
	public void deletePartnerById(String partnerId);
}
