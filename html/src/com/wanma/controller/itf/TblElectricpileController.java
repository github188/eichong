package com.wanma.controller.itf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wanma.model.TblElectricpile;
import com.wanma.service.TblElectricpileService;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.ApiUtil;
import com.wanma.support.utils.HttpUtil;

/**
 * @Description: 电桩管理控制层
 * @author wbc
 * @createTime：2015-11-19 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/itf/electric")
public class TblElectricpileController {
	@Autowired
	private TblElectricpileService electricpileService;

	@Autowired
	private RedisService redisService;

	/**
	 * @Description: 电桩地图查询
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getElectricPileMapList")
	@ResponseBody
	public String getElectricpileListForMap(HttpServletRequest request) {

		String powerInterface = request.getParameter("powerInterface");
		String chargingMode = request.getParameter("chargingMode");
		String freeStatus = request.getParameter("freeStatus");
		String matchMyCar = request.getParameter("matchMyCar");
		String userId = request.getParameter("userId");
		String cityCode = request.getParameter("cityCode");
		String reqTime = request.getParameter("reqTime");
		Map params = new HashMap();
		params.put("powerInterface",
				StringUtils.isNotBlank(powerInterface) ? powerInterface : null);
		params.put("chargingMode",
				StringUtils.isNotBlank(chargingMode) ? chargingMode : null);
		params.put("freeStatus",
				StringUtils.isNotBlank(freeStatus) ? freeStatus : null);
		params.put("matchMyCar",
				StringUtils.isNotBlank(matchMyCar) ? matchMyCar : null);
		params.put("userId", StringUtils.isNotBlank(userId) ? userId : null);
		params.put("cityCode", StringUtils.isNotBlank(cityCode) ? cityCode
				: null);
		params.put("reqTime", StringUtils.isNotBlank(reqTime) ? reqTime : null);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ELECTRIC_MAP, params);
	}

	/**
	 * @Description: 电桩列表查询
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getElectricPileListN")
	@ResponseBody
	public String getElectricPileListN(HttpServletRequest request) {

		String Longitude = request.getParameter("Longitude");
		String Latitude = request.getParameter("Latitude");
		String electricPrices = request.getParameter("electricPrices");
		String electricEvaluate = request.getParameter("electricEvaluate");
		String screenRadius = request.getParameter("screenRadius");
		String powerInterface = request.getParameter("powerInterface");
		String chargingMode = request.getParameter("chargingMode");
		String freeStatus = request.getParameter("freeStatus");
		String matchMyCar = request.getParameter("matchMyCar");
		String userId = request.getParameter("userId");
		String searchKey = request.getParameter("searchKey");
		Map params = new HashMap();
		if (StringUtils.isBlank(Longitude)) {
			return new FailedResponse(1050, "经度不能为空").toString();
		} else {
			params.put("Longitude", Longitude);
		}

		if (StringUtils.isBlank(Latitude)) {
			return new FailedResponse(1050, "纬度不能为空").toString();
		} else {
			params.put("Latitude", Latitude);
		}

		params.put("electricPrices",
				StringUtils.isNotBlank(electricPrices) ? electricPrices : null);
		params.put("electricEvaluate",
				StringUtils.isNotBlank(electricEvaluate) ? electricEvaluate
						: null);
		params.put("screenRadius",
				StringUtils.isNotBlank(screenRadius) ? screenRadius : null);
		params.put("powerInterface",
				StringUtils.isNotBlank(powerInterface) ? powerInterface : null);
		params.put("chargingMode",
				StringUtils.isNotBlank(chargingMode) ? chargingMode : null);
		params.put("freeStatus",
				StringUtils.isNotBlank(freeStatus) ? freeStatus : null);
		params.put("matchMyCar",
				StringUtils.isNotBlank(matchMyCar) ? matchMyCar : null);
		params.put("userId", StringUtils.isNotBlank(userId) ? userId : null);
		params.put("searchKey", StringUtils.isNotBlank(searchKey) ? searchKey
				: null);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ELECTRIC_LIST, params);
	}

	/**
	 * @Description: 根据输入的充电点搜索
	 * @return: ResponseBody
	 */
	@RequestMapping("/searchByAddress")
	@ResponseBody
	public String searchElectricpileList(TblElectricpile pile) {
		if (StringUtils.isNotBlank(pile.getElpiElectricpileaddress())) {
			TblElectricpile temp = new TblElectricpile();
			temp.setElpiElectricpileaddress(pile.getElpiElectricpileaddress());
			List<TblElectricpile> pileList = electricpileService
					.getElectricpileListForMap(pile);

			Map<String, Object> pileMap = null;
			List<Map<String, Object>> MapList = new ArrayList<Map<String, Object>>();

			for (TblElectricpile p : pileList) {
				pileMap = new HashMap<String, Object>();
				pileMap.put("electricId", p.getPkElectricpile());
				pileMap.put("electricType", p.getElectricType());
				pileMap.put("electricState", p.getElpiState());
				pileMap.put("longitude", p.getElpiLongitude());
				pileMap.put("latitude", p.getElpiLatitude());
				pileMap.put("cityCode", p.getElpiAreacode());
				pileMap.put("electricName", p.getElpiElectricpilename());
				pileMap.put("electricAddress", p.getElpiElectricpileaddress());
				pileMap.put("isAppoint", p.getElpiIsappoint());
				MapList.add(pileMap);
			}

			return new ResultResponse<List<Map<String, Object>>>(MapList)
					.toString();
		} else {
			return new FailedResponse(1050, "充电点地址不能为空").toString();
		}

	}

	/**
	 * @Description: 地图锚点站、桩简介
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getAnchorSummary")
	@ResponseBody
	public String getAnchorSummary(HttpServletRequest request) {
		String lng = request.getParameter("lng");
		String lat = request.getParameter("lat");
		String eid = request.getParameter("eid");
		String type = request.getParameter("type");
		Map params = new HashMap();
		params.put("lng", lng);
		params.put("lat", lat);
		if (StringUtils.isBlank(eid)) {
			return new FailedResponse(1050, "桩、站id不能为空").toString();
		} else {
			params.put("eid", eid);
		}
		if (StringUtils.isBlank(type)) {
			return new FailedResponse(1050, "桩、站类型不能为空").toString();
		} else {
			params.put("type", type);
		}
		
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ELECTRIC_DETAILMAP,
				params);

	}

	/**
	 * @Description：电桩详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/electricPileDetail")
	@ResponseBody
	public String getElectricPileDetail(HttpServletRequest request) {

		String electricPileId = request.getParameter("electricPileId");
		String pkUserinfo = request.getParameter("pkUserinfo");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		Map params = new HashMap();
		if (StringUtils.isBlank(electricPileId)) {
			return new FailedResponse(1050, "电桩id不能为空").toString();
		} else {
			params.put("electricPileId", electricPileId);
		}
		if (StringUtils.isBlank(pkUserinfo)) {
			return new FailedResponse(1050, "用户id不能为空").toString();
		} else {
			params.put("pkUserinfo", pkUserinfo);
		}

		params.put("longitude", StringUtils.isNotBlank(longitude) ? longitude
				: null);
		params.put("latitude", StringUtils.isNotBlank(latitude) ? latitude
				: null);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ELECTRIC_DETAIL, params);
	}

	/**
	 * @Description：电站详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/powerStationDetail")
	@ResponseBody
	public String getPowerStationDetail(HttpServletRequest request) {

		String powerStationId = request.getParameter("powerStationId");
		String pkUserinfo = request.getParameter("pkUserinfo");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");

		Map params = new HashMap();
		if (StringUtils.isBlank(powerStationId)) {
			return new FailedResponse(1050, "电站id不能为空").toString();
		} else {
			params.put("powerStationId", powerStationId);
		}
		if (StringUtils.isBlank(pkUserinfo)) {
			return new FailedResponse(1050, "用户id不能为空").toString();
		} else {
			params.put("pkUserinfo", pkUserinfo);
		}

		params.put("longitude", StringUtils.isNotBlank(longitude) ? longitude
				: null);
		params.put("latitude", StringUtils.isNotBlank(latitude) ? latitude
				: null);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_POWERSTATION_DETAIL,
				params);
	}

	/**
	 * @Description：枪头预约、续约
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/bespoke")
	@ResponseBody
	public String insertBespoke(HttpServletRequest request) {
		String pkBespoke = request.getParameter("pkBespoke");
		String bespElectricpileid = request.getParameter("bespElectricpileid");
		String bespBespoketime = request.getParameter("bespBespoketime");
		String bespBespokeremark = request.getParameter("bespBespokeremark");
		String bespBespoketimes = request.getParameter("bespBespoketimes");
		String bespElectricpilehead = request
				.getParameter("bespElectricpilehead");
		String bespUserinfo = request.getParameter("bespUserinfo");
		String bespResepaymentcode = request
				.getParameter("bespResepaymentcode");
		String bespFrozenamt = request.getParameter("bespFrozenamt");
		String bespBespokeprice = request.getParameter("bespBespokeprice");
		String bespBeginTime = request.getParameter("bespBeginTime");
		String bespEndTime = request.getParameter("bespEndTime");
		String did = request.getParameter("did");
		String org = request.getParameter("org");
		String payMod = request.getParameter("payMod");
		did = ApiUtil.encode(did);
		Map params = new HashMap();
		params.put("pkBespoke", StringUtils.isNotBlank(pkBespoke) ? pkBespoke
				: null);
		if (StringUtils.isBlank(bespElectricpileid)) {
			return new FailedResponse(1050, "电桩ID不能为空").toString();
		} else {
			params.put("bespElectricpileid", bespElectricpileid);
		}
		if (StringUtils.isBlank(bespBespoketime)) {
			return new FailedResponse(1050, "预约时间不能为空").toString();
		} else {
			params.put("bespBespoketime", bespBespoketime);
		}
		params.put("bespBespokeremark", StringUtils
				.isNotBlank(bespBespokeremark) ? bespBespokeremark : null);
		if (StringUtils.isBlank(bespBespoketimes)) {
			return new FailedResponse(1050, "预约时间段不能为空").toString();
		} else {
			params.put("bespBespoketimes", bespBespoketimes);
		}
		if (StringUtils.isBlank(bespElectricpilehead)) {
			return new FailedResponse(1050, "枪口ID不能为空").toString();
		} else {
			params.put("bespElectricpilehead", bespElectricpilehead);
		}
		if (StringUtils.isBlank(bespUserinfo)) {
			return new FailedResponse(1050, "用户ID不能为空").toString();
		} else {
			params.put("bespUserinfo", bespUserinfo);
		}
		params.put("bespResepaymentcode", StringUtils
				.isNotBlank(bespResepaymentcode) ? bespResepaymentcode : null);
		if (StringUtils.isBlank(bespFrozenamt)) {
			return new FailedResponse(1050, "预约冻结资金不能为空").toString();
		} else {
			params.put("bespFrozenamt", bespFrozenamt);
		}
		if (StringUtils.isBlank(bespBespokeprice)) {
			return new FailedResponse(1050, "预约单价不能为空").toString();
		} else {
			params.put("bespBespokeprice", bespBespokeprice);
		}
		if (StringUtils.isBlank(bespBeginTime)) {
			return new FailedResponse(1050, "预约开始时间不能为空").toString();
		} else {
			params.put("bespBeginTime", bespBeginTime);
		}
		if (StringUtils.isBlank(bespEndTime)) {
			return new FailedResponse(1050, "预约结束时间不能为空").toString();
		} else {
			params.put("bespEndTime", bespEndTime);
		}
		if (StringUtils.isBlank(did)) {
			return new FailedResponse(1050, "设备码不能为空").toString();
		} else {
			params.put("did", did);
		}
		params.put("org", org);
		params.put("payMod", payMod);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_INSERT_BESPOKE, params);
	}

	/**
	 * @Description：预约订单
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/myBespoke")
	@ResponseBody
	public String selectBespokes(HttpServletRequest request) {
		String bespUserInfo = request.getParameter("bespUserInfo");
		Map params = new HashMap();
		if (StringUtils.isBlank(bespUserInfo)) {
			return new FailedResponse(1050, "用户id不能为空").toString();
		} else {
			params.put("bespUserInfo", bespUserInfo);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_MYBESPOKE, params);
	}

	/**
	 * @Description：预约详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/detailBespoke")
	@ResponseBody
	public String selectBespokeById(HttpServletRequest request) {
		String pkBespoke = request.getParameter("pkBespoke");
		Map params = new HashMap();
		if (StringUtils.isBlank(pkBespoke)) {
			return new FailedResponse(1050, "预约id不能为空").toString();
		} else {
			params.put("pkBespoke", pkBespoke);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_BESPOKE_DETAIL, params);
	}

	/**
	 * @Description：取消预约
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/cancel")
	@ResponseBody
	public String updateBespStatus(HttpServletRequest request) {
		String pkBespoke = request.getParameter("pkBespoke");
		String bespBeginTime = request.getParameter("bespBeginTime");
		String bespElectricpilehead = request
				.getParameter("bespElectricpilehead");
		String uId = request.getParameter("uId");
		String did = request.getParameter("did");
		did = ApiUtil.encode(did);
		Map params = new HashMap();
		if (StringUtils.isBlank(pkBespoke)) {
			return new FailedResponse(1050, "预约id不能为空").toString();
		} else {
			params.put("pkBespoke", pkBespoke);
		}
		params.put("bespBeginTime", bespBeginTime);
		if (StringUtils.isBlank(bespElectricpilehead)) {
			return new FailedResponse(1050, "枪头id不能为空").toString();
		} else {
			params.put("bespElectricpilehead", bespElectricpilehead);
		}
		if (StringUtils.isBlank(uId)) {
			return new FailedResponse(1050, "用户id不能为空").toString();
		} else {
			params.put("uId", uId);
		}
		if (StringUtils.isBlank(did)) {
			return new FailedResponse(1050, "设备码不能为空").toString();
		} else {
			params.put("did", did);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_CANCEL_BESPOKE, params);
	}

	/**
	 * @Description：获取设备保修类型
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/paraconfig")
	@ResponseBody
	public String findConfigContentList(HttpServletRequest request) {
		String cpId = request.getParameter("cpId");
		Map params = new HashMap();
		if (StringUtils.isBlank(cpId)) {
			return new FailedResponse(1050, "配置类型不能为空").toString();
		} else {
			params.put("cpId", cpId);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_FIND_CONFIGCONTENT,
				params);
	}

	/**
	 * @Description：添加设备保修
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/addRepair")
	@ResponseBody
	public String addTblEquipmentrepair(HttpServletRequest request) {
		String eqreUserid = request.getParameter("eqreUserid");
		String eqreWarrantytypeid = request.getParameter("eqreWarrantytypeid");
		String epId = request.getParameter("epId");
		String deviceType = request.getParameter("deviceType");
		String eqreContent = request.getParameter("eqreContent");
		Map params = new HashMap();
		if (StringUtils.isBlank(eqreUserid)) {
			return new FailedResponse(1050, "用户ID不能为空").toString();
		} else {
			params.put("eqreUserid", eqreUserid);
		}
		if (StringUtils.isBlank(eqreWarrantytypeid)) {
			return new FailedResponse(1050, "设备报修类型不能为空").toString();
		} else {
			params.put("eqreWarrantytypeid", eqreWarrantytypeid);
		}
		if (StringUtils.isBlank(epId)) {
			return new FailedResponse(1050, "电桩、电站id不能为空").toString();
		} else {
			params.put("epId", epId);
		}
		if (StringUtils.isBlank(deviceType)) {
			return new FailedResponse(1050, "设备类型不能为空").toString();
		} else {
			params.put("deviceType", deviceType);
		}
		params.put("eqreContent",
				StringUtils.isNotBlank(eqreContent) ? eqreContent : null);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ADD_repair, params);
	}

	/**
	 * @Description：电站评价列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/psComment")
	@ResponseBody
	public String findPsComments(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");// 每页多少条
		String pageNumber = request.getParameter("pageNumber");// 当前第几页
		String prCoProductId = request.getParameter("prCoProductId");// 电站 ID
		Map params = new HashMap();
		if (StringUtils.isBlank(pageNum)) {
			return new FailedResponse(1050, "每页的条数不能为空").toString();
		} else {
			params.put("pageNum", pageNum);
		}
		if (StringUtils.isBlank(pageNumber)) {
			return new FailedResponse(1050, "当前的页数不能为空").toString();
		} else {
			params.put("pageNumber", pageNumber);
		}
		if (StringUtils.isBlank(prCoProductId)) {
			return new FailedResponse(1050, "电站ID不能为空").toString();
		} else {
			params.put("prCoProductId", prCoProductId);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_POWERSTATION_COMMENTS,
				params);
	}

	/**
	 * @Description：电桩评价列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/epComment")
	@ResponseBody
	public String findEpComments(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");// 每页多少条
		String pageNumber = request.getParameter("pageNumber");// 当前第几页
		String prCoProductId = request.getParameter("prCoProductId");// 电站 ID

		Map params = new HashMap();
		if (StringUtils.isBlank(pageNum)) {
			return new FailedResponse(1050, "每页的条数不能为空").toString();
		} else {
			params.put("pageNum", pageNum);
		}
		if (StringUtils.isBlank(pageNumber)) {
			return new FailedResponse(1050, "当前的页数不能为空").toString();
		} else {
			params.put("pageNumber", pageNumber);
		}
		if (StringUtils.isBlank(prCoProductId)) {
			return new FailedResponse(1050, "电站ID不能为空").toString();
		} else {
			params.put("prCoProductId", prCoProductId);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_ELECTRICPILE_COMMENTS,
				params);
	}

	/**
	 * @Description：电桩评价添加
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/addEpComment")
	@ResponseBody
	public String insertEpCommnet(HttpServletRequest request) {
		String epId = request.getParameter("epId");
		String uId = request.getParameter("uId");
		String pcId = request.getParameter("pcId");
		String uName = request.getParameter("uName");
		String epContent = request.getParameter("epContent");
		String type = request.getParameter("type");
		Map params = new HashMap();
		if (StringUtils.isBlank(epId)) {
			return new FailedResponse(1050, "电桩ID不能为空").toString();
		} else {
			params.put("epId", epId);
		}
		if (StringUtils.isBlank(uId)) {
			return new FailedResponse(1050, "用户ID不能为空").toString();
		} else {
			params.put("uId", uId);
		}
		if (StringUtils.isBlank(pcId)) {
			return new FailedResponse(1050, "评论ID不能为空").toString();
		} else {
			params.put("pcId", pcId);
		}
		if (StringUtils.isBlank(uName)) {
			return new FailedResponse(1050, "用户名称不能为空").toString();
		} else {
			params.put("uName", uName);
		}
		if (StringUtils.isBlank(epContent)) {
			return new FailedResponse(1050, "评论内容不能为空").toString();
		} else {
			params.put("epContent", epContent);
		}
		if (StringUtils.isBlank(type)) {
			return new FailedResponse(1050, "评论类型不能为空").toString();
		} else {
			params.put("type", type);
		}
		// 调用API并将结果返回
		return HttpUtil
				.doPost(WanmaConstants.API_URL_INSERT_EPCOMMENTS, params);
	}

	/**
	 * @Description：电桩星评添加
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/epStar")
	@ResponseBody
	public String insertEpStar(HttpServletRequest request) {
		String epId = request.getParameter("epId");
		String uId = request.getParameter("uId");
		String uName = request.getParameter("uName");
		String epStar = request.getParameter("epStar");
		Map params = new HashMap();
		if (StringUtils.isBlank(epId)) {
			return new FailedResponse(1050, "电桩ID不能为空").toString();
		} else {
			params.put("epId", epId);
		}
		if (StringUtils.isBlank(uId)) {
			return new FailedResponse(1050, "用户ID不能为空").toString();
		} else {
			params.put("uId", uId);
		}
		params.put("uName", StringUtils.isNotBlank(uName) ? uName : null);
		if (StringUtils.isBlank(epStar)) {
			return new FailedResponse(1050, "星评不能为空").toString();
		} else {
			params.put("epStar", epStar);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_INSERT_EPSTAR, params);
	}

	/**
	 * @Description：扫描二维码
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/selectPileInfo")
	@ResponseBody
	public String selectPileInfo(HttpServletRequest request) {
		String elpiElectricpilecode = request
				.getParameter("elpiElectricpilecode");
		String ePHeElectricpileHeadId = request
				.getParameter("ePHeElectricpileHeadId");
		Map params = new HashMap();
		if (StringUtils.isBlank(elpiElectricpilecode)) {
			return new FailedResponse(1050, "桩体编号不能为空").toString();
		} else {
			params.put("elpiElectricpilecode", elpiElectricpilecode);
		}
		if (StringUtils.isBlank(ePHeElectricpileHeadId)) {
			return new FailedResponse(1050, "枪头编号不能为空").toString();
		} else {
			params.put("ePHeElectricpileHeadId", ePHeElectricpileHeadId);
		}
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_SELECT_PILEINFO, params);
	}

	/**
	 * @Description：充电订单列表/详情
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/chargeOrderList")
	@ResponseBody
	public String chargeOrderList(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String coId = request.getParameter("coId");
		Map params = new HashMap();
		if (StringUtils.isBlank(userId)) {
			return new FailedResponse(1050, "用户ID不能为空").toString();
		} else {
			params.put("userId", userId);
		}
		params.put("coId", StringUtils.isNotBlank(coId) ? coId : null);
		// 调用API并将结果返回
		return HttpUtil.doPost(WanmaConstants.API_URL_CHARGE_ORDERLIST, params);

	}

	/**
	 * @Description：LED开关
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/ledControl")
	@ResponseBody
	public String ledControl(HttpServletRequest request) {
		String epCode = request.getParameter("epCode");// 电桩编号
		String type = request.getParameter("type");// 1开，2关
		String remainTime = request.getParameter("remainTime");// 持续闪烁时间，分钟为单位
		String uid = request.getParameter("uid");// 用户id
		String lat = request.getParameter("lat");// 维度(手机)
		String lng = request.getParameter("lng");// 经度
		String eplat = request.getParameter("eplat");// 维度(桩)
		String eplng = request.getParameter("eplng");// 经度
		// 准备参数params
		Map params = new HashMap();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if (StringUtils.isBlank(epCode) || StringUtils.isBlank(type)
				|| StringUtils.isBlank(uid) || StringUtils.isBlank(lat)
				|| StringUtils.isBlank(lng) || StringUtils.isBlank(eplat)
				|| StringUtils.isBlank(eplng)) {
			return new FailedResponse(1050, "输入参数不全！").toString();
		}
		params.put("epCode", epCode);
		params.put("type", type);
		params.put("remainTime", remainTime);
		params.put("uid", uid);
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("eplat", eplat);
		params.put("eplng", eplng);
		return HttpUtil.doPost(WanmaConstants.API_URL_LED_CONTROL, params);
	}

	/**
	 * @Description：降地锁
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/downParkLock")
	@ResponseBody
	public String downParkLock(HttpServletRequest request) {
		String epCode = request.getParameter("epCode");// 电桩编号
		String headNum = request.getParameter("headNum");// 枪口号，不是枪口id
		String parkNum = request.getParameter("parkNum");// 枪口对应的车位号
		String uid = request.getParameter("uid");// 用户id
		String lat = request.getParameter("lat");// 维度(手机)
		String lng = request.getParameter("lng");// 经度
		String eplat = request.getParameter("eplat");// 维度(桩)
		String eplng = request.getParameter("eplng");// 经度
		// 准备参数params
		Map params = new HashMap();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if (StringUtils.isBlank(epCode) || StringUtils.isBlank(headNum)
				|| StringUtils.isBlank(parkNum) || StringUtils.isBlank(uid)
				|| StringUtils.isBlank(lat) || StringUtils.isBlank(lng)
				|| StringUtils.isBlank(eplat) || StringUtils.isBlank(eplng)) {
			return new FailedResponse(1050, "输入参数不全！").toString();
		}
		params.put("epCode", epCode);
		params.put("headNum", headNum);
		params.put("parkNum", parkNum);
		params.put("uid", uid);
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("eplat", eplat);
		params.put("eplng", eplng);
		return HttpUtil.doPost(WanmaConstants.API_URL_DOWN_PARKLOCK, params);
	}

	/**
	 * 获取支付宝sigin值
	 * 
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/aliSign")
	@ResponseBody
	public String aliSign(HttpServletRequest request) {
		String subject = request.getParameter("subject");// 标题
		String body = request.getParameter("body");// 内容
		String price = request.getParameter("price");// 订单价格
		String userMobel = request.getParameter("userMobel");// 用户手机号
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if (StringUtils.isBlank(subject) || StringUtils.isBlank(body)
				|| StringUtils.isBlank(price) || StringUtils.isBlank(userMobel)) {
			return new FailedResponse(1050, "输入参数不全！").toString();
		}
		params.put("subject", subject);
		params.put("body", body);
		params.put("price", price);
		params.put("userMobel", userMobel);
		return HttpUtil.doPost(WanmaConstants.API_URL_ALI_SIGN, params);
	}

	/**
	 * 微信预支付
	 * 
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/wxTempOrder")
	@ResponseBody
	public String wxTempOrder(HttpServletRequest request) {
		String userId = request.getParameter("userId");// 用户id
		String body = request.getParameter("body");// 内容
		String price = request.getParameter("price");// 订单价格
		String ipAddr = request.getParameter("ipAddr");// 手机ip
		String tradeType = request.getParameter("tradeType");// 请求类型（APP或WAP）
		String userMobel = request.getParameter("userMobel");// 用户手机号
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(body)
				|| StringUtils.isBlank(price) || StringUtils.isBlank(ipAddr)
				|| StringUtils.isBlank(tradeType)
				|| StringUtils.isBlank(userMobel)) {
			return new FailedResponse(1050, "输入参数不全！").toString();
		}
		params.put("userId", userId);
		params.put("body", body);
		params.put("price", price);
		params.put("ipAddr", ipAddr);
		params.put("tradeType", tradeType);
		params.put("userMobel", userMobel);
		String s = HttpUtil.doPost(WanmaConstants.API_URL_WX_TEMPORDER, params);
		System.out.println(s);
		Map<String, String> map = (Map<String, String>) JSON.parse(s);
		Object xmlMap = null;
		xmlMap = xmltoMap(map.get("data"));
		System.out.println(xmlMap);
		return new AccessSuccessResult(xmlMap).toString();
	}

	public static Map xmltoMap(String xml) {
		try {
			Map map = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();
			for (Iterator it = node.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				map.put(elm.getName(), elm.getText());
				elm = null;
			}
			node = null;
			nodeElement = null;
			document = null;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
