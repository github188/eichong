package com.wanma.ims.controller.fav;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wanma.ims.common.domain.ActivityDO;
import com.wanma.ims.common.domain.FavCouponDO;
import com.wanma.ims.common.domain.FavCouponVarietyDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.ActivityService;
import com.wanma.ims.service.FavCouponService;
import com.wanma.ims.service.FavCouponVarietyService;
import com.wanma.ims.util.ErrorHtmlUtil;
import com.wanma.ims.util.ExcelExporterUtil;

/**
 * 优惠券管理
 */
@RequestMapping("/manage/fav")
@Controller
public class FavCouponController extends BaseController{
	/** 日志文件生成器 */
	private final Logger LOGGER =  LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	private FavCouponService couponService;
	@Autowired
	private FavCouponVarietyService couponVarietyService;
	@Autowired
	private ActivityService activityService;
	/**
	 * 用户主页-获取用户的优惠券
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCouponInfoByUserId")
	@ResponseBody
	public void getCouponInfoByUserId(@ModelAttribute FavCouponDO coupon,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		Map<String, Object> resultMap= new HashMap<String,Object>() ;
		try {
			int availableCoupon = couponService.getUserAvailableCoupon(coupon);//已使用
			String totalDiscountAmount = couponService.getUserDiscountAmount(coupon);//优惠金额
			resultMap.put("availableCoupon", availableCoupon); 
			resultMap.put("totalDiscountAmount", totalDiscountAmount); 
			batchResult.setDataObject(resultMap);
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getCouponInfoByUserId() error:",e);
		}
		responseJson(batchResult); 
	}
	/**
	 * 获取现金券品种下拉栏
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCouponVarietyForList")
	@ResponseBody
	public void getCouponVarietyForList(@ModelAttribute FavCouponVarietyDO couponVariety,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		try {
			List<FavCouponVarietyDO> couponVarietyList = couponVarietyService.getCouponVarietyForList(couponVariety);
			batchResult.setDataObject(couponVarietyList);
			responseJson(batchResult); 
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getCouponList() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取现金券品种下拉栏失败"));
			return;
		}
	}
	
	/**
	 * 获取活动下拉栏
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getActivityForList")
	@ResponseBody
	public void getActivityForList(@ModelAttribute FavCouponDO coupon,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		ActivityDO activity = new ActivityDO();
		try {
			if(!StringUtils.isEmpty(coupon.getCpActivitytype())){
				activity.setActType(Integer.parseInt(coupon.getCpActivitytype()));
			}
			List<ActivityDO> activityList = activityService.getActivityForList(activity);
			batchResult.setDataObject(activityList);
			responseJson(batchResult); 
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getActivityForList() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取活动下拉栏失败"));
			return;
		}
	}
	/**
	 * 获取优惠券明细列表
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getCouponList")
	@ResponseBody
	public void getCouponList(@ModelAttribute("pager") Pager pager,@ModelAttribute FavCouponDO coupon,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		List<FavCouponDO> couponList = null;
		long total = 0 ;
		try {
			coupon.setCurrentDate(new Date());
			total = couponService.getCouponCount(coupon);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			coupon.setPager(pager);
			couponList= couponService.getCouponList(coupon);
			pager.setTotal(total);
			batchResult.setDataObject(couponList);
			batchResult.setPager(pager);
			responseJson(batchResult); 
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getCouponList() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取优惠券明细列表失败"));
			return;
		}
	}
	/**
	 * 用户优惠券管理
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getUserCouponList")
	@ResponseBody
	public void getUserCouponList(@ModelAttribute("pager") Pager pager,@ModelAttribute FavCouponDO coupon,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		List<FavCouponDO> couponList = null;
		long total = 0 ;
		try {
			coupon.setCurrentDate(new Date());
			total = couponService.getUserCouponCount(coupon);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			coupon.setPager(pager);
			couponList= couponService.getUserCouponList(coupon);
			pager.setTotal(total);
			//查到后修改优惠券的状态
			Date currentTime = new Date();// 获取当前时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
			String nowtime = formatter.format(currentTime);
			for(FavCouponDO uc: couponList){
				if(uc.getCpEnddate().compareTo(nowtime)<0&&uc.getCpStatus()!=1){//已过期
					uc.setCpStatus((short)4);
				}else if(uc.getCpStatus()==1){//已使用
					uc.setCpStatus((short)3);
				}else if(uc.getCpStatus()!=1&& uc.getCpUserid()!=0&&uc.getCpEnddate().compareTo(nowtime)>0){//已兑换
					uc.setCpStatus((short)2);
				}else if(uc.getCpStatus()!=1&& uc.getCpUserid()==0){//未兑换
					uc.setCpStatus((short)1);
				}
			}
			batchResult.setDataObject(couponList);
			batchResult.setPager(pager);
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getCouponList() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取用户优惠券管理列表失败"));
			return;
		}
		responseJson(batchResult); 
	}
	/**
	 * 删除优惠券
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/removeCoupon")
	@ResponseBody
	public void removeCoupon(@RequestParam("ids") String ids,HttpServletRequest request){
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		try {
			String[] idArray = ids.split(",");
			for(String id : idArray){
				int i = couponService.getCouponStatus(id);
				if(i == 1){
					responseJson(new JsonResult(false,ResultCodeConstants.FAILED,"已使用的现金券不能删除"));
					return;
				}
			}
			for(String id : idArray){//不错执行删除操作
				couponService.deleteCoupon(id);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getCouponList() error:",e);
			responseJson(new JsonResult(false,ResultCodeConstants.EXCEPTION,"获取优惠券明细列表失败"));
			return;
		}
		responseJson(result); 
	}
	/**
     * 导出优惠券明细
     */
    @RequestMapping(value = "/exportCoupon")
    @ResponseBody
    public void exportCoupon(HttpServletResponse response, FavCouponDO searchModel) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();
        try {
        	searchModel.setCurrentDate(new Date());
        	List<FavCouponDO> couponList = couponService.getExportCouponList(searchModel);
        	Long total = couponService.getCouponCount(searchModel);
        	if (total > DownFileConstants.MAX_SIZE) {
                ErrorHtmlUtil.createErrorPage(response, ResultCodeConstants.ERROR_MSG_EXPORT_MAX_DATA);
                return;
            }
        	if (CollectionUtils.isEmpty(couponList)) {
        		  ErrorHtmlUtil.createErrorPage(response, ResultCodeConstants.ERROR_MSG_EXPORT_DATA_EMPTY);
                  return;
   	        }
   	        List<String> attrList = Lists.newArrayList("covaActivityname", "couponCityScope", "chCpstates", "cpCreatedate", "cpUpdatedate", "cpBegindate","cpEnddate","cpCouponcode","userPhone","actActivityname");
   	        List<String> header = Lists.newArrayList("现金券品种", "范围", "状态", "获取时间", "使用时间", "生效时间","到期时间","优惠码","用户手机号","活动名称");
   	        ExcelExporterUtil.exportExcel(response, attrList, header, couponList, FavCouponDO.class, DownFileConstants.FILE_COUPON_EXPORT, DownFileConstants.FILE_COUPON_EXPORT_SHEET);
        } catch (Exception e) {
        	LOGGER.error(this.getClass()+".exportCoupon() error:",e);
        	ErrorHtmlUtil.createErrorPage(response, "导出电桩信息失败！");
        }
    }
	
	
	
	
	
}
