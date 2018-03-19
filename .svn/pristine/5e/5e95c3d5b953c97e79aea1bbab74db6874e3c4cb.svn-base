package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblConcentrator;

/**
 * @Description: 后台管理集中器操作dao
 * @author lhy
 * @createTime：2015-11-30 下午13:50
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsConcentratorMapper {

	/**
	 * @Title: findConcentrators
	 * @Description: 获取集中器列表
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findConcentrators(TblConcentrator tblConcentrator);

	/**
	 * @Title: selectConcentratorCount
	 * @Description: 获取集中器总数
	 * @param params
	 * @return
	 */
	public <T> T selectConcentratorCount(TblConcentrator tblConcentrator);

	/**
	 * 保存集中器
	 * @param concentrator
	 */
	public void insert(TblConcentrator concentrator);
	
	/**
	 * 修改集中器
	 * @param concentrator
	 */
	public void update(TblConcentrator concentrator);

	/**
	 * 查询集中器信息
	 * @param concentrator
	 * @return
	 */
	public TblConcentrator findOne(TblConcentrator concentrator);

	/**
	 * 删除集中器
	 * @param idsMap
	 */
	public void delete(Map<String, String[]> idsMap);

	/**
	 * 根据集中器ID组获取绑定的电桩数
	 * @param idsMap
	 * @return
	 */
	public int getBindedCountByIds(Map<String, String[]> idsMap);

	public int isOnlineCount(Map<String, String[]> makeIdsMap);
}
