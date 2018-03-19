package com.wanma.controller;

import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.base.common.SessionMgr;
import com.pub.model.TblUser;
import com.wanma.service.CmsAssetStatisticsService;
import com.wanma.service.impl.CmsChartHeadChargeStatisticsServiceImpl;
import com.wanma.service.impl.CmsMapStatisticsServiceImpl;
 
/**
 * @Description: 图表controller
 * @author wubc
 * @createTime：2015-6-29
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/chartStatistics")
public class VCmsChartStatisticsController {
	@Autowired
	private CmsAssetStatisticsService cmsAssetStatisticsServiceImpl;
	
	@Autowired
	private CmsChartHeadChargeStatisticsServiceImpl cmsChartStatisticsServiceImpl;
	
	@Autowired
	private CmsMapStatisticsServiceImpl cmsMapStatisticsServiceImpl;
	
	/**
	 * 资产统计
	 */
	@RequestMapping("/getAssetStatisticsCount")
	@ResponseBody
	public String getAssetStatisticsCount(@RequestParam Map<String, Object> params,HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		Map<String, Object> data = cmsAssetStatisticsServiceImpl.queryAllPointCount(params);//充电点统计
		data.putAll(cmsAssetStatisticsServiceImpl.queryPileInfoCount(params));//电桩统计
		data.putAll(cmsAssetStatisticsServiceImpl.queryAllHeadCount(params));//枪口统计
		return JSONObject.toJSONString(data);
	}

	/**
	 * 充电情况统计
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping("/getChargeInfoCount")
	@ResponseBody
	public String getChargeInfoCount(@RequestParam Map<String, Object> params,HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		return cmsAssetStatisticsServiceImpl.queryChargeInfoCount(params);
	}
	
	/**
	 * 枪头统计
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping("/getHeadInfoCount")
	@ResponseBody
	public String getHeadInfoCount(@RequestParam Map<String, Object> params,HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		return cmsAssetStatisticsServiceImpl.queryHeadInfoCount(params);
	}

	/**
	 * 枪头充电统计
	 * @param params
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping("/getHeadChargeData")
	@ResponseBody
	public String getHeadChargeData(@RequestParam Map<String, Object> params,
			HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		return cmsChartStatisticsServiceImpl.getJsonData(params);
	}
	
    /**
     * 地图信息查询
     * @param request
     * @param params
     * @param pager
     * @param model
     * @param electricPileMonitor
     * @return
     */
	@RequestMapping("/getPileMapData")
	@ResponseBody
	public String getPileMapData(@RequestParam Map<String, Object> params,
			HttpServletRequest request) throws URISyntaxException {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		return cmsMapStatisticsServiceImpl.getJsonData(params);
	}
	
	/**
	 * 资产统计(充电点内部)
	 */
	@RequestMapping("/getAssetStatisticsCountInner")
	@ResponseBody
	public String getAssetStatisticsCountInner(@RequestParam Map<String, Object> params,HttpServletRequest request) throws URISyntaxException {
		Map<String, Object> data = cmsAssetStatisticsServiceImpl.queryPileInfoCountInner(params);//电桩统计
		data.putAll(cmsAssetStatisticsServiceImpl.queryAllHeadCountInner(params));//枪口统计
		return JSONObject.toJSONString(data);
	}

	/**
	 * 充电情况统计(充电点内部)
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping("/getChargeInfoCountInner")
	@ResponseBody
	public String getChargeInfoCountInner(@RequestParam Map<String, Object> params,HttpServletRequest request) throws URISyntaxException {
		return JSONObject.toJSONString(cmsAssetStatisticsServiceImpl.queryChargeInfoCountInner(params));
	}
	
	/**
	 * 枪头统计(充电点内部)
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	@RequestMapping("/getHeadInfoCountInner")
	@ResponseBody
	public String getHeadInfoCountInner(@RequestParam Map<String, Object> params,HttpServletRequest request) throws URISyntaxException {
		return JSONObject.toJSONString(cmsAssetStatisticsServiceImpl.queryHeadInfoCountInner(params));
	}
	
	
}
