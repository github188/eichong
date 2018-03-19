package com.wanma.app.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.JPushUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.bluemobi.product.web.WebFilter;
import com.wanma.app.service.AppJpushService;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.CouponService;
import com.wanma.app.service.TblFeedbackService;
import com.wanma.app.service.impl.RedisService;
import com.wanma.app.util.Base64Coder;
import com.wanma.app.util.DateUtil;
import com.wanma.app.util.HttpRequestUtil;
import com.wanma.app.util.ImageUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.model.MyWallet;
import com.wanma.model.TblEarnings;
import com.wanma.model.TblJpush;
import com.wanma.model.TblProductcomment;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUserinfo;
import com.wanma.model.UserLoginRecord;
import com.wanma.model.Userinfo;
import com.wanma.service.CmsUserInfoService;
import com.wanma.app.dao.IntegralMapper;


/**
 * @description : APP用户信息处理控制器
 * @Author: songjf
 * @Date: 2015年03月12日
 */
@Controller
@RequestMapping("/app/user")
public class AppUserController extends BaseController {
	private static Logger log = Logger.getLogger(AppUserController.class);
	/** 用户信息业务处理对象 */
	@Autowired
	private AppUserinfoService userinfoService;
	/** 极光推送业务处理对象 */
	@Autowired
	private AppJpushService jpushService;
	// 借用后台的修改功能实现app端的用户资料修改
	@Autowired
	private CmsUserInfoService tblUserInfoService;
	@Autowired
	private TblFeedbackService userFeedbackService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private IntegralMapper pointMapper;

	private static String httpurl = "";
	
	static {
		
		MessageManager mmg = new MessageManager();
		httpurl = mmg.getSystemProperties("ims.url")+"/integral/addIntegralDetails4Api.do";
	}
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
	
