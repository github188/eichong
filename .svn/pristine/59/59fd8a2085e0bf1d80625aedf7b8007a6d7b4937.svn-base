package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateinformation;

/**
 * @Description: 后台管理充电点操作dao
 * @author songjf
 * @createTime：2015-3-31 上午10:38:10
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsPowerstationMapper {
	public final static String PREFIX = CmsPowerstationMapper.class.getName();

	public TblPowerstation get(java.lang.Integer pkPowerstation);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkPowerstation);

	/**
	 * @Title: findPowers
	 * @Description: 获取充电点列表
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> findPowers(TblPowerstation tblPowerstation);

	/**
	 * @Title: selectPowerCount
	 * @Description: 获取充电点总数
	 * @param params
	 * @return
	 */
	public <T> T selectPowerCount(TblPowerstation tblPowerstation);

	public int insert(TblPowerstation tblPowerstation);

	public int update(TblPowerstation tblPowerstation);

	public int delete(java.lang.Integer pkPowerstation);
	/**
	 * 通过主键获取电桩信息
	 * @param tblPowerstation
	 * @return
	 */
	public TblPowerstation get(TblPowerstation tblPowerstation);
	/**
	 * @Title: selectPowerCount
	 * @Description: 通过Id修改充电点状态
	 * @param params
	 * @return
	 */
	public void updateStateById(TblPowerstation tblPowerstation);
	/**
	 * @Title: selectPowerCount
	 * @Description: 通过Id修改充电点驳回原因
	 * @param params
	 * @return
	 */
	public void changeElectricPileExamineReason(TblPowerstation tblPowerstation);
	public int updateByPowerId(TblPowerstation tblPowerstation);
	
	/**
	 * 获取地图模式充电点数据
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getPowerstationForMap(TblPowerstation tblPowerstation);
	/**
	 * 通过主键获取电桩信息
	 * @param feedback
	 */
	public <K, V> Map<K, V> getPowerstationById(TblPowerstation tblPowerstation);

	public int selectRateId(TblRateinformation rateinformation);
}
