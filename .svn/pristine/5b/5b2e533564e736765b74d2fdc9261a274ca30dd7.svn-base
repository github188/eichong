package com.wanma.controller;
 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.pub.model.Pager;
import com.wanma.model.TblApplyPartner;
import com.wanma.service.CmsApplyPartnerService;
import com.wanma.service.CmsCarinfoService;


@Controller
@RequestMapping("/admin/applyPartner")
public class VCmsApplyCompanyPartnerConteoller {
	
	//日志输出对象
			private static Logger log = Logger.getLogger(VCmsApplyCompanyPartnerConteoller.class);
		@Autowired
		CmsApplyPartnerService  cmsApplyPartnerService;
		@Autowired
		private CmsCarinfoService carinfoService;
		
		
		/**
		 * 取得设备保修列表
		 * 
		 * @author xiay
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/applyPartnerList")
		public String getCmsApplyPartnerList(@ModelAttribute("pager") Pager pager,
				@ModelAttribute TblApplyPartner tblApplyPartner,
				Model model,HttpServletRequest request){
				
				if(!StringUtils.isEmpty(tblApplyPartner.getCreateDateEnd())){
					tblApplyPartner.setCreateDateEnd(tblApplyPartner.getCreateDateEnd() + " 23:59:59");
				}
				//申请加电列表信息
				List<TblApplyPartner> applyPartnerList = null;
				
				//申请加电列表总数
				long total = 0;
				tblApplyPartner.setApPaPartnerType("2");
				// 用户总数
				total = cmsApplyPartnerService.getCmsApplyPartnerCount(tblApplyPartner);
				if(total<=pager.getOffset()){
					pager.setPageNum(1L);
				}
				//设置分页对象
				tblApplyPartner.setPager(pager);
				
				//取得申请加电列表信息
				applyPartnerList = cmsApplyPartnerService.getCmsApplyPartnerList(tblApplyPartner);								
				pager.setTotal(total);
				//使用完毕后将时间还原回去
				if(!StringUtils.isEmpty(tblApplyPartner.getCreateDateEnd())){
					tblApplyPartner.setCreateDateEnd(tblApplyPartner.getCreateDateEnd().replace(" 23:59:59", ""));
				}				
			//将申请加电列表信息放到会话中
			model.addAttribute("tblApplyPartner", tblApplyPartner);
			model.addAttribute("applyPartnerList", applyPartnerList);
			model.addAttribute("pager", pager);				
			//跳转至申请加电列表信息
			return "backstage/applyPartner/company/listApplyPartner";
		}
		
		
		/**
		 * 编辑申请加电状态
		 * 
		 * @author xiay
		 * @since Version 1.0
		 * @throws 无
		 */
		@RequestMapping("/updateApplyPartnerState")
		@ResponseBody
		public String updateApplyPartnerState(Map<String, Object> params,String pkApplyPartner,String apPaProcessState) {
			log.info("************编辑状态-start************");
			BaseResult baseResult = new BaseSuccess();
			params.put("pkApplyPartner", pkApplyPartner);
			params.put("apPaProcessState", apPaProcessState);
			try {
				cmsApplyPartnerService.updateApplyPartnerState(params);;
			} catch (Exception e) {
				log.error(this.getClass() + ".updateApplyPartnerState() error:"
						+ e.getLocalizedMessage());
				baseResult = new BaseFail(5001);
			}
			log.info("************编辑状态-end************");
			// 返回处理结果信息
			return baseResult.toString();
		}
		
		/**
		 * @Title: editCarinfo
		 * @Description: 跳转至编辑驳回原因页面
		 * @param params
		 * @return
		 */
		@RequestMapping("/changeApplyPartnerReason")
		public String changeApplyPartnerReason(String applyPartnerId, Model model) {
			log.info("******************跳转至编辑驳回原因页面-begin************************");	
			Integer pkApplyPartner = Integer.parseInt(applyPartnerId);
			// 根据id获取详情
			TblApplyPartner tblApplyPartner = cmsApplyPartnerService.getCmsApplyPartnerById(pkApplyPartner);
			// 将查询结果显示到画面	
			model.addAttribute("tblApplyPartner", tblApplyPartner);
			log.info("******************跳转至编辑驳回原因页面-end************************");
			return "backstage/applyPartner/company/editDealReason";
		}
		
		@RequestMapping("/updateApplyPartnerReason")
		@ResponseBody
		public String updateApplyPartnerReason(Map<String, Object> params,String pkApplyPartner,String apPaDealReason) {
			log.info("******************更新充电点申请记录-begin************************");
			// 处理结果信息
			BaseResult baseResult = new BaseSuccess();
			params.put("pkApplyPartner", pkApplyPartner);
			params.put("apPaDealReason", apPaDealReason);
				try {
					cmsApplyPartnerService.updateApplyPartnerReason(params);
				} catch (Exception e) {
					log.error(this.getClass() + ".updateApplyPartnerReason() error:"
							+ e.getLocalizedMessage());
					baseResult = new BaseFail(5001);
				}
				
			log.info("******************更新充电点申请记录-end************************");
			// 返回处理结果信息
			return baseResult.toString();
		}
		
		/**
		 * @Title: deleteById
		 * @Description: 删除充电点申请记录
		 * @param params
		 * @return
		 */
		@RequestMapping("/deleteById")
		@ResponseBody
		public String deleteById(String applyPartnerId) {
			log.info("******************删除充电点申请记录-begin************************");
			BaseResult baseResult = new BaseSuccess();
			Integer pkApplyPartner = Integer.parseInt(applyPartnerId);
			try {
				// 删除其他配置参数中配置名称
				cmsApplyPartnerService.deleteApplyPartnerById(pkApplyPartner);;
			} catch (Exception e) {
				log.error(this.getClass() + ".deleteById() error:"
						+ e.getLocalizedMessage());
				baseResult = new BaseFail(5001);
			}
			log.info("******************删除充电点申请记录-end************************");
			// 返回处理结果信息
			return baseResult.toString();
		}
		
		
		
		/**
		 * @Title: deleteCarinfos
		 * @Description: 批量删除充电点申请记录
		 * @param pkCarinfos
		 *            主键，多个以英文逗号分隔
		 * @return
		 */
		@RequestMapping("/deleteApplyPartners")
		@ResponseBody
		public String deleteApplyPartners(@RequestParam("ids") String pkApplyPartners) {
			log.info("******************批量删除充电点申请记录-begin************************");

			BaseResult baseResult = new BaseSuccess();
			
			try {
				// 批量删除充电点申请记录
				cmsApplyPartnerService.deleteApplyPartnerByIdS(pkApplyPartners);;
			} catch (Exception e) {
				log.error(this.getClass() + ".deleteApplyPartners() error:"
						+ e.getLocalizedMessage());
				baseResult = new BaseFail(5001);
			}

			log.info("******************批量删除充电点申请记录-end************************");
			// 返回处理结果信息
			return baseResult.toString();
		}

		
}
