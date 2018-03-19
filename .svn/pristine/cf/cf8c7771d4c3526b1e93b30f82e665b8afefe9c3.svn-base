package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblBespoke;
import com.wanma.page.Page;

/**
 * @Description: 预约操作dao接口
 * @author songjf
 * @createTime：2015-3-19 下午08:57:58
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebBespokeMapper {
	public final static String PREFIX = WebBespokeMapper.class.getName();
	/**
	 * @Title: get
	 * @Description: 获取预约信息
	 * @param pkBespoke
	 *            预约id
	 * @return
	 */
	public TblBespoke get(java.lang.Integer pkBespoke);
	public <K, V> Map<K, V> findOne(java.lang.Integer pkBespoke);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insert
	 * @Description: 新增预约
	 * @param params
	 * @return
	 */
	public int insert(TblBespoke tblBespoke);

	public int update(TblBespoke tblBespoke);

	public int delete(java.lang.Integer pkBespoke);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * @Title: selectBespoke
	 * @Description: 预约 获取电桩枪口名称 预约单价
	 * @param params
	 * @return
	 */
	public <T, K, V> T selectBespoke(Map<K, V> params);

	/**
	 * @Title: selectElectInfo
	 * @Description: 获取电桩，枪口信息
	 * @param pkElectricpileHead
	 * @return
	 */
	public <T, K, V> T selectElectInfo(Map<K, V> params);

	/**
	 * @Title: selectBespokes
	 * @Description: 获取我的预约
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> selectBespokes(Map<K, V> params);

	/**
	 * @Title: updateBespStatus
	 * @Description: 更新预约状态
	 * @param params
	 * @return
	 */
	public <K, V> int updateBespStatus(Map<K, V> params);

	/**
	 * @Title: selectBespokeById
	 * @Description: 根据id获取预约信息
	 * @param params
	 * @return
	 */
	public <T, K, V> T selectBespokeById(Map<K, V> params);

	/**
	 * @Title: isBespoke
	 * @Description: 判断此枪口是否被预约
	 * @param params
	 * @return
	 */
	public Map<String,Object> isBespoke(int bespElectricPileHead);

	/**
	 * @Title: selectUserAndElectInfo
	 * @Description: 获取电桩编号 用户账户 用户余额
	 * @param params
	 * @return
	 */
	public <T, K, V> T selectUserAndElectInfo(TblBespoke bespoke);

	/**
	 * @Title: selectUnitPrice
	 * @Description: 获取预约电桩单价
	 * @param params
	 * @return
	 */
	public <T> T selectUnitPrice(int pkElectricPile);

	/**
	 * @Title: selectFrozenAmt
	 * @Description: 获取冻结金额
	 * @param params
	 * @return
	 */
	public <K, V> Double selectFrozenAmt(Map<K, V> params);

	/**
	 * @Title: selectLockPilehead
	 * @Description: 判断此枪口是否正在预约中
	 * @param params
	 * @return
	 */
	public <K, V> long selectLockPilehead(Map<K, V> params);

	/**
	 * @Title: selectCountByUserId
	 * @Description: 根据用户id判断此用户当前是否有预约枪口
	 * @param params
	 * @return
	 */
	public int selectCountByUserId(TblBespoke bespoke);

	/**
	 * 根据用户id，查找用户未支付的预约单的数量
	 * @param bespoke
	 * @return
	 */
	public int getUnpayNumByUserId(TblBespoke bespoke);
	/**
	 * @Title: selectAtEndTime
	 * @Description: 获取到预约结束时间还未处理的预约
	 * @param params
	 * @return
	 */
	public <T> List<T> selectAtEndTime();
	
	/**
	 * 根据用户id和枪头id,电桩id获取预约编号
	 * @param params
	 * @return
	 */
	public String getBpByUidAndHeadid(Map<String,Integer> params);

}
