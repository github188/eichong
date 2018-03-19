package com.wanma.web.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.DataModel;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.utils.DateUtil;
import com.bluemobi.product.utils.JPushUtil;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.model.CommitLog;
import com.wanma.model.MyProductcomment;
import com.wanma.model.TblEarnings;
import com.wanma.model.TblFootprint;
import com.wanma.model.TblInvoice;
import com.wanma.model.TblJpush;
import com.wanma.model.TblProductcomment;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;
import com.wanma.model.WalletRecord;
import com.wanma.web.dao.CmsCommitLogMapper;
import com.wanma.web.dao.CouponMapper;
import com.wanma.web.dao.PowerStationCommentMapper;
import com.wanma.web.dao.TblInvoiceMapper;
import com.wanma.web.dao.TblUserMapper;
import com.wanma.web.dao.WebEarningsMapper;
import com.wanma.web.dao.WebElecPileCommentMapper;
import com.wanma.web.dao.WebFootprintMapper;
import com.wanma.web.dao.WebProductcommentMapper;
import com.wanma.web.dao.WebPurchasehistoryMapper;
import com.wanma.web.service.WebJpushService;
import com.wanma.web.service.WebUserinfoService;
import com.wanma.web.support.common.ErrorResponse;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.Response;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.common.SuccessResponse;
import com.wanma.web.support.utils.AliSMS;
import com.wanma.web.support.utils.ImageUtil;
import com.wanma.web.support.utils.MD5Utils;
import com.wanma.web.support.utils.RegexUtil;

