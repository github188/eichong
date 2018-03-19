package com.wanma.service;

import java.util.List;

import com.wanma.model.TblBespoke;

/**
 * 预约业务处理器
 * 
 * @author xiay
 *
 */
public interface CmsBespokeService {
	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @return 无
	 */
	public TblBespoke findBespoke(String pkBespoke);

	/**
	 * 编辑充电消费
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void modifyBespoke(TblBespoke tblBespoke);

	/**
	 * 取得充电消费一览
	 * 
	 * @return
	 */
	public List<TblBespoke> getBespokeList();

	/**
	 * 查询充电消费个数
	 * 
	 * @return
	 */
	public long searchBespokeCount(TblBespoke tblBespoke);

	/**
	 * 查询充电消费一览
	 * 
	 * @return
	 */
	public List<TblBespoke> searchBespokeList(TblBespoke tblBespoke);
	
	/**
	 * 删除充电消费
	 * 
	 * @param pkBespoke
	 * @return
	 */
	public int deleteBespoke(String pkBespoke);
	
	
	/**
	 * 根据充电消费ID取得充电消费信息
	 * 
	 * @return 无
	 */
	public TblBespoke findUnitBespoke(String pkBespoke);

	/**
	 * 取得充电消费一览
	 * 
	 * @return
	 */
	public List<TblBespoke> getUnitBespokeList();

	/**
	 * 查询充电消费个数
	 * 
	 * @return
	 */
	public long searchUnitBespokeCount(TblBespoke tblBespoke);

	/**
	 * 查询充电消费一览
	 * 
	 * @return
	 */
	public List<TblBespoke> searchUnitBespokeList(TblBespoke tblBespoke);
	
	/**
	 * 删除充电消费
	 * 
	 * @param pkBespoke
	 * @return
	 */
	public int deleteUnitBespoke(String pkBespoke);
	
	
	/**
	 * 根据充电消费ID取得充电消费信息  --纯商家
 	 * 
	 * @return 无
	 */
	public TblBespoke findFirmBespoke(String pkBespoke);

	/**
	 * 取得充电消费一览--纯商家
	 * 
	 * @return
	 */
	public List<TblBespoke> getFirmBespokeList();

	/**
	 * 查询充电消费个数--纯商家
	 * 
	 * @return
	 */
	public long searchFirmBespokeCount(TblBespoke tblBespoke);

	/**
	 * 查询充电消费一览--纯商家
	 * 
	 * @return
	 */
	public List<TblBespoke> searchFirmBespokeList(TblBespoke tblBespoke);
	

}
