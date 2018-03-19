package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.WanmaConstants;
import com.wanma.dao.CmsConfigparameterMapper;
import com.wanma.model.TblConfigparameter;
import com.wanma.service.CmsConfigparameterService;
@Service
public class CmsConfigparameterServiceImpl implements CmsConfigparameterService {
	/* 配置参数操作dao */
	@Autowired
	private CmsConfigparameterMapper configparameterMapper;

	/**
	 * @Title: findConPara
	 * @Description: 获取配置参数
	 * @param params
	 * @return
	 */
	@Override
	public TblConfigparameter findConPara(Integer pkConfigparameter) {
		return configparameterMapper.findConPara(pkConfigparameter);
	}

	/**
	 * @Title: findConParaList
	 * @Description: 获取配置参数列表
	 * @param params
	 * @return
	 */
	@Override
	public List<TblConfigparameter> findConParaList(
			TblConfigparameter tblConfigparameter) {
		return configparameterMapper.findConParaList(tblConfigparameter);
	}

	/**
	 * @Title: insertConPara
	 * @Description: 新增配置参数名称
	 * @param params
	 * @return
	 */
	@Override
	public int insertConPara(TblConfigparameter tblConfigparameter) {
		tblConfigparameter.setCopaCreatedate(new Date());
		tblConfigparameter.setCopaUpdatedate(new Date());
		tblConfigparameter
				.setCopaStatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		return configparameterMapper.insertConPara(tblConfigparameter);
	}

	/**
	 * @Title: updateConPara
	 * @Description: 更新配置参数名称
	 * @param params
	 * @return
	 */
	@Override
	public int updateConPara(TblConfigparameter tblConfigparameter) {
		tblConfigparameter.setCopaUpdatedate(new Date());
		return configparameterMapper.updateConPara(tblConfigparameter);
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除配置参数名称
	 * @param params
	 * @return
	 */
	@Override
	public int deleteById(TblConfigparameter tblConfigparameter) {
		tblConfigparameter
				.setCopaStatus(WanmaConstants.CONFIG_PARAMETER_INVALID);
		return configparameterMapper.deleteById(tblConfigparameter);
	}

	/**
	 * @Title: findCount
	 * @Description: 获取配置参数总数
	 * @param params
	 * @return
	 */
	@Override
	public long findCount(TblConfigparameter tblConfigparameter) {
		return configparameterMapper.findCount(tblConfigparameter);
	}

}
