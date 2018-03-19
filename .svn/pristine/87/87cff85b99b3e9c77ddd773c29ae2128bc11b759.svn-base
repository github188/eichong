package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblCompany;
import com.wanma.model.TblUser;
import com.wanma.service.CompanyManagerService;

/**
 * 公司管理
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-6-24 下午06:59:30
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
@Controller
@RequestMapping("/admin/companyFlag/")
public class CmsCompanyFlagController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(CmsCompanyFlagController.class);
	@Autowired
    private CompanyManagerService companyManagerService;
	
	/**
	 * 获取公司标识列表数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/getCompanyFlagList")
	public String getCompanyFlagList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblCompany tblCompany, Model model,HttpServletRequest request) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		tblCompany.setUserId(loginUser.getUserId());
		tblCompany.setUserLevel(loginUser.getUserLevel());
		long total=companyManagerService.findCompanyFlagListCount(tblCompany);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		tblCompany.setPager(pager);
		List<TblCompany> companyList = companyManagerService.findCompanyFlagList(tblCompany);
		pager.setTotal(total);
		model.addAttribute("pager", pager);
		model.addAttribute("tblCompany", tblCompany);
		model.addAttribute("companyList", companyList);
		return "backstage/companyFlag/companyFlag-list";
	}
	/**
	 * 公司修改界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/changeCompanyFlagUI")
	public String changeCompanyFlagUI(Model model,@RequestParam(value = "companyId") String pkCompanyid) {
		
		TblCompany tblCompany=companyManagerService.getCompanyById(JudgeNullUtils.nvlInteger(pkCompanyid));
		
		model.addAttribute("tblCompany", tblCompany);
		return "backstage/companyFlag/companyFlag-edit";
	}
	
	/**
	 * 添加公司标识
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/changeCompanyFlag")
    @ResponseBody
	public String changeCompanyFlag(@ModelAttribute TblCompany tblCompany,Model model) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if(tblCompany.getCpyPostcode()==null){
				tblCompany.setCpyPostcode(0);
			}
			companyManagerService.modifyCompanyFlag(tblCompany);
			dwzResult = new DwzAjaxResult("200", "修改成功", "getCompanyFlagList",
					"closeCurrent", "");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改失败", e);
			dwzResult = new DwzAjaxResult("300", "修改失败", "changeCompanyFlagUI", "",
			"");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 删除公司标识
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/removeCompanyFlag")
	@ResponseBody
	public String removeConcentrator(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if (companyManagerService.isFlagBeUsed(ids)){
				dwzResult = new DwzAjaxResult("300", "存在已被使用的公司标识，不可删除",
						"getCompanyFlagList", "", "");
			}else{
				companyManagerService.deleteFlag(ids);
				dwzResult = new DwzAjaxResult("200", "删除成功",
						"getCompanyFlagList", "", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "getConcentratorList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 标识唯一性检查
	 * 
	 */
	@RequestMapping(value = "/checkCpyCompanyNumber")
	@ResponseBody
	public String checkCpyCompanyNumber(TblCompany tblCompany) {
		// 返回处理结果信息
		return String.valueOf(companyManagerService.checkCpyCompanyNumber(tblCompany));
	}

	/**
	 * 是否被使用
	 * 
	 */
	@RequestMapping(value = "/isFlagBeUsed")
	@ResponseBody
	public String isFlagBeUsed(TblCompany tblCompany) {
		// 返回处理结果信息
		return String.valueOf(companyManagerService.isFlagBeUsed(tblCompany.getPkCompanyid().toString()));
	}
}
