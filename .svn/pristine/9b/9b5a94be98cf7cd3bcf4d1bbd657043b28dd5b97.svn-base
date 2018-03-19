package com.wanma.ims.controller.fin;

import com.wanma.ims.common.domain.FinAccountRelationDO;
import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.UserNormalDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.FinAccountRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户关系
 */
@RequestMapping("/manage/fin")
@Controller
public class FinAccountRelationController extends BaseController{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FinAccountRelationService finAccountRelationService;
	
	/**
	* <p>Description: 获取公司首页中账务关系数据</p>
	* @param
	* @author bingo
	* @date 2017-7-10
	 */
	@RequestMapping(value = "/getFinAccountRelation4Cpy")
	@ResponseBody
	public void getFinAccountRelation4Cpy(@RequestParam("accountId") String accountId, Model model,HttpServletRequest request) {
		JsonResult batchResult = new JsonResult();
		
		if (accountId == null || "".equals(accountId)) {
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "资金账户信息不允许为空！"));
			return;
		}
		
		List<FinAccountRelationDO> finAccountRelationDOList = finAccountRelationService.getFinAccountRelation4Cpy(accountId);
		if (finAccountRelationDOList == null) {
			finAccountRelationDOList = new ArrayList<FinAccountRelationDO>();
		}
		batchResult.setDataObject(finAccountRelationDOList);
		
		responseJson(batchResult);
	}
	
	/**
	* <p>Description: 获取用户首页中账户关系数据</p>
	* @param
	* @author bingo
	* @date 2017-6-29
	 */
	@RequestMapping(value = "/getFinAccountRelation4User")
	@ResponseBody
	public void getFinAccountRelation4User(@ModelAttribute UserNormalDO userNormal, Model model,HttpServletRequest request) {
		JsonResult batchResult = new JsonResult();
		
		if (userNormal == null || userNormal.getUserId() == null) {
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "用户信息不允许为空！"));
			return;
		}
		
		List<FinAccountRelationDO> finAccountRelationDOList = finAccountRelationService.getFinAccountRelation4User(userNormal);
		if (finAccountRelationDOList == null) {
			finAccountRelationDOList = new ArrayList<FinAccountRelationDO>();
		}
		batchResult.setDataObject(finAccountRelationDOList);
		
		responseJson(batchResult);
	}
	
	/**
	* <p>Description: 获取卡首页中账户关系数据</p>
	* @param
	* @author bingo
	* @date 2017-6-29
	 */
	@RequestMapping(value = "/getFinAccountRelation4Card")
	@ResponseBody
	public void getFinAccountRelation4Card(@ModelAttribute UserCardDO userCard, Model model,HttpServletRequest request) {
		JsonResult batchResult = new JsonResult();
		
		if (userCard == null || userCard.getUcId() == null) {
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "卡信息不允许为空！"));
			return;
		}
		
		List<FinAccountRelationDO> finAccountRelationDOList = finAccountRelationService.getFinAccountRelation4Card(userCard);
		if (finAccountRelationDOList == null) {
			finAccountRelationDOList = new ArrayList<FinAccountRelationDO>();
		}
		batchResult.setDataObject(finAccountRelationDOList);
		
		responseJson(batchResult);
	}
	
	/**
	* <p>Description: 获取账户关系列表</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/getFinAccountRelation")
	@ResponseBody
	public void getFinAccountRelationList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute FinAccountRelationDO finAccountRelation, Model model,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		finAccountRelation.setIsDel(0);
		Long total = finAccountRelationService.getFinAccountRelationCount(finAccountRelation);
		if (total <= pager.getOffset()) {
			pager.setPageNo(1L);
		}
		pager.setTotal(total);
		finAccountRelation.setPager(pager);
		
		List<FinAccountRelationDO> finAccountRelationList = finAccountRelationService.getFinAccountRelationList(finAccountRelation);
		if (finAccountRelationList == null) {
			finAccountRelationList = new ArrayList<FinAccountRelationDO>();
		}
		batchResult.setDataObject(finAccountRelationList);
		batchResult.setPager(pager);
		
		responseJson(batchResult);
	}
	
	/**
	* <p>Description: 新增账户关系</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/addFinAccountRelation")
	@ResponseBody
	public void addFinAccountRelation(@ModelAttribute FinAccountRelationDO finAccountRelation, Model model, HttpServletRequest request) {
		//取当前登录用户
		UserDO user = getCurrentLoginUser();
		if (user == null) {
			log.error(this.getClass() + ".addFinAccountRelation() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER);

			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER));
			return;
		}
		
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		try {
			baseResultDTO  = finAccountRelationService.addFinAccountRelation(finAccountRelation, user);
		} catch (Exception e) {
			log.error(this.getClass() + ".addFinAccountRelation() error : ", e);
		}
		if(!baseResultDTO.isSuccess()){
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, baseResultDTO.getErrorDetail()));
		}else {			
			responseJson(new JsonResult());
		}
	}
	
	
	/**
	* <p>Description: 修改账户关系</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/modifyFinAccountRelation")
	@ResponseBody
	public void modifyFinAccountRelation(@ModelAttribute FinAccountRelationDO finAccountRelation, Model model, HttpServletRequest request) {
		//取当前登录用户
		UserDO user = getCurrentLoginUser();
		if (user == null) {
			log.error(this.getClass() + ".modifyFinAccountRelation() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER));
			return;
		}
		
		int result = 0;
		try {
			result = finAccountRelationService.modifyFinAccountRelation(finAccountRelation, user);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyFinAccountRelation() error : ", e);
		}
		if (result == 0) {
			log.error(this.getClass() + ".modifyFinAccountRelation() error : " + ResultCodeConstants.ERROR_MSG_ERROR_MODIFY);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_ERROR_MODIFY));
		}else if (result == 2) {
			log.error(this.getClass() + ".modifyFinAccountRelation() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_MODIFY_ACCOUNT_RELATION);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_MODIFY_ACCOUNT_RELATION));
		}else {
			responseJson(new JsonResult());
		}
	}
	
	
	/**
	* <p>Description: 删除账户关系</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/removeFinAccountRelation")
	@ResponseBody
	public void removeFinAccountRelation(@ModelAttribute FinAccountRelationDO finAccountRelation, Model model, HttpServletRequest request) {
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		try {
			baseResultDTO = finAccountRelationService.removeFinAccountRelation(finAccountRelation);
		} catch (Exception e) {
			log.error(this.getClass() + ".removeFinAccountRelation() error : ", e);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, e.toString()));
		}
		
		if(!baseResultDTO.isSuccess()){
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, baseResultDTO.getErrorDetail()));
		}else {
			responseJson(baseResultDTO);
		}
	}
}
