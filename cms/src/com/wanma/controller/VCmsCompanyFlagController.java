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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.SessionMgr;
import com.base.util.JudgeNullUtils;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.TblCompany;
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
public class VCmsCompanyFlagController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(VCmsCompanyFlagController.class);
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
			@ModelAttribute("pager") Pager pager,
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
		BaseResult baseResult = new BaseSuccess();
		try {
			if(tblCompany.getCpyPostcode()==null){
				tblCompany.setCpyPostcode(0);
			}
			companyManagerService.modifyCompanyFlag(tblCompany);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyPassword() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		
		// 返回处理结果信息
		return baseResult.toString();
	}
	
	/**
	 * 删除公司标识
	 * 
	 * @param request
	 * @return
	 * @throws 
	 */
	@RequestMapping(value = "/removeCompanyFlag")
	@ResponseBody
	public String removeCompanyFlag(@RequestParam("ids") String ids) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			if (companyManagerService.isFlagBeUsed(ids)){
				baseResult = new BaseFail("存在已被使用的公司标识，不可删除");
			}else{
				companyManagerService.deleteFlag(ids);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".removeCompanyFlag() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
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
