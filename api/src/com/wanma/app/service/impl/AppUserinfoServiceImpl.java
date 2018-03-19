package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bluemobi.model.PinCode;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.dao.AppElecPileCommentMapper;
import com.wanma.app.dao.AppProductcommentMapper;
import com.wanma.app.dao.AppUserinfoMapper;
import com.wanma.app.dao.AuthCodeMapper;
import com.wanma.app.dao.PowerStationCommentMapper;
import com.wanma.app.dao.TblEarningsMapper;
import com.wanma.app.dao.TblFootprintMapper;
import com.wanma.app.dao.TblPurchasehistoryMapper;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.util.DateUtil;
import com.wanma.app.util.MD5Util;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.MyProductcomment;
import com.wanma.model.MyWallet;
import com.wanma.model.TblEarnings;
import com.wanma.model.TblFootprint;
import com.wanma.model.TblProductcomment;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUserinfo;
import com.wanma.model.UserLoginRecord;
import com.wanma.model.Userinfo;
import com.wanma.model.WalletRecord;

/**
 * 
 * @Description: 用户信息业务处理类
 * @author songjf
 * @createTime：2015-3-13 上午10:31:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service("userinfoService")
@Transactional
public class AppUserinfoServiceImpl implements AppUserinfoService {

	/** 用户信息业务操作DAO */
	@Autowired
	private AppUserinfoMapper userinfoDao;

	/** 验证码操作用DAO */
	@Autowired
	AuthCodeMapper authCodeMapper;
	@Autowired
	private AppProductcommentMapper productcommentMapper;
	@Autowired
	private TblFootprintMapper tblFootprintMapper;
	@Autowired
	private TblPurchasehistoryMapper tblPurchasehistoryMapper;
	@Autowired
	private TblEarningsMapper tblEarningsMapper;
	@Autowired
	private AppElecPileCommentMapper appElecPileCommentMapper;
	@Autowired
	private PowerStationCommentMapper powerStationCommentMapper;

	
	
	
	
	@Override
	public void insertLoginRecord(UserLoginRecord record) throws Exception
	{
	
		userinfoDao.insertLoginRecord(record);
	}
	
	/**
	 * @Title: checkLoginUser
	 * @Description: 登录检查
	 * @param usinPhone
	 *            注册手机号
	 * @param usInPassword
	 *            密码
	 * @return String 用户登录检查结果 001:成功 002:用户不存在 003:密码错误
	 */
	@Override
	public String checkLoginUser(Map<String, Object> params) {
		// 手机号
		String usinPhone = (String) params.get("usinPhone");
		
		// 根据注册手机号查询用户是否存在
		Map<String, Object> map = userinfoDao.getUserByMobile(usinPhone);
		
		// 如果取得的用户id为空，返回（002:用户不存在）标识
		if (null == map || StringUtils.isEmpty(map.get("user_id").toString())) {
			return CommonConsts.USER_STATUS_CHECK_NO_USER;
		}
		
		String pkUserInfo = map.get("user_id").toString();
		String userStatus = map.get("user_status").toString();
		if("2".equals(userStatus)){
			return CommonConsts.USER_STATUS_CHECK_FREEZE;
		}
		String loginpwd = params.get("usInPassword").toString();
		int userCount = 0;
		//16位是前期一次加密，17位是后期两次加密
		if(loginpwd.length() == 32){
			// 根据用户注册手机号和密码取得用户数
			userCount = userinfoDao.getUserCountByPassword(params);
		}else{
			String dbpwd = map.get("user_password").toString();
			String md5Str = MD5Util.MD5Encode(dbpwd + usinPhone,null);
			if(md5Str.equals(loginpwd.substring(0, 32)))
				userCount = 1;
		}
		// 如果取得的用户数小于1个，返回（003:密码错误）标识
		if (userCount < 1) {
			return CommonConsts.USER_STATUS_CHECK_ERROR_PASSWORD;
		}
		return pkUserInfo;
	}
	
	@Override
	public String checkCLoginUser(Map<String, Object> params) {
		// 手机号
		String usinPhone = (String) params.get("account");
		
		// 根据注册手机号查询用户是否存在
		Map<String, Object> map = userinfoDao.getUserByMobile(usinPhone);
		
		// 如果取得的用户id为空，返回（002:用户不存在）标识
		if (null == map || StringUtils.isEmpty(map.get("user_id").toString())) {
			return CommonConsts.USER_STATUS_CHECK_NO_USER;
		}
		
		String pkUserInfo = map.get("user_id").toString();
		String userStatus = map.get("user_status").toString();
		if("2".equals(userStatus)){
			return CommonConsts.USER_STATUS_CHECK_FREEZE;
		}
		String loginpwd = params.get("pwd").toString();
		int userCount = 0;
		//32位是前期一次加密，33位是后期两次加密
		if(loginpwd.length() == 32){
			// 根据用户注册账户和密码取得用户数，传进来的key和sql用到的key不一致，需要转换下
			params.put("usinPhone", params.get("account"));
			params.put("usInPassword", params.get("pwd"));
			userCount = userinfoDao.getUserCountByPassword(params);
		}else{
			String dbpwd = map.get("user_password").toString();
			String md5Str = MD5Util.MD5Encode(dbpwd + usinPhone,null);
			if(md5Str.equals(loginpwd.substring(0, 32)))
				userCount = 1;
		}
		// 如果取得的用户数小于1个，返回（003:密码错误）标识
		if (userCount < 1) {
			return CommonConsts.USER_STATUS_CHECK_ERROR_PASSWORD;
		}
		return pkUserInfo;
	}

	/**
	 * @Title: getUserById
	 * @Description: 根据用户id获取用户信息
	 * @param pkUserInfo
	 *            用户id
	 * @return
	 */
	@Override
	public TblUserinfo getUserById(int pkUserInfo) {
		return userinfoDao.get(pkUserInfo);

	}
	
	@Override
	public void updateUserInfo(TblUserinfo pkUserInfo)
	{
		userinfoDao.updateUserInfo(pkUserInfo);
	}

	/**
	 * @Title: userIsExist
	 * @Description: 判断用户是否存在
	 * @param usinPhone
	 * @return
	 */
	public int userIsExist(String usinPhone) {
		return userinfoDao.userIsExist(usinPhone);
	}

	/**
	 * @Title: checkAuthCode
	 * @Description: 检查验证码是否正确
	 * @param authCode
	 *            验证码
	 * @param usinPhone
	 *            手机号
	 * @return 0正确  1验证码错误  2超时  3无效
	 */
	@Override
	public int checkAuthCode(String authCode, String usinPhone) {
		// 验证码对象
		PinCode pinCode = null;
		// 验证码有效时间
		long effectSends = CommonConsts.AUTHCODE_EFFECTIVE_TIME;
		// 验证码生成的时间
		long createTime = 0;
		// 时间比较结果
		long compareResult = 0;
		// 数据库保存的验证码
		String sysAuthCode = "";
		// 根据手机号取得验证码对象
		pinCode = WanmaConstants.pinCodes.get(usinPhone);
		// 判断验证码
		if (ObjectUtil.isEmpty(pinCode)) {
			//取不到验证码对象，则为无效验证码
			return 3;
		} else {
			// 取得系统中的验证码
			sysAuthCode = pinCode.getCode();
			// 为验证码生成时间加上有效时长
			createTime = pinCode.getCreatetime()+ effectSends*1000;
			// 比较系统时间和加上有效时长验证码生成的时间
			compareResult = System.currentTimeMillis()-createTime;

			// 如果系统时间超过有效时间或者验证码不对，则为无效验证码
			if( !sysAuthCode.equals(authCode)) return 1;}
			if (compareResult > 0 )return 2;
		// 返回验证码确认结果
		return 0;
	}

	/**
	 * @Title: insertUserinfo
	 * @Description: 保存用户注册信息
	 * @param params
	 * @return
	 */
	@Override
	public void insertUserinfo(TblUserinfo userinfo) throws Exception {

		// 1：正常 2 ：冻结 3 ：商家升级审核中 4 普通用户
		userinfo.setUsinUserstatus(1);
		userinfoDao.insertUserBase(userinfo);
		// 初始账户余额为0
		BigDecimal account = new BigDecimal(0);
		userinfo.setUsinAccountbalance(account);
		
		String accountNo="FA"+userinfo.getPkUserinfo().toString()+new Date().getTime();
		userinfo.setAccountNo(accountNo);
		
		//新增资金账户
		userinfoDao.insertUserFinBill(userinfo);
		//新增普通用户表信息
		userinfoDao.insertUserNormal(userinfo);
		//新增账务关系
		userinfoDao.insertFinRelation(userinfo);
		
		
		if (ObjectUtil.isNotEmpty(userinfo.getAllMultiFile())) {
			// 处理用户图片
			MultipartFileUtil.saveAllMulti(userinfo,
					WanmaConstants.MULTI_TYPE_USER_AVATAR,
					userinfo.getPkUserinfo() + "");
		}

	}

	/**
	 * @Title: resetPasswrod
	 * @Description: 重置密码
	 * @param params
	 *            新密码 用户id
	 * @return
	 */
	@Override
	public int resetPasswrod(Map<String, Object> params) {
		return userinfoDao.resetPasswrod(params);
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userinfo
	 * @return
	 */
	@Override
	public Userinfo getUserInfo(Userinfo userinfo) {
		Userinfo userinfo2 = new Userinfo();

		// 01获取普通用户信息
		TblUserinfo tblUserinfo = userinfoDao.get(JudgeNullUtils.nvlInteget(userinfo.getPkUserId()));
		if(null != tblUserinfo){
			userinfo2.setPkUserId(JudgeNullUtils.nvlInteget(tblUserinfo.getPkUserinfo()));
			userinfo2.setUserImage(JudgeNullUtils.nvlStr(tblUserinfo.getUsinHeadimage()));
			userinfo2.setUserRealName(JudgeNullUtils.nvlStr(tblUserinfo.getUsinFacticityname()));
			userinfo2.setUserNickName(JudgeNullUtils.nvlStr(tblUserinfo.getUsinUsername()));
			userinfo2.setUserSex(JudgeNullUtils.nvlStr(tblUserinfo.getUsinSex()));
			userinfo2.setUserBrithy(JudgeNullUtils.nvlStr(tblUserinfo.getUsinBirthdate()));
			userinfo2.setUserIcNo(JudgeNullUtils.nvlStr(tblUserinfo.getUsinIccode()));
			userinfo2.setUserTel(JudgeNullUtils.nvlStr(tblUserinfo.getUsinPhone()));
			userinfo2.setUserMail(JudgeNullUtils.nvlStr(tblUserinfo.getUsinEmail()));
			userinfo2.setUserDriveNo(JudgeNullUtils.nvlStr(tblUserinfo.getUsinDrivinglicense()));
			userinfo2.setUserCarType(JudgeNullUtils.nvlStr(tblUserinfo.getUsinCarinfoId()));
			userinfo2.setUserIntegral(JudgeNullUtils.nvlStr(tblUserinfo.getUsinIntegrate()));
			userinfo2.setUserType(JudgeNullUtils.nvlStr("1"));
			userinfo2.setUserCarTypeName(JudgeNullUtils.nvlStr(tblUserinfo.getUsinCarinfoName()));
			userinfo2.setUsinUserstatus(JudgeNullUtils.nvlStr(tblUserinfo.getUsinUserstatus()));
			userinfo2.setUserBlance(JudgeNullUtils.nvlStr(tblUserinfo.getUsinAccountbalance()));
			userinfo2.setpCode(JudgeNullUtils.nvlStr(tblUserinfo.getpCode()));
			userinfo2.setcCode(JudgeNullUtils.nvlStr(tblUserinfo.getcCode()));
			userinfo2.setaCode(JudgeNullUtils.nvlStr(tblUserinfo.getaCode()));
			userinfo2.setAddress(JudgeNullUtils.nvlStr(tblUserinfo.getUsinUseraddress()));
			userinfo2.setApplyCard(JudgeNullUtils.nvlStr(tblUserinfo.getApplyCard()));
			userinfo2.setUserAB(JudgeNullUtils.nvlStr(tblUserinfo.getUsinAccountbalance().toString()));
			String chargeCard = JudgeNullUtils.nvlStr(tblUserinfo.getChargeCard());
			if(!"".equals(chargeCard)){
				userinfo2.setChargeCard(chargeCard.split(",")[0]);
			}else{
				userinfo2.setChargeCard("");
			}
			userinfo2.setPlateNum(JudgeNullUtils.nvlStr(tblUserinfo.getUsinPlatenumber()));
			userinfo2.setVehicleNum(JudgeNullUtils.nvlStr(tblUserinfo.getUsinVehiclenumbe()));
			
		}
		return userinfo2;
	}

	/**
	 * 一键升级为商家
	 * 
	 * @param userinfo
	 * @return
	 */
	@Override
	public void upgradeoUser(TblUserinfo tblUserinfo) {
		userinfoDao.upgradeoUser(tblUserinfo);
	}

	/**
	 * @Title: findProComments
	 * @Description: 获取产品评论
	 * @param params
	 * @return
	 */
	@Override
	public List<MyProductcomment> findProComments(
			TblProductcomment fProductcomment) {
		List<MyProductcomment> myProductcommentList = new ArrayList<MyProductcomment>();

		List<TblProductcomment> commentList = productcommentMapper
				.findProCommentsByUser(fProductcomment);
		for (TblProductcomment tblProductcomment : commentList) {
			MyProductcomment myProductcomment = new MyProductcomment();
			myProductcomment.setProductCommentId(JudgeNullUtils
					.nvlStr(tblProductcomment.getPkProductcomment()));
			myProductcomment.setPrCoContent(JudgeNullUtils
					.nvlStr(tblProductcomment.getPrcoContent()));
			myProductcomment.setPrCoCreatedate(DateUtil.toDateFormat(
					tblProductcomment.getPrcoCreatedate(), "yyyy-MM-dd HH:mm"));
			myProductcommentList.add(myProductcomment);
		}
		return myProductcommentList;
	}
	
	/**
	 * 根据userid获取电桩、电站评论列表
	 */
	public List<HashMap<String, Object>> findProCommentsN(int userId) {
		List<MyProductcomment> myProductcommentList=new ArrayList<MyProductcomment>();
	 
		List<HashMap<String, Object>> commentList = productcommentMapper
				.findProCommentsByUserId(userId);
		return commentList;
	}
	
	/**
	 * 删除给定的评论
	 * @param cId
	 * @param type
	 */
	@Override
	public void removeMyCommentN(String cId,String type) {
		if("1".equals(type)){
			appElecPileCommentMapper.delete(Integer.parseInt(cId));
		}else if("2".equals(type)){
			powerStationCommentMapper.delete(Integer.parseInt(cId));
		}
	}
	
	/**
	 * @Title: findProComments
	 * @Description: 删除产品评论
	 * @param params
	 * @return
	 */
	@Override
	public void removeMyComment(String productId) {
		if (productId.indexOf(",") > 0) {
			String[] collectIds = productId.split(",");
			for (int i = 0; i < collectIds.length; i++) {
				productcommentMapper.delete(JudgeNullUtils
						.nvlInteget(collectIds[i]));
			}
		} else {
			productcommentMapper.delete(JudgeNullUtils.nvlInteget(productId));
		}
	}

	/**
	 * @Title: findProComments
	 * @Description: 获取我的足迹
	 * @param params
	 * @return
	 */
	@Override
	public List<TblFootprint> getMyFootPrint(TblFootprint tblFootprint) {
		return tblFootprintMapper.find(tblFootprint);
	}

	/**
	 * @Title: findProComments
	 * @Description: 获取我的钱包
	 * @param params
	 * @return
	 */
	@Override
	public MyWallet getMyWallet(TblPurchasehistory tblPurchasehistory,
			TblEarnings tblEarnings) {
		// tblPurchasehistoryMapper
		// tblEarningsMapper
		MyWallet myWallet = new MyWallet();
		// 00-获取余额
		TblUserinfo tblUserinfo = userinfoDao.get(JudgeNullUtils.nvlInteget(tblPurchasehistory.getPuhiUserid()));
		if(StringUtils.isEmpty(tblUserinfo)){
			myWallet.setBalance("0");
		}else{
			myWallet.setBalance(JudgeNullUtils.nvlStr(tblUserinfo.getUsinAccountbalance()));
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 01获取消费记录
		List<WalletRecord> consumeRecordList = new ArrayList<WalletRecord>();
		List<TblPurchasehistory> purchasehistoryList = tblPurchasehistoryMapper.find(tblPurchasehistory);
		for (TblPurchasehistory tblPurchasehistory2 : purchasehistoryList) {
			WalletRecord walletRecord = new WalletRecord();
			walletRecord.setRecordContent(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiPurchasecontent()));
			walletRecord.setRecordMoney(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiMonetary()));
			walletRecord.setRecordTitle(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiType()));
			walletRecord.setRecordTime(format.format(tblPurchasehistory2.getPuhiPurchasehistorytime()));
			consumeRecordList.add(walletRecord);
		}
		myWallet.setConsumeRecord(consumeRecordList);
		
		// 02：获取收益记录
		/*List<WalletRecord> earningsRecordList = new ArrayList<WalletRecord>();
		List<TblEarnings> earningsList = tblEarningsMapper.find(tblEarnings);
		for (TblEarnings tblEarnings2 : earningsList) {
			WalletRecord walletRecord = new WalletRecord();
			walletRecord.setRecordContent(JudgeNullUtils.nvlStr(tblEarnings2.getEarContent()));
			walletRecord.setRecordMoney(JudgeNullUtils.nvlStr(tblEarnings2.getEarMonetary()));
			walletRecord.setRecordTitle(JudgeNullUtils.nvlStr("收益"));

			earningsRecordList.add(walletRecord);
		}
		myWallet.setEarningsRecord(earningsRecordList);*/
		return myWallet;
	}
	
	@Override
	public MyWallet myBills(Map<String, Object> params) {
		// tblPurchasehistoryMapper
		// tblEarningsMapper
		MyWallet myWallet = new MyWallet();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 01获取消费记录
		List<WalletRecord> consumeRecordList = new ArrayList<WalletRecord>();
		List<TblPurchasehistory> purchasehistoryList = tblPurchasehistoryMapper.myBills(params);
		for (TblPurchasehistory tblPurchasehistory2 : purchasehistoryList) {
			WalletRecord walletRecord = new WalletRecord();
			walletRecord.setRecordContent(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiPurchasecontent()));
			walletRecord.setRecordMoney(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiMonetary()));
			walletRecord.setRecordTitle(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiType()));
			walletRecord.setRecordTime(format.format(tblPurchasehistory2.getPuhiPurchasehistorytime()));
			consumeRecordList.add(walletRecord);
		}
		myWallet.setConsumeRecord(consumeRecordList);
		
		return myWallet;
	}
	
	public MyWallet invoicePurList(long iId){
		MyWallet myWallet = new MyWallet();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 01获取消费记录
		List<WalletRecord> consumeRecordList = new ArrayList<WalletRecord>();
		List<TblPurchasehistory> purchasehistoryList = tblPurchasehistoryMapper.invoicePurList(iId);
		for (TblPurchasehistory tblPurchasehistory2 : purchasehistoryList) {
			WalletRecord walletRecord = new WalletRecord();
			walletRecord.setRecordContent(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiPurchasecontent()));
			walletRecord.setRecordMoney(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiMonetary()));
			walletRecord.setRecordTitle(JudgeNullUtils
					.nvlStr(tblPurchasehistory2.getPuhiType()));
			walletRecord.setRecordTime(format.format(tblPurchasehistory2.getPuhiPurchasehistorytime()));
			consumeRecordList.add(walletRecord);
		}
		myWallet.setConsumeRecord(consumeRecordList);
		
		return myWallet;
	}
	
	@Override
	public void userRecharge(TblUserinfo tblUserinfo) {
		// TODO Auto-generated method stub
		userinfoDao.userRecharge(tblUserinfo);
	}

	/**
	 * @Title: getByPhone
	 * @Description: 根据用户手机号获取用户信息
	 * @param usinPhone
	 * @return
	 */
	@Override
	public TblUserinfo getByPhone(String usinPhone) {
		return userinfoDao.getByPhone(usinPhone);
	}
	
	/**
	 * 更新用户账户余额
	 * @param userMap
	 * 			userId 用户id blance 账户余额
	 */
	public void updateUserBlance(Map<String, Object> userMap){
		userinfoDao.updateUserBlance(userMap);
	}
	
	
	/**
	 * 更新用户的登陆设备id
	 */
	public void updateUserDeviceId(Map<String, Object> params){
		userinfoDao.updateUserDeviceId(params);
	}
	
	/**
	 * 设置支付密码
	 * @param map
	 */
	public void setPayPwd(Map<String, Object> map){
		userinfoDao.setPayPwd(map);
	}
	
	/**
	 * 获取用户账户余额
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserABById(int userId){
		Map<String, Object> map = userinfoDao.getUserABById(userId);
		return (BigDecimal)map.get("norm_account_balance");
	}
	
	/**
	 * 根据用户id修改用户密码
	 * @param params
	 */
	public void updatePwByUid(Map<String, String> params){
		userinfoDao.updatePwByUid(params);
	}
	
	public void applyCard(Map<String, String> params){
		userinfoDao.applyCard(params);
	}
	
	/**
	 * 更新用户表中卡申请状态
	 * @param params
	 * 			uId 用户id，status 状态1申请中2已发卡
	 */
	public void updateUserApplyCardStatus(Map<String, String> params){
		userinfoDao.updateUserApplyCardStatus(params);
	}
	
	public List<Map<String, String>> applyListByUid(long uId){
		return userinfoDao.applyListByUid(uId);
	}
	
	/**
	 * 从user表获取用户的状态，正常还是冻结
	 * @param uId
	 * @return
	 */
	public int getStatusFromUserTable(long uId){
		return userinfoDao.getStatusFromUserTable(uId);
	}
	
	/**
	 * 获取月账单
	 * @param uId
	 * @return
	 */
	public List<Map<String,Object>> getMtBills(Map<String,Object> params){
		return tblPurchasehistoryMapper.getMtBills(params);
	}
	
	/**
	 * 获取消费记录
	 * @param uId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getPhDetail(Map<String, Object> params) {
		List<Map<String,Object>> rstList = tblPurchasehistoryMapper.getPhDetail(params);
		Double mn = 0D, pcm = 0D;
		for (Map<String, Object> map : rstList) {
			mn = map.get("mn") == null ? 0D : Double.parseDouble(map.get("mn").toString());
			pcm = map.get("pcm") == null ? 0D : Double.parseDouble(map.get("pcm").toString());
			map.put("mn", (mn - pcm));
		} 
		return rstList;
	}
}
