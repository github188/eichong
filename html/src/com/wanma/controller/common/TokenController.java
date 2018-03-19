package com.wanma.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanma.model.TblPartner;
import com.wanma.service.CommonService;
import com.wanma.service.TcbPartnerService;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.AesCBC;
import com.wanma.support.utils.CecPost;
import com.wanma.support.utils.JsonResult;
import com.wanma.support.utils.MD5Util;
import com.wanma.support.utils.TokenUtil;

/**
 * @Description: 充电管理控制层
 * @author lhy
 * @createTime：2015-11-19 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/tk")
public class TokenController {

	/*private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenController.class);*/
	@Autowired
	private RedisService redisService;
	@Autowired
	private TcbPartnerService partnerService;
	
	@Autowired
	private CommonService commonService;


	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	@ResponseBody
	public String getToken(String OperatorID, String OperatorSecret)
			throws Exception {
		String org = OperatorID;
		String secret = OperatorSecret;

		if (StringUtils.isBlank(org) || StringUtils.isBlank(secret)) {
			ResultResponse resultRespone = new ResultResponse();
			resultRespone.setStatus(500);
			resultRespone.setMsg("组织机构代码和密钥不可为空！");
			return resultRespone.toString();
		}
		String authCode = redisService.strGet(WanmaConstants.PREFIX_ORG + org)
				.split(":")[0];
		Map<String, Object> data = new HashMap<String, Object>();
		if (!authCode.equals(secret)) {
			ResultResponse resultRespone = new ResultResponse();
			resultRespone.setStatus(500);
			resultRespone.setMsg("操作失败！");
			data.put("operatorID", org);
			data.put("succStat", 1);
			data.put("accessToken", "");
			data.put("tokenAvailableTime", 0);
			data.put("failReason", 2);
			resultRespone.setData(data);
			return resultRespone.toString();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String time = df.format(new Date());
		String t = MD5Util.Md5(org + secret + time);

		data.put("OperatorID", org);
		data.put("SuccStat", 0);
		data.put("AccessToken", t);
		data.put("TokenAvailableTime", WanmaConstants.PREFIX_TOKWEN_TERM);
		data.put("FailReason", 0);
		return new ResultResponse(data).toString();
	}
	
	@RequestMapping(value = "/getPartner", method = RequestMethod.GET)
	@ResponseBody
	public String getPartner(String OperatorID)
			throws Exception {
		TblPartner partner =new TblPartner();
		partner=partnerService.PartnerInfo(OperatorID);

		
		return new ResultResponse(partner).toString();
	}
	@RequestMapping(value="/query_third_token" , method = RequestMethod.GET)
	@ResponseBody
	public String query_token_nari(String OperatorID)
			throws Exception {
		Map<String,String> map =new HashMap<String,String>();
		
		map=commonService.getCecToken(OperatorID);
		
		return new ResultResponse(map).toString();
	}

}
