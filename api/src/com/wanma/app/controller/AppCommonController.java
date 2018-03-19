/** 
 * FileName AppCommonController.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.wanma.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.model.PinCode;
import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.model.CodeDetail;
//import com.bluemobi.product.service.AreaService;
import com.bluemobi.product.service.GroupDetailService;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.AuthCodeService;
import com.wanma.common.WanmaConstants;

/**
 * FileName AppCommonController.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * APP公共处理控制器
 */
@Controller
@RequestMapping("/app/auth")
public class AppCommonController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(AppCommonController.class);

	/** 验证码业务处理对象 */
	@Autowired
	AuthCodeService authCodeService;

	/** 用户业务处理对象 */
	@Autowired
	private GroupDetailService groupDetailService;

	/** 省份业务处理对象 */
	// @Autowired
	// private ProvinceService provinceService;

	/** 区县业务处理对象 */
	// @Autowired
	// private AreaService areaService;
	/**
	 * 获取验证码处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param request
	 *            请求信息
	 * @return 处理结果JSON信息
	 * @throws 无
	 */
	@RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
	@ResponseBody
	public String getAuthCode(HttpServletRequest request) {
		// 手机号码
		String mobileNumber = RequestParamUtil.getEncodeParam(request,
				"mobileNumber");

		// 未输入手机号
		if (StringUtil.isEmpty(mobileNumber)) {
			// 返回未输入手机号错误信息
			return new AccessErrorResult(1001, "error.msg.empty.phone_number")
					.toString();
		}

		// 验证是否合格手机号
		log.info("手机号码:"+mobileNumber);
		Pattern p = Pattern.compile("^[1]\\d{10}$");
		Matcher m = p.matcher(mobileNumber);
		if (!m.find()) {
			return new AccessErrorResult(1001, "error.msg.invalid.phone_number")
					.toString();
		}

		log.info("手机号码:"+mobileNumber);
		// 限制手机发送的次数和频率
		PinCode pinCode = WanmaConstants.pinCodes.get(mobileNumber);
		String code = ProcessInfoCommon.getDigitRandomKey(4);
		if (pinCode != null && !mobileNumber.equals("getAuthCode")) {
			if (!isPinCodeCountLimit(pinCode)) {
				return new AccessErrorResult(1001, "error.msg.send.limit")
						.toString();
			}
			if (!isNotPinCodeInterval(pinCode)) {
				return new AccessErrorResult(1001, "error.msg.send.fast")
						.toString();
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
				return new AccessErrorResult(1001,
						"error.msg.send.auth_code_error").toString();
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

	/**
	 * 取得公共分类列表
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param request
	 *            请求信息
	 * @return 处理结果JSON信息
	 * @throws 无
	 */
	@RequestMapping(value = "/codeList", method = RequestMethod.GET)
	@ResponseBody
	public String categoryList(HttpServletRequest request) {

		// 分类组信息列表
		List<CodeDetail> codeList = null;
		// 分类组ID
		String codeGroupId = RequestParamUtil.getEncodeParam(request,
				"codeGroupId");

		// 未输入分类组ID
		if (StringUtil.isEmpty(codeGroupId)) {
			// 返回未输入分类组ID错误信息
			return new AccessErrorResult(1001, "error.msg.empty.code_group_id")
					.toString();
		}

		try {
			// 执行分类组信息查询处理
			codeList = groupDetailService.getCodeDetailList(codeGroupId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			// 返回登录信息错误信息
			return new AccessErrorResult(1001, "error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息以及分类组信息列表
		return new AccessSuccessResult(codeList).toString();

	}

}
