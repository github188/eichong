package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblApplyPartner;

public interface CmsApplyPartnerService {
	
	public void  insertCmsApplyPartner(TblApplyPartner  tblApplyPartner);
	/**
	 * 查询列表
	 * @param tblApplyPartner
	 * @return
	 */
	public List<TblApplyPartner>  getCmsApplyPartnerList(TblApplyPartner  tblApplyPartner);

	public long getCmsApplyPartnerCount(TblApplyPartner  tblApplyPartner);
	
	/**
	 * 根据ID查
	 * @param tblApplyPartner
	 * @return
	 */
	public TblApplyPartner  getCmsApplyPartnerById(Integer pkApplyPartner);
	/**
	 * 更新申请状态
	 * @param params
	 */
	public void updateApplyPartnerState(Map<String,Object> params);
	/**
	 * 编辑驳回原因
	 * @param params
	 */
	public void updateApplyPartnerReason(Map<String,Object> params);
	
	/**
	 * 删除充电点申请
	 * @param pkApplyPartner
	 */
	public void deleteApplyPartnerById(Integer pkApplyPartner);
	
	/**
	 * 删除充电点申请
	 * @param pkApplyPartner
	 */
	public void deleteApplyPartnerByIdS(String pkApplyPartners);
	
	public List<String> getImages(TblApplyPartner tblApplyPartner);

}
