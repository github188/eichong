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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.BasicListAndMutiFile;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblDynamic;
import com.wanma.model.TblUser;
import com.wanma.service.CmsDynamicService;
import com.wanma.service.impl.RedisService;


/**
 * @Description: 动态管理控制层
 * @author wbc
 * @createTime：2015-8-24 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/dynamic")
public class CmsDynamicController extends BaseController{
		// 日志输出对象
		private static Logger log = Logger.getLogger(CmsDynamicController.class);
		@Autowired
		private CmsDynamicService dynamicService;
		@Autowired
		private RedisService redisService;
		
		/**
		 * @Title: list
		 * @Description: 获取动态列表
		 * @param TblDynamic
		 * @return
		 */
		@RequestMapping("/list")
		public String list(@ModelAttribute("pager") DwzPagerMySQL pager,
				TblDynamic dynamic, Model model, HttpServletRequest request) {
			log.info("******************获取动态列表-begin************************");
			if(!checkOprateValid(request)){
				return WanmaConstants.ERROR_PAGE;
			}else{
				// 动态总数
				long total = dynamicService.count(dynamic);
				if (total <= pager.getOffset()) {
					pager.setPageNum(1L);
				}
				// 设置查询参数
				dynamic.setPager(pager);
				List<TblDynamic> dynamicList = dynamicService.list(dynamic);
				pager.setTotal(total);
	
				// 将查询结果显示到画面
				model.addAttribute("dynamicList", dynamicList);
				model.addAttribute("pager", pager);
				model.addAttribute("dynamic", dynamic);
				log.info("******************获取动态列表-end************************");
	
				// 跳转至车型列表页面
				return "backstage/dynamic/dynamic-list";
			}	
		}	
		
		/**
		 * @Title: newDynamic
		 * @Description: 跳转至新增动态页面
		 * @param Dynamic
		 * @return
		 */
		@RequestMapping("/new")
		public String newDynamic(Model model) {
			log.info("******************跳转至新增动态页面************************");
			return "backstage/dynamic/dynamic-add";

		}
		
		/**
		 * @Title: insertDynamic
		 * @Description: 新增动态页面
		 * @param params
		 * @return
		 */
		@RequestMapping("/add")
		@ResponseBody
		public String add(HttpServletRequest request,
				TblDynamic dynamic,@RequestParam(value = "file", required = false) MultipartFile[] file) {
			log.info("******************新增动态-begin************************");
			// 处理结果信息
			DwzAjaxResult dwzResult = null;
			
			try {
				// 登陆用户
				TblUser loginUser = SessionMgr.getWebUser(request);
				// 新增动态
				dynamic.setReleUsepk(loginUser.getUserAccount());
				dynamicService.add(dynamic,file);
				dwzResult = new DwzAjaxResult("200", "新增成功", "dynamic",
						"closeCurrent", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "新增失败", "dynamic", "", "");
			}
			log.info("******************新增动态-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		/**
		 * @Title: deleteById
		 * @Description: 禁用动态
		 * @param params
		 * @return
		 */
		@RequestMapping("/deleteById")
		@ResponseBody
		public String deleteById(TblDynamic tblDynamic) {
			log.info("******************禁用动态-begin************************");
			DwzAjaxResult dwzResult = null;

			try {
				dynamicService.disableDynamic(tblDynamic);
				dwzResult = new DwzAjaxResult("200", "禁用成功", "dynamic", "", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "禁用失败", "dynamic", "", "");
			}

			log.info("******************禁用动态-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		/**
		 * @Title: edit
		 * @Description: 跳转至更新动态页面
		 * @param params
		 * @return
		 */
		@RequestMapping("/edit")
		public String edit(TblDynamic tblDynamic, Model model) {
			log.info("******************跳转至更新动态页面-begin************************");
			// 根据id获取动态详情
			TblDynamic dynamic = dynamicService.get(tblDynamic.getPkRelease());
			// 将查询结果显示到画面
			model.addAttribute("dynamic", dynamic);
			log.info("******************跳转至更新动态页面-end************************");
			return "backstage/dynamic/dynamic-edit";
		}

		/**
		 * @Title: updateDynamic
		 * @Description: 更新动态
		 * @param Dynamic
		 * @return
		 */
		@RequestMapping("/update")
		@ResponseBody
		public String update(TblDynamic dynamic,@RequestParam(value = "file", required = false) MultipartFile[] file) {
			log.info("******************更新动态-begin************************");
			// 处理结果信息
			DwzAjaxResult dwzResult = null;

			try {
				dynamicService.update(dynamic,file);
				dwzResult = new DwzAjaxResult("200", "编辑成功", "dynamic",
						"closeCurrent", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "编辑失败", "dynamic", "", "");
			}
			log.info("******************更新动态-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}
		
		/**
		 * @Title: delete
		 * @Description: 删除动态
		 * @param Dynamic
		 * @return
		 */
		@RequestMapping("/delete")
		@ResponseBody
		public String delete(@RequestParam("ids") String ids) {
			log.info("******************删除动态-begin************************");
			// 处理结果信息
			DwzAjaxResult dwzResult = null;

			try {
				dynamicService.deleteByIds(ids);
				dwzResult = new DwzAjaxResult("200", "删除成功", "dynamic",
						"", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "删除失败", "dynamic", "", "");
			}
			log.info("******************删除动态-end************************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}
		
		/**
		 * @Title: upload
		 * @Description: 上传文件
		 * @param MultipartFile
		 * @return
		 */
		@RequestMapping("/upload")
		@ResponseBody
		public String upload(@RequestParam("filedata") MultipartFile file) {
			BasicListAndMutiFile fileModel=new BasicListAndMutiFile();
			fileModel.setAllMultiFile(new MultipartFile[]{file});
			String refId=System.currentTimeMillis()+"";
			MultipartFileUtil.saveAllMulti(fileModel, WanmaConstants.DYNAMIC, refId);
			String fileName=dynamicService.getUploadImgUrl(WanmaConstants.DYNAMIC, refId);
			JSONObject json=new JSONObject();
			json.put("err", "");
			json.put("msg", fileName);
			return json.toString();
		}
		
		/**
		 * @Title: list
		 * @Description: 获取动态列表
		 * @param TblDynamic
		 * @return
		 */
		@RequestMapping("/listViewCount")
		public String listViewCount(@ModelAttribute("pager") DwzPagerMySQL pager,
				TblDynamic dynamic, Model model, HttpServletRequest request) {
			log.info("******************获取动态列表-begin************************");
			if(!checkOprateValid(request)){
				return WanmaConstants.ERROR_PAGE;
			}else{
				// 设置查询参数
				dynamic.setPager(pager);
				List<TblDynamic> dynamicList = dynamicService.list(dynamic);
				// 动态总数
				long total = dynamicService.count(dynamic);
				pager.setTotal(total);
				for(TblDynamic dynamicTemp:dynamicList){
					String countStr = redisService.strGet("html:dynamic:count:"+dynamicTemp.getPkRelease());
					if(countStr == null)
						countStr = "0:0:0";
					String[] countArray = countStr.split(":");
					int webCount = Integer.valueOf(countArray[0]);
					int androidCount = Integer.valueOf(countArray[1]);
					int iosCount = Integer.valueOf(countArray[2]);
					dynamicTemp.setTotalCount(webCount+iosCount+androidCount);
					dynamicTemp.setWebCount(webCount);
					dynamicTemp.setIosCount(iosCount);
					dynamicTemp.setAndroidCount(androidCount);
				}
				// 将查询结果显示到画面
				model.addAttribute("dynamicList", dynamicList);
				model.addAttribute("pager", pager);
				model.addAttribute("dynamic", dynamic);
				log.info("******************获取动态列表-end************************");
	
				// 跳转至车型列表页面
				return "backstage/dynamic/userViewCountInfo-list";
			}	
		}
		
		
}
