/**     
 * @Title:  TblCardapplicationformService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年1月14日 下午1:44:41   
 * @version V1.0     
 */
package com.wanma.service;

import java.util.List;

import com.wanma.model.TblCardapplicationform;

/**
 * @author bc
 *
 */
public interface TblCardapplicationformService {
	public List<TblCardapplicationform> findCardapplicationformList(
			TblCardapplicationform card);

	public long findCardapplicationformListCount(TblCardapplicationform card);
	
	public void bindCard(TblCardapplicationform card);

	public String findCardapplicationformId(TblCardapplicationform card);
}
