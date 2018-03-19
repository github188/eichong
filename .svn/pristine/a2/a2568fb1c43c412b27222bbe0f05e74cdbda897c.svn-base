package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblBespoke;
import com.wanma.model.TblUser;
import com.wanma.service.CmsBespokeService;

/**
 * 预约管理控制器
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/admin/order")
public class CmsBespokeController extends BaseController{

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsBespokeController.class);
	
	/** 预约处理器 */
	@Autowired
	private CmsBespokeService tblBespokeService;
	
	/**
	 * 取得预约列表处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/subsOrder")
	public String getBespokeList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblBespoke tblBespoke, Model model,HttpServletRequest request) {

		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {// 个体商家
			tblBespoke.setBespUser(loginUser.getUserId()+"");
		} else if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblBespoke.setBespUser(loginUser.getUserAccount());
		}
		
		// 登录预约信息
		List<TblBespoke> BespokeList = null;
		// 预约总数
		long total = 0;

		if (tblBespoke == null) {
			// 取得预约列表
			BespokeList = tblBespokeService.getBespokeList();
		} else {
			if(("2").equals(tblBespoke.getBespOrderType())){
				tblBespoke.setBespOrderType("");
				tblBespoke.setBespBespokestatus(7);
			}
			// 预约总数
			total =tblBespokeService.searchBespokeCount(tblBespoke);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblBespoke.setPager(pager);
			// 取得预约列表
			BespokeList = tblBespokeService.searchBespokeList(tblBespoke);
			if(tblBespoke.getBespBespokestatus()!=null && tblBespoke.getBespBespokestatus()==7){
				tblBespoke.setBespOrderType("2");
			}
			
			pager.setTotal(total);
		}
		
		// 将预约信息放到会话中
		model.addAttribute("tblBespoke", tblBespoke);
		model.addAttribute("BespokeList", BespokeList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/order/listBespoke";
	}	
	
	/**
	 * 取得预约列表处理 --个体商家
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/subsUnitOrder")
	public String getUnitBespokeList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblBespoke tblBespoke, Model model,HttpServletRequest request) {

		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
//		if (loginUser.getUserLevel().toString().equals(WanmaConstants.USER_LEVER_personl3)) {// 个体商家
//			tblBespoke.setBespUser(loginUser.getUserId()+"");
//		} else if (loginUser.getUserLevel().toString().equals(
//				WanmaConstants.USER_LEVER_merchant2)) {// 纯商家
//			tblBespoke.setBespUser(loginUser.getUserAccount());
//		}
		
		// 登录预约信息
		List<TblBespoke> unitBespokeList = null;
		// 预约总数
		long total = 0;

		if (tblBespoke == null) {
			// 取得预约列表
			unitBespokeList = tblBespokeService.getUnitBespokeList();
		} else {
			if(("2").equals(tblBespoke.getBespOrderType())){
				tblBespoke.setBespOrderType("");
				tblBespoke.setBespBespokestatus(7);
			}
			tblBespoke.setBespUser(loginUser.getUserId().toString());
			tblBespoke.setUserLevel(loginUser.getUserLevel().toString());
			// 预约总数
			total =tblBespokeService.searchUnitBespokeCount(tblBespoke);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblBespoke.setPager(pager);
			// 取得预约列表
			unitBespokeList = tblBespokeService.searchUnitBespokeList(tblBespoke);
			pager.setTotal(total);
			if(tblBespoke.getBespBespokestatus()!=null && tblBespoke.getBespBespokestatus()==7){
				tblBespoke.setBespOrderType("2");
			}
		}

		// 将预约信息放到会话中
		model.addAttribute("tblBespoke", tblBespoke);
		model.addAttribute("unitBespokeList", unitBespokeList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/UnitOrder/listUnitBespoke";
	}	
	
	/**
	 * 取得预约列表处理 --纯商家
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/subsFirmOrder")
	public String getFirmBespokeList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblBespoke tblBespoke, Model model,HttpServletRequest request) {

		// 登录预约信息
		List<TblBespoke> firmBespokeList = null;
		// 预约总数
		long total = 0;
		if (tblBespoke == null) {
			// 取得预约列表
			firmBespokeList = tblBespokeService.getBespokeList();
		} else {
			if(("2").equals(tblBespoke.getBespOrderType())){
				tblBespoke.setBespOrderType("");
				tblBespoke.setBespBespokestatus(7);
			}
			// 登陆用户
						TblUser loginUser = SessionMgr.getWebUser(request);
			tblBespoke.setBespUser(loginUser.getUserId().toString());
			tblBespoke.setUserLevel(loginUser.getUserLevel().toString());
			// 预约总数
			total =tblBespokeService.searchFirmBespokeCount(tblBespoke);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblBespoke.setPager(pager);
			// 取得预约列表
			firmBespokeList = tblBespokeService.searchFirmBespokeList(tblBespoke);
			pager.setTotal(total);
			if(tblBespoke.getBespBespokestatus()!=null && tblBespoke.getBespBespokestatus()==7){
				tblBespoke.setBespOrderType("2");
			}
		}

		// 将预约信息放到会话中
		model.addAttribute("tblBespoke", tblBespoke);
		model.addAttribute("firmBespokeList", firmBespokeList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/pureBusiness/listBusBespoke";
	}	
}
