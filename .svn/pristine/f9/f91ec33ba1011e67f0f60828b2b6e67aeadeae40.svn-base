package com.wanma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.dao.CmsCommitLogMapper;
import com.wanma.model.TblUserCard;
import com.wanma.service.ChargeCardService;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.impl.RedisService;

@Controller
@RequestMapping("/admin/userCard")
public class CmsUserCardController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsUserCardController.class);

	@Autowired
	private ChargeCardService cardService;
	@Autowired
	private TblUserService userService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private CmsCommitLogMapper commitLogDao;
	@Autowired
	private CmsCommitLogService commitLogService;

	/**
	 * 取得充电卡列表处理
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
	@RequestMapping(value = "/userCardList")
	public String cardUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblUserCard userCard, Model model,
			HttpServletRequest request) {
		List<TblUserCard> userCardList = null;
		long total = 0;
		try {
			total = cardService.getCardCount(userCard);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			userCard.setPager(pager);
			userCardList = cardService.getCardUserList(userCard);
		} catch (Exception e) {
			log.error("获取充电卡列表失败...");
			log.error(e.getMessage());
			return "backstage/userCardInfo/listCardInfo";
		}
		model.addAttribute("userCard", userCard);
		model.addAttribute("userCardList", userCardList);
		model.addAttribute("pager", pager);
		return "backstage/userCardInfo/listCardInfo";
	}

	/***
	 * 绑定卡号初始化界面
	 * 
	 * @param model
	 * @param concentrator
	 * @return
	 */
	@RequestMapping(value = "/bindCardUi")
	public String bindCardUi(Model model, TblUserCard card) {
		model.addAttribute("card", card);
		return "backstage/userCardInfo/bindCardInfo";
	}
	/**
	 * 绑定卡号处理
	 */
	@RequestMapping("/bindCard")
	@ResponseBody
	public String bindCard(TblUserCard userCard,HttpServletRequest request) throws IOException {
		DwzAjaxResult dwzResult;
		String ucUserId=request.getParameter("org4.id");
		userCard.setUcUserId(ucUserId);
		try {
			cardService.bindCard(userCard);
			dwzResult = new DwzAjaxResult("200", "绑定成功", "userCardList",
					"closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "", "", "");

		}
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 挂失
	 */
	@RequestMapping("/cardLoss")
	@ResponseBody
	public String cardLoss(TblUserCard userCard,HttpServletRequest request) throws IOException {
		DwzAjaxResult dwzResult;
		try {
//			if(!validAuthCode(userCard))
//				return new JsonObject(new DwzAjaxResult("300", "手机验证码错误!", "", "", "")).toString();
			TblUserCard tempCard=cardService.getUsercard(userCard);
			if(tempCard.getUcStatus()==1){
				return new DwzAjaxResult("300", "该充电卡已挂失", "userCardList", "closeCurrent", "").toJSONString();
			}
			userCard.setUcStatus(1);
			cardService.updateUserard(userCard);
			commitLogService.insert("充电卡挂失，卡号："+tempCard.getUcExternalCardNumber());
			dwzResult = new DwzAjaxResult("200", "操作成功", "userCardList","", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "操作失败:系统错误", "userCardList", "", "");

		}
		return new JsonObject(dwzResult).toString();
	}
	
//	/**
//	 * 取消挂失
//	 */
//	@RequestMapping("/cardFind")
//	@ResponseBody
//	public String cardFind(TblUserCard userCard,HttpServletRequest request) throws IOException {
//		DwzAjaxResult dwzResult;
//		try {
//			if(!validAuthCode(userCard))
//				return new JsonObject(new DwzAjaxResult("300", "手机验证码错误!", "", "", "")).toString();
//			TblUserCard tempCard=cardService.getUsercard(userCard);
//			if(tempCard.getUcStatus()==0){
//				return new DwzAjaxResult("300", "该充电卡是正常的", "userCardList", "closeCurrent", "").toJSONString();
//			}
//			userCard.setUcStatus(0);
//			cardService.updateUserard(userCard);
//			dwzResult = new DwzAjaxResult("200", "操作成功", "userCardList","closeCurrent", "");
//		} catch (Exception e) {
//			log.error(e.getLocalizedMessage());
//			dwzResult = new DwzAjaxResult("300", "操作失败:系统错误", "userCardList", "", "");
//
//		}
//		return new JsonObject(dwzResult).toString();
//	}
//
//	/***
//	 * 验证码输入界面
//	 * 
//	 * @param model
//	 * @param concentrator
//	 * @return
//	 */
//	@RequestMapping(value = "/inputAuthCode")
//	public String inputAuthCode(Model model, TblUserCard card) {
//		model.addAttribute("card", card);
//		return "backstage/userCardInfo/inputAuthCode";
//	}
//	
//	/**
//	 * 校验手机验证码
//	 * @param userCard
//	 * @return
//	 */
//	private boolean validAuthCode(TblUserCard userCard){
//		String msgCode = userCard.getAuthCode();
//		String phone = userCard.getUserAccount();
//		String[] authCodeArr = getRedisAuthCode(phone).split(":");
//		String redisAuthCode = authCodeArr[1];
//		return msgCode.equals(redisAuthCode);
//	}
//
//	private String getRedisAuthCode(String userAccount) {
//		String currentDate = DateUtil.currentYourDate("yyyy-MM-dd");
//		String str = redisService.strGet("app:authcode:" + userAccount + ":"
//				+ currentDate);
//		return StringUtils.isNotBlank(str) ? str : "0:000000:"
//				+ (System.currentTimeMillis() - 60001);
//	}
}
