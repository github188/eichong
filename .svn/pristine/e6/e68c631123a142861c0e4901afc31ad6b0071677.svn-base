package com.wanma.ims.controller.config.app;

import java.util.Date;
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

import com.wanma.ims.common.domain.AdvertisementDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.AdvertisementService;



/**
 * 闪屏  插屏管理
 */
@Controller
@RequestMapping("/manage/app/advertisement")
public class AdvertisementController extends BaseController {
	// 日志输出对象
		private static Logger log = Logger.getLogger(AdvertisementController.class);
		@Autowired
		private AdvertisementService advertisementService;
	/**
	 * 闪屏列表
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAdvertisementList")
	@ResponseBody
	public void getSplashList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute AdvertisementDO advertisementDO,HttpServletRequest request) {
		JsonResult result = new JsonResult();
		List<AdvertisementDO> splashList = null;
		long total = 0;
		try {
			total = advertisementService.selectAdvertisementCount(advertisementDO);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			advertisementDO.setPager(pager);
			splashList = advertisementService.selectAdvertisementList(advertisementDO);
			pager.setTotal(total);
			result.setDataObject(splashList);
			result.setPager(pager);
		} catch (Exception e) {
			log.error(this.getClass()+".getSplashList() error:",e);
		}
		responseJson(result);
		
	}
	/**
	 * 新增闪屏 插屏
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addAdvertisement", method = RequestMethod.POST)
	@ResponseBody
	public void addAdvertisement(AdvertisementDO advertisementDO, 
			@RequestParam(value = "file", required = true) MultipartFile[] file,
			@RequestParam(value = "picListFile", required = false) MultipartFile[] picListFile,
			HttpServletRequest request){
		JsonResult result = new JsonResult();
		UserDO loginUser = getCurrentLoginUser();
		try {
			if(advertisementService.getLimitCount(advertisementDO)>0){
				if(advertisementDO.getAdType()==1){
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"该时间段已经存在一个闪屏图片，请更换起止时间"));
					return;
				}else {
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"该时间段该位置已经存在一个插屏图片，请更换起止时间"));
					return;
				}
			}
			advertisementDO.setFkUserId(loginUser.getUserId().intValue());
			BaseResultDTO resultDTO  = advertisementService.addAdvertisement(advertisementDO,file,picListFile,loginUser);
		    if (resultDTO.isFailed()) {
                result = new JsonResult(false, ResultCodeConstants.FAILED, resultDTO.getErrorDetail());
            }
			responseJson(result);
		} catch (Exception e) {
			log.error(this.getClass()+".addAdvertisement() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,e.getMessage()));
			return;
		}	
	}
	/**
	 * 根据主键获取插屏 闪屏
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getAdvertisementById")
	@ResponseBody
	public void getAdvertisementById(@ModelAttribute AdvertisementDO advertisementDO,HttpServletRequest request){
		JsonResult result = new JsonResult();
		try {
			advertisementDO = advertisementService.getAdvertisementById(advertisementDO);
			result.setDataObject(advertisementDO);
		} catch (Exception e) {
			log.error(this.getClass()+".addAppButton() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取失败"));
			return;
		}
		responseJson(result);	
	}
//	/**
//	 * 修改闪屏 插屏
//	 * @author mb
//	 * @param 
//	 * @return
//	 */
//	@RequestMapping(value = "/updateAdvertisement")
//	@ResponseBody
//	public void updateAdvertisement(@ModelAttribute AdvertisementDO advertisementDO,
//			@RequestParam(value = "file", required = true) MultipartFile[] file,
//			@RequestParam(value = "picListFile", required = false) MultipartFile[] picListFile,
//			HttpServletRequest request){
//		JsonResult result = new JsonResult();
//		UserDO loginUser = getCurrentLoginUser();
//		try {
//			if(advertisementService.getLimitCount(advertisementDO)>0){
//				if(advertisementDO.getAdType()==1){
//					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"该时间段已经存在一个闪屏图片，请更换起止时间"));
//					return;
//				}else {
//					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"该时间段已经存在一个插屏图片，请更换起止时间"));
//					return;
//				}
//			}
//			advertisementDO.setAdStatus(0);
//			advertisementDO.setFkUserId(loginUser.getUserId().intValue());
//			ResultDTO<String> resultDTO = advertisementService.updateAdvertisement(advertisementDO,file,picListFile,loginUser);
//			if (resultDTO.isFailed()) {
//                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
//            } else {
//                result.setDataObject(resultDTO.getModule());
//            }
//		} catch (Exception e) {
//			log.error(this.getClass()+".updateAdvertisement() error:",e);
//			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"修改失败"));
//			return;
//		}
//		responseJson(result);	
//	}
/**
 * 下架
 * @author mb
 * @param 
 * @return
 */
	@RequestMapping(value = "/downAdvertisement")
	@ResponseBody
	public void downAdvertisement(@ModelAttribute AdvertisementDO advertisementDO,HttpServletRequest request){
		JsonResult result = new JsonResult();
		UserDO loginUser = getCurrentLoginUser();
		try {
			advertisementDO.setEndAdTimeDate(new Date());
			advertisementDO.setAdStatus(1);
			advertisementDO.setFkUserId(loginUser.getUserId().intValue());
			if(!advertisementService.downAdvertisement(advertisementDO)){
				responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"下架失败"));
				return;
			}
		} catch (Exception e) {
			log.error(this.getClass()+".downAdvertisement() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"修改失败"));
			return;
		}
		responseJson(result);	
	}
}
