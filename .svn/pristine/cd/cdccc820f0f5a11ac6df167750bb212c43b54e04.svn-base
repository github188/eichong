package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.CouponService;
import com.wanma.app.service.impl.AppCarGarageServiceImpl;
import com.wanma.app.util.DateUtil;

@Controller
@RequestMapping("/app/coupon")
public class CouponController {
	private static Log log = LogFactory.getLog(CouponController.class);
	
	/**
	 * 获取个人的优惠券列表
	 * 		uId 用户id，type 查询类型 1可用2已使用3过期，currentPage 当前页码从1开始，pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String getCouponList(@RequestParam Map<String, Object> params,AppPager pager){
		if(StringUtils.isEmpty(params.get("uId")) || StringUtils.isEmpty(params.get("type"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		params.put("time", DateUtil.addDay(DateUtil.getDate(), -30));
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			list = couponService.getCouponList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(list).toString();
	}
	/**
	 * 获取个人的优惠券数量
	 * 		uId 用户id，type 查询类型 1可用2已使用3过期，
	 * @return
	 */
	@RequestMapping("/count")
	@ResponseBody
	public String getCouponCount(@RequestParam Map<String, Object> params){
		if(StringUtils.isEmpty(params.get("uId")) ){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		params.put("time", DateUtil.addDay(DateUtil.getDate(), -30));
		Map<String, Object> countList = new HashMap<String,Object>();
		try{
			countList = couponService.getCouponListCount(params);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(countList).toString();
	}
	
	/**
	 * 兑换优惠券
	 * 		uId 用户id，code 兑换码
	 * @return
	 */
	@RequestMapping("/exchange")
	@ResponseBody
	public String code2Voucher(@RequestParam Map<String, Object> params){
		if(StringUtils.isEmpty(params.get("uId")) || StringUtils.isEmpty(params.get("code"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		String code = params.get("code").toString();
		if(code.length() != 5 || code.indexOf("o") > 0 || code.indexOf("i") > 0 || code.indexOf("l") > 0){
			return new AccessErrorResult(1000, "兑换码无效，无法完成代金券兑换").toString();
		}
		
		try{
			boolean boo = couponService.codeIsExist(code);
			if(!boo) return new AccessErrorResult(1000, "兑换码不存在，无法兑换代金券").toString();
			
			//检验兑换时间是否在活动期内
			Map<String, Object> map = couponService.getActiTimeByCode(code);
			Date today = DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDate());
			Date bigin = DateUtil.convertStringToDate("yyyy-MM-dd", map.get("act_BeginDate").toString());
			Date end = DateUtil.convertStringToDate("yyyy-MM-dd", map.get("act_EndDate").toString());
			if(today.before(bigin) || today.after(end)){
				return new AccessErrorResult(1000, "兑换码不在兑换期内，不可用来兑换代金券").toString();
			}
			//券品种下架的话，也不可以兑换
			if("1".equals(map.get("cova_Stutas").toString())){
				return new AccessErrorResult(1000, "该活动已下架，无法完成代金券兑换").toString();
			}
			
			//检验兑换的用户身份合法性
			int status = userService.getStatusFromUserTable(Integer.parseInt(params.get("uId").toString()));
			if(status != 1){
				return new AccessErrorResult(1000, "该账户已被冻结，无法兑换代金券").toString();
			}
			
			//获取用户的总有效优惠码数
			int cpNum = couponService.getValidCPByUid(Integer.parseInt(params.get("uId").toString()));
			if(cpNum >= 1000){
				return new AccessErrorResult(1000,"超过数量限制（1000），暂时无法兑换").toString();
			}
			
			//检查该优惠券有效天数和是否绑定用户
			map.clear();
			map = couponService.getValidDaysByCode(code);
			Long boundUId = Long.parseLong(map.get("cp_UserId").toString());
			if(boundUId > 0){
				return new AccessErrorResult(1000,"代金券已被兑换，不可重复兑换").toString();
			}
			
			params.put("beginDate", today);
			params.put("endDate", map.get("act_CouponEndDate").toString());//劵的结束日期为该劵的活动的劵到期时间
			couponService.code2Voucher(params);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new AccessErrorResult(1000,"兑换代金券失败").toString();
		}
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 兑换优惠券
	 * @param uId 用户id，actRule 活动规则
	 * @return
	 */
	@RequestMapping("/generateCode")
	@ResponseBody
	public String generateCode(@RequestParam Map<String, Object> params){
		String uId=params.get("uId").toString();
		int actRule=(Integer)params.get("actRule");
		couponService.generateCode(uId,actRule);
		return new AccessSuccessResult().toString();
	}	
	
	@Autowired
	CouponService couponService;
	@Autowired
	AppUserinfoService userService;
}
