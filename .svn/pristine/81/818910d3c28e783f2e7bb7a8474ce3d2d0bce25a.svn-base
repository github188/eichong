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

import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblProductcomment;
import com.wanma.model.TblUser;
import com.wanma.service.CmsProductcommentService;

/**
 * 评论控制器
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/evaluate")
public class CmsProductcommentController extends BaseController{
	
	//日志输出对象
	private static Logger log = Logger.getLogger(CmsProductcommentController.class);
	
	/** 评论业务处理对象 */
	@Autowired
	private CmsProductcommentService productService;
	
	/**
	 * 分页查询评论信息
	 * 
	 * @author xiay
	 * @param pager
	 * @param tblProductcomment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/evaluateList")
	public String getContentList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblProductcomment tblProductcomment,
			Model model,HttpServletRequest request){
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{
			//评论表信息
			List<TblProductcomment> contentList = null;
			
			//用户总数
			long total = 0;
			
			if(tblProductcomment == null){
				//取得评论表
				contentList = productService.getContentList();
			}else {
				TblUser tblUser = SessionMgr.getWebUser(request);
				// 用户总数
				tblProductcomment.setPrcoUserid(tblUser.getUserId().intValue());
				tblProductcomment.setUserLevel(tblUser.getUserLevel());
		    	total = productService.searchContentCount(tblProductcomment);
				if(total<=pager.getOffset()){
					pager.setPageNum(1L);
				}
				//设置分页对象
				tblProductcomment.setPager(pager);
				
				//取得评论表
				contentList = productService.searchProContentList(tblProductcomment);
				
				
				pager.setTotal(total);
			}
			// 取得登录用户信息
			TblUser loginUser = SessionMgr.getWebUser(request);
			//将评论信息放到会话中
			model.addAttribute("loginUserLevel", loginUser.getUserLevel());
			model.addAttribute("tblProductcomment", tblProductcomment);
			model.addAttribute("contentList", contentList);
			model.addAttribute("pager", pager);
			
			//跳转至评论表界面
			return "backstage/evaluate/listEvaluate";
		}	
	}
	
	/**
	 * 删除评论
	 * 
	 * @author xiay
	 * @param pager
	 * @param pkProductcomment
	 * 					评论ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteProductAll")
	@ResponseBody
	public String deleteProductAll(@RequestParam("ids") String pkProductcomments){
		// 处理结果信息
		DwzAjaxResult dwzResult;
       
		try {
			TblProductcomment tblProductcomment = new TblProductcomment();
			if(pkProductcomments != null){				
				String[] idss = pkProductcomments.split(",");
				for(String pkProductcomment:idss){					
					String[] typeNum = pkProductcomment.split(":");
				   	pkProductcomment = typeNum[1];			
					  if(Integer.parseInt(typeNum[0])==1){
							productService.removeElectricpilecomment(Integer.parseInt(pkProductcomment));
						}else if(Integer.parseInt(typeNum[0])==2){
							productService.deleteProduct(Integer.parseInt(pkProductcomment));							
						}else {					
							productService.removePowerstationcomment(Integer.parseInt(pkProductcomment));
						}
					
				}							
			}					
			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "evaluateList", "", "");
			// 如果更新评论表界面不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "evaluateList", "","");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
