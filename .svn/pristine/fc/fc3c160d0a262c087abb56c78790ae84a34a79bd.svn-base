/**     
 * @Title:  TblCardapplicationformServiceImpl.java   
 * @Package com.wanma.service.impl   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年1月14日 下午1:52:50   
 * @version V1.0     
 */
package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblCardapplicationformMapper;
import com.wanma.model.TblCardapplicationform;
import com.wanma.service.TblCardapplicationformService;

/**
 * @author bc
 *
 */
@Service
public class TblCardapplicationformServiceImpl implements
		TblCardapplicationformService {
	@Autowired
	TblCardapplicationformMapper cardapplicationformMapper;

	@Override
	public List<TblCardapplicationform> findCardapplicationformList(
			TblCardapplicationform card) {
		return cardapplicationformMapper.findCardapplicationformList(card);
	}

	@Override
	public long findCardapplicationformListCount(TblCardapplicationform card) {
		return cardapplicationformMapper.findCardapplicationformListCount(card);
	}

	@Override
	public void bindCard(TblCardapplicationform card) {
		cardapplicationformMapper.bindCardApply(card);
	}

	@Override
	public String findCardapplicationformId(TblCardapplicationform card) {
		return cardapplicationformMapper.findCardapplicationformId(card);
	}

}
