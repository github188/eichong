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

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsCarCompanyService;
import com.wanma.service.CmsCarinfoService;

/**
 * @Description: 电动车厂家详情控制层
 * @createTime：2015-4-1 上午11:55:05
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/carCompany")
public class CmsCarCompanyController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsCarCompanyController.class);
	/* 电动车厂家详情处理对象 */
	@Autowired
	private CmsCarCompanyService carCompanyService;
	// 配置参数内容处理对象
	@Autowired
	private CmsCarinfoService carinfoService;

	/**
	 * @Title: findCarCompanyList
	 * @Description: 获取电动车厂家列表
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/findCarCompanyList")
	public String findCarCompanyList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			Map<String, Object> params, Model model, HttpServletRequest request) {
		log.info("******************获取电动车厂家详情列表-begin************************");
		if (!checkOprateValid(request)) {
			return WanmaConstants.ERROR_PAGE;
		}
		List<HashMap<String, Object>> carCompanyList = new ArrayList<HashMap<String, Object>>();
		String carCompany_Name = request.getParameter("carCompany_Name");
		params.put("carCompany_Name", carCompany_Name);
		params.put("offset", pager.getOffset());
		params.put("numPerPage", pager.getNumPerPage());
		long total = carCompanyService.findCarCompanyCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
			params.put("offset", 0);
		}
		carCompanyList = carCompanyService.findCarCompanyList(params);
		pager.setTotal(total);
		// 将查询结果显示到画面
		model.addAttribute("carCompanyList", carCompanyList);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		log.info("******************获取电动车厂家列表-end************************");
		// 跳转至车型列表页面
		return "backstage/carCompany/carCompany-list";
	}

	/**
	 * @Title: newCarCompany
	 * @Description: 跳转至新增电动车厂家页面
	 * @param CarCompany
	 * @return
	 */
	@RequestMapping("/newCarCompany")
	public String newCarCompany(Model model) {
		log.info("******************跳转至新增电动车厂家页面************************");
		return "backstage/carCompany/carCompany-add";

	}

	/**
	 * @Title: insertCarCompany
	 * @Description: 新增电动车厂家页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertCarCompany")
	@ResponseBody
	public String insertCarCompany(Map<String, Object> params,
			HttpServletRequest req) {
		log.info("******************新增制造厂商-begin************************");
		// 处理结果信息
		String carCompany_Name = req.getParameter("carCompany_Name");
		String carCompany_Remark = req.getParameter("carCompany_Remark");
		params.put("carCompany_Name", carCompany_Name);
		DwzAjaxResult dwzResult = null;
		HashMap<String, Object> carCompany = new HashMap<String, Object>();
		carCompany = carCompanyService.getByProperty(params);
		params.put("carCompany_Remark", carCompany_Remark);
		try {
			if (carCompany == null || carCompany.isEmpty()) {
				// 新增电动车厂家
				carCompanyService.insertCarCompany(params);
				dwzResult = new DwzAjaxResult("200", "新增成功",
						"findCarCompanyList", "closeCurrent", "");
			} else {
				dwzResult = new DwzAjaxResult("300", "新增失败,名称重复",
						"findCarCompanyList", "", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "findCarCompanyList",
					"", "");
		}
		log.info("******************新增制造厂商-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除电动车厂家
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(Map<String, Object> params,
			@RequestParam("ids") String ids) {
		log.info("******************删除电动车厂家-begin************************");
		DwzAjaxResult dwzResult = null;
		String[] approvalArray = ids.split(",");
		String result=checkIds(approvalArray);
		if(StringUtils.isNotBlank(result)){
			return  new DwzAjaxResult("300", "存在已绑定车型的厂家："+result,
					"findCarCompanyList", "", "").toJSONString();
		}	
		for (int i = 0; i < approvalArray.length; i++) {
			int pkCarCompany = Integer.parseInt(approvalArray[i]);
				try {
					params.put("pk_carCompany", pkCarCompany);
					// 删除其他配置参数中配置名称
					carCompanyService.deleteCarCompanyById(params);
				} catch (Exception e) {
					// 出错日志
					log.info(e.getLocalizedMessage());
					// 设置错误信息
					dwzResult = new DwzAjaxResult("300", "删除失败",
							"findCarCompanyList", "", "");
				} 

		}
		dwzResult = new DwzAjaxResult("200", "删除成功",
				"findCarCompanyList", "", "");
		log.info("******************删除电动车厂家-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 校验是否可删除
	 * @param approvalArray
	 * @return
	 */
	private String checkIds(String[] approvalArray) {
		String errorCode="";
		for (int i = 0; i < approvalArray.length; i++) {
			int pkCarCompany = Integer.parseInt(approvalArray[i]);
			TblCarinfo tblCarinfo = new TblCarinfo();
			tblCarinfo.setCarinfoCompanyId(pkCarCompany);
			List<TblCarinfo> tblCarinfoList = carinfoService
					.findCarList(tblCarinfo);
			if (!(tblCarinfoList == null || tblCarinfoList.isEmpty()))
				errorCode+=pkCarCompany+",";
		}
		return StringUtils.removeEnd(errorCode, ",");
	}

	/**
	 * @Title: editCarCompany
	 * @Description: 跳转至更新电动车厂家页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editCarCompany")
	public String editCarCompany(Map<String, Object> params, Model model,
			String pk_carCompany) {
		log.info("******************跳转至更新电动车厂家页面-begin************************");
		params.put("pk_carCompany", pk_carCompany);
		params = carCompanyService.findCarCompanyById(params);
		// 将查询结果显示到画面
		model.addAttribute("params", params);
		log.info("******************跳转至更新电动车厂家页面-end************************");
		return "backstage/carCompany/carCompany-edit";
	}

	/**
	 * @Title: updateCarCompany
	 * @Description: 更新电动车厂家
	 * @param CarCompany
	 * @return
	 */
	@RequestMapping("/updateCarCompany")
	@ResponseBody
	public String updateCarCompany(Map<String, Object> params,
			HttpServletRequest req) {
		log.info("******************更新电动车厂家-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		String pk_carCompany = req.getParameter("pk_carCompany");
		String carCompany_Name = req.getParameter("carCompany_Name");
		String carCompany_Remark = req.getParameter("carCompany_Remark");

		params.put("carCompany_Name", carCompany_Name);
		params.put("pk_carCompany", pk_carCompany);
		HashMap<String, Object> carCompany = new HashMap<String, Object>();
		carCompany = carCompanyService.getByProperty(params);
		params.put("carCompany_Remark", carCompany_Remark);
		if (carCompany == null || carCompany.isEmpty()) {
			try {
				carCompanyService.updateCarCompany(params);
				dwzResult = new DwzAjaxResult("200", "编辑成功",
						"findCarCompanyList", "closeCurrent", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "编辑失败",
						"findCarCompanyList", "", "");
			}

		} else {
			dwzResult = new DwzAjaxResult("300", "编辑失败,厂家品牌已存在",
					"findCarCompanyList", "", "");
		}

		log.info("******************更新电动车厂家-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
