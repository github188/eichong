package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblApplyElecPile;

public interface CmsApplyElecPileService {
	
	/**
	 * 查询列表
	 * @param tblApplyElecPile
	 * @return
	 */
	public List<TblApplyElecPile>  getCmsApplyElecPileList(TblApplyElecPile  tblApplyElecPile);

	public long getCmsApplyElecPileCount(TblApplyElecPile  tblApplyElecPile);
	
	/**
	 * 根据ID查
	 * @param tblApplyElecPile
	 * @return
	 */
	public TblApplyElecPile  getCmsApplyElecPileById(Integer pkApplyElecPile);
	/**
	 * 更新申请状态
	 * @param params
	 */
	public void updateApplyElecPileState(Map<String,Object> params);
	/**
	 * 编辑驳回原因
	 * @param params
	 */
	public void updateApplyElecPileReason(Map<String,Object> params);
	
	/**
	 * 删除充电点申请
	 * @param pkApplyElecPile
	 */
	public void deleteApplyElecPileById(Integer pkApplyElecPile);
	
	/**
	 * 删除充电点申请
	 * @param pkApplyElecPile
	 */
	public void deleteApplyElecPileByIdS(String pkApplyElecPiles);
	
	
}
