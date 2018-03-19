/**     
 * @Title:  TblApplyEpController.java   
 * @Package com.wanma.controller.itf   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年12月2日 下午2:47:42   
 * @version V1.0     
 */
package com.wanma.controller.itf;

//import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wanma.dao.TblPurchasehistoryMapper;
import com.wanma.model.ChargeGive;
import com.wanma.model.TblUser;
import com.wanma.service.TblUserService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.MessageManager;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.SuccessResponse;
import com.wanma.support.utils.AliSMS;
import com.wanma.support.utils.HttpRequest;
import com.wanma.support.utils.RegexUtil;

/**
 * @desc 充值赠送Controller
 * @author cdy
 *
 */
@Controller
@RequestMapping("/itf/charge")
public class ChargeGiftController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private TblPurchasehistoryMapper tblPurchasehistoryMapper;
	@Autowired
	private TblUserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChargeGiftController.class);

	/**
	 * @Description: 初始化
	 * @return: ResponseBody
	 */
	@RequestMapping(value = "/init")
	@ResponseBody
	public String init(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date now = new Date();
		MessageManager manager = MessageManager.getMessageManager();
		String loginPhone = manager.getSystemProperties("chargeGiftPhone");
		String validPhone = redisService.strGet("app:chgift:phone");
		if (!loginPhone.equals(validPhone)) {
			return new FailedResponse(1050, "你想干啥子").toString();
		}
		String record = redisService.strGet("app:chgift:" + loginPhone);
		if (StringUtils.isNotBlank(record)) {
			String[] arr = record.split(":");
			try {
				Date d = sdf.parse(arr[0]);
				// 不超过2小时，不需要发登陆码
				if (now.getTime() - d.getTime() < 2 * 3600000) {
					return new SuccessResponse().toString();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// 初始化登陆码给登陆手机号
		Random random = new Random();
		int code = random.nextInt(899999) + 100000;
		// String message = new String("您的登陆码是：" + code
		// + "。请不要把登陆码泄露给其他人。如非本人操作，可不用理会！");
		// BluemobiCommon.sendWanMatMessage(message, loginPhone);
		Map smsParams = new HashMap();
		smsParams.put("code", code + "");
		AliSMS.sendAliSMS(loginPhone, "SMS_16845182",
				JSON.toJSONString(smsParams));
		redisService.strSet("app:chgift:" + loginPhone, sdf.format(now) + ":"
				+ code);
		return new SuccessResponse().toString();
	}

	/**
	 * @Description: 验证
	 * @return: ResponseBody
	 */
	@RequestMapping(value = "/valid")
	@ResponseBody
	public String valid(HttpServletRequest request) {
		String code = request.getParameter("code");
		if (checkCode(code)) {
			return new SuccessResponse().toString();
		}
		return new FailedResponse(1050, "登陆码错误！！！").toString();
	}

	private boolean checkCode(String code) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date now = new Date();
		MessageManager manager = MessageManager.getMessageManager();
		String loginPhone = manager.getSystemProperties("chargeGiftPhone");
		String validPhone = redisService.strGet("app:chgift:phone");
		// 登陆手机号验证
		if (!loginPhone.equals(validPhone)) {
			return false;
		}
		String record = redisService.strGet("app:chgift:" + loginPhone);
		if (StringUtils.isNotBlank(record)) {
			String[] arr = record.split(":");
			try {
				Date d = sdf.parse(arr[0]);
				// 不超过2小时，且验证码正确
				if (now.getTime() - d.getTime() < 2 * 3600000) {
					if (code.equals(arr[1]))
						return true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * @Description: 充值赠送操作--前置验证发送
	 * @return: ResponseBody
	 */
	@RequestMapping("/chargeGift")
	@ResponseBody
	public String chargeGift(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		String charge = request.getParameter("charge");
		String gift = request.getParameter("gift");
		String remark = request.getParameter("remark");
		String code = request.getParameter("code");
		String type = request.getParameter("type");

		if (!checkCode(code)) {
			return new FailedResponse(1001, "验证码出错").toString();
		}
		// 校验手机号
		if ("2".equals(type)) {
			if (new Double(charge) > 2000) {
				return new FailedResponse(1001, "普通账户充值金额不能高于2000元！")
						.toString();
			}
			if (!RegexUtil.isMobile(phone)) {
				return new FailedResponse(1001, "该账户名称输入错误！").toString();
			}

		} else if ("1".equals(type) && new Double(charge) > 1000000) {

			return new FailedResponse(1001, "大账户充值金额不能高于100万元！").toString();
		}

		if (RegexUtil.isNumeric(charge) && RegexUtil.isNumeric(gift)
				&& new Double(gift) <= 100) {
			Map params = new HashMap();
			params.put("phone", phone);
			params.put("charge", charge);
			params.put("gift", gift);
			params.put("remark", remark);
			params.put("type", type);

			MessageManager manager = MessageManager.getMessageManager();
			String url = manager.getSystemProperties("chargeGiftUrl");
			return HttpRequest.post(url, params);
		} else {
			return new FailedResponse(1050, "参数出错").toString();
		}

	}

	/**
	 * @Description: 充值赠送操作
	 * @return: ResponseBody
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/chargeGiftFinish")
	@ResponseBody
	public String chargeGiftFinish(HttpServletRequest request) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8"); 
		
		String phone = request.getParameter("phone");
		TblUser user = userService.getNormUserByAccount(phone);
		if (null == user) {
			return new FailedResponse(1001, "用户不存在").toString();
		}
		if (user.getUserStatus() == 2) {
			return new FailedResponse(1001, "用户被冻结").toString();
		}
		String charge = request.getParameter("charge");
		String gift = request.getParameter("gift");
		String remark = URLDecoder.decode(request.getParameter("remark"), "UTF-8");
		String type = request.getParameter("type");
		remark = StringUtils.isNotBlank(remark) ? remark : "";
		// 校验参数
		if (RegexUtil.isNumeric(charge) && RegexUtil.isNumeric(gift)
				&& new Double(gift) <= 100) {

			ChargeGive chargegive = new ChargeGive();
			chargegive.setCharge(charge);
			chargegive.setGift(gift);
			chargegive.setRemark(remark);
			chargegive.setType(type);

			userService.addCharge(chargegive, user);

			return new SuccessResponse().toString();
		} else {
			return new FailedResponse(1050, "参数出错").toString();
		}

	}

}
