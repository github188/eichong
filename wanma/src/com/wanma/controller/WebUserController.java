package com.wanma.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.DateUtil;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.AliSMS;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;
import com.wanma.service.impl.RedisService;
import com.wanma.web.support.utils.ImageUtil;

/**
 * @description : APP用户信息处理控制器
 * @Author: songjf
 * @Date: 2015年03月12日OIO
 */
@Controller
@RequestMapping("/web/user")
public class WebUserController extends BaseController {

	@Autowired
	private TblUserService tblUserService;
	@Autowired
	private RedisService redisService;

	/**
	 * 获取图片验证码
	 */
	@RequestMapping(value = "/getValidCode")
	public void getValiCode(HttpServletRequest request,
			HttpServletResponse response) {
		this.createValidCode(request, response);
	}

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
	 * @return
	 * @Title: resetPasswrod
	 * @Description: 重置密码
	 */
	@RequestMapping(value = "/resetPasswrod")
	@ResponseBody
	public String resetPasswrod(@RequestParam Map<String, Object> params,
			HttpServletRequest request) {
		DwzAjaxResult dwzResult = null;
		String msgCode = request.getParameter("msgCode");
		String phone = request.getParameter("phone");
		String[] authCodeArr = getRedisAuthCode(phone).split(":");
		String redisAuthCode = authCodeArr[1];
		
		if (!msgCode.equals(redisAuthCode)) {
			dwzResult = new DwzAjaxResult("300", "手机验证码错误!", "", "", "");
			return new JsonObject(dwzResult).toString();
		}
		
		long sendTime = new Long(authCodeArr[2]);
		if (System.currentTimeMillis() - sendTime >= 600000) {
			dwzResult = new DwzAjaxResult("300", "手机验证码已过期", "", "", "");
			return new JsonObject(dwzResult).toString();
		}

		if (StringUtils.isEmpty(msgCode)) {
			dwzResult = new DwzAjaxResult("300", "短信验证码不能为空!", "", "", "");
		}
		if (StringUtils.isEmpty(phone)) {
			dwzResult = new DwzAjaxResult("300", "手机号不能为空!", "", "", "");
		}
		String userPassword = request.getParameter("userPassword");
		String rePassword = request.getParameter("rePassword");
		if (!rePassword.equals(userPassword)) {
			dwzResult = new DwzAjaxResult("300", "2次密码输入不一致!", "", "", "");
		}
		TblUser user = new TblUser();
		user.setUserAccount(request.getParameter("userAccount"));
		try {
			user = tblUserService.findLoginUser(user);
			user.setUserPassword(userPassword);
			user.setUserLevel(null);
			tblUserService.updateUser(user);
			dwzResult = new DwzAjaxResult("200", "密码修改成功！", "", "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 发送短信接口
	 */
	@RequestMapping("/sendMsg")
	@ResponseBody
	public String sendMsg(
			@RequestParam(value = "phone", defaultValue = "") String phone,
			HttpServletRequest request) {
		DwzAjaxResult dwzResult = null;
		String authCode = getRedisAuthCode(phone).split(":")[1];
//		BluemobiCommon.sendWanMatMessage(new String("您的验证码：" + authCode.trim()
//				+ "打死都不要告诉别人。如非本人操作，不用理会，走你！"), phone);
//		dwzResult = new DwzAjaxResult("200", "短信发送成功!", "", "", "");
		JSONObject parValue = new JSONObject();
		parValue.put("code", authCode.trim());
		if(AliSMS.sendAliSMS(phone, "SMS_17070054", parValue.toString())){
			dwzResult = new DwzAjaxResult("200", "短信发送成功!", "", "", "");
		}else{
			dwzResult = new DwzAjaxResult("300", "短信发送失败!", "", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 根据用户名获取手机号
	 */
	@RequestMapping("/getPhoneByUserAccount")
	@ResponseBody
	public String getPhoneByUserAccount(TblUser user) {
		return tblUserService.findPhoneByAccount(user);
	}

	/**
	 * 手机号码验证
	 */
	@RequestMapping("/validPhone")
	@ResponseBody
	public String validPhone(TblUser user, HttpServletRequest request) {
		DwzAjaxResult dwzResult = null;
		if (tblUserService.findUserCountByPhone(user) == 0) {
			dwzResult = new DwzAjaxResult("300", "该手机号尚未注册!", "", "", "");
		}
		String phone = user.getPhone();
		// 验证是否合格手机号
		Pattern p = Pattern.compile("^[1][3578]\\d{9}$");
		Matcher m = p.matcher(phone);
		if (!m.find()) {
			dwzResult = new DwzAjaxResult("300", "手机号码不正确", "", "", "");
		}
		String[] authCodeArr = getRedisAuthCode(phone).split(":");
		int sendCount = new Integer(authCodeArr[0]);
		if (sendCount >= 5) {
			dwzResult = new DwzAjaxResult("300", "今天验证码发送已达到5条，请明天再试", "", "",
					"");
		} else {
			String authCode = ProcessInfoCommon.getDigitRandomKey(4);
			setRedisAuthCode(phone, authCode);
			dwzResult = new DwzAjaxResult("200", "验证码获取成功！", "", "", "");

		}
		return new JsonObject(dwzResult).toString();
	}

	private String getRedisAuthCode(String userAccount) {
		String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
		String str = redisService.strGet("app:authcode:" + userAccount + ":"
				+ currentDate);
		return StringUtils.isNotBlank(str) ? str : "0:000000:"
				+ (System.currentTimeMillis() - 60001);
	}

	/**
	 * 存储格式-count:code:time
	 */
	private void setRedisAuthCode(String userAccount, String authCode) {
		String authCodeStr = getRedisAuthCode(userAccount);
		int failNum = StringUtils.isNotBlank(authCodeStr) ? new Integer(
				authCodeStr.split(":")[0]) : 0;
		String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
		redisService
				.strSet("app:authcode:" + userAccount + ":" + currentDate,
						failNum + 1 + ":" + authCode + ":"
								+ System.currentTimeMillis());
	}

}
