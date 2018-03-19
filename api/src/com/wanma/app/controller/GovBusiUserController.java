package com.wanma.app.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.GovBusiUserService;
import com.wanma.model.GovBusiUser;



/**
 * @description : APP政企版用户信息处理控制器
 * @Author: lyh
 * @Date: 2015年03月12日
 */
@Controller
@RequestMapping("/gov/user")
public class GovBusiUserController extends BaseController {
	private static Logger log = Logger.getLogger(GovBusiUserController.class);
	@Autowired
	private GovBusiUserService gbuService;
	/** 用户信息业务处理对象 */
	@Autowired
	private AppUserinfoService userinfoService;
	
	/**
	 * 获取政企版用户信息
	 */
	@RequestMapping("/getGovBusiUserInfo")
	@ResponseBody
	public String getGovBusiUserInfo(HttpServletRequest request) throws AppRequestErrorException {
		// 政企版用户ID
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		GovBusiUser info = new GovBusiUser();
		try {
			if (StringUtils.isBlank(userId)) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.AUTH_MAP_KEY_USER_ID), "用户ID不为空!").toString();
			}
			info = gbuService.getGovBusiUserInfo(userId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户信息失败", e);
			return new AccessErrorResult(Integer.parseInt(CommonConsts.PROCESS_STATUS_NG), "获取用户信息失败！").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(info).toString();
	}
	
	/**
	 * 绑定手机号
	 */
	@RequestMapping("/bindPhoneNum")
	@ResponseBody
	public String bindPhoneNum(HttpServletRequest request)  throws AppRequestErrorException{
		//获取手机号和验证码
		String usinPhone = RequestParamUtil.getEncodeParam(request, "usinPhone");
		String authCode = RequestParamUtil.getEncodeParam(request, "authCode");
		log.info("检查手机号和验证码是否为空......");
		if (StringUtils.isBlank(usinPhone) || StringUtils.isBlank(authCode)) {
			return new AccessErrorResult(Integer.parseInt(CommonConsts.USER_STATUS_CHECK_NO_USER_ACCOUND), "手机号和验证码不为空！").toString();
		}
		//检查手机号是否正确
		log.info("检查手机号是否正确-begin......");
		Pattern p = Pattern.compile("^[1]\\d{10}$");
		Matcher m = p.matcher(usinPhone);
		if (!m.find()) {
			return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_PHONE_INVALID), "手机号码格式错误，请重新输入！").toString();
		}
		log.info("检查手机号是否正确-end......");
		
		//检查手机号和验证码是否正确
		log.info("检查手机号和验证码是否正确-begin......");		
		try {			 
			log.info("验证码为：" + authCode + ",等待检查");
			// 检查验证码是否正确或超时
			int validity = userinfoService.checkAuthCode(authCode, usinPhone);
			log.error("验证结果validity为：" + validity + "");
			if (validity == 1) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ERROR), "验证码错误，请重新输入").toString();
			}
			if (validity == 2) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ERROR_NG), "验证码已失效，请重新获取").toString();
			}
			if (validity == 3) {
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_ON_ERROR), "验证码错误，请重新输入").toString();
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}
		//检查手机号是否已被绑定
		log.info("检查手机号是否已绑定-begin......");
		try {
			int	count = gbuService.getPhoneCount(usinPhone);
			if(count == 1){
				return new AccessErrorResult(Integer.parseInt(CommonConsts.CHECK_RESULT_PHONE_NO),"该手机号已被绑定！").toString();
			}
			log.info("检查手机号是否已绑定-end......");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户信息失败", e);		
			return new AccessErrorResult(Integer.parseInt(CommonConsts.PROCESS_STATUS_NG), "获取用户信息失败！").toString();
		}

		log.info("验证该用户是否存在............");
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		int user = gbuService.getUserById(userId);
		if(user == 0){
			return new AccessErrorResult(Integer.parseInt(CommonConsts.USER_STATUS_CHECK_NO_USER), "用户不存在！").toString();
		}
		//验证结束，绑定手机号
		log.info("******************验证完毕-绑定手机号-begin************************");
		
		int bindPhone = gbuService.bindPhoneNumber(userId,usinPhone);
		log.info("********************绑定手机号-end*******************************");
		if(bindPhone ==0){
			log.info("绑定手机号失败............");
			return new AccessErrorResult(2004, "绑定手机号失败！").toString();
		}
		return new AccessSuccessResult().toString();
		
	}
}
