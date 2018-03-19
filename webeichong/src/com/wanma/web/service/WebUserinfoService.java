package com.wanma.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wanma.model.CommitLog;
import com.wanma.model.MyProductcomment;
import com.wanma.model.TblFootprint;
import com.wanma.model.TblUser;
import com.wanma.model.WalletRecord;
import com.wanma.web.support.common.Response;

public interface WebUserinfoService {

	// 登录
	public Response<?> login(Map<String, Object> params,
			HttpServletRequest request, HttpServletResponse response);

	// cookie登录
	public Boolean loginByCookieUser(HttpServletRequest request, TblUser user);

	// 保存用户注册信息
	public Response<?> regist(TblUser user, HttpServletRequest request);

	// 重置密码
	public Response<?> resetPasswrod(Map<String, Object> params,
			HttpServletRequest request);

	public TblUser getUserInfo(TblUser user);

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取产品评论
	 */
	public List<MyProductcomment> findProComments(Map<String, Object> params);

	public long countProComments(Map<String, Object> params);

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 删除产品评论
	 */
	public void removeMyComment(String productId);

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的足迹
	 */
	public List<TblFootprint> getMyFootPrint(TblFootprint tblFootprint);

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的消费/收益明细
	 */
	public List<WalletRecord> findMyWalletList(Map<String, Object> params);

	public long countWallet(Map<String, Object> params);

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的消费明细
	 */
	public List<WalletRecord> findConsumeList(Map<String, Object> params);

	public long countConsume(Map<String, Object> params);

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的收益明细
	 */
	public List<WalletRecord> findEarningList(Map<String, Object> params);

	public long countEarning(Map<String, Object> params);

	/**
	 * 生成图片验证码
	 *
	 * @param request
	 * @param response
	 */
	public void createValidCode(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 发送短信
	 *
	 * @param phone
	 * @param request
	 * @return
	 */
	public Response<?> sendMsg(String phone, HttpServletRequest request);

	/**
	 * 更新用户信息
	 *
	 * @param userinfo
	 * @param request
	 * @return
	 */
	public Response<?> updateUserInfo(TblUser user, HttpServletRequest request);

	/**
	 * 用户充值
	 *
	 * @param tblUserinfo
	 * @return
	 */
	public Response<?> userRecharge(TblUser tblUser, HttpServletRequest request);

	/**
	 * 用户充值
	 * 
	 * @param userId
	 * @param tradeNo
	 * @param totalFee
	 * @return
	 */
	public Boolean addReChargeRecord(HttpServletRequest request);

	public List<HashMap<String, Object>> getCommentsByUserId(
			Map<String, Object> params);

	public long countComments(Map<String, Object> params);

	// 根据评论id查出评论
	public HashMap<String, Object> getCommentById(int pkComment);

	/**
	 * 根据评论id删除对应的电桩或电站评论
	 * 
	 * @param cId
	 * @param type
	 */
	public void removeMyCommentN(String cId, String type);

	/**
	 * 用户充值
	 * 
	 * @param userId
	 * @param tradeNo
	 * @param totalFee
	 * @return
	 */
	public Boolean addReChargeRecordForTenpay(Map<String, Object> map,
			HttpServletRequest request);

	public TblUser getUserById(TblUser user);

	public void chargeErrorLog(CommitLog commitLog, HttpServletRequest request);
}
