package com.wanma.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.JacksonJsonMapper;
import com.wanma.common.ChineseUtill;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsCarTypeService;

@Controller
@RequestMapping("admin/cartype")
public class CmsCarTypeController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsCarTypeController.class);
	
	@Autowired
	private CmsCarTypeService cartypeService;
	
	/**
	 * 获取车型列表
	 * 
	 * @author xi
	 * @return Object
	 * @throws 无
	*/
	@RequestMapping("/getCarType")
	@ResponseBody
	public Object getCarType(@ModelAttribute TblCarinfo tblCarinfo) {
		log.info("获取车型列表");	
		// 返回数据
		List<String[]> cars = new ArrayList<String[]>();
		// 获取地区列表
		List<TblCarinfo> carList = cartypeService.searchCarList(tblCarinfo);
		cars.add(new String[]{"", "不限"});
		if (carList != null && carList.size() > 0) {
			for (TblCarinfo Carinfo : carList) {
				cars.add(new String[]{Carinfo.getPkCarinfo()+"", Carinfo.getCarinfoStylename()});
			}
		}

		return JSONObject.toJSONString(cars);
	}
	
	/**
	 * 获取车型名称列表
	 * 
	 * @author xiay
	 * @return Object
	 * @throws 无
	 */
	/**
	 * @param tblCarinfo
	 * @return
	 */
	@RequestMapping("/getCarTypeName")
	@ResponseBody
	public Object getCarTypeName(@ModelAttribute TblCarinfo tblCarinfo,String carInfoCompanyId){
		try {
			//String carinfoBrandname = ChineseUtill.toChinese(tblCarinfo.getCarinfoBrandname());
			tblCarinfo.setCarinfoCompanyId(JudgeNullUtils.nvlInteger(carInfoCompanyId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TblCarinfo> carList = cartypeService.searchCarList(tblCarinfo);
		return JSONObject.toJSONString(carList);
	}
}
