package com.wanma.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.app.service.IntegralService;
import com.wanma.app.util.HttpRequestUtil;
import com.wanma.app.util.duiba.BulidUrl;
import com.wanma.app.util.duiba.SignTool;
import com.wanma.app.util.duiba.entity.AddCreditsParams;
import com.wanma.app.util.duiba.entity.CreditConsumeParams;
import com.wanma.app.util.duiba.entity.CreditNotifyParams;
import com.wanma.app.util.duiba.result.AddCreditResult;
import com.wanma.app.util.duiba.result.CreditConsumeResult;

import java.util.*;

@Controller
@RequestMapping("/duiba")
public class DuiBaController {
	
	 @Autowired
	 private IntegralService integralService;

	 private static Logger log = Logger.getLogger(GovBusiRecordController.class);
	 
	 static String appKey = "3xaRiicGTbgvowUF3FVsPC6xyooS";  //此处填写开发者自己的appKey
	 static String appSecret = "fkm2rcu8FwdkprMVQoZka3gVfYC"; //此处填写开发者自己的appSecret
	 
		private static String httpurl = "";

		   
		   static {
				MessageManager mmg = new MessageManager();
				httpurl = mmg.getSystemProperties("ims.url")+"/integral/addIntegralDetails4Api.do";
                
				//httpurl = "http://101.69.243.110:18080/ims/integral/addIntegralDetails4Api.do";
					
				 
		   }

	 /**
	  * 兑吧商城主页面跳转
	  * @param request
	  * @param response
	  */
	@RequestMapping({ "/freeLogin" })
	@ResponseBody
	public void freeLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
		String userId=RequestParamUtil.getEncodeParam(request, "userId");
		
		if (StringUtils.isEmpty(RequestParamUtil.getEncodeParam(request, "userId"))) {
			
			
			return;
			
		}
		

	
		
		
		int uid = Integer.parseInt(userId);
		long points = integralService.getuserpointById(uid);
		
		log.info("FREELOGIN"+userId+"  "+String.valueOf(points));
		
		String backUrl=	BulidUrl.buildAutoLoginRequest(userId, points,null);
		
		 log.info(backUrl);
		
