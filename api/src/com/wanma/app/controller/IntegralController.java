package com.wanma.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.dao.IntegralMapper;
import com.wanma.app.service.IntegralService;
import com.wanma.app.util.HttpRequestUtil;

@Controller
@RequestMapping("/app/point")
public class IntegralController {
	private static Log log = LogFactory.getLog(IntegralController.class);
	@Autowired
	private IntegralService integralService;
	
	@Autowired
	private IntegralMapper mapper;

	private static String httpurl = "";

   static {
		MessageManager mmg = new MessageManager();
		httpurl = mmg.getSystemProperties("ims.url")+"/integral/addIntegralDetails4Api.do";

		 
   }

	/**
	 * @Description: 获得可兑换列表
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/getconvertibleList")
	@ResponseBody
	public String getconvertibleList(@RequestParam Map<String, Object> param) {

		log.info("*********获得可兑换列表--begin****************");
		if (StringUtils.isEmpty(param.get("userId"))	|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = integralService.getconvertibleList(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获得可兑换列表失败", e);
			return new AccessErrorResult(2004, "获得可兑换列表失败").toString();
		}
		log.info("*********获得可兑换列表--end****************");

		return new AccessSuccessResult(list).toString();

	}

	@RequestMapping("/signablelist")
	@ResponseBody
	public String signablelist(@RequestParam Map<String, Object> params,
			AppPager pager) throws AppRequestErrorException {

	   
			
		if (StringUtils.isEmpty(params.get("userId"))	|| StringUtils.isEmpty(params.get("pid"))
				|| StringUtils.isEmpty(params.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		int uid = Integer.valueOf(params.get("userId").toString()).intValue();
		int signCount = integralService.getUserTodaySignCount(uid);
		

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpledateformat.format(calendar.getTime());
		List<Map<String, Object>> tmpResult = integralService
				.getSignList(uid);

		Set<String> dateSet = new HashSet();
		Map<String, Integer> dateIndexMap = new HashMap();
		for (int i = 0; i < tmpResult.size(); i++) {
			Map<String, Object> tmpMap = tmpResult.get(i);
			String theDate = tmpMap.get("date").toString().substring(0, 10);
			if(i==0)
			{
				today = theDate;
			}
//			String sday = tmpMap.get("start_date").toString().substring(0, 10);
//			String eday=  tmpMap.get("end_date").toString().substring(0, 10);
//			
//			  log.info("strart"+"-"+sday+"   "+theDate+"  "+"enddate"+" "+eday);
//			
//			if(!(theDate.compareTo(sday)>=0) || !(theDate.compareTo(eday)<=0)  )
//			{
//			   log.info("dateerror");
//			   break;
//			}else{
//				
//				log.info(theDate.compareTo(sday));
//				log.info(theDate.compareTo(eday));
//			}
//			
			
			// Integer.valueOf(params.get("userId").toString()).intValue();
			int point = Integer.valueOf(tmpMap.get("point").toString()).intValue();
					//(int) tmpMap.get("point");
			if (dateIndexMap.containsKey(tmpMap.get("date").toString().substring(0, 10))) {
				if (point >= dateIndexMap.get(theDate).intValue()) {
					dateIndexMap.put(theDate, new Integer(point));
				}

			} else {
				dateIndexMap.put(theDate, new Integer(point));

			}

		}

		for (int i = 0; i < tmpResult.size(); i++) {
			Map<String, Object> tmpMap = tmpResult.get(i);
			String theDate = tmpMap.get("date").toString().substring(0, 10);
			int point = Integer.valueOf(tmpMap.get("point").toString()).intValue();
			if (dateIndexMap.get(theDate).intValue() == point) {

				if (theDate.equals(today)) {
					tmpMap.put("canSign", signCount == 0 ? "1" : "0");

				} else {
					tmpMap.put("canSign", "0");
				}

				result.add(tmpMap);
			}
		}
		return new AccessSuccessResult(result).toString();
	}

	/**
	 * 获取积分详情
	 */
	@RequestMapping("/getUserPoint")
	@ResponseBody
	public String getUserPoint(@RequestParam Map<String, Object> param,
			AppPager pager)
			throws AppRequestErrorException {
		// 用户ID
	
			
		if (StringUtils.isEmpty(param.get("userId"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		param.put("pager", pager);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = integralService.getUserPointById(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取积分详情失败了", e);
			return new AccessErrorResult(2004, "获取积分详情失败").toString();
		}

		return new AccessSuccessResult(list).toString();

	}

	// if(StringUtils.isEmpty(param.get("userId"))){
	// return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();

	/**
	 * @Description: 获得积分兑奖列表
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/getpointdrawList")
	@ResponseBody
	public String getpointdrawList(@RequestParam Map<String, Object> param) {

		log.info("*********获得积分抽奖列表--begin****************");
		if (StringUtils.isEmpty(param.get("userId"))
				|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = integralService.getdrawableList(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获得积分抽奖列表失败", e);
			return new AccessErrorResult(2004, "获得积分抽奖列表失败").toString();
		}
		log.info("*********获得积分抽奖列表--end****************");

		return new AccessSuccessResult(list).toString();

	}

	/**
	 * @Description: 积分抽奖
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/pointdraw")
	@ResponseBody
	public String pointdraw(@RequestParam Map<String, Object> param) {
		if (StringUtils.isEmpty(param.get("userId"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {

			Integer count = integralService.canDrawByActivityId(param);
			map.put("count", count);

		} catch (Exception e) {

			return new AccessErrorResult(2004, "error.msg.invalid.sql")
					.toString();
		}
		return new AccessSuccessResult(map).toString();
	}

	@RequestMapping("/dailysign")
	@ResponseBody
	public String DailySign(@RequestParam Map<String, Object> param) {
		if (StringUtils.isEmpty(param.get("userId"))	|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
		int uid = Integer.valueOf(param.get("userId").toString()).intValue();
		
		if(	integralService.getUserTodaySignCount(uid)>0)
		{
			return new AccessErrorResult(2004, "您已签到！")
			.toString();

		}
		

		Map<String, String> postpars = new HashMap<String, String>();
		postpars.put("userId", (String) param.get("userId"));
		postpars.put("direction", "0");
		postpars.put("integralActivityId", "3");

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
				Map<String,Object> 	obj1=(Map<String, Object>) map.get("obj"); 
		         obj.put("integralValue", obj1.get("integralValue"));	
				return new AccessSuccessResult(obj).toString();
			}
			else{
				
				return new AccessErrorResult(1002, "error msg")
				.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new AccessErrorResult(2004, "error.msg.invalid.sql")
					.toString();

		}

	//	log.info(requestResult);



		
	}

	@RequestMapping("/getpointpresentrate")
	@ResponseBody
	public String getpointpresentrate(@RequestParam Map<String, Object> param) {
		if (StringUtils.isEmpty(param.get("userId"))
				|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
	

		
		
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = integralService.getpointpresentrate(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());

			return new AccessErrorResult(2004, "获取失败").toString();
		}
		log.info("*********获得积分比率--end****************");

		return new AccessSuccessResult(list).toString();
	}
	/*
	 * 积分兑换
	 */
	@RequestMapping("/pointexchange")
	@ResponseBody
	public String pointexchange(@RequestParam Map<String, Object> param) {
		if (StringUtils.isEmpty(param.get("userId"))|| StringUtils.isEmpty(param.get("stationid"))
				|| StringUtils.isEmpty(param.get("couponvarietyid"))	|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
		
		Map<String, String> postpars = new HashMap<String, String>();
		postpars.put("userId", param.get("userId").toString());
		postpars.put("direction", "0");
		postpars.put("integralActivityId", "6");
		postpars.put("couponVarietyId", param.get("couponvarietyid").toString());
		postpars.put("electricPileId", param.get("stationid").toString());//电站
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
				
				log.info(map.get("obj"));
				
				Map<String,Object> 	obj1=(Map<String, Object>) map.get("obj"); 
		         obj.put("integralValue", obj1.get("integralValue"));	
		         obj.put("choiceCount", obj1.get("choiceCount"));	
		         obj.put("couponCount", obj1.get("couponCount"));	
				
			     //	obj.get("")
				 // JSONObject jsonObject = JSONObject.fromObject(map.get("obj"));
				return new AccessSuccessResult(obj).toString();

				
			}
			else{
				
				return new AccessErrorResult(1002, "兑换失败")
				.toString();
			}
			
			

			//log.info("ceshi:" + map);

		} catch (Exception e) {
			e.printStackTrace();
			return new AccessErrorResult(2004, "error.msg.invalid.sql")
					.toString();

		}

		
	}

	/*
	 * 充值赠送积分
	 */
	@RequestMapping("/rechargepresentpoint")
	@ResponseBody
	public String rechargepresentpoint(@RequestParam Map<String, Object> param) {
		log.info("*********充值获取积分 start****************");
		if (StringUtils.isEmpty(param.get("userId"))
				|| StringUtils.isEmpty(param.get("amount"))	|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
		Map<String, String> postpars = new HashMap<String, String>();
		postpars.put("userId", (String) param.get("userId"));
		postpars.put("direction", "0");
	
		postpars.put("moneyInvolved", param.get("amount").toString());
		postpars.put("integralActivityId", "1");
		
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
				
				log.info(map.get("obj"));
				
				Map<String,Object> 	obj1=(Map<String, Object>) map.get("obj"); 
		         obj.put("integralValue", obj1.get("integralValue"));	
		         obj.put("choiceCount", obj1.get("choiceCount"));	
		         obj.put("couponCount", obj1.get("couponCount"));	
				
			     //	obj.get("")
				 // JSONObject jsonObject = JSONObject.fromObject(map.get("obj"));
		         log.info("*********充值获取积分 end****************");
				return new AccessSuccessResult(obj).toString();

				
			}
			else{
				
				return new AccessErrorResult(1002, "error msg")
				.toString();
			}
			
			

			//log.info("ceshi:" + map);

		} catch (Exception e) {
			e.printStackTrace();
			return new AccessErrorResult(2004, "error.msg.invalid.sql")
					.toString();

		}
		

		
	}

	/*
	 * 充电赠送积分
	 */
	@RequestMapping("/chargingpresentpoint")
	@ResponseBody
	public String chargingpresentpoint(@RequestParam Map<String, Object> param) {

		log.info("******************充电送积分接口-begin************************");
		if (StringUtils.isEmpty(param.get("userId"))
				|| StringUtils.isEmpty(param.get("amount"))
				|| StringUtils.isEmpty(param.get("oid"))	|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		
		Map<String, String> postpars = new HashMap<String, String>();
		postpars.put("userId", (String) param.get("userId"));
		postpars.put("direction", "0");
		postpars.put("moneyInvolved", param.get("amount").toString());
		postpars.put("integralActivityId", "2");
		postpars.put("chargingOrderId",param.get("oid").toString() );
		
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
				
				log.info(map.get("obj"));
				Map<String,Object> 	obj1=(Map<String, Object>) map.get("obj"); 
		         obj.put("integralValue", obj1.get("integralValue"));	
		         obj.put("choiceCount", obj1.get("choiceCount"));	
		         obj.put("couponCount", obj1.get("couponCount"));	
				
			     //	obj.get("")
				 // JSONObject jsonObject = JSONObject.fromObject(map.get("obj"));
		     	log.info("******************充电送积分接口-end************************");
				return new AccessSuccessResult(obj).toString();

				
			}
			else{
				
				String str = map.get("msg").toString();
		     	log.info("******************充电送积分接口-end************************");
				return new AccessErrorResult(1002, str)
				.toString();
			}
			
			

			//log.info("ceshi:" + map);

		} catch (Exception e) {
			e.printStackTrace();
	     	log.info("******************充电送积分接口-end************************");
			return new AccessErrorResult(2004, "error.msg.invalid.sql")
					.toString();

		}
		
	}

