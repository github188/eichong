package com.wanma.controller;

import java.util.List;

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
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblCarmaker;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCarmakerService;


/**
 * @Description: 制造厂商controller
 * @author wubc
 * @createTime：2015-5-27 
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/carmaker")
public class CmsCarmakerController extends BaseController{
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsCarmakerController.class);
	/* 制造厂商service */
	@Autowired
	private CmsCarmakerService carmakerService;
	
	@RequestMapping("/findCarMakerList")
	public String findCarList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblCarmaker	 carmaker, Model model,HttpServletRequest request) {
		log.info("******************获取制造厂商列表-begin************************");
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}
		// 制造厂商总数
		long total = carmakerService.getCount(carmaker);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		// 设置查询参数
		carmaker.setPager(pager);
		
		List<TblCarmaker> carmakerList = carmakerService.getAll(carmaker);
		
		pager.setTotal(total);
		
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
				model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("carmakerList", carmakerList);
		model.addAttribute("pager", pager);
		model.addAttribute("carmaker", carmaker);
		log.info("******************获取制造厂商列表-end************************");
		// 跳转至制造厂商列表页面
		return "backstage/carMaker/carMaker-list";
	}
	
	/**
	 * @Title: newcarMaker
	 * @Description: 跳转至制造厂商页面
	 * @param carMaker
	 * @return
	 */
	@RequestMapping("/newCarmaker")
	public String newcarMaker(Model model) {
		log.info("******************跳转至制造厂商页面************************");
		return "backstage/carMaker/carMaker-add";

	}

	/**
	 * @Title: insertCarMaker
	 * @Description: 新增制造厂商页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertCarMaker")
	@ResponseBody
	public String insertCarMaker(TblCarmaker carMaker) {
		log.info("******************新增制造厂商-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
        int makerRemarkInt  = Integer.parseInt(carMaker.getMakerRemark());
		try {
			if(makerRemarkInt<100&&makerRemarkInt>=0){
				if(makerRemarkInt<=9){
					carMaker.setMakerRemark("0"+makerRemarkInt);
				}
				List<TblCarmaker> tempCarmakerList = carmakerService.getByProperty(carMaker);
				if(tempCarmakerList==null||tempCarmakerList.isEmpty()){								
					carmakerService.insert(carMaker);
					dwzResult = new DwzAjaxResult("200", "新增成功", "findCarMakerList",
							"closeCurrent", "");
				}else{
					dwzResult = new DwzAjaxResult("300", "新增失败,名称或标识重复", "findCarMakerList", "", "");				
				}
			}else{
				dwzResult = new DwzAjaxResult("300", "新增失败,标志不是0到99的数字", "findCarMakerList", "", "");
			}
			
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "findCarMakerList", "", "");
		}
		log.info("******************新增制造厂商-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除制造厂商
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(TblCarmaker tblcarMaker) {
		log.info("******************删除制造厂商-begin************************");
		DwzAjaxResult dwzResult = null;
		try {
			//判断是否被电桩绑定
			if(carmakerService.isBondWithElectricPile(tblcarMaker.getPkCarmaker())){
				dwzResult = new DwzAjaxResult("300", "该厂商已绑定电桩，无法删除！", "findCarMakerList", "", "");
			} else {
				// 删除其他配置参数中配置名称
				carmakerService.delete(tblcarMaker.getPkCarmaker());
				dwzResult = new DwzAjaxResult("200", "删除成功", "findCarMakerList", "", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findCarMakerList", "", "");
		}

		log.info("******************删除制造厂商-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editCarMaker
	 * @Description: 跳转至更新制造厂商页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editCarMaker")
	public String editCarMaker(TblCarmaker tblcarMaker, Model model) {
		log.info("******************跳转至更新制造厂商页面-begin************************");
		//判断是否被电桩绑定
		if(carmakerService.isBondWithElectricPile(tblcarMaker.getPkCarmaker())){
			model.addAttribute("erMessage", "该制造厂商已被绑定，无法编辑");
			return "list_button_error";
		}
		// 根据id获取制造厂商
		TblCarmaker carMaker = carmakerService.getOne(tblcarMaker);
		// 将查询结果显示到页面
		model.addAttribute("carMaker", carMaker);
		log.info("******************跳转至更新制造厂商页面-end************************");
		return "backstage/carMaker/carMaker-edit";
	}

	/**
	 * @Title: updateCarMaker
	 * @Description: 更新制造厂商
	 * @param carMaker
	 * @return
	 */
	@RequestMapping("/updateCarMaker")
	@ResponseBody
	public String updateCarMaker(TblCarmaker carMaker) {
		log.info("******************更新制造厂商-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		int makerRemarkInt = Integer.parseInt(carMaker.getMakerRemark());
		try {
			if(makerRemarkInt<100&&makerRemarkInt>=0){
				if(makerRemarkInt<=9){
					carMaker.setMakerRemark("0"+makerRemarkInt);
				}
				List<TblCarmaker> tempCarmakerList = carmakerService.getByProperty(carMaker);
				if(tempCarmakerList==null||tempCarmakerList.isEmpty()){							
						carmakerService.update(carMaker);
						dwzResult = new DwzAjaxResult("200", "编辑成功", "findCarMakerList",
								"closeCurrent", "");					
				}else{
					  dwzResult = new DwzAjaxResult("300", "编辑失败,名称或标识重复", "findCarMakerList", "", "");
				}
			}else{
				dwzResult = new DwzAjaxResult("300", "编辑失败,标志不是0到99的数字", "findCarMakerList", "", "");
			}
		
		}catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "findCarMakerList", "", "");
		}
		log.info("******************更新制造厂商-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteCarMakers
	 * @Description: 批量删除制造厂商
	 * @param pkcarMakers
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/deleteCarMakers")
	@ResponseBody
	public String deleteCarMakers(@RequestParam("ids") String pkcarMakers) {
		log.info("******************批量删除制造厂商-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			//判断是否被电桩绑定
			if(pkcarMakers != null){
				String[] ids = pkcarMakers.split(",");
				if(ids != null && ids.length > 0){
					String errorCode="";
					for(String idstr:ids){
						if(carmakerService.isBondWithElectricPile(Integer.valueOf(idstr))){
							errorCode+=idstr+",";
						}
					}
					errorCode=StringUtils.removeEnd(errorCode, ",");
					if(StringUtils.isNotBlank(errorCode)){
						return new DwzAjaxResult("300", "存在已绑定电桩的厂商:"+errorCode, "findCarMakerList", "", "").toJSONString();
					}
				}
				
			}
			// 批量删除制造厂商
			carmakerService.deleteBatch(pkcarMakers);

			dwzResult = new DwzAjaxResult("200", "删除成功", "findCarMakerList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findCarMakerList", "", "");
		}

		log.info("******************批量删除制造厂商-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

}
