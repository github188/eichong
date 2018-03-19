package com.wanma.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.DateUtil;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.model.MyProductcomment;
import com.wanma.model.PinCode;
import com.wanma.model.TblFootprint;
import com.wanma.model.TblUser;
import com.wanma.model.WalletRecord;
import com.wanma.web.service.AuthCodeService;
import com.wanma.web.service.WebUserinfoService;
import com.wanma.web.service.impl.RedisService;
import com.wanma.web.support.common.ErrorResponse;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.common.SuccessResponse;
import com.wanma.web.support.utils.JsonUtil;

/**
 * @description : APP用户信息处理控制器
 * @Author: songjf
 * @Date: 2015年03月12日OIO
 */
@Controller
@RequestMapping("/web/user")
public class WebUserController extends BaseController {
	private static Log log = LogFactory.getLog(WebUserController.class);
	/**
	 * 用户信息业务处理对象
	 */
	@Autowired
	private WebUserinfoService userinfoService;
	@Autowired
	private AuthCodeService authCodeService2;
	
	@Autowired
	private RedisService redisService;

	/**
	 * 登录
	 *
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam Map<String, Object> params,
			HttpServletResponse response, HttpServletRequest request) {
		return userinfoService.login(params, request, response).toString();
	}

	/**
	 * 注册
	 *
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpServletRequest request, TblUser user) {
		String msgCode = request.getParameter("msgCode");
		String redisAuthCode=getRedisAuthCode(user.getUserAccount()).split(":")[1];
		if (!msgCode.equals(redisAuthCode)) {
			return new ErrorResponse("手机验证码错误!").toString();
		}
		return userinfoService.regist(user, request).toString();
	}

	/**
	 * 获取图片验证码
	 */
	@RequestMapping(value = "/getValidCode")
	public void getValiCode(HttpServletRequest request,
			HttpServletResponse response) {
		userinfoService.createValidCode(request, response);
	}

	/**
	 * @return
	 * @Title: resetPasswrod
	 * @Description: 重置密码
	 */
	@RequestMapping(value = "/resetPasswrod", method = RequestMethod.POST)
	@ResponseBody
	public String resetPasswrod(@RequestParam Map<String, Object> params,
			HttpServletRequest request) {
		String msgCode = request.getParameter("msgCode");
		String userAccount = request.getParameter("userAccount");
		String redisAuthCode=getRedisAuthCode(userAccount).split(":")[1];
		if (!msgCode.equals(redisAuthCode)) {
			return new ErrorResponse("手机验证码错误!").toString();
		}
		return userinfoService.resetPasswrod(params, request).toString();
	}

	/**
	 * 获取用户信息
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public String getUserInfo(TblUser user) {
		return userinfoService.getUserInfo(user).toString();
	}

	/**
	 * 发送短信接口
	 */
	@RequestMapping("/sendMsg")
	@ResponseBody
	public String sendMsg(
			@RequestParam(value = "phone", defaultValue = "") String phone,
			HttpServletRequest request) {
		String authCode=getRedisAuthCode(phone).split(":")[1];
		BluemobiCommon.sendWanMatMessage(new String("您的验证码：" 
				+ authCode.trim() + "打死都不要告诉别人。如非本人操作，不用理会，走你！"), phone);
		return new SuccessResponse().toString();
	}

	/**
	 * 手机号码验证
	 */
	@RequestMapping("/validPhone")
	@ResponseBody
	public String validPhone(TblUser user,HttpServletRequest request) {
		String checkPhoneExistFlag=request.getParameter("checkPhoneExistFlag");
		TblUser tempUser=userinfoService.getUserInfo(user);
		if(StringUtils.isNotBlank(checkPhoneExistFlag)){
			if(tempUser!=null){
				return new FailedResponse("手机号已存在!").toString();
			}
		}else{
			if(tempUser==null){
				return new FailedResponse("该手机号尚未注册!").toString();
			}
		}
		String userAccount=user.getUserAccount();
		//验证是否合格手机号
		Pattern p = Pattern.compile("^[1][3578]\\d{9}$");  
		Matcher m = p.matcher(userAccount);  
		if(!m.find()){
			return new ErrorResponse("手机号码不正确").toString();
		}
		String[] authCodeArr=getRedisAuthCode(userAccount).split(":");
		int sendCount=new Integer(authCodeArr[0]);
		long sendTime=new Long(authCodeArr[2]);
		if(sendCount==5){
			return new ErrorResponse("今天验证码发送已达到5条，请明天再试").toString();
		}
		if(System.currentTimeMillis()-sendTime<=60000){
			return new ErrorResponse("发送验证码的时间间隔不能少于1分钟").toString();
		}
		String authCode = ProcessInfoCommon.getDigitRandomKey(6);
		setRedisAuthCode(userAccount, authCode);
		return new SuccessResponse().toString();
	}

