package com.wanma.service;

import java.util.List;

import com.wanma.model.TblConcentrator;

/**
 * @Description: 集中器管理业务处理接口
 * @author lhy
 * @createTime：2015-11-30
 * @updator：
 * @updateTime：
 * @version：V2.0
 */
public interface CmsConcentratorService {
	
	/**
	 * 获取集中器列表
	 * @param concentrator
	 * @return
	 */
	public List<TblConcentrator> findConcentratorList(
			TblConcentrator concentrator);

	/**
	 * 集中器列表条数
	 * @param concentrator
	 * @return
	 */
	public long selectConcentratorCount(TblConcentrator concentrator);

	/**
	 * 保存集中器
	 * @param concentrator
	 */
	public void insert(TblConcentrator concentrator) throws Exception;
	
	/**
	 * 修改集中器
	 * @param concentrator
	 */
	public void updateConcentrator(TblConcentrator concentrator) throws Exception;

	/**
	 * 获取集中器
	 * @param concentrator
	 */
	public TblConcentrator findOne(TblConcentrator concentrator);

	/**
	 * 解绑
	 * @param concentrator
	 */
	public void unbindConcentrator(String unbindPkElectricpile, String reSortPkElectricpiles);
	
	/**
	 * 删除集中器
	 */
	public void delete(String ids);
	
	/**
	 * 存在已绑定电桩的集中器不可删除
	 * @param idsMap
	 * @return
	 */
	public boolean checkDelete(String ids);
	
	public String sendUpdateConcentrator(String id, String apiBaseUrl)
			throws Exception;

}
