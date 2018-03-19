package com.wanma.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblBespoke;
import com.wanma.model.TblElectricpilehead;

/**
 * @Description: 预约业务处理接口
 * @author songjf
 * @createTime：2015-3-17 下午03:54:12
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppBespokeService {

	/**
	 * @Title: selectBespoke
	 * @Description: 预约 获取电桩枪口名称 预约单价
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectBespoke(Map<String, Object> params);

	/**
	 * @Title: selectElectInfo
	 * @Description: 获取电桩，枪口信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectElectInfo(Map<String, Object> params);

	/**
	 * @Title: insertBespoke
	 * @Description: 新增预约
	 * @param params
	 * @return
	 */
	public int sendBespoke(TblBespoke bespoke) throws IOException;

	/**
	 * @Title: selectBespokes
	 * @Description: 获取我的预约
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectBespokes(Map<String, Object> params);

	/**
	 * @Title: update
	 * @Description: 更新预约
	 * @param params
	 * @return
	 */
	public int update(TblBespoke bespoke);

	/**
	 * @Title: contract
	 * @Description: 续约
	 * @param params
	 * @return
	 */
	public int contract(TblBespoke bespoke,String did,String buyTime);

	/**
	 * @Title: getById
	 * @Description: 根据id获取枪头数据
	 * @param params
	 * @return
	 */
	public TblElectricpilehead getById(Map<String, Object> params);

	/**
	 * @Title: selectBespokeById
	 * @Description: 根据id获取预约信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectBespokeById(Map<String, Object> params);

	/**
	 * @Title: updateBespStatus
	 * @Description: 请求电桩，取消预约
	 * @param params
	 * @return
	 */
	public String updateBespStatus(Map<String, Object> params);

	/**
	 * @Title: isBespoke
	 * @Description: 判断此枪口是否被预约/充电中/停用
	 * @param params
	 * @return
	 */
	public Map<String,Object> isBespoke(int bespElectricPileHead);

	/**
	 * @Title: updateStatus
	 * @Description: 更新预约状态
	 * @param params
	 * @return
	 */
	public int updateStatus(Map<String, Object> params);

	/**
	 * @Title: updateState
	 * @Description: 更新枪头预约状态
	 * @param params
	 * @return
	 */
	public int updateState(Map<String, Object> params);

	/**
	 * @Title: get
	 * @Description: 获取预约信息
	 * @param pkBespoke
	 *            预约id
	 * @return
	 */
	public TblBespoke get(long pkBespoke);

	/**
	 * @Title: cancelBespoke
	 * @Description: 取消预约
	 * @param params
	 * @return
	 */
	public void cancelBespoke(Map<String, Object> params);

	/**
	 * @Title: selectLockPilehead
	 * @Description: 判断此枪口是否正在预约中
	 * @param params
	 * @return
	 */
	public long selectLockPilehead(Map<String, Object> params);

	/**
	 * @Title: selectUnitPrice
	 * @Description: 获取电桩单价
	 * @param params
	 * @return
	 */
	public Double selectUnitPrice(int pkElectricPile);

	/**
	 * @Title: selectCountByUserId
	 * @Description: 根据用户id判断此用户当前是否有预约枪口
	 * @param params
	 * @return
	 */
	public int selectCountByUserId(TblBespoke bespoke);

	/**
	 * 判断用户是否存在未付款的预约
	 * @param bespoke
	 * @return
	 */
	public int getUnpayNumByUserId(TblBespoke bespoke);
	/**
	 * @Title: selectAtEndTime
	 * @Description: 获取到预约结束时间还未处理的预约
	 * @return
	 */
	public List<TblBespoke> selectAtEndTime();

	/**
	 * 根据用户id和枪头id,电桩id获取预约编号
	 * @param uid
	 * @param headid
	 * @return
	 */
	public String getBpByUidAndHeadid(int uid,int headid,int epid);
	
	/**
	 * 根据预约编号和用户id查预约订单id
	 * @return
	 */
	public int getBespIdByBespCodeAndUid(TblBespoke tblBespoke);

	public int getPileState(int pileId);
}