	/**
	 * 更新用户信息
	 */
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserInfo(HttpServletRequest request,
			TblUser user) {
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile file = req.getFile("file");
		user.setTitleMultiFile(file);
		Long userId = SessionMgr.getUserId(request);
		user.setUserId(userId);
		// userinfo.setUsinAcura(userinfo.getBrandName()+":"+userinfo.getUsinAcura());
		return userinfoService.updateUserInfo(user, request).toString();
        
	}
								

	/**
	 * 获取我的评论
	 */
	@RequestMapping("/getMyComment")
	@ResponseBody
	public String getMyComment(@RequestParam Map<String, Object> params) {
		log.info("******************获取我的评论明细-begin************************");
		Integer begin = null, pageSize = null;
		// 获取分页参数
		if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
			pageSize = Integer.valueOf((String) params.get("pageNum"));
		}
		if (!StringUtils.isEmpty((String) params.get("pageNumber"))) {
			begin = Integer.valueOf((String) params.get("pageNumber"));
		}
		PageResponse<List<MyProductcomment>> pager = new PageResponse<List<MyProductcomment>>(
				begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			if (StringUtil.isEmpty((String) params.get("userId"))) {
				// 用户ID为空错误信息
				return new FailedResponse("error.msg.invalid.parameter")
						.toString();
			}
			List<MyProductcomment> productcommentList = userinfoService
					.findProComments(params);
			pager.setCountData(userinfoService.countProComments(params));
			pager.setDate(productcommentList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取我的评论失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return pager.toString();
	}

	/**
	 * 删除我的评论
	 */
	@RequestMapping("/removeMyComment")
	@ResponseBody
	public String removeMyComment(HttpServletRequest request) {
		// 评论ID
		String productCommentId = RequestParamUtil.getEncodeParam(request,
				"productCommentId");
		try {
			if (StringUtil.isEmpty(productCommentId)) {
				// 评论ID为空错误信息
				return new FailedResponse("error.msg.invalid.parameter")
						.toString();
			}
			userinfoService.removeMyComment(productCommentId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除我的评论失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	/**
	 * 我的余额
	 */
	@RequestMapping("/getMyWallet")
	@ResponseBody
	public String getMyWallet(TblUser user) {
		return new ResultResponse(userinfoService.getUserInfo(user)).toString();
	}

	/**
	 * 我的消费/收益明细
	 */
	@RequestMapping("/findMyWallet")
	@ResponseBody
	public String findMyWalletDetail(@RequestParam Map<String, Object> params,
			HttpServletRequest request) {
		log.info("******************获取消费/收益明细-begin************************");
		Integer begin = null, pageSize = null;
		// 获取分页参数
		if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
			pageSize = Integer.valueOf((String) params.get("pageSize"));
		}
		if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
			begin = Integer.valueOf((String) params.get("pageNum"));
		}
		PageResponse<List<WalletRecord>> pager = new PageResponse<List<WalletRecord>>(
				begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		// 搜索明细类型 1-搜索全部 2-搜索消费明细 3-搜索收益明细
		String walletType = (String) params.get("walletType");
		params.put("walletType", walletType);
		params.put("userId", SessionMgr.getUserId(request));
		List<WalletRecord> myWalletList = new ArrayList<WalletRecord>();
		try {
			if (params.get("userId")==null) {
				// 用户ID为空错误信息
				return new FailedResponse("error.msg.invalid.parameter")
						.toString();
			}
			pager.setCountData(userinfoService.countWallet(params));
			pager.setDate(userinfoService.findMyWalletList(params));
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户信息失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		// 返回处理成功信息
		return pager.toString();
	}

	/**
	 * 我的足迹
	 */
	@RequestMapping("/getMyFootPrint")
	@ResponseBody
	public String getMyFootPrint(HttpServletRequest request) {
		// 用户ID
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		List<TblFootprint> tblFootprintList = new ArrayList<TblFootprint>();
		try {
			if (StringUtil.isEmpty(userId)) {
				// 用户ID为空错误信息
				return new FailedResponse("error.msg.invalid.parameter")
						.toString();
			}
			TblFootprint tblFootprint = new TblFootprint();
			tblFootprint.setFoprUserId(JudgeNullUtils.nvlInteget(userId));
			tblFootprintList = userinfoService.getMyFootPrint(tblFootprint);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取我的足迹失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new ResultResponse<List<TblFootprint>>(tblFootprintList)
				.toString();
	}

	/**
	 * 退出登录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		return SessionMgr.removeWebUser(request) ? new SuccessResponse()
				.toString() : new FailedResponse().toString();
	}

	@RequestMapping("/getCommentsByUserId")
	@ResponseBody
	public String getCommentsByUserId(@RequestParam Map<String, Object> params,
			HttpServletRequest request) throws UnsupportedEncodingException {
		log.info("******************获取评论信息-begin************************");
		Integer begin = null, pageSize = null;
		// 获取分页参数
		if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
			pageSize = Integer.valueOf((String) params.get("pageSize"));
		}
		if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
			begin = Integer.valueOf((String) params.get("pageNum"));
		}
		PageResponse<List<HashMap<String, Object>>> pager = new PageResponse<List<HashMap<String, Object>>>(
				begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			params.put("userId", SessionMgr.getWebUser(request).getUserId());
			List<HashMap<String, Object>> maps = userinfoService
					.getCommentsByUserId(params);
			pager.setCountData(userinfoService.countComments(params));
			pager.setDate(maps);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取评论信息错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取评论信息-end************************");
		// 返回处理成功信息
		return pager.toString();
	}

	@RequestMapping("/getCommentById")
	@ResponseBody
	public String getCommentById(HttpServletRequest req,
			HttpServletResponse resp) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		int pkComment = Integer.parseInt(req.getParameter("id"));
		HashMap<String, Object> map = userinfoService.getCommentById(pkComment);
		return JsonUtil.map2json(map);
	}

	/**
	 * 删除我的评论
	 * 
	 * @param request
	 * @return
	 * @throws WebRequestErrorException
	 */
	@RequestMapping("/removeMyCommentN")
	@ResponseBody
	public String removeMyCommentN(HttpServletRequest request) {

		// 评论ID
		String cId = RequestParamUtil.getEncodeParam(request, "cId");
		// 1电桩，2电站
		String type = RequestParamUtil.getEncodeParam(request, "type");

		try {
			// 参数不完整
			if (StringUtil.isEmpty(type) || StringUtil.isEmpty(cId)) {
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			}
			userinfoService.removeMyCommentN(cId, type);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("删除我的评论失败", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
	
	/********
	 * 检测一天的验证码发送次数是否到达上限
	 * 检测逻辑为
	 * 如果来请求验证码跟最后一次发送验证码不是同一天
	 * 			则将发送次数计数器清零
	 * 如果最后一次请求跟当次
	 * 			则判断当天的发送次数是否到达上限
	 * @param Pincode pinCode 验证码对象
	 * @return boolean
	 * 
	 *****/
	private boolean isPinCodeCountLimit(PinCode pinCode) { 
		int day = pinCode.getDay();
		int count = pinCode.getCount();
		int today = Integer.parseInt( new SimpleDateFormat("yyMMdd").format(new Date()));
		if(day<today) {
			pinCode.setDay(today);
			pinCode.setCount(0);
			return true;
		} else {
			//每天只能发5条，基数从0开始
			if(count>=5) { 
				return false;
			} else {
				return true;
			}
		}
	}
	
	/****
	 * 检测两次请求验证码的时间间隔
	 * @param Pincode pinCode 验证码对象
	 * @return boolean 
	 * ****/
	private boolean isNotPinCodeInterval(PinCode pinCode) {
		long lastTime = pinCode.getCreatetime();
		//两次短信之间不能少于60秒
		if(System.currentTimeMillis()-lastTime<=60000) 
			return false;
		return true;
	}
	
	
	
	private String getRedisAuthCode(String userAccount){
    	String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
    	String str=redisService.strGet("app:authcode:" + userAccount + ":"+currentDate);
    	return StringUtils.isNotBlank(str)?str:"0:000000:"+(System.currentTimeMillis()-60001);
    }
	
    
	/**
	 * 存储格式-count:code:time
	 */
    private void setRedisAuthCode(String userAccount,String authCode){
    	String authCodeStr=getRedisAuthCode(userAccount);
    	int failNum=StringUtils.isNotBlank(authCodeStr)?new Integer(authCodeStr.split(":")[0]):0;
    	String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
    	redisService.strSet("app:authcode:" + userAccount + ":"+currentDate,
    			failNum+1+":"+authCode+":"+System.currentTimeMillis());
    }
}
