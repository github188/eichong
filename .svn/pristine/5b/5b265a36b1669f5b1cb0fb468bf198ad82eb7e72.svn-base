package com.wanma.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.model.TblApplyElecPile;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsApplyElecPileService;
import com.wanma.service.CmsCarCompanyService;
import com.wanma.service.CmsCarinfoService;


@Controller
@RequestMapping("/admin/applyElecPile")
public class CmsApplyElecPileConteoller {
	
	//日志输出对象
			private static Logger log = Logger.getLogger(CmsApplyElecPileConteoller.class);
		@Autowired
		CmsApplyElecPileService  cmsApplyElecPileService;
		@Autowired
		private CmsCarinfoService carinfoService;
		@Autowired
		private CmsCarCompanyService carCompanyService;
		/**
		 * 取得设备保修列表
		 * 
		 * @author xiay
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/applyElecPileList")
		public String getCmsApplyElecPileList(@ModelAttribute("pager") DwzPagerMySQL pager,
				@ModelAttribute TblApplyElecPile tblApplyElecPile,
				Model model,HttpServletRequest request){
			
			List<HashMap<String,Object>> brandList = new ArrayList<HashMap<String,Object>>();
			//传进来的参数不包括时分秒，在这里补上
			if(!StringUtils.isEmpty(tblApplyElecPile.getCreateDateEnd())){
				tblApplyElecPile.setCreateDateEnd(tblApplyElecPile.getCreateDateEnd() + " 23:59:59");
			}
			if(!StringUtils.isEmpty(tblApplyElecPile.getUpdateDateEnd())){
				tblApplyElecPile.setUpdateDateEnd(tblApplyElecPile.getUpdateDateEnd() + " 23:59:59");
			}
			if(tblApplyElecPile.getApEpCarcompanyId()!=null){
				List<TblCarinfo> carTypeList = null;
				Integer pkCarcompany = Integer.parseInt(tblApplyElecPile.getApEpCarcompanyId());
				//获取电动车车型
				carTypeList = carinfoService.searchCarinfoList(pkCarcompany);		
				model.addAttribute("carTypeList", carTypeList);			
			}					
				//获取电动车厂家
				brandList = carCompanyService.findCarCompanyList(null);		
			
				//申请加电列表信息
				List<TblApplyElecPile> applyElecPileList = null;
				
				//申请加电列表总数
				long total = 0;
				
				// 用户总数
				total = cmsApplyElecPileService.getCmsApplyElecPileCount(tblApplyElecPile);
				if(total<=pager.getOffset()){
					pager.setPageNum(1L);
				}
				//设置分页对象
				tblApplyElecPile.setPager(pager);
				
				//取得申请加电列表信息
				applyElecPileList = cmsApplyElecPileService.getCmsApplyElecPileList(tblApplyElecPile);								
				pager.setTotal(total);
				//使用完毕后将时间还原回去
				if(!StringUtils.isEmpty(tblApplyElecPile.getCreateDateEnd())){
					tblApplyElecPile.setCreateDateEnd(tblApplyElecPile.getCreateDateEnd().replace(" 23:59:59", ""));
				}
				if(!StringUtils.isEmpty(tblApplyElecPile.getUpdateDateEnd())){
					tblApplyElecPile.setUpdateDateEnd(tblApplyElecPile.getUpdateDateEnd().replace(" 23:59:59", ""));
				}
			//将申请加电列表信息放到会话中
			model.addAttribute("tblApplyElecPile", tblApplyElecPile);
			model.addAttribute("applyElecPileList", applyElecPileList);
			model.addAttribute("brandList", brandList);
			model.addAttribute("pager", pager);				
			//跳转至申请加电列表信息
			return "backstage/applyElecPile/listApplyElecPile";
		}
		
		
		/**
		 * 编辑申请加电状态
		 * 
		 * @author xiay
		 * @since Version 1.0
		 * @throws 无
		 */
		@RequestMapping("/updateApplyElecPileState")
		@ResponseBody
		public String updateApplyElecPileState(Map<String, Object> params,String pkApplyElecPile,String apEpProcessState) {
			log.info("************编辑状态-start************");
			// 处理结果信息
			DwzAjaxResult dwzResult = null;
			params.put("pkApplyElecPile", pkApplyElecPile);
			params.put("apEpProcessState", apEpProcessState);
			try {
				cmsApplyElecPileService.updateApplyElecPileState(params);;
				dwzResult = new DwzAjaxResult("200", "编辑成功", "applyElecPileList", "", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "编辑失败", "applyElecPileList", "", "");
			}
			log.info("************编辑状态-end************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}
		
		/**
		 * @Title: editCarinfo
		 * @Description: 跳转至查看详情页面
		 * @param params
		 * @return
		 */
		@RequestMapping("/checkApplyElecPileDetail")
		public String checkApplyElecPileDetail(String applyElecPileId, Model model) {
			log.info("******************跳转申购电桩查看详情页面-begin************************");
			Integer pkApplyElecPile = Integer.parseInt(applyElecPileId);
			// 根据id获取详情
			TblApplyElecPile tblApplyElecPile = cmsApplyElecPileService.getCmsApplyElecPileById(pkApplyElecPile);			
			//获取品牌车型
			List<HashMap<String, Object>> brandList = carCompanyService.findCarCompanyList(null);					
			//获取电动车车型
			List<TblCarinfo> carTypeList = null;
			Integer pkCarcompany = Integer.parseInt(tblApplyElecPile.getApEpCarcompanyId());
			carTypeList = carinfoService.searchCarinfoList(pkCarcompany);							
			// 将查询结果显示到画面
			model.addAttribute("carTypeList", carTypeList);
			model.addAttribute("tblApplyElecPile", tblApplyElecPile);
			model.addAttribute("brandList", brandList);
			// 	
			log.info("******************跳转至申购电桩查看详情页面-end************************");
			return "backstage/applyElecPile/detailApplyElecPile";
		}
		
		/**
		 * @Title: editCarinfo
		 * @Description: 跳转至编辑驳回原因页面
		 * @param params
		 * @return
		 */
		@RequestMapping("/changeApplyElecPileReason")
		public String changeApplyElecPileReason(String applyElecPileId, Model model) {
			log.info("******************跳转至编辑驳回原因页面-begin************************");	
			Integer pkApplyElecPile = Integer.parseInt(applyElecPileId);
			// 根据id获取详情
			TblApplyElecPile tblApplyElecPile = cmsApplyElecPileService.getCmsApplyElecPileById(pkApplyElecPile);
			// 将查询结果显示到画面	
			model.addAttribute("tblApplyElecPile", tblApplyElecPile);
			log.info("******************跳转至编辑驳回原因页面-end************************");
			return "backstage/applyElecPile/editEpDealReason";
		}
		
		@RequestMapping("/updateApplyElecPileReason")
		@ResponseBody
		public String updateApplyElecPileReason(Map<String, Object> params,String pkApplyElecPile,String apEpDealReason) {
			log.info("******************更新充电点申请记录-begin************************");
			// 处理结果信息
			DwzAjaxResult dwzResult = null;
			params.put("pkApplyElecPile", pkApplyElecPile);
			params.put("apEpDealReason", apEpDealReason);
				try {
					cmsApplyElecPileService.updateApplyElecPileReason(params);
					dwzResult = new DwzAjaxResult("200", "编辑成功", "applyElecPileList",
							"closeCurrent", "");
				} catch (Exception e) {
					// 出错日志
					log.info(e.getLocalizedMessage());
					// 设置错误信息
					dwzResult = new DwzAjaxResult("300", "编辑失败", "applyElecPileList", "", "");
				}
				
			log.info("******************更新充电点申请记录-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}
		
		/**
		 * @Title: deleteById
		 * @Description: 删除充电点申请记录
		 * @param params
		 * @return
		 */
		@RequestMapping("/deleteById")
		@ResponseBody
		public String deleteById(String applyElecPileId) {
			log.info("******************删除充电点申请记录-begin************************");
			DwzAjaxResult dwzResult = null;
			Integer pkApplyElecPile = Integer.parseInt(applyElecPileId);
			try {
				// 删除其他配置参数中配置名称
				cmsApplyElecPileService.deleteApplyElecPileById(pkApplyElecPile);;
				dwzResult = new DwzAjaxResult("200", "删除成功", "applyElecPileList", "", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "删除失败", "applyElecPileList", "", "");
			}

			log.info("******************删除充电点申请记录-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}
		
		
		
		/**
		 * @Title: deleteCarinfos
		 * @Description: 批量删除充电点申请记录
		 * @param pkCarinfos
		 *            主键，多个以英文逗号分隔
		 * @return
		 */
		@RequestMapping("/deleteApplyElecPiles")
		@ResponseBody
		public String deleteCarinfos(@RequestParam("ids") String pkApplyElecPiles) {
			log.info("******************批量删除充电点申请记录-begin************************");

			DwzAjaxResult dwzResult = null;
			
			try {
				// 批量删除充电点申请记录
				cmsApplyElecPileService.deleteApplyElecPileByIdS(pkApplyElecPiles);;

				dwzResult = new DwzAjaxResult("200", "删除成功", "applyElecPileList", "", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "删除失败", "applyElecPileList", "", "");
			}

			log.info("******************批量删除充电点申请记录-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		
}
