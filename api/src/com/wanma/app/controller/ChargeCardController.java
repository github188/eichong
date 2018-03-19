package com.wanma.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.ChargeCardService;
import com.wanma.app.service.impl.RedisService;
import com.wanma.app.util.DateUtil;
import com.wanma.common.AliSMS;
import com.wanma.model.TblUserinfo;

/**
 * 充电卡本地application相关接口
 * @author hFei
 *
 */
@Controller
@RequestMapping("/app/card")
public class ChargeCardController {
	private static Logger log = Logger.getLogger(ChargeCardController.class);
	
	/**
	 * 充电卡本地application登录
	 * @param params
	 * 		account 用户账户，pwd 用户密码
	 * @return
	 */
	@RequestMapping("/clogin")
	@ResponseBody
	public String clogin(@RequestParam Map<String, Object> params){
		log.info("******************充电卡鉴权用户登录-begin************************");
		if(StringUtils.isEmpty(params.get("account")) || StringUtils.isEmpty(params.get("pwd"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		// 登录用户信息
		//TblUserinfo userinfo = new TblUserinfo();
		String processResult = "";
		try {
			//lpw代表登陆密码
			String failNum = redisService.strGet("app:lpw:" + params.get("account") + ":num");
			String date = redisService.strGet("app:lpw:" + params.get("account") + ":d");
			String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
			if(currentDate.equals(date) && Integer.parseInt(failNum) >= 5){
				return new AccessErrorResult(1001, "您已输错5次密码，请明天再试").toString();
			}
			
			// 登录验证处理结果
			processResult = userinfoService.checkCLoginUser(params);

			// 登陆用户不存在
			if (CommonConsts.USER_STATUS_CHECK_NO_USER.equals(processResult)) {
				return new AccessErrorResult(1001,"error.msg.invalid.user").toString();
				// 密码错误
			}else if(CommonConsts.USER_STATUS_CHECK_FREEZE.equals(processResult)){
				return new AccessErrorResult(1001, "该账户已被冻结，如有疑问请致电客服。").toString();
			}else if (CommonConsts.USER_STATUS_CHECK_ERROR_PASSWORD.equals(processResult)) {
				if(StringUtils.isEmpty(failNum))
					failNum = "0";
				//输错密码就将当天的错误次数加1
				redisService.strSet("app:lpw:" + params.get("account") + ":d", currentDate);
				redisService.strSet("app:lpw:" + params.get("account") + ":num", Integer.parseInt(failNum) + 1 + "");
				return new AccessErrorResult(1001,"error.msg.invalid.password").toString();
			}
			
			//如果登陆成功就将错误次数清零
			redisService.strSet("app:lpw:" + params.get("account") + ":num", "0");
			
			//userinfo = userinfoService.getUserById(Integer.parseInt(processResult));
			//对设备唯一标识解密
			/*String did = params.get("did").toString();
			if(!StringUtils.isEmpty(did)){
				byte[] b = Base64Coder.decode(did);
				did = WebFilter.judgeRequest(new String(b));
				params.put("did", did);
			}else{
				params.put("did", "");
			}*/

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		log.info("******************充电卡鉴权用户登录-end************************");
		/*if(!StringUtils.isEmpty(userinfo.getPayPwd())){
			userinfo.setIsPpw(1);
		}*/
		
		return new AccessSuccessResult(processResult).toString();
	}
	
	/**
	 * 初始化充电卡
	 * @param params
	 * 			inNum 内卡号，outNum 外卡号，compNum 公司标识
	 * @return
	 */
	@RequestMapping("/initCard")
	@ResponseBody
	public String initCard(@RequestParam Map<String, String> params){
		if(StringUtils.isEmpty(params.get("inNum")) || StringUtils.isEmpty(params.get("outNum"))
				/*|| StringUtils.isEmpty(params.get("compNum"))*/){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		try{
			int num = chargeCardService.countCardByOutNum(params.get("outNum"));
			if(num >= 1){
				return new AccessErrorResult(1001, "此卡已被初始化过").toString();
			}
/*			params.put("payMode", params.get("outNum").toString().substring(0,2));
*/			chargeCardService.initCard(params);
		}catch(Exception e){
			log.error(e.getMessage());
			return new AccessErrorResult(2002,"卡初始化失败").toString();
		}
		
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 获取公司标识列表
	 * @return
	 */
	@RequestMapping("/compMarkList")
	@ResponseBody
	public String compMarkList(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		try{
			list = chargeCardService.getCompMarkList();
		}catch(Exception e){
			log.error(e.getMessage());
			return new AccessErrorResult(2004, "获取公司标识列表失败").toString();
		}
		
		return AccessSuccessResult.nAccessSuccessResult(list).toJSONString();
	}
	
	/**
	 * 根据内卡号获取卡及绑定的人的基本信息
	 * @param inNum 内卡号
	 * @return
	 */
	@RequestMapping("/rechargeInfo")
	@ResponseBody
	public String rechargeInfo(String inNum){
		if(StringUtils.isEmpty(inNum)){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//get card info by inside number
			map = chargeCardService.getCardInfoByInNum(inNum);
			if(null == map){
				return new AccessErrorResult(2004,"该卡未绑定用户或卡已被挂失，获取充电卡信息失败").toString();
			}
			//get user info that bound by card
			TblUserinfo userInfo = userinfoService.getUserById(Integer.parseInt(map.get("uId").toString()));
			map.put("uName", userInfo.getUsinFacticityname());
			map.put("uPhone", userInfo.getUsinPhone());
			map.put("uBlance", userInfo.getUsinAccountbalance() + "");
		}catch(Exception e){
			log.error(e.getMessage());
			return new AccessErrorResult(2004,"获取基本信息失败").toString();
		}
			
		return AccessSuccessResult.nAccessSuccessResult(map).toJSONString();
	}
	
	/**
	 * 充电卡充值
	 * @param params
	 * 		inNum 内卡号，outNum 外卡号，account 登陆账户，pwd 登陆密码（md5），uId 充值用户id，sum 充值金额
	 * @return
	 */
	@RequestMapping("/cardRecharge")
	@ResponseBody
	public String cardRecharge(@RequestParam Map<String, Object> params){
		if(StringUtils.isEmpty(params.get("inNum")) || StringUtils.isEmpty(params.get("outNum"))
				|| StringUtils.isEmpty(params.get("account")) || StringUtils.isEmpty(params.get("pwd"))
				|| StringUtils.isEmpty(params.get("sum")) || StringUtils.isEmpty(params.get("uId"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString(); 
		}
		
		try{
			String code = userinfoService.checkCLoginUser(params);
			if("002".equals(code) || "003".equals(code) || "004".equals(code)){
				return new AccessErrorResult(1001, "非法操作，请重新登陆").toString();
			}
			
			int cardCount = chargeCardService.countCardByOutAndInNum(params);
			if(cardCount < 1){
				return new AccessErrorResult(1001, "充电卡校验失败").toString();
			}
			
			Map<String, Object> map = chargeCardService.getCardInfoByInNum(params.get("inNum").toString());
			if(null == map || StringUtils.isEmpty(map.get("pk_UserCard").toString())){
				return new AccessErrorResult(1001, "该卡未绑定用户或卡已被挂失，无法充值").toString();
			}
			if(!params.get("uId").equals(map.get("uId").toString())){
				return new AccessErrorResult(1001, "请求参数效验失败，请重新再试").toString();
			}
			
			double sum = Double.parseDouble(params.get("sum").toString());
			/*if(sum > 1000 || sum < 0){
				return new AccessErrorResult(1001, "充值金额不合法，请重新输入").toString();
			}*/
			
			TblUserinfo userInfo = userinfoService.getUserById(Integer.parseInt(map.get("uId").toString()));
			params.put("userId", params.get("uId"));
			BigDecimal balance = userInfo.getUsinAccountbalance().add(new BigDecimal(params.get("sum").toString()));
			params.put("blance", balance);
			userinfoService.updateUserBlance(params);
			
			//send SMS that balance info to user
			Calendar calendar = Calendar.getInstance();
			Map smsParams=new HashMap();
			smsParams.put("m", calendar.get(Calendar.MONTH) + 1);
			smsParams.put("d", calendar.get(Calendar.DAY_OF_MONTH));
			smsParams.put("nun", params.get("sum").toString());
			smsParams.put("tnum", balance);
			AliSMS.sendAliSMS(userInfo.getUsinPhone(), "SMS_16860119", JSON.toJSONString(smsParams));
//			ApplicationCommon.sendWanMatMessage(new String("尊敬的用户：" + (calendar.get(Calendar.MONTH) + 1)
//					+ "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日您成功充值" 
//					+ params.get("sum").toString() + "元，当前您的账户余额为" + balance + "元。"), userInfo.getUsinPhone());
//			
			chargeCardService.addPurchaseHis(sum + "", userInfo.getUsinPhone(), params.get("outNum").toString(), 
					params.get("account").toString(), userInfo.getPkUserinfo());
			
		}catch(Exception e){
			log.error(e.getMessage());
			return new AccessErrorResult(1001, "充值失败，请重试").toString();
		}
		
		return new AccessSuccessResult().toString();
	}
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private AppUserinfoService userinfoService;
	@Autowired
	private ChargeCardService chargeCardService;
	
	
	public static void main(String[] args) throws Exception{
		//c.addPurchaseHis("10", "13333333333", "w123456", "caiwu", 1044);
	}
}
