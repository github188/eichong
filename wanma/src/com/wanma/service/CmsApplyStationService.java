package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblApplyStation;

public interface CmsApplyStationService {
	
	public void  insertCmsApplyStation(TblApplyStation  tblApplyStation);
	/**
	 * 查询列表
	 * @param tblApplyStation
	 * @return
	 */
	public List<TblApplyStation>  getCmsApplyStationList(TblApplyStation  tblApplyStation);

	public long getCmsApplyStationCount(TblApplyStation  tblApplyStation);
	
	/**
	 * 根据ID查
	 * @param tblApplyStation
	 * @return
	 */
	public TblApplyStation  getCmsApplyStationById(Integer pkApplyStation);
	/**
	 * 更新申请状态
	 * @param params
	 */
	public void updateApplyStationState(Map<String,Object> params);
	/**
	 * 编辑驳回原因
	 * @param params
	 */
	public void updateApplyStationReason(Map<String,Object> params);
	
	/**
	 * 删除充电点申请
	 * @param pkApplyStation
	 */
	public void deleteApplyStationById(Integer pkApplyStation);
	
	/**
	 * 删除充电点申请
	 * @param pkApplyStation
	 */
	public void deleteApplyStationByIdS(String pkApplyStations);
	

}
