package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.impl.AppCarGarageServiceImpl;

@Controller
@RequestMapping("/app/carGarage")
public class AppCarGarageController {
	private static Log log = LogFactory.getLog(AppCarGarageController.class);
	
	/**
	 * 获取车辆维修列表
	 * 		latitude 维度，longitude 经度，kw 查询关键字，type 查询类型 1名称 2地址，currentPage 当前页码从1开始，pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String getCarGarageList(@RequestParam Map<String, Object> params,AppPager pager){
		if(StringUtils.isEmpty(params.get("latitude")) || StringUtils.isEmpty(params.get("longitude"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtils.isEmpty(params.get("kw"))){
			//如果不是搜索就必须传入分页参数
			if(StringUtils.isEmpty(pager.getPageNum()) || StringUtils.isEmpty(pager.getPageNumber())){
				return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
			}
			//重新设置分页的其实记录数
			params.put("pagerNumber", pager.getPageNumber());
			params.put("pagerNum", pager.getPageNum());
		}
		try{
			list = appCarGarageService.getCarGarageList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(list).toString();
	}
	
	@Autowired
	AppCarGarageServiceImpl appCarGarageService;
}
