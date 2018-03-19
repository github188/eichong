package com.wanma.ims.controller.config.app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.wanma.ims.common.domain.BannerDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.BannerService;



/**
 * app首页banner轮播图
 */
@Controller
@RequestMapping("/manage/app/banner")
public class BannerController extends BaseController {
	// 日志输出对象
		private static Logger log = Logger.getLogger(BannerController.class);
		@Autowired
		private BannerService bannerService;
	/**
	 * banner列表
	 * @author mb
	 * @param 
	 * @return
	 */
		@RequestMapping(value = "/getBannerList")
		@ResponseBody
		public void getBannerList(@ModelAttribute("pager") Pager pager,
				@ModelAttribute BannerDO banner,HttpServletRequest request) {
			JsonResult result = new JsonResult();
			List<BannerDO> bannerList = null;
			long total = 0;
			try {
				total = bannerService.selectBannerCount(banner);
				if (total <= pager.getOffset()) {
					pager.setPageNo(1L);
				}
				banner.setPager(pager);
				bannerList = bannerService.selectBannerList(banner);
				pager.setTotal(total);
				result.setDataObject(bannerList);
				result.setPager(pager);
			} catch (Exception e) {
				log.error(this.getClass()+".getBannerList() error:",e);
			}
			responseJson(result);
			
		}
		/**
		 * 新增banner
		 * @author mb
		 * @param 
		 * @return
		 */
		@RequestMapping(value = "/addBanner")
		@ResponseBody
		public void addBanner(@ModelAttribute BannerDO banner,@RequestParam(value = "file", required = false) MultipartFile[] file,HttpServletRequest request){
			JsonResult result = new JsonResult();
			try {
				if(banner.getBannerProvinceCode().isEmpty()){
					banner.setBannerProvinceCode("");
					banner.setBannerCityCode("");
				}
				BaseResultDTO resultDTO = bannerService.insertBanner(banner,file,getCurrentLoginUser());
				if (resultDTO.isFailed()) {
		                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
		        }
			} catch (Exception e) {
				log.error(this.getClass()+".addBanner() error:",e);
				responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,e.getMessage()));
				return;
			}
			responseJson(result);	
		}
			
			/**
			 * 根据banner主键获取信息
			 * @author mb
			 * @param 
			 * @return
			 */
				@RequestMapping(value = "/getBannerById")
				@ResponseBody
				public void getBannerById(@ModelAttribute BannerDO banner,HttpServletRequest request){
					JsonResult result = new JsonResult();
					try {
						banner = bannerService.getBannerById(banner);
						result.setDataObject(banner);
					} catch (Exception e) {
						log.error(this.getClass()+".getBannerById() error:",e);
						responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取banner失败"));
						return;
					}
					responseJson(result);	
				}
			
			
		/**
		 * 编辑banner
		 * @author mb
		 * @param 
		 * @return
		 */
			@RequestMapping(value = "/editBanner", method = RequestMethod.POST)
			@ResponseBody
			public void editBanner(@ModelAttribute BannerDO banner,@RequestParam(value = "file", required = false) MultipartFile[] file,HttpServletRequest request){
				JsonResult result = new JsonResult();
				try {
					if(banner.getBannerProvinceCode().isEmpty()){
						banner.setBannerProvinceCode("");
						banner.setBannerCityCode("");
					}
					BannerDO oldBanner = bannerService.getBannerById(banner);
					banner.setBannerStatus(oldBanner.getBannerStatus());
					banner.setBannerSort(oldBanner.getBannerSort());
					if(oldBanner.getBannerStatus()==2){
						Calendar cal = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();
				        cal.setTime(new Date());
				        cal.set(Calendar.HOUR_OF_DAY, 0);
				        cal.set(Calendar.MINUTE, 0);
				        cal.set(Calendar.SECOND, 0);
				        cal.set(Calendar.MILLISECOND, 0);
				        cal.add(Calendar.DAY_OF_MONTH, 1);
				        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				        Date bannerEndTime =sdf.parse(banner.getBannerEndTime());
				        cal2.setTime(bannerEndTime);
				        if(cal2.after(cal)||cal2.equals(cal)){
				        	banner.setBannerStatus(0);
				        	banner.setBannerSort(99);
				        }
					}
					BaseResultDTO resultDTO = bannerService.updateBanner(banner,file,getCurrentLoginUser());
					if (resultDTO.isFailed()) {
		                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
					}
				} catch (Exception e) {
					log.error(this.getClass()+".addBanner() error:",e);
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,e.getMessage()));
					return;
				}
				responseJson(result);	
			}
			/**
			 * 下架banner
			 * @author mb
			 * @param 
			 * @return
			 */
				@RequestMapping(value = "/downBanner")
				@ResponseBody
				public void downBanner(@ModelAttribute BannerDO banner,HttpServletRequest request){
					JsonResult result = new JsonResult();
					try {
						banner = bannerService.getBannerById(banner);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						Date bannerBeginTime = sdf.parse(banner.getBannerBeginTime());
						Date bannerEndTime = sdf.parse(banner.getBannerEndTime());
						Date now = new Date();
						if(bannerEndTime.before(now)){//判断是否为已结束
							responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"已结束无法下架"));
							return;
						}else {
							banner.setBannerEndTime(sdf.format(now));//下架的时候把结束时间改变当前时间
							bannerService.offShelfBannerById(banner);
						}
					} catch (Exception e) {
						log.error(this.getClass()+".downBanner() error:",e);
						responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"下架banner失败"));
						return;
					}
					responseJson(result);	
				}		
			/**
			 *  删除banner
			 * @author mb
			 * @param 
			 * @return
			 */
			@RequestMapping(value = "/deleteBanner")
			@ResponseBody
			public void deleteBanner(@ModelAttribute BannerDO banner,HttpServletRequest request){
				JsonResult result = new JsonResult();
				try {
					if(!bannerService.deleteBannerById(banner)){
						responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"删除banner失败"));
						return;
					}
				} catch (Exception e) {
					log.error(this.getClass()+".deleteBanner() error:",e);
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"删除banner失败"));
					return;
				}
				responseJson(result);	
			}
			
			/**
			 * 修改轮播顺序
			 * @author mb
			 * @param 
			 * @return
			 */
			@RequestMapping(value = "/editBannerOrder")
			@ResponseBody
			public void editBannerOrder(String pkBannerId,String newBannerSort,HttpServletRequest request){
				JsonResult result = new JsonResult();
				try {
					BannerDO banner = new BannerDO();
					banner.setPkBannerId(Integer.parseInt(pkBannerId));
					banner.setBannerSort(Integer.parseInt(newBannerSort));
					if(!bannerService.changeBannerSort(banner)){
						responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"删除banner失败"));
						return;
					}
				} catch (Exception e) {
					log.error(this.getClass()+".deleteBanner() error:",e);
					responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"删除banner失败"));
					return;
				}
				responseJson(result);	
			}
}
