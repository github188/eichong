package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblBespoke;

/**
 * 预约消费订单
 * 
 * @author xiay
 *
 */
public interface CmsBespokeMapper {
	
	/**
	 * 根据预约消费ID取得预约消费信息
	 * 
	 * @return
	 */
	public TblBespoke findBespoke(String pkBespoke);

	/**
	 * 添加预约消费
	 * 
	 * @return
	 */
	public int saveBespoke(TblBespoke tblBespoke);

	/**
	 * 编辑预约消费
	 * 
	 * @return
	 */
	public void modifyBespoke(TblBespoke tblBespoke);

	/**
	 * 取得预约消费一览
	 * 
	 * @return
	 */
	public List<TblBespoke> getBespokeList();

	/**
	 * 查询预约消费个数
	 *
	 * @return
	 */
	public long searchBespokeCount(TblBespoke tblBespoke);

	/**
	 * 查询预约消费一览
	 * 
	 * @return
	 */
	public List<TblBespoke> searchBespokeList(TblBespoke TtblBespoke);
	
	/**
	 * 删除预约消费
	 * 
	 * @return
	 */
	public int deleteBespoke(String pkBespoke);
	
	
	/**
	 * 根据预约消费ID取得预约消费信息 --个人商户
	 * 
	 * @return
	 */
	public TblBespoke findUnitBespoke(String pkBespoke);
	
	/**
	 * 取得预约消费一览--个人商户
	 * 
	 * @return
	 */
	public List<TblBespoke> getUnitBespokeList();

	/**
	 * 查询预约消费个数--个人商户
	 *
	 * @return
	 */
	public long searchUnitBespokeCount(TblBespoke tblBespoke);

	/**
	 * 查询预约消费一览--个人商户
	 * 
	 * @return
	 */
	public List<TblBespoke> searchUnitBespokeList(TblBespoke TtblBespoke);
	
	
	/**
	 * 根据预约消费ID取得预约消费信息 --纯商家
	 * 
	 * @return
	 */
	public TblBespoke findFirmBespoke(String pkBespoke);
	
	/**
	 * 取得预约消费一览--纯商家
	 * 
	 * @return
	 */
	public List<TblBespoke> getFirmBespokeList();

	/**
	 * 查询预约消费个数--纯商家
	 *
	 * @return
	 */
	public long searchFirmBespokeCount(TblBespoke tblBespoke);

	/**
	 * 查询预约消费一览--纯商家
	 * 
	 * @return
	 */
	public List<TblBespoke> searchFirmBespokeList(TblBespoke TtblBespoke);
	
	
 
    
	
}
