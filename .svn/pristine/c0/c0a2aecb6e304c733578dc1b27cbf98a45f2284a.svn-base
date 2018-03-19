package com.wanma.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MD5Util;
import com.bluemobi.product.utils.RandomNumer;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblPartner;
import com.wanma.model.TblUser;
import com.wanma.service.TblPartnerService;
import com.wanma.service.impl.RedisService;
@Controller
@RequestMapping("/admin/partner")
public class CmsPartnerController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsPartnerController.class);

	@Autowired
	private TblPartnerService tblPartnerService;
	@Autowired
	private RedisService redisService;
	/**
	 	/**
	 * 第三方接口用户
	 * @author mb
	 * @since Version 1.0
	 * @param pkUserinfo
	 * @throws 无
	 * @return String 画面跳转URI
	 */
	@RequestMapping(value = "/partnerList")
	public String getUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblPartner partner, Model model,
			HttpServletRequest request) {
		// 用户总数
		long total = 0;
		List<TblPartner> partnerList = tblPartnerService.getPartnerList();
		partner.setPager(pager);
		total = tblPartnerService.getPartnerListCount();
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		// 取得用户列表
		pager.setTotal(total);
		model.addAttribute("paper", pager);
		model.addAttribute("partnerList",partnerList);
		return "backstage/partner/listPartner";
	}
	/**
	 * 用户添加初始化处理
	 * 
	 * @author mb
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newPartner")
	public String newUser(@ModelAttribute TblPartner partner,
			Model model) {
		
		// 跳转至用户添加页面
		return "backstage/partner/newPartner";
	}
	/**
	 * 新增第三方接口用户
	 * @author mb
	 * @param user
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 */
	@RequestMapping(value = "/savePartner")
	@ResponseBody
	public String savePartner(@ModelAttribute TblPartner partner,HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			String partnerKey = partner.getPartnerKey();
			//判断key是否已经存在
			if(tblPartnerService.checkPartnerName(partnerKey)!=0){
				 dwzResult = new DwzAjaxResult("300", "新增失败,该用户身份标示已存在", "newPartner", "","");
				 return new JsonObject(dwzResult).toString();
			}
			//判断paymod是否有值
			if(partner.getPaymod()==0){
				 dwzResult = new DwzAjaxResult("300", "新增失败,付费方式不能为空", "newPartner", "","");
				 return new JsonObject(dwzResult).toString();
			}
			//随机产生10位字符串（包括特殊字符）
			String partnerToken = RandomNumer.getUpperMd5Number(partnerKey, 10);
			partner.setPartnerToken(partnerToken);;
			tblPartnerService.addPartner(partner);
			//缓存第三方身份信息
			redisService.strSet("app:org:"+partnerKey, partnerToken+":"+partner.getPaymod());
			dwzResult = new DwzAjaxResult("200", "新增成功",
					"partnerList", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "newPartner", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * @Title: editPartner
	 * @Description: 重新生成密钥
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/resetPartnerKey")
	@ResponseBody
	public String resetPartnerKey(@RequestParam("partnerId") Integer partnerId, Model model) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			TblPartner partner = tblPartnerService.selectPartnerNameById(partnerId);
			String partnerKey =partner.getPartnerKey();
			//随机产生10位字符串（包括特殊字符）
			String partnerToken = RandomNumer.getUpperMd5Number(partnerKey, 10);
			partner.setPartnerToken(partnerToken);;
			partner.setPartnerId(partnerId);
			tblPartnerService.updatePartnerKeyById(partner);
			//缓存第三方身份信息
			redisService.strSet("app:org:"+partnerKey, partnerToken+":"+partner.getPaymod());
			dwzResult = new DwzAjaxResult("200", "重新生成密钥成功", "partnerList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "重新生成密钥失败", "partnerList", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * @Title: editPartner
	 * @Description: 跳转修改用户身份标示页面
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/editPartner")
	public String editPartner(@RequestParam("partnerId") Integer partnerId,Model model) {
		TblPartner partner = tblPartnerService.selectPartnerNameById(partnerId);
		model.addAttribute("partner", partner);
		// 跳转至用户添加页面
		return "backstage/partner/editPartner";
	}
	/**
	 * @Title: modifyPartner
	 * @Description: 修改用户身份标示
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/modifyPartner")
	@ResponseBody
	public String modifyPartner(@ModelAttribute TblPartner partner, Model model) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			//更新用户身份
			tblPartnerService.updatePartnerKeyById(partner);
			//缓存第三方身份信息
			redisService.strSet("app:org:"+partner.getPartnerKey(), partner.getPartnerToken()+":"+partner.getPaymod());
			dwzResult = new DwzAjaxResult("200", "修改成功", "partnerList", "closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "modifyPartner", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * @Title: deletePartner
	 * @Description: 删除
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/deletePartner")
	@ResponseBody
	public String deletePartner(@RequestParam("partnerId") String partnerId) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			//缓存第三方身份信息
			TblPartner partner = tblPartnerService.selectPartnerNameById(Integer.parseInt(partnerId));
			redisService.strRemove("app:org:"+partner.getPartnerKey());
			tblPartnerService.deletePartnerById(partnerId);
			dwzResult = new DwzAjaxResult("200", "删除成功", "partnerList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "partnerList", "","");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
