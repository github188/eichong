/**     
 * @Title:  TblApplyEpController.java   
 * @Package com.wanma.controller.itf   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年12月2日 下午2:47:42   
 * @version V1.0     
 */  
package com.wanma.controller.itf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblApplyEp;
import com.wanma.service.TblUserCollectService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.HttpUtil;



/**
 * @desc 我的收藏Controller
 * @author cdy
 */
@Controller
@RequestMapping("/itf/collect")
public class TblUserCollectController {

	
	@Autowired
	TblUserCollectService tblUserCollectService;
	/**
	 * @Description: 我的收藏列表
	 * @param: userId
	 * @param: longitude
	 * @param: latitude
	 * @return: ResponseBody
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public String list(HttpServletRequest request) {
		//@RequestParam也可以用
		Map params = new HashMap();
//		System.out.println(request.getParameter("longitude"));
//		System.out.println("controller: longitude=" + param.get("lng"));
		if (StringUtils.isBlank(request.getParameter("userId"))
			|| StringUtils.isBlank(request.getParameter("lat"))
			|| StringUtils.isBlank(request.getParameter("lng"))) {
			return new FailedResponse(1050,"输入参数不全!").toString();
		}
		params.put("userId", request.getParameter("userId"));
		params.put("lat", request.getParameter("lat"));
		params.put("lng", request.getParameter("lng"));
		return HttpUtil.doPost(WanmaConstants.API_URL_FAVORITE_LIST, params);	
	}
	
	/**
	 * @Description: 添加收藏
	 * @return: ResponseBody
	 */
	@RequestMapping(value="/add", method = RequestMethod.GET)
	@ResponseBody
	public String add(HttpServletRequest request) {
		Map params = new HashMap();
		if (StringUtils.isBlank(request.getParameter("userId"))
				|| StringUtils.isBlank(request.getParameter("favoriteType"))//类型（1电桩，2电站）
				|| StringUtils.isBlank(request.getParameter("favoriteTypeId"))) {  //电桩ID/电桩ID
				return new FailedResponse(1050,"输入参数不全!").toString();
			}
		params.put("userId", request.getParameter("userId"));
		params.put("favoriteType", request.getParameter("favoriteType"));
		params.put("favoriteTypeId", request.getParameter("favoriteTypeId"));
		return HttpUtil.doPost(WanmaConstants.API_URL_ADD_COLLECT, params);	
	}
	
	/**
	 * @Description: 根据收藏ID删除收藏记录
	 * @return: ResponseBody
	 */
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(HttpServletRequest request) {
		Map params = new HashMap();
		if (StringUtils.isBlank(request.getParameter("userCollectId"))) { 
				return new FailedResponse(1050,"收藏id不能为空").toString();
			}
		params.put("userCollectId", request.getParameter("userCollectId"));

		return HttpUtil.doPost(WanmaConstants.API_URL_DELETE_COLLECT, params);	
	}
	

	
	
}
