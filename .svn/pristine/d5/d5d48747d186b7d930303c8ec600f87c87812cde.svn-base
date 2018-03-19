package com.wanma.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.ElectricPileList;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblUser;
import com.wanma.web.service.WebElectricPileListService;
import com.wanma.web.service.WebElectricPileMapService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.utils.JsonUtil;

/**
 * 电桩查找(列表模式)
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-13 下午02:58:19
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/electricPileList")
public class WebElectricPileListController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(WebElectricPileListController.class);

	/** 反馈业务处理对象 */
	@Autowired
	private WebElectricPileListService electricPileListService;
	@Autowired
	private WebElectricPileMapService electricPileMapService;

	/**
	 * 获取电桩查找列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/getElectricPileList")
	@ResponseBody
	public String getElectricPileList(@RequestParam Map<String, Object> params,HttpServletRequest request){
		log.info("******************获取电桩查找列表模式数据-begin************************");
		Integer pageNum = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
        	pageSize = Integer.valueOf((String) params.get("pageSize"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	pageNum = Integer.valueOf((String) params.get("pageNum"));
        }
        PageResponse<List<ElectricPileList>> pager = new PageResponse<List<ElectricPileList>>(pageNum, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		String suitable=(String) params.get("suitable");
		if(suitable!=null){
			if (!StringUtil.isEmpty(suitable)) {
				TblUser user=SessionMgr.getWebUser(request);
				TblElectricpile pile=electricPileMapService.getPileConditionByUserId(user.getUserId());
				if(pile!=null){
					Integer chargingmode=pile.getElpiChargingmode();
					if(chargingmode!=null){
						if(chargingmode==1){
							params.put("chargingMode", null);
						}else{
							params.put("chargingMode",chargingmode);
						}
					}
					Integer elpiPowerinterface=pile.getElpiPowerinterface();
					if(elpiPowerinterface!=null){
						params.put("powerInterface",elpiPowerinterface);
					}
				}else{
					String s = "您还未填写车牌及车型，请到个人中心--我的资料中修改--needSelectValue"; 
					return JsonUtil.object2json(s);
				}
				
			}	
		}
		try {
			List<ElectricPileList> electricPileList = electricPileListService.getElectricPileForList(params);
            pager.setCountData(electricPileListService.countElectricPileForList(params));
            pager.setDate(electricPileList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
//		RedisClient redisClient=new RedisClient();
//    	redisClient.addListClickCount();
		// 返回处理成功信息
		return pager.toString();
	}
	
	/**
	 * 获取电桩查找列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 
	@RequestMapping("/getElectricPileList")
	@ResponseBody
	public String getElectricPileList(@RequestParam Map<String, Object> params){
		log.info("******************获取电桩查找列表模式数据-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	pageSize = Integer.valueOf((String) params.get("pageNum"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNumber"))) {
        	begin = Integer.valueOf((String) params.get("pageNumber"));
        }
        PageResponse<List<ElectricPileList>> pager = new PageResponse<List<ElectricPileList>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			List<ElectricPileList> electricPileList = electricPileListService.getElectricPileList(params);
            pager.setCountData(electricPileListService.countPowerstation(params) + electricPileListService.countElectricPile(params));
            pager.setDate(electricPileList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return pager.toString();
	}

	/**
	 * 快速检索
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/getSearchElectricList")
	@ResponseBody
	public String getSearchingElectricList(@RequestParam Map<String, Object> params){
		log.info("******************获取电桩查找列表模式数据-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	pageSize = Integer.valueOf((String) params.get("pageNum"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNumber"))) {
        	begin = Integer.valueOf((String) params.get("pageNumber"));
        }
        PageResponse<List<ElectricPileList>> pager = new PageResponse<List<ElectricPileList>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			// 未输入经纬度
			if (StringUtil.isEmpty((String) params.get("longitude")) || StringUtil.isEmpty((String) params.get("latitude"))) {
				// 返回未输入经纬度错误信息
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			List<ElectricPileList> electricPileList = electricPileListService.getElectricPileList(params);
            pager.setCountData(electricPileListService.countPowerstation(params));
            pager.setDate(electricPileList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩列表失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return pager.toString();
	}

	/**
	 * 获取相关电站列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/getRelatedList")
	@ResponseBody
	public String getRelatedList(@RequestParam Map<String, Object> params){
		log.info("******************获取相关电站列表模式数据-begin************************");
		List<Map<String, Object>> relatedList = new ArrayList<Map<String, Object>>();
		try {
			// 未输入经纬度
			if (StringUtil.isEmpty((String) params.get("longitude")) || StringUtil.isEmpty((String) params.get("latitude"))) {
				// 返回未输入经纬度错误信息
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			relatedList = electricPileListService.getRelatedList(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电站列表失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new ResultResponse<List<Map<String, Object>>>(relatedList).toString();
	}

}
