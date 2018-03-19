package com.wanma.ims.controller.config.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.NewsInfoDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.NewsInfoService;



/**
 * 资讯列表
 */
@Controller
@RequestMapping("/manage/app/newsInfo")
public class NewsInfoController extends BaseController {
	// 日志输出对象
		private static Logger log = Logger.getLogger(NewsInfoController.class);
		@Autowired
		private NewsInfoService newsInfoService;
	/**
	 * 资讯列表
	 * @author mb
	 * @param 
	 * @return
	 */
		@RequestMapping(value = "/getNewsInfoList")
		@ResponseBody
		public void getNewsInfoList(@ModelAttribute("pager") Pager pager,
				@ModelAttribute NewsInfoDO newsInfoDO,HttpServletRequest request) {
			JsonResult result = new JsonResult();
			List<NewsInfoDO> newsInfoList = null;
			long total = 0;
			try {
				total = newsInfoService.selectNewsInfoCount(newsInfoDO);
				if (total <= pager.getOffset()) {
					pager.setPageNo(1L);
				}
				newsInfoDO.setPager(pager);
				newsInfoList = newsInfoService.selectNewsInfoList(newsInfoDO);
				pager.setTotal(total);
				result.setDataObject(newsInfoList);
				result.setPager(pager);
			} catch (Exception e) {
				log.error(this.getClass()+".getNewsInfoList() error:",e);
			}
			responseJson(result);
		}
		/**
		 * 新增资讯
		 * @author mb
		 * @param 
		 * @return
		 */
			@RequestMapping(value = "/addNewsInfo", method = RequestMethod.POST)
			@ResponseBody
			public void addNewsInfo(@ModelAttribute NewsInfoDO newsInfoDO,@RequestParam(value = "file", required = false) MultipartFile[] file,HttpServletRequest request) {
				JsonResult result = new JsonResult();
				try {
					BaseResultDTO resultDTO = newsInfoService.addNewsInfo(newsInfoDO,file,getCurrentLoginUser());
					if (resultDTO.isFailed()) {
		                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
					}
				} catch (Exception e) {
					log.error(this.getClass()+".addNewsInfo() error:",e);
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,e.getMessage()));
					return;
				}
				responseJson(result);
			}
			/**
			 * 编辑资讯
			 * @author mb
			 * @param 
			 * @return
			 */
				@RequestMapping(value = "/editNewsInfo", method = RequestMethod.POST)
				@ResponseBody
				public void editNewsInfo(@ModelAttribute NewsInfoDO newsInfoDO,@RequestParam(value = "file", required = false) MultipartFile[] file,HttpServletRequest request) {
					JsonResult result = new JsonResult();
					try {
						BaseResultDTO resultDTO =newsInfoService.updateNewsInfo(newsInfoDO,file,getCurrentLoginUser());
						if (resultDTO.isFailed()) {
			                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
						}
					} catch (Exception e) {
						log.error(this.getClass()+".editNewsInfo() error:",e);
						responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,e.getMessage()));
						return;
					}
					responseJson(result);
				}
		/**
		 * 删除资讯
		 * @author mb
		 * @param 
		 * @return
		 */
			@RequestMapping(value = "/deleteNewsInfo")
			@ResponseBody
			public void deleteNewsInfo(@ModelAttribute NewsInfoDO newsInfoDO,HttpServletRequest request) {
				JsonResult result = new JsonResult();
				try {
					if(!newsInfoService.deleteNewsInfo(newsInfoDO)){
						responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"删除资讯失败"));
						return;
					}
				} catch (Exception e) {
					log.error(this.getClass()+".deleteNewsInfo() error:",e);
				}
				responseJson(result);
			}
			/**
			 * 下架资讯
			 * @author mb
			 * @param 
			 * @return
			 */
				@RequestMapping(value = "/downNewsInfo")
				@ResponseBody
				public void downNewsInfo(@ModelAttribute NewsInfoDO newsInfoDO,HttpServletRequest request) {
					JsonResult result = new JsonResult();
					try {
						if(!newsInfoService.downNewsInfoById(newsInfoDO)){
							responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"下架资讯失败"));
							return;
						}
					} catch (Exception e) {
						log.error(this.getClass()+".deleteNewsInfo() error:",e);
					}
					responseJson(result);
				}
/**
 * 下架资讯
 * @author mb
 * @param 
 * @return
 */
	@RequestMapping(value = "/getNewsInfoById")
	@ResponseBody
	public void getNewsInfoById(@ModelAttribute NewsInfoDO newsInfoDO,HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			newsInfoDO = newsInfoService.getNewsInfoById(newsInfoDO);
			result.setDataObject(newsInfoDO);
		} catch (Exception e) {
			log.error(this.getClass()+".getNewsInfoById() error:",e);
		}
		responseJson(result);
	}
		
}
