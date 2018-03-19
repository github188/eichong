package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblApplyEp;

/**
 * @desc 申请建桩DAO
 * @author cdy
 *
 */
public interface TblApplyEpMapper {
	
	/**
	 * 插入申请电桩记录
	 * @param ApplyEp
	 * @return aepId
	 */
	public Long insert(TblApplyEp ApplyEp);
	
	/**
	 * 根据申请电桩用户ID查询申请电桩记录
	 * @param ApplyEp
	 * @return List<TblApplyEp>
	 */
	public List<TblApplyEp> getByAepUserId(TblApplyEp ApplyEp);
	
}
