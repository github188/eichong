package com.wanma.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ChargeCardService {
	/**
	 * 初始化充电卡
	 * @param params
	 * 			inNum 内卡号,outNum 外卡号
	 */
	public void initCard(Map<String, String> params) throws Exception;
	
	/**
	 * 根据外卡号统计卡数量
	 * @param outNum 外卡号
	 * @return
	 * @throws Exception
	 */
	public int countCardByOutNum(String outNum) throws Exception;
	
	/**
	 * 获取公司标识列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCompMarkList() throws Exception;
	
	/**
	 * get charge-card info by inner number of card
	 * @param inNum
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getCardInfoByInNum(String inNum) throws Exception;
	
	/**
	 * count card number by inside number and outside number 
	 * @param params
	 * 		inNum: inside number, outNum: outside number
	 * @return
	 * @throws Exception
	 */
	public int countCardByOutAndInNum(Map<String, Object> params) throws Exception;
	
	/**
	 * 充值记录
	 * @param sum 充值金额 
	 * @param userPhone 充值账户
	 * @param outNum 外卡号
	 * @param account 操作人账号
	 * @param userId 充值用户id
	 * @throws Exception
	 */
	public void addPurchaseHis(String sum,String userPhone,String outNum,String account,int userId) throws Exception;
	
	/**
	 * 根据用户id获取绑定的卡列表
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> cardListByUid(long uId) throws Exception;
	
	/**
	 * 根据用户和外卡号修改卡状态
	 * @param params
	 * 		uId 用户id， outNum 外卡号， status 状态
	 * @throws Exception
	 */
	public void updateStatusByUidAndOutNum(Map<String, String> params) throws Exception;
	
	/**
	 * 根据userId 获取电卡信息
	 * @param params
	 * 		userId 用户id， 
	 * @throws Exception
	 */
	public Map<String,Object>getcardByUid(long userId) throws Exception;
	
	public Map<String,Object>myApplyInfo(long userId) throws Exception;
} 
