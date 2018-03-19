package com.wanma.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.model.PinCode;
import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.JPushUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.bluemobi.product.web.WebFilter;
import com.wanma.app.service.AppGovBusiCheckUserService;
import com.wanma.app.service.AppJpushService;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.AuthCodeService;
import com.wanma.app.util.Base64Coder;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblJpush;
import com.wanma.model.TblUser;

/**
 * @description : APP用户信息处理控制器
 * @Author: songjf
 * @Date: 2015年03月12日
 */
@Controller
@RequestMapping("/gov/check")
public class GovBusiCheckController extends BaseController {
	private static Log log = LogFactory.getLog(GovBusiCheckController.class);
	/** 用户信息业务处理对象 */
	@Autowired
	private AppGovBusiCheckUserService CheckUserService;
	/** 极光推送业务处理对象 */
	@Autowired
	private AppJpushService jpushService;
	/** 用户信息业务处理对象 */
	@Autowired
	private AppUserinfoService userinfoService;
	/** 验证码业务处理对象 */
	@Autowired
	AuthCodeService authCodeService;

	/**
	 * 用户登录验证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String checkSms(@RequestParam Map<String, Object> params) {
		log.info("******************用户登录-begin************************");
		TblUser tbluser = new TblUser();
		String name = (String) params.get("userName");
		String password = (String) params.get("passoword");
		if (StringUtil.isEmpty(name)||StringUtil.isEmpty(password)){
			return new AccessErrorResult(1001,"账号不能为空").toString();
		}
		tbluser.setUserAccount(name);
		log.info("开始验证账号信息");
		Map<String, Object> user = CheckUserService.checkUser(tbluser);
		if (null==user || StringUtils.isEmpty(user.get("userId"))) {
			return new AccessErrorResult(Integer.parseInt(CommonConsts.USER_STATUS_CHECK_ERROR_USER), "非爱充政企版用户").toString();
		} else  {
			Object userId = user.get("userId");
			boolean ok = user.get("userPassword").equals(password);
			if (ok == true) {
			 //获取设备唯一标识
			 String did = params.get("did").toString();
				if (!StringUtils.isEmpty(did)) {
					byte[] b = Base64Coder.decode(did);
					did = WebFilter.judgeRequest(new String(b));
					params.put("did", did);
				} else {
					params.put("did", "");
				}
			 params.put("userId", user.get("userId"));
			 CheckUserService.updateUserDeviceId(params);
			// 推送唯一标示
			String jpushRegistrationid = (String) params.get("jpushRegistrationid");
			// 设备类型
			String jpushDevicetype = (String) params.get("jpushDevicetype");
			TblJpush jpush = jpushService.getByuserInfo(Integer.parseInt(String.valueOf(userId)));
			if (!ObjectUtil.isEmpty(jpush)) {
				String oldRegId = jpush.getJpushRegistrationid();
				if (!StringUtils.isEmpty(oldRegId) && !oldRegId.equals(jpushRegistrationid)) {
					JPushUtil.loginPushNotifyByRegId("", "", oldRegId, jpush.getJpushDevicetype());
				}
				jpush.setJpushRegistrationid(jpushRegistrationid);
				jpush.setJpushDevicetype(jpushDevicetype);
				jpushService.update(jpush);
			} else {
				jpush = new TblJpush();
				jpush.setJpushRegistrationid(jpushRegistrationid);
				jpush.setJpushUserinfo(Integer.parseInt(String.valueOf(userId)));
				jpush.setJpushDevicetype(jpushDevicetype);
				jpushService.insert(jpush);
			  }
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("userId",userId );
				data.put("userAccount",user.get("userAccount"));
				data.put("cpyId", user.get("cpyId"));
				data.put("balance", user.get("balance"));
				data.put("tradeType", user.get("tradeType"));
				data.put("billId", user.get("billId"));
				data.put("cpyNumber", user.get("cpyNumber"));
				data.put("pamymentRule", user.get("pamymentRule"));
			   log.info("******************用户登录-end************************");
				return new AccessSuccessResult(data).toString();
			} else {
				log.info("******************密码错误************************");
				return new AccessErrorResult(Integer.parseInt(CommonConsts.USER_STATUS_CHECK_ERROR_PASSWORD), "密码不正确").toString();
			  }
		   }
	}
    /**
     * 退出登录
     * @param params
     * @return
     */
	@RequestMapping("/logOut")
	@ResponseBody
	public String loginOut(@RequestParam Map<String, Object> params) {
		                  params.put("did", "");
		     log.info("******************用户退出登录-begin************************");
		try {
			CheckUserService.updateUserDeviceId(params);
			TblJpush jpush = jpushService.getByuserInfo(Integer.parseInt(params
					.get("userId").toString()));
			if (null != jpush && !"".equals(jpush.getJpushRegistrationid())) {
				jpush.setJpushRegistrationid("");
				jpush.setJpushDevicetype("");
				jpushService.update(jpush);
			}
		} catch (Exception e) {
			log.info("******************用户退出登录失败-end************************");
			log.error(e.getMessage());
			return new AccessErrorResult(Integer.parseInt(CommonConsts.USER_STATUS_CHECK_ERROR), "退出失败,请稍后再试!").toString();
		}
		log.info("******************用户退出登录-end************************");
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 3:密码重置
	 * @param params
	 * @return
	 */
	@RequestMapping("/retrievePassword")
	@ResponseBody
	public String retrievePassword(@RequestParam Map<String, Object> params){
		log.info("******************重置密码-begin************************");
		try {
			// 手机号码
			String usinPhone = (String) params.get("usinPhone");
			
			// 验证是否合格手机号
			Pattern p = Pattern.compile("^[1]\\d{10}$");
			Matcher m = p.matcher(usinPhone);
			if (!m.find()) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_PHONE_INVALID), "手机号格式不对")
						.toString();
			}
			// 判断此号码是否被绑定
			int isExist = CheckUserService.userIsExist(usinPhone);
			if (isExist < 1) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_PHONE_NO), "没有绑定手机号").toString();
			}
			//判断是否是政企版用户
			int count = CheckUserService.userPhone(usinPhone);
			if(count <1){
				return new AccessErrorResult(Integer.parseInt(CommonConsts.USER_STATUS_CHECK_ERROR_USER), "非爱充政企版用户").toString();
			}
			// 短信验证码
			String authCode = (String) params.get("authCode");
			// 是否输入验证码
			if (StringUtil.isEmpty(authCode)) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_NO), "验证码为空").toString();
			}
			// 检查验证码是否正确或超时(0正确  1验证码错误  2超时  3无效)
			int validity = userinfoService.checkAuthCode(authCode, usinPhone);
			if (validity == 1) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ERROR), "验证码错误,请重新输入").toString();
			}
			if (validity == 2) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ERROR_NG), "验证码已失效，请重新获取").toString();
			}
			if (validity == 3) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ON_ERROR), "无效验证码").toString();
			}
			CheckUserService.resetPasswrod(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************重置密码-end************************");
		return new AccessSuccessResult().toString();
	}
	
	
	/**
	 *  4:校验验证码是否正确
	 * @param params
	 * @return
	 */
	@RequestMapping("/checkAuthCode")
	@ResponseBody
	public String checkAuthCode(@RequestParam Map<String, Object> params) {
		log.info("******************检查验证码是否正确-begin************************");
		try {
			// 验证码
			String authCode = (String) params.get("authCode");
			log.error("验证码为：" + authCode + ",等待检查");
			if (StringUtil.isEmpty(authCode)) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_NO), "验证码为空").toString();
			}
			// 手机号
			String usinPhone = (String) params.get("usinPhone");
			if (StringUtil.isEmpty(usinPhone)) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_NO_PHONE), "手机号为空").toString();
			}
			// 检查验证码是否正确或超时(0正确  1验证码错误  2超时  3无效)
			int validity = userinfoService.checkAuthCode(authCode, usinPhone);
			log.error("验证结果validity为：" + validity + "");
			if (validity == 1) {
				log.error("返回error.msg.error.auth_code");
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ERROR), "验证码错误").toString();
			}
			if (validity == 2) {
				log.error("返回error.msg.timeout.auth_code");
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ERROR_NG), "验证码超时").toString();
			}
			if (validity == 3) {
				log.error("返回error.msg.invalid.auth_code");
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ON_ERROR), "无效验证码").toString();
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		log.info("******************检查验证码是否正确-end************************");
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 5:获取验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
	@ResponseBody
	public String getAuthCode(HttpServletRequest request) {
		// 手机号码
		String mobileNumber = RequestParamUtil.getEncodeParam(request,"mobileNumber");
		// 未输入手机号
		if (StringUtil.isEmpty(mobileNumber)) {
			// 返回未输入手机号错误信息
			return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_NO_PHONE), "手机号为空")
					.toString();
		}
		// 验证是否合格手机号
		Pattern p = Pattern.compile("^[1]\\d{10}$");
		Matcher m = p.matcher(mobileNumber);
		if (!m.find()) {
			return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_PHONE_INVALID), "手机号格式不对")
					.toString();
		}

		// 限制手机发送的次数和频率
		PinCode pinCode = WanmaConstants.pinCodes.get(mobileNumber);
		String code = ProcessInfoCommon.getDigitRandomKey(4);
		if (pinCode != null && !mobileNumber.equals("getAuthCode")) {
			if (!isPinCodeCountLimit(pinCode)) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_DEGREE), "发送验证码次数已达上限").toString();
			}
			if (!isNotPinCodeInterval(pinCode)) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_INTERVAL), "发送验证码时间间隔过短").toString();
			}
			pinCode.setCode(code);
			pinCode.setCount(pinCode.getCount() + 1);
			pinCode.setCreatetime(System.currentTimeMillis());
		} else {
			pinCode = new PinCode();
			pinCode.setCode(code);
			pinCode.setCreatetime(System.currentTimeMillis());
			pinCode.setCount(1);
			pinCode.setDay(Integer.parseInt(new SimpleDateFormat("yyMMdd")
					.format(new Date())));
		}
		try {
			// 生成系统验证码
			String flag = authCodeService.sendAuthCode(mobileNumber, code);
			if (flag.equals(BluemobiCommon.MSG_SEND_RESULT_NG)) {
				return new AccessErrorResult(Integer.parseInt(BluemobiCommon.MSG_SEND_RESULT_NG), "短信发送失败").toString();
			}
			WanmaConstants.pinCodes.put(mobileNumber, pinCode);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			// 返回登录信息错误信息
			return new AccessErrorResult(1001, "error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
	
	/********
	 * 检测一天的验证码发送次数是否到达上限 检测逻辑为 如果来请求验证码跟最后一次发送验证码不是同一天 则将发送次数计数器清零 如果最后一次请求跟当次
	 * 则判断当天的发送次数是否到达上限
	 * 
	 * @param Pincode
	 *            pinCode 验证码对象
	 * @return boolean
	 * 
	 *****/
	private boolean isPinCodeCountLimit(PinCode pinCode) {
		int day = pinCode.getDay();
		int count = pinCode.getCount();
		int today = Integer.parseInt(new SimpleDateFormat("yyMMdd")
				.format(new Date()));
		if (day < today) {
			pinCode.setDay(today);
			pinCode.setCount(0);
			return true;
		} else {
			// 每天只能发5条，基数从0开始
			if (count >= 5) {
				return false;
			} else {
				return true;
			}
		}
	}

	/****
	 * 检测两次请求验证码的时间间隔
	 * 
	 * @param Pincode
	 *            pinCode 验证码对象
	 * @return boolean
	 * ****/
	private boolean isNotPinCodeInterval(PinCode pinCode) {
		long lastTime = pinCode.getCreatetime();
		// 两次短信之间不能少于60秒
		if (System.currentTimeMillis() - lastTime <= 60000)
			return false;
		return true;
	}
	
	

}
