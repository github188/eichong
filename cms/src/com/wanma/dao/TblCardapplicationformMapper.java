/**     
 * @Title:  TblCardapplicationformMapper.java   
 * @Package com.wanma.dao   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年1月14日 下午1:37:52   
 * @version V1.0     
 */
package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblCardapplicationform;

/**
 * @author bc
 *
 */
public interface TblCardapplicationformMapper {
	public List<TblCardapplicationform> findCardapplicationformList(
			TblCardapplicationform card);

	public long findCardapplicationformListCount(TblCardapplicationform card);

	public void bindCardApply(TblCardapplicationform card);

	public String findCardapplicationformId(TblCardapplicationform card);
}