		response.sendRedirect(backUrl);  
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	/**
	 * 积分扣除接口，兑吧回调
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/pointConsume" })
	@ResponseBody
	public String parseCreditConsume(HttpServletRequest request) throws Exception{
		
		log.info("pointConsume ");
	    log.info(request.getParameterMap());	
               		
	    boolean pointConsumeresult = false;
	    String error_msg = "";
		 Map map = new HashMap();  
	        Enumeration paramNames = request.getParameterNames();  
	        while (paramNames.hasMoreElements()) {  
	            String paramName = (String) paramNames.nextElement();  
	  
	            String[] paramValues = request.getParameterValues(paramName);  
	            if (paramValues.length == 1) {  
	                String paramValue = paramValues[0];  
	                if (paramValue.length() != 0) {  
	                    map.put(paramName, paramValue);  
	                }  
	            }  
	        }  
	  
	        Set<Map.Entry<String, String>> set = map.entrySet();  
	        System.out.println("------------------------------");  
	        for (Map.Entry entry : set) {  
	            System.out.println(entry.getKey() + ":" + entry.getValue());  
	        }  
	        System.out.println("------------------------------");  

	        
	        //request.getParameter("uid") credits
	    	Map<String, String> postpars = new HashMap<String, String>();
			postpars.put("userId", (String)request.getParameter("uid"));
			postpars.put("direction", "1");
			postpars.put("integralValue", (String)request.getParameter("credits"));
			if((String)request.getParameter("params")==null || (String)request.getParameter("params")=="" )
			{
				postpars.put("integralActivityId", "7");
				log.info("抽奖活动");
			}else
			{
				log.info("兑换活动");
				postpars.put("integralActivityId", "6"); //couponVarietyId
				postpars.put("couponVarietyId", request.getParameter("params"));
				
			}

			String requestResult = new String();
			Map<String, String> postmap = new HashMap<String, String>();
			postmap.put("contents", postpars.toString());
			log.info("GLB contents"+postpars.toString());
			log.info(postmap);
			try {
				requestResult = HttpRequestUtil.post(httpurl, postmap);
				JSONObject jasonObject = JSONObject.fromObject(requestResult);
				log.info("jasonObject:" + jasonObject);
				Map resultmap = jasonObject;
				Map<String,Object> obj=new HashMap<String,Object>();
				if( (boolean) resultmap.get("success")){
			
					pointConsumeresult = true;
				
				}
				else{
					
					error_msg = resultmap.get("msg").toString();
					
					log.info("调用GLB"+obj.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			
				log.info("调用GLB"+e.getMessage());
			}
	        
	        
		boolean verify=SignTool.signVerify(appSecret, request);
//		if(!verify){
//			throw new Exception("签名验证失败");
//		}
		CreditConsumeParams params=new CreditConsumeParams();
		params.setAppKey(appKey);
		params.setUid(request.getParameter("uid"));
		params.setCredits(Long.valueOf(request.getParameter("credits")));
		params.setTimestamp(new Date(Long.valueOf(request.getParameter("timestamp"))));
		params.setDescription(request.getParameter("description"));
		params.setOrderNum(request.getParameter("orderNum"));
		params.setType(request.getParameter("type"));
		if(request.getParameter("facePrice")!=null){
			params.setFacePrice(Integer.valueOf(request.getParameter("facePrice")));
		}
		if(request.getParameter("actualPrice")!=null){
			params.setActualPrice(Integer.valueOf(request.getParameter("actualPrice")));
		}
		if(request.getParameter("itemCode")!=null){
			params.setItemCode(request.getParameter("itemCode"));
		}
		params.setAlipay(request.getParameter("alipay"));
		params.setPhone(request.getParameter("phone"));
		params.setQq(request.getParameter("qq"));
		if(request.getParameter("waitAudit")!=null){
			params.setWaitAudit(Boolean.valueOf(request.getParameter("waitAudit")));
		}
		params.setIp(request.getParameter("ip"));
		params.setParams(request.getParameter("params"));
		CreditConsumeResult result= new CreditConsumeResult(pointConsumeresult);
		try{
		result.setBizId(params.getUid()+new Date().getTime());
		result.setCredits(integralService.getuserpointById(Integer.parseInt(request.getParameter("uid").toString())));
		//result.setStatus("ok");
		result.setErrorMessage("");
		if(!pointConsumeresult)
		{
			result.setErrorMessage(error_msg);
		}
		
		
		log.info(result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}
	/**
	 * 积分扣除确认通知接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/notify" })
	@ResponseBody
	public String parseCreditNotify(HttpServletRequest request) throws Exception{
		
		
		log.info("notify ");
		 Map map = new HashMap();  
	        Enumeration paramNames = request.getParameterNames();  
	        while (paramNames.hasMoreElements()) {  
	            String paramName = (String) paramNames.nextElement();  
	  
	            String[] paramValues = request.getParameterValues(paramName);  
	            if (paramValues.length == 1) {  
	                String paramValue = paramValues[0];  
	                if (paramValue.length() != 0) {  
	                    map.put(paramName, paramValue);  
	                }  
	            }  
	        }  
	  
	        Set<Map.Entry<String, String>> set = map.entrySet();  
	        System.out.println("------------------------------");  
	        for (Map.Entry entry : set) {  
	            System.out.println(entry.getKey() + ":" + entry.getValue());  
	        }  
	        System.out.println("------------------------------"); 
		if(!appKey.equals(request.getParameter("appKey"))){
			throw new Exception("appKey不匹配");
		}
		if(request.getParameter("timestamp")==null){
			throw new Exception("请求中没有带时间戳");
		}
		boolean verify=SignTool.signVerify(appSecret, request);
	
		if(!verify){
			
			log.info("通知签名验证失败");
			throw new Exception("签名验证失败");
		}
		
		
		
		
		CreditNotifyParams params=new CreditNotifyParams();
		params.setSuccess(Boolean.valueOf(request.getParameter("success")));
		params.setErrorMessage(request.getParameter("errorMessage"));
		params.setBizId(request.getParameter("bizId"));
		params.setUid(request.getParameter("uid"));
		params.setAppKey(request.getParameter("appKey"));
		params.setTimestamp(new Date(Long.valueOf(request.getParameter("timestamp"))));
		params.setOrderNum(request.getParameter("orderNum"));
		return "ok";
	}
	/**
	 * 积分增加接口，兑吧回调
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCredits")
	@ResponseBody
	public String parseaddCredits(HttpServletRequest request) throws Exception {
		
		
		boolean addcreditResult = false;
		String error_msg = "";
		
		log.info("addCredits "+request.getParameter("params").toString());
		 Map map = new HashMap();  
	        Enumeration paramNames = request.getParameterNames();  
	        while (paramNames.hasMoreElements()) {  
	            String paramName = (String) paramNames.nextElement();  
	  
	            String[] paramValues = request.getParameterValues(paramName);  
	            if (paramValues.length == 1) {  
	                String paramValue = paramValues[0];  
	                if (paramValue.length() != 0) {  
	                    map.put(paramName, paramValue);  
	                }  
	            }  
	        }   
	        Set<Map.Entry<String, String>> set = map.entrySet();  
	        System.out.println("------------------------------");  
	        for (Map.Entry entry : set) {  
	            System.out.println(entry.getKey() + ":" + entry.getValue());  
	        }  
	        System.out.println("------------------------------?"); 
		if(!appKey.equals(request.getParameter("appKey"))){
			log.info("appKey不匹配");
			throw new Exception("appKey不匹配");
			
		}
		if(request.getParameter("timestamp")==null){
			log.info("请求中没有带时间戳");
			throw new Exception("请求中没有带时间戳");
		}
		boolean verify=SignTool.signVerify(appSecret, request);
		verify = true;
		if(!verify){
			log.info("签名验证失败");
			throw new Exception("签名验证失败");
		}
		
		log.info("description "+request.getParameter("description").toString());
	 	Map<String, String> postpars = new HashMap<String, String>();
		postpars.put("userId", (String)request.getParameter("uid"));
		postpars.put("direction", "0");
	
		if(request.getParameter("description").toString().indexOf("兑换")!=-1)
		{
			log.info("兑换优惠券");
			postpars.put("integralActivityId", "6"); //couponVarietyId
			postpars.put("couponVarietyId", request.getParameter("params"));
			
		}else{ //抽奖
			log.info("抽奖活动");
		
			postpars.put("integralActivityId", "7");
			if(request.getParameter("params").toString().equals("9999"))//加积分
			{
				
			
				String description = request.getParameter("description").toString();
				String point = description.substring(0, description.indexOf("积分"));
				postpars.put("integralValue", point);
				log.info("加积分"+point);
			}else{//优惠券

				String coupon = request.getParameter("params").toString();
				//couponVarietyId
				postpars.put("couponVarietyId", coupon);
				log.info("加券"+coupon);
			}

		}
		
		try {
			
			Map<String, String> postmap = new HashMap<String, String>();
			postmap.put("contents", postpars.toString());
			log.info("GLB contents"+postpars.toString());
		    String	requestResult = HttpRequestUtil.post(httpurl, postmap);
			JSONObject jasonObject = JSONObject.fromObject(requestResult);
			log.info("jasonObject:" + jasonObject);
			Map resultmap = jasonObject;
			Map<String,Object> obj=new HashMap<String,Object>();
			if( (boolean) resultmap.get("success")){
				 addcreditResult = true;
				
			}
			else{
				
				error_msg = resultmap.get("msg").toString();
				log.info("调用GLB"+obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		
			log.info("调用GLB"+e.getMessage());
		}
		
		
	
		
		AddCreditsParams params=new AddCreditsParams();
		params.setAppKey(appKey);
		params.setUid(request.getParameter("uid"));
		params.setCredits(Long.valueOf(request.getParameter("credits")));
		params.setTimestamp(new Date(Long.valueOf(request.getParameter("timestamp"))));
		params.setDescription(request.getParameter("description"));
		params.setOrderNum(request.getParameter("orderNum"));
		params.setType(request.getParameter("type"));
		if(request.getParameter("transfer")!=null){
			params.setTransfer(request.getParameter("transfer"));
		}
		AddCreditResult result= new AddCreditResult(addcreditResult);
		
		result.setBizId("DB"+params.getUid()+new Date().getTime());
		result.setCredits(integralService.getuserpointById(Integer.parseInt(request.getParameter("uid").toString())));
		
		return result.toString();
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * 以下仅用作测试
	 * 
	 * 
	 */
	
	
	@RequestMapping(value = "/consumeTest")
	@ResponseBody
	public String consumeTest(HttpServletRequest request) throws Exception {
		
		
		
		log.info("addCredits "+request.getParameter("params").toString());
	  
		if(request.getParameter("contents")!=null)
		{
			
			String requestResult = new String();
			Map<String, String> postmap = new HashMap<String, String>();
			postmap.put("contents", request.getParameter("contents").toString());
			log.info("GLB contents"+request.getParameter("contents").toString());
			
			try {
				requestResult = HttpRequestUtil.post(httpurl, postmap);
				JSONObject jasonObject = JSONObject.fromObject(requestResult);
				log.info("jasonObject:" + jasonObject);
				Map resultmap = jasonObject;
				Map<String,Object> obj=new HashMap<String,Object>();
				if( (boolean) resultmap.get("success")){
					Map<String,Object> 	obj1=(Map<String, Object>) resultmap.get("obj"); 
			        //   obj.put("integralValue", obj1.get("integralValue"));	
					
					log.info("调用GLB"+obj.toString());
					
					return obj.toString();
				}
				else{
					
					log.info("调用GLB"+obj.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			
				log.info("调用GLB"+e.getMessage());
			}
			
		}
		
		
		
		return "";

	}
	
	
	@RequestMapping(value = "/addcreditsTest")
	@ResponseBody
	public String addcreditsTest(HttpServletRequest request) throws Exception {
		
		
		
		log.info("addCredits "+request.getParameter("params").toString());
	  
		if(request.getParameter("contents")!=null)
		{
			
			String requestResult = new String();
			Map<String, String> postmap = new HashMap<String, String>();
			postmap.put("contents", request.getParameter("contents").toString());
			log.info("GLB contents"+request.getParameter("contents").toString());
			log.info(postmap);
			try {
				requestResult = HttpRequestUtil.post(httpurl, postmap);
				JSONObject jasonObject = JSONObject.fromObject(requestResult);
				log.info("jasonObject:" + jasonObject);
				Map resultmap = jasonObject;
				Map<String,Object> obj=new HashMap<String,Object>();
				if( (boolean) resultmap.get("success")){
					Map<String,Object> 	obj1=(Map<String, Object>) resultmap.get("obj"); 
			        //   obj.put("integralValue", obj1.get("integralValue"));	
					
					log.info("调用GLB"+obj.toString());
					
					return obj.toString();
				}
				else{
					
					log.info("调用GLB"+obj.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			
				log.info("调用GLB"+e.getMessage());
			}
			
		}
		
		
		
		return "";

	}
	
}
