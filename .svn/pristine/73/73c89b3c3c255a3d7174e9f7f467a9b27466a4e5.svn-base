package com.wanma.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.WanmaConstants;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.wanma.model.TblCarcompany;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsCarCompanyService;
import com.wanma.service.CmsCarinfoService;
import com.wanma.service.CmsConfigcontentService;

/**
 * 运营管理-配置管理-车型品牌
 * 
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/carinfo")
public class CmsCarinfoController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsCarinfoController.class);
	/* 电动车车型详情处理对象 */
	@Autowired
	private CmsCarinfoService carinfoService;
	@Autowired
	private CmsCarCompanyService carCompanyService;
	// 配置参数内容处理对象
	@Autowired
	private CmsConfigcontentService configcontentService;


	@RequestMapping(value = "/carinfoPage")
	public String carinfoPage(HttpServletRequest request) {
		return "backstage/carinfo/carinfoList";
	}

	/**
	 * @Title: findCarList
	 * @Description: 获取电动车车型详情列表
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/getCarinfoList")
	@ResponseBody
	public String findCarList(@ModelAttribute("pager") Pager pager,
			TblCarinfo carinfo, Model model, HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		log.info("******************获取电动车车型详情列表-begin************************");
		// 电动车车型总数
		long total = carinfoService.findCount(carinfo);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		carinfo.setPager(pager);
		carinfo.setCarInfocarstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		List<TblCarinfo> carList = carinfoService.findCarList(carinfo);
		for (TblCarinfo entity : carList) {
			entity.setExtValue2(WanmaConstants.getConfigName("16", entity
					.getCarinfoBatteryType().toString()));
			entity.setExtValue3(WanmaConstants.getConfigName("17", entity
					.getCarinfoChargingMode().toString()));
			entity.setExtValue4(WanmaConstants.getConfigName("5", entity
					.getCarinfoPowerInterface().toString()));
		}
		pager.setTotal(total);
		baseResult = new BaseResult(carList, pager);
		log.info("******************获取电动车车型详情列表-end************************");
		// 跳转至车型列表页面
		return baseResult.toString();
	}

	/**
	 * @Title: insertCarinfo
	 * @Description: 新增电动车车型页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/carinfoSave")
	@ResponseBody
	public String carinfoSave(TblCarinfo carinfo) {
		log.info("******************新增电动车车型-begin************************");
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			// 判断名字是否重复
			HashMap<String, Object> carInfoMap = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("CarInfo_StyleName", carinfo.getCarinfoStylename());
			carInfoMap = carinfoService.getByProperty(params);
			if (carInfoMap == null || carInfoMap.isEmpty()) {
				// 新增电动车车型
				carinfoService.insertCarinfo(carinfo);
			} else {
				baseResult = new BaseFail("新增失败,名称重复");
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".carinfoSave() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("******************新增电动车车型-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 
	 * @param tblCarinfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/carinfoDetail")
	@ResponseBody
	public String carinfoDetail(TblCarinfo tblCarinfo) {
		// 根据id获取电动车车型详情
		TblCarinfo carinfo = carinfoService.findById(tblCarinfo);
		return new BaseResult(carinfo).toString();
	}

	/**
	 * @Title: updateCarinfo
	 * @Description: 更新电动车车型
	 * @param carinfo
	 * @return
	 */
	@RequestMapping("/carinfoModify")
	@ResponseBody
	public String carinfoModify(TblCarinfo carinfo) {
		log.info("******************更新电动车车型-begin************************");
		// 处理结果信息
		BaseResult baseResult = null;

		try {
			// 判断名字是否重复
			HashMap<String, Object> carInfoMap = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("CarInfo_StyleName", carinfo.getCarinfoStylename());
			params.put("pk_CarInfo", carinfo.getPkCarinfo());
			carInfoMap = carinfoService.getByProperty(params);
			if (carInfoMap == null || carInfoMap.isEmpty()) {
				carinfoService.updateCarinfo(carinfo);
				baseResult = new BaseSuccess();
			} else {
				baseResult = new BaseFail("修改失败,名称重复");
				
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".carinfoModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("******************更新电动车车型-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * @Title: deleteCarinfos
	 * @Description: 批量删除电动车车型
	 * @param pkCarinfos
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/carinfoRemove")
	@ResponseBody
	public String carinfoRemove(@RequestParam("ids") String pkCarinfos) {
		log.info("******************批量删除电动车车型-begin************************");
		BaseResult baseResult = new BaseSuccess();
		try {
			BaseResult result = carinfoService.checkDelete(pkCarinfos);
			if (result != null)
				return result.toString();
			// 批量删除电动车车型
			carinfoService.deleteCarinfos(pkCarinfos);
		} catch (Exception e) {
			log.error(this.getClass() + ".carinfoRemove() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		log.info("******************批量删除电动车车型-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 汽车品牌列表
	 * 
	 * @param pager
	 * @param params
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCarcompanyList")
	@ResponseBody
	public String getCarcompanyList(@ModelAttribute("pager") Pager pager,
			TblCarcompany carcompany, Model model, HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		List<TblCarcompany> carCompanyList = new ArrayList<TblCarcompany>();
		long total = carCompanyService.findCarCompanyCount(carcompany);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		carCompanyList = carCompanyService.findCarCompanyList(carcompany);
		pager.setTotal(total);
		baseResult = new BaseResult(carCompanyList, pager);
		return baseResult.toString();
	}

	@RequestMapping("/carcompanySave")
	@ResponseBody
	public String carcompanySave(TblCarcompany carcompany,
			HttpServletRequest req) {
		BaseResult baseResult = new BaseSuccess();
		// 处理结果信息
		TblCarcompany temp = new TblCarcompany();
		temp = carCompanyService.getByProperty(carcompany);
		try {
			if (temp == null) {
				// 新增电动车厂家
				carCompanyService.insertCarCompany(carcompany);
			} else {
				baseResult = new BaseFail("新增失败,名称重复");
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".carcompanySave() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}

	@RequestMapping("/carcompanyDetail")
	@ResponseBody
	public String carcompanyDetail(TblCarcompany params, Model model,
			String pk_carCompany) {
		params = carCompanyService.findCarCompanyById(params);
		return new BaseResult(params).toString();
	}

	@RequestMapping("/carcompanyModify")
	@ResponseBody
	public String carcompanyModify(TblCarcompany params, HttpServletRequest req) {
		BaseResult baseResult = new BaseSuccess();
		carCompanyService.updateCarCompany(params);
		return baseResult.toString();
	}

	@RequestMapping("/carcompanyRemove")
	@ResponseBody
	public String carcompanyRemove(@RequestParam("pkCarcompany") String ids) {
		BaseResult baseResult = new BaseSuccess();
		String[] approvalArray = ids.split(",");
		String result = checkIds(approvalArray);
		if (StringUtils.isNotBlank(result)) {
			return new BaseFail("存在已绑定车型的厂家：" + result).toString();
		}
		TblCarcompany carcompany = null;
		for (int i = 0; i < approvalArray.length; i++) {
			try {
				carcompany = new TblCarcompany();
				carcompany.setPkCarcompany(new Long(approvalArray[i]));
				// 删除其他配置参数中配置名称
				carCompanyService.deleteCarCompanyById(carcompany);
			} catch (Exception e) {
				log.error(this.getClass() + ".carcompanyRemove() error:"
						+ e.getLocalizedMessage());
				baseResult = new BaseFail(5001);
			}

		}
		return baseResult.toString();
	}

	/**
	 * 校验是否可删除
	 * 
	 * @param approvalArray
	 * @return
	 */
	private String checkIds(String[] approvalArray) {
		String errorCode = "";
		for (int i = 0; i < approvalArray.length; i++) {
			int pkCarCompany = Integer.parseInt(approvalArray[i]);
			TblCarinfo tblCarinfo = new TblCarinfo();
			tblCarinfo.setCarinfoCompanyId(pkCarCompany);
			List<TblCarinfo> tblCarinfoList = carinfoService
					.findCarList(tblCarinfo);
			if (!(tblCarinfoList == null || tblCarinfoList.isEmpty()))
				errorCode += pkCarCompany + ",";
		}
		return StringUtils.removeEnd(errorCode, ",");
	}
}
