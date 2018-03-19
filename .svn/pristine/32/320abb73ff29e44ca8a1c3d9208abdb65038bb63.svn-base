package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.model.TblThrWarning;
import com.wanma.service.CmsWarningService;

@Controller
@RequestMapping("/admin/warning")
public class TblWarningController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsActivityController.class);

	@Autowired
	private CmsWarningService cmsWarningService;

	/**
	 * 加载渠道活动列表
	 * 
	 * @param pager
	 * @param tblActivity
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listWarning")
	public String listActivity(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblThrWarning tblWarning, Model model,
			HttpServletRequest request) {

		try {
			long total = cmsWarningService.getWarningCount(tblWarning);

			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblWarning.setPager(pager);
			List<TblThrWarning> warningList = cmsWarningService
					.getWarningList(tblWarning);
			pager.setTotal(total);
			model.addAttribute("warningList", warningList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblWarning", tblWarning);

		} catch (Exception e) {
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/configure/warning/listWarning";
	}

	@RequestMapping(value = "/addWarning")
	public String addWarning(Model model, HttpServletRequest request) {
		// 获取优惠券品种
		List<TblThrWarning> companyType = null;

		companyType = cmsWarningService.getCompanyType();
		model.addAttribute("companyType", companyType);

		return "backstage/configure/warning/addWarning";
	}

	@RequestMapping(value = "/saveWarning")
	@ResponseBody
	public String saveWarning(TblThrWarning tblWarning, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			
			//long count=cmsWarningService.getAccountNumber(tblWarning);

			
			/*if(count<1){
				dwzResult = new DwzAjaxResult("300",
						"该企业下不存在账户,请先新增账户!", "", "", "");
				
			}
			else*/ 
			if (Integer.valueOf(tblWarning.getThwThreshold()).intValue() < 100
					|| Integer.valueOf(tblWarning.getThwThreshold()).intValue() > 1000000) {

				dwzResult = new DwzAjaxResult("300",
						"预警金额在100至100万之间,请检查预警金额是否满足要求！", "", "", "");

			} else if (!"1".equals(tblWarning.getThwCellphone().substring(0, 1))
					|| tblWarning.getThwCellphone().length() != 11) {

				dwzResult = new DwzAjaxResult("300", "请检查运营手机号是否正确！", "", "", "");

			} else if (!"".equals(tblWarning.getThwCustomerPhone())&&(!"1".equals(tblWarning.getThwCustomerPhone().substring(0, 1))
					|| tblWarning.getThwCustomerPhone().length() != 11)) {

				dwzResult = new DwzAjaxResult("300", "请检查公司手机号是否正确！", "", "", "");

			} 
			else {
				
				long count=cmsWarningService.getWarningCout(tblWarning);
				if(count>0){
					dwzResult = new DwzAjaxResult("300", "该企业已存在数据,请勿重复新增！", "listWarning",
							"closeCurrent", "");
				}
				else{
				cmsWarningService.insertWarning(tblWarning);

				dwzResult = new DwzAjaxResult("200", "新增成功", "listWarning",
						"closeCurrent", "");
				}
			}

		} catch (Exception e) {
			// 出错日志
			log.error(e.toString() + e.getStackTrace()[0]);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "listWarning",
					"closeCurrent", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	@RequestMapping("/editWarning")
	public String editWarning(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblThrWarning TblWarning, Model model, HttpServletRequest request) {
		
		model.addAttribute("TblWarning", TblWarning);
		// 跳转至广告新增页面
		return "backstage/configure/warning/editWarning";
	}
	
	@RequestMapping(value = "/modifyWarning")
	@ResponseBody
	public String modifyWarning(TblThrWarning tblWarning, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			

			 if (Integer.valueOf(tblWarning.getThwThreshold()).intValue() < 100
					|| Integer.valueOf(tblWarning.getThwThreshold()).intValue() > 1000000) {

				dwzResult = new DwzAjaxResult("300",
						"预警金额在100至100万之间,请检查预警金额是否满足要求！", "", "", "");

			} else if (!"1".equals(tblWarning.getThwCellphone().substring(0, 1))
					|| tblWarning.getThwCellphone().length() != 11) {

				dwzResult = new DwzAjaxResult("300", "请检查运营手机号是否正确！", "", "", "");

			 }else if (!"".equals(tblWarning.getThwCustomerPhone())&&(!"1".equals(tblWarning.getThwCustomerPhone().substring(0, 1))
					|| tblWarning.getThwCustomerPhone().length() != 11)) {

				dwzResult = new DwzAjaxResult("300", "请检查公司手机号是否正确！", "", "", "");

			} 
			
			else {
				
				cmsWarningService.updateWarning(tblWarning);

				dwzResult = new DwzAjaxResult("200", "修改成功", "listWarning",
						"closeCurrent", "");
				
			}

		} catch (Exception e) {
			// 出错日志
			log.error(e.toString() + e.getStackTrace()[0]);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "listWarning",
					"closeCurrent", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	@RequestMapping(value = "/removeWarning")
	@ResponseBody
	public String removeWarning(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = new DwzAjaxResult("200", "删除成功", "listWarning", "", "");
		try {
			String[] idArray = ids.split(",");

			// 不出错执行删除操作
			for (String id : idArray) {
				cmsWarningService.updateWarningFlag(id);

			}
			return dwzResult.toJSONString();
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "操作失败:系统出错", "listWarning", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