/**
 * @author songjf
 * @Description: 用户信息业务处理类
 * @createTime：2015-3-13 上午10:31:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebUserinfoServiceImpl implements WebUserinfoService {
	private  Log log = LogFactory.getLog(this.getClass());
	/**
	 * 用户信息业务操作DAO
	 */
	@Autowired
	private TblUserMapper userDao;
	@Autowired
	private WebProductcommentMapper productcommentMapper;
	@Autowired
	private WebFootprintMapper tblFootprintMapper;
	@Autowired
	private WebPurchasehistoryMapper tblPurchasehistoryMapper;
	@Autowired
	private WebEarningsMapper tblEarningsMapper;
	@Autowired
	private WebElecPileCommentMapper webElecPileCommentMapper;
	@Autowired
	private PowerStationCommentMapper powerStationCommentMapper;
	@Autowired
	private CmsCommitLogMapper commitLogDao;
	@Autowired
	private TblInvoiceMapper invoiceDao;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	WebJpushService jpushService;

	/**
	 * @param params
	 *            用户名 密码
	 * @param request
	 * @return String 用户登录检查结果 001:成功 002:用户不存在 003:密码错误
	 */
	@Override
	public Response<?> login(Map<String, Object> params,
			HttpServletRequest request, HttpServletResponse response) {
		// 检查验证码
		if (StringUtils.isEmpty(SessionMgr.getWebCode(request))) {
			return new ErrorResponse("System Error!");
		}
		String webCode = request.getParameter("code");
		if (StringUtils.isEmpty(webCode)
				|| !webCode.equalsIgnoreCase(SessionMgr.getWebCode(request))) {
			return new FailedResponse("验证码错误!");
		}

		// 手机号
		String userAccount = (String) params.get("userAccount");
		// 参数错误
		if (StringUtils.isEmpty(userAccount)) {
			return new ErrorResponse("error.msg.invalid.parameter");
		}
		// 用户登录验证手机号密码重做
		TblUser user = new TblUser();
		user.setUserAccount(userAccount);
		user = userDao.getByAccount(user);
		if (user == null) {// 用户不存在
			return new FailedResponse("error.msg.invalid.user");
		} else {
			if (user.getUserStatus() == 2) {// 用户被冻结
				return new FailedResponse("error.msg.invalid.user_status");
			} else if (!((String) params.get("userPassword")).equals(user
					.getUserPassword())) {
				return new FailedResponse("error.msg.invalid.password");
			}
		}
		try {
			user.setNormDeviceId(request.getSession().getId());
			userDao.updateNormalUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new FailedResponse("error.msg.invalid.parameter");
		}
		// 保存session
		SessionMgr.addWebUser(request, user);
		return new ResultResponse(user);
	}

	/**
	 * @param user
	 * @return
	 * @Title: loginByCookieUser
	 * @Description: cookie登录
	 */
	public Boolean loginByCookieUser(HttpServletRequest request, TblUser user) {
		TblUser tempUser = userDao.getByAccount(user);
		if (user.getUserPassword().equals(tempUser.getUserPassword())) {
			try {
				tempUser.setNormDeviceId(request.getSession().getId());
				userDao.updateNormalUser(tempUser);
			} catch (Exception e) {
				return false;
			}
			SessionMgr.addWebUser(request, tempUser);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @return
	 * @Title: insertUserinfo
	 * @Description: 保存用户注册信息
	 */
	@Override
	public Response<?> regist(TblUser user, HttpServletRequest request) {
		if (!RegexUtil.isTel(user.getUserAccount())) {
			return new FailedResponse("手机号码不正确!");
		}
		// 检查图片验证码
		if (StringUtils.isEmpty(SessionMgr.getWebCode(request))) {
			return new FailedResponse("图片验证码错误!");
		}
		TblUser tempUser = userDao.get(user);
		if (tempUser != null) {
			return new FailedResponse("手机号码已存在!");
		}
		String webCode = request.getParameter("code");
		String code = SessionMgr.getWebCode(request);
		if (StringUtils.isEmpty(webCode)
				|| !webCode.equalsIgnoreCase(SessionMgr.getWebCode(request))) {
			return new FailedResponse("图片验证码错误!");
		}
		String rePassword = request.getParameter("rePassword");
		if (!rePassword.equals(user.getUserPassword())) {
			return new FailedResponse("2次密码输入不一致!");
		}
		// 初始账户余额为100/0
		BigDecimal account = new BigDecimal(0);
		user.setNormAccountBalance(account);
		user.setUserLeval(6);
		// 1：正常 2 ：冻结 3 ：商家升级审核中 4 普通用户
		user.setUserStatus(1);
		user.setNormCarCompanyId(0);
		user.setNormCarTypeId(0);
		user.setNormRealName("");
		user.setNormStatus(1);
		user.setNormRegisteType(2);
		/*
		 * user.setNormPayPassword(""); user.setNormDeviceId("");
		 * user.setNormEmail(""); user.setNormAddress("");
		 */
		try {
			userDao.insert(user);
			userDao.insertNormalUser(user);
		} catch (Exception e) {
			return new ErrorResponse();
		}
		// 保存session
		SessionMgr.addWebUser(request, user);
		// 返回信息
		return new ResultResponse(user);
	}

	/**
	 * @param params
	 *            新密码 用户id
	 * @return
	 * @Title: resetPasswrod
	 * @Description: 重置密码
	 */
	@Override
	public Response<?> resetPasswrod(Map<String, Object> params,
			HttpServletRequest request) {
		String msgCode = (String) params.get("msgCode");
		String userAccount = (String) params.get("userAccount");
		if (StringUtils.isEmpty(msgCode)) {
			return new FailedResponse("短信验证码不能为空!");
		}
		if (StringUtils.isEmpty(userAccount)) {
			return new FailedResponse("手机号不能为空!");
		}
		String userPassword = request.getParameter("userPassword");
		String rePassword = MD5Utils.Md5(request.getParameter("rePassword"));
		if (!rePassword.equals(userPassword)) {
			return new FailedResponse("2次密码输入不一致!");
		}
		TblUser user = new TblUser();
		user.setUserAccount(userAccount);
		user.setUserPassword(userPassword);
		return userDao.update(user) == 1 ? new SuccessResponse()
				: new FailedResponse("用户不存在!");
	}

	/**
	 * 获取用户信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public TblUser getUserInfo(TblUser user) {
		return userDao.getByAccount(user);
	}

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的评论
	 */
	@Override
	public List<MyProductcomment> findProComments(Map<String, Object> params) {
		List<MyProductcomment> myProductcommentList = new ArrayList<MyProductcomment>();

		List<TblProductcomment> commentList = productcommentMapper
				.findProCommentsByUser(params);
		for (TblProductcomment tblProductcomment : commentList) {
			MyProductcomment myProductcomment = new MyProductcomment();
			myProductcomment.setProductCommentId(JudgeNullUtils
					.nvlStr(tblProductcomment.getPkProductcomment()));
			myProductcomment.setPrCoContent(JudgeNullUtils
					.nvlStr(tblProductcomment.getPrcoContent()));
			myProductcomment.setPrCoCreatedate(DateUtil.toDateFormat(
					tblProductcomment.getPrcoCreatetime(), "yyyy-MM-dd HH:mm"));
			myProductcomment.setPrCoUserName(JudgeNullUtils
					.nvlStr(tblProductcomment.getPrcoUsername()));
			myProductcomment.setPrCoUserImage(JudgeNullUtils
					.nvlStr(tblProductcomment.getUserImage()));
			myProductcommentList.add(myProductcomment);
		}
		return myProductcommentList;
	}

	@Override
	public long countProComments(Map<String, Object> params) {
		return productcommentMapper.countProCommentsByUser(params);
	}

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 删除产品评论
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
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的足迹
	 */
	@Override
	public List<TblFootprint> getMyFootPrint(TblFootprint tblFootprint) {
		return tblFootprintMapper.find(tblFootprint);
	}

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的消费和收益明细
	 */
	@Override
	public List<WalletRecord> findMyWalletList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return tblPurchasehistoryMapper.findWallet(params);
	}

	@Override
	public long countWallet(Map<String, Object> params) {
		return tblPurchasehistoryMapper.countWallet(params);
	}

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的消费明细
	 */
	@Override
	public List<WalletRecord> findConsumeList(Map<String, Object> params) {
		// 01获取消费记录
		List<WalletRecord> consumeRecordList = new ArrayList<WalletRecord>();
		List<TblPurchasehistory> purchasehistoryList = tblPurchasehistoryMapper
				.findPage(params);
		for (TblPurchasehistory tblPurchasehistory : purchasehistoryList) {
			WalletRecord walletRecord = new WalletRecord();
			walletRecord.setRecordContent(JudgeNullUtils
					.nvlStr(tblPurchasehistory.getPuhiPurchasecontent()));
			walletRecord.setRecordMoney(JudgeNullUtils
					.nvlStr(tblPurchasehistory.getPuhiMonetary()));
			walletRecord.setRecordTitle(JudgeNullUtils
					.nvlStr(tblPurchasehistory.getPuhiType()));
			walletRecord.setRecordTime(DateUtil.toDateFormat(
					tblPurchasehistory.getPuhiPurchasehistorytime(),
					"yyyy-MM-dd HH:mm:ss"));
			consumeRecordList.add(walletRecord);
		}
		return consumeRecordList;
	}

	@Override
	public long countConsume(Map<String, Object> params) {
		return tblPurchasehistoryMapper.countConsume(params);
	}

	/**
	 * @return
	 * @Title: findProComments
	 * @Description: 获取我的收益明细
	 */
	@Override
	public List<WalletRecord> findEarningList(Map<String, Object> params) {
		// 02：获取收益记录
		List<WalletRecord> earningsRecordList = new ArrayList<WalletRecord>();
		List<TblEarnings> earningsList = tblEarningsMapper.findPage(params);
		for (TblEarnings tblEarnings : earningsList) {
			WalletRecord walletRecord = new WalletRecord();
			walletRecord.setRecordContent(JudgeNullUtils.nvlStr(tblEarnings
					.getEarContent()));
			walletRecord.setRecordMoney(JudgeNullUtils.nvlStr(tblEarnings
					.getEarMonetary()));
			walletRecord.setRecordTitle(JudgeNullUtils.nvlStr("5"));
			walletRecord.setRecordTime(DateUtil.toDateFormat(
					tblEarnings.getEarPurchasehistorytime(),
					"yyyy-MM-dd HH:mm:ss"));
			earningsRecordList.add(walletRecord);
		}
		return earningsRecordList;
	}

	@Override
	public long countEarning(Map<String, Object> params) {
		return tblEarningsMapper.countEarning(params);
	}

	/**
	 * 图片验证码
	 *
	 * @param request
	 * @param response
	 */
	@Override
	public void createValidCode(HttpServletRequest request,
			HttpServletResponse response) {

		int width = 0, height = 0;
		try {
			width = Integer.parseInt(request.getParameter("width"));
			height = Integer.parseInt(request.getParameter("height"));
		} catch (Exception e) {
		}

		Map<String, BufferedImage> map = ImageUtil.createImage(width == 0 ? 115
				: width, height == 0 ? 40 : height);
		String code = map.keySet().iterator().next();
		// 获取image流
		BufferedImage image = map.get(code);
		// 保存key
		SessionMgr.addWebCode(request, code);
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 短信发送
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public Response<?> sendMsg(String phone, HttpServletRequest request) {
		String code = ProcessInfoCommon.getDigitRandomKey(6);
		BluemobiCommon.sendWanMatMessage("您的验证码是：" + code + "。请不要把验证码泄露给其他人。",
				phone);
		String result = "002";
		// 发送短信
		if (!result.equals(BluemobiCommon.MSG_SEND_RESULT_OK))
			return new FailedResponse("短信发送失败!");
		// 保存短信验证码
		Map<String, String> map = new HashMap<String, String>();
		map.put(phone, code);
		SessionMgr.addMsgCode(map, request.getSession());
		return new SuccessResponse();
	}

	/**
	 * 更新用户资料
	 *
	 * @param userinfo
	 * @param request
	 * @return
	 */
	@Override
	public Response<?> updateUserInfo(TblUser user, HttpServletRequest request) {
		String filePath = "";
		// 上传头像
		if (user.getTitleMultiFile() != null
				&& user.getTitleMultiFile().getSize() > 0) {

			MultipartFile file = user.getTitleMultiFile();

			filePath = MultipartFileUtil.uploadSingleFile(user,
					CommonConsts.USER_NORMALUSE,
					String.valueOf(user.getUserId()), true);
			/*
			 * //以下为冗余代码 userinfo.setUsinHeadimage(filePath); //冗余代码结束
			 */}
		if (StringUtils.isBlank(user.getUserAccount())
		/* || StringUtils.isBlank(userinfo.getUsinEmail()) */) {
			return new FailedResponse("用户账号有误");
		}
		if (userDao.updateNormalUser(user) != 1) {
			return new FailedResponse();
		}
		user = userDao.get(user);
		SessionMgr.addWebUser(request, user);
		return new ResultResponse<TblUser>(user);
	}

	/**
	 * 充值
	 *
	 * @param tblUserinfo
	 * @return
	 */
	@Override
	public Response<?> userRecharge(TblUser user, HttpServletRequest request) {
		// parem error
		if (user == null || user.getUserId() == null
				|| user.getNormAccountBalance() == null) {
			return new ErrorResponse("error.msg.invalid.parameter");
		}
		try {
			TblUser tempUser = userDao.get(user);
			// 查无此人
			if (tempUser == null) {
				return new ErrorResponse("error.msg.invalid.user");
			}

			tempUser.setNormAccountBalance(tempUser.getNormAccountBalance()
					.add(user.getNormAccountBalance()));
			userDao.updateNormalUser(tempUser);
			// session update
			SessionMgr.addWebUser(request, tempUser);
			return new SuccessResponse();
		} catch (Exception e) {
			// 返回登录信息错误信息
			return new ErrorResponse("钱数过大!");
		}
	}

	@Override
	public List<HashMap<String, Object>> getCommentsByUserId(
			Map<String, Object> params) {
		// TODO Auto-generated method stub

		return productcommentMapper.getProCommentsByUserId(params);
	}

	@Override
	public long countComments(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return productcommentMapper.countComments(params);
	}

	@Override
	public HashMap<String, Object> getCommentById(int pkComment) {
		// TODO Auto-generated method stub
		return productcommentMapper.getCommentById(pkComment);
	}

	/**
	 * 删除给定的评论
	 * 
	 * @param cId
	 * @param type
	 */
	@Override
	public void removeMyCommentN(String cId, String type) {
		if ("1".equals(type)) {
			webElecPileCommentMapper.delete(Integer.parseInt(cId));
		} else if ("2".equals(type)) {
			powerStationCommentMapper.delete(Integer.parseInt(cId));
		}
	}

	@Override
	public Boolean addReChargeRecord(HttpServletRequest request) {
		log.error("addReChargeRecord start...");
		String tradeNo = request.getParameter("out_trade_no");
		String totalFee = request.getParameter("total_fee");
		//body参数格式:userId_充值类型_订单id
		String[] extArray = request.getParameter("body").split("_");
		String userId="",puhiType="",orderId="";
		if(extArray.length==1){
			userId=extArray[0];
			puhiType="4";
			orderId="";
		}else{
			userId=extArray[0];
			puhiType=extArray[1];
			orderId=extArray[2];
		}
		log.error("addReChargeRecord2 1");
		
		String puhiPurchasecontent="支付宝充值";
		if("5".equals(puhiType)){
			puhiPurchasecontent="发票运费(支付宝)";
		}
		log.error("addReChargeRecord2 2");
		if("4".equals(puhiType)&&!validFee(totalFee)){
			log.error("addReChargeRecord2 2-1");
			CommitLog commitLog = new CommitLog();
			commitLog.setLogName(puhiPurchasecontent);
			commitLog.setLogContent(request.getParameter("total_fee") );
			commitLog.setCoLoUserId(userId);
			chargeErrorLog(commitLog, request);
			return false;
		}
		log.error("addReChargeRecord2 3");
		TblPurchasehistory purchase = new TblPurchasehistory();
		purchase.setPuhiUserid(new Integer(userId));
		purchase.setPuhiType(new Integer(puhiType));
		purchase.setPuhiMonetary(new BigDecimal(totalFee));
		purchase.setPuhiPaymentNumber(tradeNo);
		purchase.setPuhiConsumerremark(tradeNo);
		Date d = new Date();
		purchase.setPuhiPurchasehistorytime(d);
		purchase.setPuhiCreatedate(d);
		purchase.setPuhiUpdatedate(d);
		purchase.setPuhiPurchasecontent(puhiPurchasecontent);
		purchase.setPuhiChargeType(1);
		log.error("addReChargeRecord2 4");
		//开发票
		if(puhiType.equals("5")){
			log.error("addReChargeRecord2 4-1");
			TblInvoice iv=new TblInvoice();
			iv.setPkInvoice(new Long(orderId));
			iv.setIvPayFreight(1);
			iv.setIvInvoiceStatus(0);
			iv.setIvFreightAmount(new BigDecimal(totalFee));
			invoiceDao.updateInvoice(iv);
			log.error("addReChargeRecord2 4-2");
		}
		return userRecharge(purchase, request);
	}

	@Override
	public Boolean addReChargeRecordForTenpay(Map<String, Object> map,
			HttpServletRequest request) {
		log.error("addReChargeRecordForTenpay2 start...");
		String tradeNo = (String) map.get("out_trade_no");
		String totalFee = (String) map.get("total_fee");
		log.error("addReChargeRecordForTenpay2 step1");
		BigDecimal fee = new BigDecimal(totalFee).divide(new BigDecimal("100"),
				2, RoundingMode.UP);
		log.error("addReChargeRecordForTenpay2 step2");
		String extString = tradeNo.split("ech")[1];
		String[] extArray = extString.split("_");
		String userId="",puhiType="",orderId="";
		if(extArray.length==1){
			userId=extArray[0];
			puhiType="4";
			orderId="";
		}else{
			userId=extArray[0];
			puhiType=extArray[1];
			orderId=extArray[2];
		}
		String puhiPurchasecontent="微信充值";
		if("5".equals(puhiType)){
			puhiPurchasecontent="发票运费(微信)";
		}
		log.error("addReChargeRecordForTenpay2 step2");
		if("4".equals(puhiType)&&!validFee(fee.toString())){
			log.error("addReChargeRecordForTenpay2 step2-1");
			CommitLog commitLog = new CommitLog();
			commitLog.setLogName(puhiPurchasecontent);
			commitLog.setLogContent(fee.toString());
			commitLog.setCoLoUserId(userId);
			chargeErrorLog(commitLog, request);
			return false;
		}
		log.error("addReChargeRecordForTenpay2 step3");
		TblPurchasehistory purchase = new TblPurchasehistory();
		purchase.setPuhiUserid(new Integer(userId));
		purchase.setPuhiType(new Integer(puhiType));
		purchase.setPuhiMonetary(fee);
		purchase.setPuhiPaymentNumber(tradeNo);
		purchase.setPuhiConsumerremark(tradeNo);
		

		
		Date d = new Date();
		purchase.setPuhiPurchasehistorytime(d);
		purchase.setPuhiCreatedate(d);
		purchase.setPuhiUpdatedate(d);
		purchase.setPuhiChargeType(2);
		purchase.setPuhiPurchasecontent(puhiPurchasecontent);
		log.error("addReChargeRecordForTenpay2 step4");
		//开发票
		if(puhiType.equals("5")){
			log.error("addReChargeRecordForTenpay2 step4-1");
			TblInvoice iv=new TblInvoice();
			iv.setPkInvoice(new Long(orderId));
			iv.setIvPayFreight(2);
			iv.setIvInvoiceStatus(0);
			iv.setIvFreightAmount(fee);
			invoiceDao.updateInvoice(iv); 
		}
		log.error("addReChargeRecordForTenpay2 step5");
		return userRecharge(purchase, request);

	}

	private Boolean userRecharge(TblPurchasehistory purchase,
			HttpServletRequest request) {
		log.error("userRecharge start...");
		// 用户账户添加余额
		TblUser user = new TblUser();
		user.setUserId(purchase.getPuhiUserid().longValue());
		user = userDao.get(user);
		user.setNormAccountBalance(user.getNormAccountBalance().add(
				purchase.getPuhiMonetary()));
		if (user.getNormAccountBalance().intValue() > 1000000) {
			return false;
		}
		// 添加充值记录
		boolean flag = false;//用来判断是否享受新用户充值送活动
		long count = tblPurchasehistoryMapper.getPurchaseHistory(purchase);
		if (count == 0&&purchase.getPuhiType()==4) {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("actRule", 8);
			List<Map<String, Object>> newGenerateList=couponMapper.getGenerateCouponList(params);
			if(newGenerateList != null && newGenerateList.size() > 0){//新用户充值活动存在
				TblPurchasehistory newPurchase = new TblPurchasehistory();
				newPurchase.setPuhiUserid(purchase.getPuhiUserid());
				newPurchase.setAct_beginTime(DateUtil.parse(newGenerateList.get(0).get("act_beginDate").toString(),"yyyy-MM-dd"));
				long purchaseCount= tblPurchasehistoryMapper.getPurchaseHistory(newPurchase);
				if(purchaseCount==0){
					chargeReturnCoupon(user.getUserId(), purchase.getPuhiMonetary().doubleValue(),newGenerateList);//新用户充值送活动
					flag = true;
				}
			}
			params.put("actRule",5);
			List<Map<String, Object>> generateList=couponMapper.getGenerateCouponList(params);
			if(generateList !=null && generateList.size() >0 && flag==false){
				TblPurchasehistory newPurchase = new TblPurchasehistory();
				newPurchase.setPuhiUserid(purchase.getPuhiUserid());
				newPurchase.setAct_beginTime(DateUtil.parse(generateList.get(0).get("act_beginDate").toString(),"yyyy-MM-dd"));
				long purchaseCount = tblPurchasehistoryMapper.getPurchaseHistory(newPurchase);
				if(purchaseCount>0){
					chargeReturnCoupon(user.getUserId(), purchase.getPuhiMonetary().doubleValue(),generateList);//充值送活动
				}
			}
			
			// 添加充值记录
			int num = tblPurchasehistoryMapper.insert(purchase);
			userDao.updateNormalUser(user);
			SessionMgr.addWebUser(request, user);
			log.error("userRecharge end...");
			return true;
		} else {
			log.error("userRecharge error...");
			return false;
		}
	}

	public TblUser getUserById(TblUser user) {
		return userDao.get(user);
	}

	// 充值异常日志记录
	public void chargeErrorLog(CommitLog commitLog, HttpServletRequest request) {
		String totalFee = commitLog.getLogContent();
		commitLog.setIpAddress(getIpAddr(request));
		commitLog.setCreateDate(new Date());
		commitLog.setUpdateDate(new Date());
		commitLog.setLogContent("充值金额为:" + totalFee);
		commitLogDao.insert(commitLog);
		TblUser user = new TblUser();
		user.setUserId(new Long(commitLog.getCoLoUserId()));
		user = getUserById(user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*BluemobiCommon.sendWanMatMessage("[告警] " + sdf.format(new Date())
				+ "有一条异常充值记录，充值金额为：" + totalFee + "元，请尽快查看解决。 ",
				user.getUserAccount());*/
		Map smsParams=new HashMap();
		smsParams.put("order",  sdf.format(new Date()));
		smsParams.put("num",  totalFee);
		AliSMS.sendAliSMS(user.getUserAccount(), "totalFee", JSON.toJSONString(smsParams));
	}
	
	private boolean validFee(String totalFee) {
		return true;
//		if ("0.01".equals(totalFee)||"10".equals(totalFee) || "20".equals(totalFee)
//				|| "50".equals(totalFee) || "100".equals(totalFee)
//				|| "200".equals(totalFee) || "500".equals(totalFee)) {
//			return true;
//		}
//		return false;
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 充值送(规则解析：对于任意一次充值，始终只有一条最优规则，并且每个用户具有最高额限定)
	 * @param uId 用户id
	 * @param cv 充值金额
	 * @param generateList 
	 */
	private void chargeReturnCoupon(long uId,double cv, List<Map<String, Object>> generateList){
		Map<String, Object> params=new HashMap<String, Object>();
		if(generateList != null && generateList.size() > 0){
			/***************************单人最高额限定开始**********************************/
			params.put("uId", uId);
			params.put("bgDate", generateList.get(0).get("act_beginDate"));
			params.put("edDate", generateList.get(0).get("act_endDate"));
			Double chargeSumB = couponMapper.getPersonChargeSum(params);
			double chargeSum = chargeSumB == null? 0:chargeSumB;
			double topMoney = Double.valueOf(generateList.get(0).get("act_top_money").toString());
			double divisor = cv;
			if(chargeSum+cv > topMoney){
				divisor = topMoney - chargeSum;
			}
			/***************************单人最高额限定结束**********************************/
			if(divisor > 0){
				for(int i=0;i<generateList.size();i++){
					divisor += 0.01;
					Map<String, Object> obj0 = generateList.get(i);
					/***************************最优选开始**********************************/
					String singleMoneyStr = obj0.get("act_single_money").toString();
					int n = (int) Math.floor(divisor/Double.valueOf(singleMoneyStr));
					if(n >= 1){
						int acscNum=0;//生成数量
						int couponTerm=0;//有效期
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Date d=new Date();
						List<Map<String, Object>> list=null;
						for(int j=i;j<generateList.size();j++){
							Map<String, Object> obj = generateList.get(j);
							if(!singleMoneyStr.equals(obj.get("act_single_money").toString()))
								return;
							acscNum=((Integer)obj.get("acsc_num"))*n;
							obj.put("cp_UserId", uId);
							obj.put("cp_Status", 0);
							obj.put("cp_CouponCode", "");
							obj.put("cp_BeginDate", sdf.format(d));
							obj.put("cp_EndDate", obj.get("act_CouponEndDate"));
							list=new ArrayList<Map<String,Object>>();
							for(int k=0;k<acscNum;k++){
								list.add(obj);
							}
							couponMapper.generateCode(list);
						}
						/***************************最优选结束**********************************/
					}
					// 获取用户推送信息
					TblJpush jpush = jpushService.getByuserInfo(Integer.valueOf(String.valueOf(uId)));
					DataModel dm= JPushUtil.point2point("新的优惠券", "",
							 jpush.getJpushRegistrationid(),jpush.getJpushDevicetype(), "10");
					log.error(dm.getMsg()+"设备唯一标识："+jpush.getJpushRegistrationid());
				}
			 }
	 }
	}	
}