	/*
	 * 分享后获取积分
	 */
	@RequestMapping("/getpointbyshare")
	@ResponseBody
	public String getpointbyshare(@RequestParam Map<String, Object> param) {

		if (StringUtils.isEmpty(param.get("userId"))
				|| StringUtils.isEmpty(param.get("oid"))	|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		Map<String, String> postpars = new HashMap<String, String>();
		postpars.put("userId", param.get("userId").toString());
		postpars.put("direction", "0");
		postpars.put("integralActivityId", "5");
		postpars.put("chargingOrderId",param.get("oid").toString() );
		
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
				
				log.info(map.get("obj"));
				
				int uid = Integer.valueOf(param.get("userId").toString()).intValue();
				Map<String,Object> 	obj1=(Map<String, Object>) map.get("obj"); 
		         obj.put("integralValue", obj1.get("integralValue"));	
		         obj.put("choiceCount", obj1.get("choiceCount"));	
		         obj.put("couponCount", obj1.get("couponCount"));	
				
		         mapper.marksharegetpointbyuId(uid);
			     //	obj.get("")
				 // JSONObject jsonObject = JSONObject.fromObject(map.get("obj"));
				return new AccessSuccessResult(obj).toString();

				
			}
			else{
				
				String str = map.get("msg").toString();
				
				return new AccessErrorResult(1002, str)
				.toString();
			}
			
			

			//log.info("ceshi:" + map);

		} catch (Exception e) {
			e.printStackTrace();
			return new AccessErrorResult(2004, "error.msg.invalid.sql")
					.toString();

		}

	
	}
	
	
	@RequestMapping("/findactivitylist")
	@ResponseBody
	public String findactivitylist(@RequestParam Map<String, Object> param) {
		if (StringUtils.isEmpty(param.get("userId"))
				|| StringUtils.isEmpty(param.get("pid"))
				|| StringUtils.isEmpty(param.get("cid"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = integralService.findactivitylist(param);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());

			return new AccessErrorResult(2004, "获取失败").toString();
		}
		log.info("*********获得活动列表--end****************");

		return new AccessSuccessResult(list).toString();
	}
	
	@RequestMapping("/getuserpointByUserId")
	@ResponseBody
	public String getuserpointByUserId(@RequestParam Map<String, Object> param) {
		if (StringUtils.isEmpty(param.get("userId"))) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int uid = Integer.valueOf(param.get("userId").toString()).intValue();
			long  point = integralService.getuserpointById(uid);
		
			map.put("point",new Long(point));
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());

			return new AccessErrorResult(2004, "获取失败").toString();
		}
		log.info("*********获得活动列表--end****************");

		return new AccessSuccessResult(map).toString();
	}

}