	@RequestMapping("/activityStatus")
	@ResponseBody
	public String activityStatus(@RequestParam Map<String, Object> params) {
		
		log.info("*********查询积分是否开启--begin****************");
		if (StringUtils.isEmpty(params.get("userId"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
		try
		{
		    MessageManager mmg = new MessageManager();
		    // 0关闭 1开启 2部分开启
		    String status =  mmg.getSystemProperties("integralActivity_status");
		    log.info( mmg.getSystemProperties("integralActivity_status"));
		    Map<String,Object> result = new HashMap();
		    result.put("status", "0");
		    if(status.equals("1"))
		    {
		    	 result.put("status", "1");
		    }
		    if(status.equals("2"))
		    {
		    	String users =  mmg.getSystemProperties("integralActivity_users");
		        String[] uids =	users.split(",");
		        for(String s:uids){
                   if(s.equals(params.get("userId").toString()))
                   {
                	   result.put("status", "1");
                	   break;
                   }
                 }
		    }
		    
		    return new AccessSuccessResult(result).toString();
		    
		}catch (Exception e)
		{
			log.error(e.getMessage());
			return new AccessErrorResult(2004, "查询活动失败").toString();
		}
	}

	/**
	 * @Title: login
	 * @Description: 用户登录
	 * @param params
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam Map<String, Object> params,HttpServletRequest request) {
		log.info("******************用户登录-begin************************");
		// 登录用户信息
		TblUserinfo userinfo = new TblUserinfo();
		try {
			
			
			
			
			// lpw代表登陆密码
			String failNum = redisService.strGet("app:lpw:"	+ params.get("usinPhone") + ":num");
			String date = redisService.strGet("app:lpw:" + params.get("usinPhone") + ":d");
			String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
			if (currentDate.equals(date) && Integer.parseInt(failNum) >= 5) {
				return new AccessErrorResult(1001, "您已输错5次密码，请明天再试").toString();
			}
			if(!currentDate.equals(date)){
				redisService.strSet("app:lpw:" + params.get("usinPhone") + ":d",currentDate);
				redisService.strSet("app:lpw:" + params.get("usinPhone") + ":num", "0");
				failNum="0";
			}
			// 登录验证处理结果
			String processResult = userinfoService.checkLoginUser(params);

			// 登陆用户不存在
			if (CommonConsts.USER_STATUS_CHECK_NO_USER.equals(processResult)) {
				return new AccessErrorResult(1001, "error.msg.invalid.user").toString();
				// 密码错误
			} else if (CommonConsts.USER_STATUS_CHECK_FREEZE.equals(processResult)) {
				return new AccessErrorResult(1001, "该账户已被冻结，如有疑问请致电客服。").toString();
			} else if (CommonConsts.USER_STATUS_CHECK_ERROR_PASSWORD.equals(processResult)) {
				if (StringUtils.isEmpty(failNum))
					failNum = "0";
				// 输错密码就将当天的错误次数加1
				redisService.strSet("app:lpw:" + params.get("usinPhone") + ":d",currentDate);
				redisService.strSet("app:lpw:" + params.get("usinPhone") + ":num", Integer.parseInt(failNum) + 1 + "");
				int n = Integer.parseInt(failNum) + 1;
				return new AccessErrorResult(1001, "您已输错密码" + n + "次，还剩余" + (5 - n) + "次").toString();
			}

			// 如果登陆成功就将错误次数清零
			redisService.strSet("app:lpw:" + params.get("usinPhone") + ":num","0");

			userinfo = userinfoService.getUserById(Integer.parseInt(processResult));
			
			BigDecimal userAB = userinfoService.getUserABById(userinfo.getPkUserinfo());
			userinfo.setUsinAccountbalance(userAB);
			
			// 对设备唯一标识解密
			String did = params.get("did").toString();
			if (!StringUtils.isEmpty(did)) {
				byte[] b = Base64Coder.decode(did);
				did = WebFilter.judgeRequest(new String(b));
				params.put("did", did);
			} else {
				params.put("did", "");
			}
			params.put("userId", userinfo.getPkUserinfo());
			// 更新用户的登陆设备id
			userinfoService.updateUserDeviceId(params);

			// 推送唯一标示
			String jpushRegistrationid = (String) params.get("jpushRegistrationid");
			// 设备类型
			String jpushDevicetype = (String) params.get("jpushDevicetype");

			// 如果不同
			TblJpush jpush = jpushService.getByuserInfo(userinfo.getPkUserinfo());
			if (!ObjectUtil.isEmpty(jpush)) {
				String oldRegId = jpush.getJpushRegistrationid();
				if (!StringUtils.isEmpty(oldRegId) && !oldRegId.equals(jpushRegistrationid)) {
					JPushUtil.pushNotifyByRegId("", "", oldRegId, jpush.getJpushDevicetype());
				}
				jpush.setJpushRegistrationid(jpushRegistrationid);
				jpush.setJpushDevicetype(jpushDevicetype);
				jpushService.update(jpush);
			} else {
				jpush = new TblJpush();
				jpush.setJpushRegistrationid(jpushRegistrationid);
				jpush.setJpushUserinfo(userinfo.getPkUserinfo());
				jpush.setJpushDevicetype(jpushDevicetype);
				jpushService.insert(jpush);
			}
			
			
			UserLoginRecord loginrecord = new UserLoginRecord();
			
			loginrecord.setUserId(Long.valueOf(userinfo.getPkUserinfo()));
			loginrecord.setDeviceType(Integer.valueOf(jpushDevicetype));
			loginrecord.setRegistrationID(params.get("did").toString());
			
			if (!StringUtils.isEmpty(params.get("cid")) &&!StringUtils.isEmpty(params.get("location")) ) {
				
				log.info("******************插入登录记录************************");
				
				try
				{
				loginrecord.setcCode(params.get("cid").toString());
				String location = params.get("location").toString();
				if(location.split(",").length==2)
				{
					loginrecord.setLongitude(location.split(",")[0]);
					loginrecord.setLatitude(location.split(",")[1]);
				}
				if(!StringUtils.isEmpty(request.getRemoteAddr()))
				{
					loginrecord.setIPaddress(request.getRemoteAddr());
				}
				
				//userinfoService.insertLoginRecord(loginrecord);
				}catch (Exception e)
				{
					log.info("******************插入登录记录失败************************");
				}
				log.info("******************插入登录记录成功************************");
			}
			
		
			

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}
		log.info("******************用户登录-end************************");
		if (!StringUtils.isEmpty(userinfo.getPayPwd())) {
			userinfo.setIsPpw(1);
		}
		return new AccessSuccessResult(userinfo).toString();
	}

	/**
	 * 退出登录，清空did和推送id
	 * 
	 * @param params
	 *            userId 用户id
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(@RequestParam Map<String, Object> params) {
		params.put("did", "");
		try {
			userinfoService.updateUserDeviceId(params);

			TblJpush jpush = jpushService.getByuserInfo(Integer.parseInt(params
					.get("userId").toString()));
			if (null != jpush && !"".equals(jpush.getJpushRegistrationid())) {
				jpush.setJpushRegistrationid("");
				jpush.setJpushDevicetype("");
				jpushService.update(jpush);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return new AccessErrorResult(2001, "退出失败，请稍后再试").toString();
		}

		return new AccessSuccessResult().toString();
	}

	/**
	 * 检查该手机号是否被注册
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping("/checkphone")
	@ResponseBody
	public String checkPhone(@RequestParam String phone) {
		int isExist = userinfoService.userIsExist(phone);
		if (isExist > 0) {
			return new AccessErrorResult(1001,"error.msg.registed.phone_number").toString();
		}
		return new AccessSuccessResult().toString();
	}
	
	
	
	

	/**
	 * @Title: regist
	 * @Description: 注册
	 * @param userinfo
	 * @param MultipartFile
	 * @return
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(@RequestParam Map<String, String> params) {
		log.info("******************用户注册-begin************************");
		// 重新初始化对象，防止业务处理较慢，造成对象复用，数据重复
		TblUserinfo ui = new TblUserinfo();
		ui.setUsinPhone(params.get("usinPhone"));
		ui.setUsinPassword(params.get("usinPassword"));
		ui.setPlatform(params.get("platform"));
		int isExist = 0;
		if (StringUtils.isEmpty(params.get("invitePhone"))) {
			ui.setInvitePhone("");
		} 
//		解决bugID-2770
		else {
			// 填了邀请码的走，要给邀请人送代金券
			ui.setInvitePhone(params.get("invitePhone"));
//			isExist = userinfoService.userIsExist(ui.getInvitePhone());
//			if (isExist <= 0) {
//				return new AccessErrorResult(1001, "该邀请码无效").toString();
//			}
		}

		params.clear();

		try {
			// 判断此号码是否被注册
			isExist = userinfoService.userIsExist(ui.getUsinPhone());
			if (isExist > 0) {
				return new AccessErrorResult(1001,"error.msg.registed.phone_number").toString();
			}
			// A注册后邀请手机号用户不存在时不写入库
			TblUserinfo inviteUser = userinfoService.getByPhone(ui.getInvitePhone());
			if (inviteUser == null || inviteUser.getUsinUserstatus() != 1) {
				ui.setInvitePhone("");
			}
			ui.setUsinGroupid(6);
			userinfoService.insertUserinfo(ui);
			// 注册送1-注册送现金券活动，2-首次体验享现金券，3-邀请注册返现金券活动，4-邀请首次消费返现金券活动
			couponService.generateCode(ui.getPkUserinfo() + "", 1);
			couponService.generateCode(ui.getPkUserinfo() + "", 2);
			// 邀请送
			if (!StringUtils.isEmpty(ui.getInvitePhone())&&!ui.getInvitePhone().equals(ui.getUsinPhone())) {
				if(inviteUser!=null&&inviteUser.getUsinUserstatus()==1){
//					//获取用户的总有效优惠码数,超过1000张，邀请送不执行
//					int couponCount=couponService.getValidCPByUid(inviteUser.getPkUserinfo());
//					if(couponCount < 1000){
						couponService.generateCode(inviteUser.getPkUserinfo() + "", 3);
//					}
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "数据库操作失败").toString();
		}

		log.info("******************用户注册-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: modifyUser
	 * @Description: 完善用户资料
	 * @param request
	 * @param userinfo
	 * @param MultipartFile
	 * @return
	 */
	@RequestMapping(value = "/modifyUser", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyUser(HttpServletRequest request, TblUserinfo userinfo) {
		log.info("******************用户资料修改-begin************************");
		if (StringUtils.isEmpty(userinfo.getPkUserinfo())) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
		Userinfo tmpUser = new Userinfo();
		tmpUser.setPkUserId(userinfo.getPkUserinfo().intValue());
		tmpUser = userinfoService.getUserInfo(tmpUser);
        String uagent =    request.getHeader("User-Agent");
        
		log.info("user agent "+uagent);
		if (userinfo.getIsCard() == 1) {
			if (StringUtils.isEmpty(userinfo.getUsinVehiclenumbe())
					|| StringUtils.isEmpty(userinfo.getUsinFacticityname())
					|| StringUtils.isEmpty(userinfo.getUsinUseraddress())
					|| StringUtils.isEmpty(userinfo.getUsinCarinfoId())) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter")
						.toString();
			}
		}
		try {// --
			tblUserInfoService.modifyUser(userinfo);
			int count = pointMapper.usermodinfogetpointcount(userinfo.getPkUserinfo().intValue());
			log.info("user count"+count);
			
			if(StringUtils.isEmpty(tmpUser.getUserNickName())||StringUtils.isEmpty(tmpUser.getUserBrithy())
					||StringUtils.isEmpty(tmpUser.getPlateNum())||
					StringUtils.isEmpty(tmpUser.getpCode())||StringUtils.isEmpty(tmpUser.getcCode())||
					StringUtils.isEmpty(tmpUser.getaCode())||StringUtils.isEmpty(tmpUser.getUserImage()))
			{
				tmpUser = userinfoService.getUserInfo(tmpUser);
				
				if(!StringUtils.isEmpty(tmpUser.getUserNickName())&& !StringUtils.isEmpty(tmpUser.getUserBrithy())
						&& !StringUtils.isEmpty(tmpUser.getPlateNum())&&
						!StringUtils.isEmpty(tmpUser.getpCode())&& !StringUtils.isEmpty(tmpUser.getcCode()) &&
						!StringUtils.isEmpty(tmpUser.getaCode())&&!StringUtils.isEmpty(tmpUser.getUserImage()))
				{
					
					count = pointMapper.usermodinfogetpointcount(userinfo.getPkUserinfo().intValue());
					log.info("user count"+count);
					
					if(count==0)
					{
						Map<String, String> postpars = new HashMap<String, String>();
						postpars.put("userId", userinfo.getPkUserinfo().toString());
						postpars.put("direction", "0");
						postpars.put("integralActivityId", "4");

						String requestResult = new String();
						Map<String, String> postmap = new HashMap<String, String>();
						postmap.put("contents", postpars.toString());
						log.info(postmap);
						try {
							requestResult = HttpRequestUtil.post(httpurl, postmap);
							JSONObject jasonObject = JSONObject.fromObject(requestResult);
							log.info("jasonObject:" + jasonObject);
							Map map = jasonObject;
							Map<String,Object> obj=new HashMap<String,Object>();
							if( (boolean) map.get("success")){
						      log.info("修改资料送积分success");
							}
							else{
								
								   log.info("修改资料送积分failure");
							}
						} catch (Exception e) {
							e.printStackTrace();
					
							  log.info("修改资料送积分failure");
						}
						
						
					}
					
				}
				
				
				
			}
			

			// 推送唯一标示
			String jpushRegistrationid = request.getParameter("jpushRegistrationid");
			
			// 设备类型
			String jpushDevicetype = (String) request.getParameter("jpushDevicetype");
			if(!StringUtils.isEmpty(jpushRegistrationid) && !StringUtils.isEmpty(jpushDevicetype)){
				TblJpush jpush = jpushService.getByuserInfo(userinfo.getPkUserinfo());
				if (!ObjectUtil.isEmpty(jpush)) {
					String oldRegId = jpush.getJpushRegistrationid();
					if (!StringUtils.isEmpty(oldRegId) && !oldRegId.equals(jpushRegistrationid)) {
						JPushUtil.pushNotifyByRegId("", "", oldRegId, jpush.getJpushDevicetype());
					}
					jpush.setJpushRegistrationid(jpushRegistrationid);
					jpush.setJpushDevicetype(jpushDevicetype);
					jpushService.update(jpush);
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(2001, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************用户资料修改-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * 设置支付密码
	 * 
	 * @param params
	 *            uid 用户id pwd md5加密后的密码
	 * @return
	 */
	@RequestMapping("/setPayPwd")
	@ResponseBody
	public String setPayPwd(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("pwd").toString())) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		try {
			userinfoService.setPayPwd(params);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}

		return new AccessSuccessResult().toString();
	}

	/**
	 * 检查支付密码正确性
	 * 
	 * @param params
	 *            uid 用户id pwd 双层加密后的的密码，规则与登陆一致
	 * @return
	 */
	@RequestMapping("/checkPayPwd")
	@ResponseBody
	public String checkPayPwd(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("uid").toString())) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		/*TblUserinfo userInfo = null;

		try {
			// 如果当天输错三次密码就锁定密码
			String failNum = redisService.strGet("app:ppw:" + params.get("uid")	+ ":num");
			String date = redisService.strGet("app:ppw:" + params.get("uid") + ":d");
			String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
			if (currentDate.equals(date) && "3".equals(failNum)) {
				return new AccessErrorResult(1001, "已输错3次密码，请明天再试").toString();
			}

			userInfo = userinfoService.getUserById(Integer.parseInt(params.get("uid").toString()));
			if (null == userInfo) {
				return new AccessErrorResult(1001, "error.msg.invalid.user").toString();
			}

			if (StringUtils.isEmpty(userInfo.getPayPwd())) {
				return new AccessErrorResult(1001, "请先设置支付密码").toString();
			}

			String dbPayPwd = userInfo.getPayPwd();
			String md5Str = MD5Util.MD5Encode(dbPayPwd + userInfo.getUsinPhone(), null);
			if (!md5Str.equals(params.get("pwd").toString().substring(0, 32))) {
				if (StringUtils.isEmpty(failNum))
					failNum = "0";
				// 输错密码就将当天的错误次数加1
				redisService.strSet("app:ppw:" + params.get("uid") + ":d", currentDate);
				redisService.strSet("app:ppw:" + params.get("uid") + ":num", Integer.parseInt(failNum) + 1 + "");
				return new AccessErrorResult(1001, "支付密码错误").toString();
			}
			// 如果验证通过就将错误次数清零
			redisService.strSet("app:ppw:" + params.get("uid") + ":num", "0");
		} catch (Exception e) {
			log.error(e.getMessage());
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}*/
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: checkAuthCode
	 * @Description: 检查验证码是否正确
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
				return new AccessErrorResult(1001, "error.msg.empty.auth_code").toString();
			}
			// 手机号
			String usinPhone = (String) params.get("usinPhone");
			if (StringUtil.isEmpty(usinPhone)) {
				return new AccessErrorResult(1001,"error.msg.empty.phone_number").toString();
			}
			// 检查验证码是否正确或超时
			int validity = userinfoService.checkAuthCode(authCode, usinPhone);
			log.error("验证结果validity为：" + validity + "");
			if (validity == 1) {
				log.error("返回error.msg.error.auth_code");
				return new AccessErrorResult(1001, "error.msg.error.auth_code").toString();
			}
			if (validity == 2) {
				log.error("返回error.msg.timeout.auth_code");
				return new AccessErrorResult(1001,"error.msg.timeout.auth_code").toString();
			}
			if (validity == 3) {
				log.error("返回error.msg.invalid.auth_code");
				return new AccessErrorResult(1001,"error.msg.invalid.auth_code").toString();
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
	 * @Title: resetPasswrod
	 * @Description: 重置密码
	 * @param request
	 * @param userinfo
	 * @param MultipartFile
	 * @return
	 */
	@RequestMapping("/resetPasswrod")
	@ResponseBody
	public String resetPasswrod(@RequestParam Map<String, Object> params) {
		log.info("******************重置密码-begin************************");
		try {
			// 手机号码
			String usinPhone = (String) params.get("usinPhone");
			// 判断此号码是否被注册
			int isExist = userinfoService.userIsExist(usinPhone);
			if (isExist < 1) {
				return new AccessErrorResult(1001,"error.msg.unregist.phone_number").toString();
			}

			// 短信验证码
			String authCode = (String) params.get("authCode");
			// 是否输入验证码
			if (StringUtil.isEmpty(authCode)) {
				return new AccessErrorResult(1001, "error.msg.empty.auth_code").toString();
			}

			// 检查验证码是否正确或超时
			int validity = userinfoService.checkAuthCode(authCode, usinPhone);
			if (validity == 1) {
				return new AccessErrorResult(1001, "error.msg.error.auth_code").toString();
			}
			if (validity == 2) {
				return new AccessErrorResult(1001,
						"error.msg.timeout.auth_code").toString();
			}
			if (validity == 3) {
				return new AccessErrorResult(1001,
						"error.msg.invalid.auth_code").toString();
			}

			userinfoService.resetPasswrod(params);
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
	 * 根据用户id修改密码
	 * 
	 * @param params
	 *            uId用户id，opw旧密码，npw新密码
	 * @return
	 */
	@RequestMapping("/modPassword")
	@ResponseBody
	public String modPassword(@RequestParam Map<String, String> params) {
		if (StringUtils.isEmpty(params.get("uId"))
				|| StringUtils.isEmpty(params.get("opw"))
				|| StringUtils.isEmpty(params.get("npw"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}

		TblUserinfo userinfo = new TblUserinfo();
		userinfo = userinfoService.getUserById(Integer.parseInt(params
				.get("uId")));
		if (!params.get("opw").equals(userinfo.getUsinPassword())) {
			return new AccessErrorResult(1001, "原密码验证失败，请重新输入").toString();
		} else {
			userinfoService.updatePwByUid(params);
		}

		return new AccessSuccessResult().toString();
	}

	/**
	 * 根据用户id修改用户支付密码
	 * 
	 * @param params
	 *            uid用户id，oppw旧支付密码，nppw新支付密码
	 * @return
	 */
	@RequestMapping("/modPayPwd")
	@ResponseBody
	public String modPayPwd(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("uid"))
				|| StringUtils.isEmpty(params.get("oppw"))
				|| StringUtils.isEmpty(params.get("nppw"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}

		TblUserinfo userinfo = new TblUserinfo();
		userinfo = userinfoService.getUserById(Integer.parseInt(params.get("uid").toString()));
		if (!params.get("oppw").equals(userinfo.getPayPwd())) {
			return new AccessErrorResult(1001, "原密码验证失败，请重新输入").toString();
		}
		params.put("pwd", params.get("nppw"));
		userinfoService.setPayPwd(params);

		return new AccessSuccessResult().toString();
	}

	/**
	 * 获取用户信息
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request)
			throws AppRequestErrorException {

		// 用户ID
		String userId = RequestParamUtil.getEncodeParam(request, "userId");

		Userinfo userinfo = new Userinfo();
		try {
			if (StringUtil.isEmpty(userId)) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			userinfo.setPkUserId(Integer.parseInt(userId));
			userinfo = userinfoService.getUserInfo(userinfo);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户信息失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(userinfo).toString();
	}

	/**
	 * 获取用户账户余额
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getUserAB")
	@ResponseBody
	public String getUserABById(@RequestParam Map<String, Object> params) {
		if (StringUtils.isEmpty(params.get("uid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		try {
			BigDecimal userAB = userinfoService.getUserABById(Integer.parseInt(params.get("uid").toString()));
			map.put("userAB", userAB);
		} catch (Exception e) {
			return new AccessErrorResult(2004, "error.msg.invalid.sql").toString();
		}
		return new AccessSuccessResult(map).toString();
	}

	/**
	 * 供财务充值用,不对外开放
	 * @param uId
	 * @param blance
	 * @return
	 */
	@RequestMapping("/setUserAB")
	@ResponseBody
	public String setUserAB(Map<String, Object> params){
		if (StringUtils.isEmpty(params.get("uId")) || StringUtils.isEmpty(params.get("blance"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		userinfoService.updateUserBlance(params);
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 一键升级为商家
	 */
	/*
	 * @RequestMapping("/upgradeoUser")
	 * 
	 * @ResponseBody public String upgradeoUser(HttpServletRequest request)
	 * throws AppRequestErrorException {
	 * 
	 * // 用户ID String userId = RequestParamUtil.getEncodeParam(request,
	 * "userId");
	 * 
	 * TblUserinfo tblUserinfo = new TblUserinfo(); try { // 未输入用户id if
	 * (StringUtil.isEmpty(userId) || StringUtil.isEmpty(userId)) { return new
	 * AccessErrorResult(1050,"error.msg.miss.parameter") .toString(); }
	 * tblUserinfo.setPkUserinfo(Integer.parseInt(userId)); tblUserinfo
	 * .setUsinUserstatus
	 * (ApplicationConsts.ElectricPileManager.USER_STATE_GENERAL);
	 * userinfoService.upgradeoUser(tblUserinfo); } catch (Exception e) {
	 * log.error(e.getLocalizedMessage()); log.error(" 一键升级为商家失败", e); return
	 * new AccessErrorResult(2001,"error.msg.invalid.parameter") .toString(); }
	 * 
	 * return new AccessSuccessResult().toString(); }
	 */

	/**
	 * 获取我的评论
	 */
	/*
	 * @RequestMapping("/getMyComment")
	 * 
	 * @ResponseBody public String getMyComment(HttpServletRequest request)
	 * throws AppRequestErrorException {
	 * 
	 * // 用户ID String userId = RequestParamUtil.getEncodeParam(request,
	 * "userId");
	 * 
	 * List<MyProductcomment> productcommentList = new
	 * ArrayList<MyProductcomment>(); try { if (StringUtil.isEmpty(userId) ||
	 * StringUtil.isEmpty(userId)) { return new
	 * AccessErrorResult(1050,"error.msg.miss.parameter") .toString(); }
	 * TblProductcomment fProductcomment = new TblProductcomment();
	 * fProductcomment.setPrcoUserid(JudgeNullUtils.nvlInteget(userId));
	 * productcommentList = userinfoService .findProComments(fProductcomment); }
	 * catch (Exception e) { // 保存错误LOG log.error(e.getLocalizedMessage());
	 * log.error("获取我的评论失败", e); // 返回登录信息错误信息 return new
	 * AccessErrorResult(2004,"error.msg.invalid.parameter") .toString(); }
	 * 
	 * // 返回处理成功信息 return new
	 * AccessSuccessResult(productcommentList).toString(); }
	 */

	/**
	 * 获取我的评论
	 */
	@RequestMapping("/getMyCommentN")
	@ResponseBody
	public String getMyCommentN(HttpServletRequest request)
			throws AppRequestErrorException {

		// 用户ID
		String userId = RequestParamUtil.getEncodeParam(request, "userId");

		List<HashMap<String, Object>> productcommentList = new ArrayList<HashMap<String, Object>>();
		try {
			if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(userId)) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			TblProductcomment fProductcomment = new TblProductcomment();
			fProductcomment.setPrcoUserid(JudgeNullUtils.nvlInteget(userId));
			productcommentList = userinfoService.findProCommentsN(Integer.parseInt(userId));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取我的评论失败", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(productcommentList).toString();
	}

	/**
	 * 删除我的评论
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/removeMyCommentN")
	@ResponseBody
	public String removeMyCommentN(HttpServletRequest request)
			throws AppRequestErrorException {

		// 评论ID
		String cId = RequestParamUtil.getEncodeParam(request, "cId");
		// 1电桩，2电站
		String type = RequestParamUtil.getEncodeParam(request, "type");

		try {
			// 参数不完整
			if (StringUtil.isEmpty(type) || StringUtil.isEmpty(cId)) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			userinfoService.removeMyCommentN(cId, type);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("删除我的评论失败", e);
			return new AccessErrorResult(2003, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * 删除我的评论
	 */
	/*
	 * @RequestMapping("/removeMyComment")
	 * 
	 * @ResponseBody public String removeMyComment(HttpServletRequest request)
	 * throws AppRequestErrorException {
	 * 
	 * // 评论ID String productCommentId =
	 * RequestParamUtil.getEncodeParam(request, "productCommentId");
	 * 
	 * try { // 未输入经纬度 if (StringUtil.isEmpty(productCommentId) ||
	 * StringUtil.isEmpty(productCommentId)) { // 返回未输入经纬度错误信息 return new
	 * AccessErrorResult(1050,"error.msg.miss.parameter") .toString(); }
	 * userinfoService.removeMyComment(productCommentId); } catch (Exception e)
	 * { // 保存错误LOG log.error(e.getLocalizedMessage()); log.error("删除我的评论失败",
	 * e); // 返回登录信息错误信息 return new
	 * AccessErrorResult(2003,"error.msg.invalid.parameter") .toString(); }
	 * 
	 * // 返回处理成功信息 return new AccessSuccessResult().toString(); }
	 */

	/**
	 * 我的钱包
	 */
	@RequestMapping("/getMyWallet")
	@ResponseBody
	public String getMyWallet(HttpServletRequest request)
			throws AppRequestErrorException {

		// 用户ID
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		String starttime = RequestParamUtil.getEncodeParam(request, "starttime");
		String endtime = RequestParamUtil.getEncodeParam(request, "endtime");
		String type = RequestParamUtil.getEncodeParam(request, "type");
		MyWallet myWallet = new MyWallet();

		try {
			// 未输入用户id
			if (StringUtil.isEmpty(userId)) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			TblPurchasehistory tblPurchasehistory = new TblPurchasehistory();
			tblPurchasehistory.setPuhiUserid(JudgeNullUtils.nvlInteget(userId));
			if (!StringUtils.isEmpty(type))
				tblPurchasehistory.setPuhiType(Integer.parseInt(type));
			tblPurchasehistory.setStarttime(starttime);
			tblPurchasehistory.setEndtime(endtime);

			TblEarnings tblEarnings = new TblEarnings();
			tblEarnings.setEarUserid(JudgeNullUtils.nvlInteget(userId));
			myWallet = userinfoService.getMyWallet(tblPurchasehistory,tblEarnings);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户信息失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(myWallet).toString();
	}

	@RequestMapping("/myBills")
	@ResponseBody
	public String myBills(@RequestParam Map<String, Object> params,
			AppPager pager) throws AppRequestErrorException {

		MyWallet myWallet = new MyWallet();

		try {
			// 未输入用户id
			if (StringUtils.isEmpty(params.get("uId"))) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}

			params.put("pageNum", pager.getPageNum());
			params.put("pageNumber", pager.getPageNumber());
			myWallet = userinfoService.myBills(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户信息失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(myWallet).toString();
	}

	/**
	 * 我的足迹
	 */
	/*
	 * @RequestMapping("/getMyFootPrint")
	 * 
	 * @ResponseBody public String getMyFootPrint(HttpServletRequest request)
	 * throws AppRequestErrorException {
	 * 
	 * // 用户ID String userId = RequestParamUtil.getEncodeParam(request,
	 * "userId");
	 * 
	 * List<TblFootprint> tblFootprintList = new ArrayList<TblFootprint>();
	 * 
	 * try { // 未输入经纬度 if (StringUtil.isEmpty(userId) ||
	 * StringUtil.isEmpty(userId)) { // 返回未输入经纬度错误信息 return new
	 * AccessErrorResult(1050,"error.msg.miss.parameter") .toString(); }
	 * TblFootprint tblFootprint = new TblFootprint();
	 * tblFootprint.setFoprUserId(JudgeNullUtils.nvlInteget(userId));
	 * tblFootprintList = userinfoService.getMyFootPrint(tblFootprint); } catch
	 * (Exception e) { // 保存错误LOG log.error(e.getLocalizedMessage());
	 * log.error("获取我的足迹失败", e); // 返回登录信息错误信息 return new
	 * AccessErrorResult(1000,"error.msg.invalid.parameter") .toString(); }
	 * 
	 * // 返回处理成功信息 return new AccessSuccessResult(tblFootprintList).toString();
	 * }
	 */

	/**
	 * 查询给定用户的所有反馈的信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getFeedbackList")
	@ResponseBody
	public String getFeedbackList(@RequestParam Map<String, Object> params,	AppPager pager) {
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());

		List<Map<String, Object>> list = null;

		try {
			list = userFeedbackService.getMyFeedbackList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取用户反馈列表错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(list).toString();
	}

	/**
	 * 查询选定消息详情
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getFeedbackContent")
	@ResponseBody
	public String getFeedbackContent(int feedbackId) {

		Map<String, Object> contentMap = null;

		try {
			contentMap = userFeedbackService.getMyFeedbackContent(feedbackId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取反馈详情错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(contentMap).toString();
	}

	@RequestMapping("/getMtBills")
	@ResponseBody
	public String getMtBills(@RequestParam Map<String, Object> params,
			AppPager pager) throws AppRequestErrorException {

		List<Map<String,Object>> data = null;
		try {
			// 未输入用户id
			if (StringUtils.isEmpty(params.get("uId"))) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}

			params.put("pager", pager);
			data = userinfoService.getMtBills(params);
			if(data != null && data.size() > 0){
				for(Map<String,Object> m:data){
					double qt = 0;
					Object detail = m.get("detail");
					if(detail != null && !"".equals(detail)){
						String[] arr = detail.toString().split(",");
						for(int i=0;i<arr.length;i++){
							String[] earr = arr[i].split("\\|");
							if(!"11".equals(earr[0])){
								qt += Double.valueOf(earr[2]);
							}
							if("1".equals(earr[0]))
								m.put("mn", earr[1]);
							if("4".equals(earr[0]))
								m.put("cz", earr[1]);
							if("6".equals(earr[0]))
								m.put("cp", earr[1]);
							if("11".equals(earr[0]))
								m.put("tk", earr[1]);
						}
					}
					if(m.get("mn") == null)
						m.put("mn", 0.00);
					if(m.get("cp") == null)
						m.put("cp", 0.00);
					if(m.get("cz") == null)
						m.put("cz", 0.00);
					if(m.get("tk") == null)
						m.put("tk", 0.00);
					m.put("mn", Double.parseDouble(m.get("mn").toString()) - Double.parseDouble(m.get("tk").toString()));
					m.put("qt", qt);
					m.remove("detail");
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取月账单失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(data).toString();
	}

	@RequestMapping("/getMtBillsDetail")
	@ResponseBody
	public String getMtBillsDetail(@RequestParam Map<String, Object> params,
			AppPager pager) throws AppRequestErrorException {

		List<Map<String,Object>> data = null;
		try {
			// 未输入用户id
			if (StringUtils.isEmpty(params.get("uId")) && StringUtils.isEmpty(params.get("mt"))) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}

			params.put("pager", pager);
			data = userinfoService.getPhDetail(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取月账单明细失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(data).toString();
	}

	@RequestMapping("/getCzDetail")
	@ResponseBody
	public String getCzDetail(@RequestParam Map<String, Object> params,
			AppPager pager) throws AppRequestErrorException {

		List<Map<String,Object>> data = null;
		try {
			// 未输入用户id
			if (StringUtils.isEmpty(params.get("uId")) && StringUtils.isEmpty(params.get("mt"))) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			params.put("type","4");
			params.put("pager", pager);
			data = userinfoService.getPhDetail(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取月充值明细失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(data).toString();
	}

	@RequestMapping("/myCpInfoList")
	@ResponseBody
	public String myCpInfoList(@RequestParam Map<String, Object> params) throws AppRequestErrorException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			// 未输入用户id
			if (StringUtils.isEmpty(params.get("uId")) ) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			
			if( StringUtils.isEmpty(params.get("cCode")))
			{
				params.put("cCode", "330100");
			}
			
			params.put("dc", 3);
			list= couponService.getMyCoupon(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取我的优惠信息失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}
		

		// 返回处理成功信息
		return new AccessSuccessResult(list).toString();
	}
	
	@RequestMapping("/myCpInfo")
	@ResponseBody
	public String myCpInfo(@RequestParam Map<String, Object> params) throws AppRequestErrorException {

		Map<String,Object> data = null;
		try {
			// 未输入用户id
			if (StringUtils.isEmpty(params.get("uId"))) {
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			params.put("dc", 3);
			data = couponService.getMyCouponOne(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取我的优惠信息失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult(data).toString();
	}
}
