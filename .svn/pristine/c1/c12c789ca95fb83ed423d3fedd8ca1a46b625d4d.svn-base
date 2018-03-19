package com.wanma.ims.controller.fin;

import com.wanma.ims.common.domain.FinBillAccountDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.FinBillAccountService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 账单科目
 */
@RequestMapping("/manage/fin")
@Controller
public class FinBillAccountController extends BaseController{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FinBillAccountService finBillAccountService;
	
	/**
	* <p>Description: 获取账单科目列表</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/getFinBillAccount")
	@ResponseBody
	public void getFinBillAccountList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute FinBillAccountDO finBillAccout, Model model,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		Long total = finBillAccountService.getFinBillAccountCount(finBillAccout);
		if (total <= pager.getOffset()) {
			pager.setPageNo(1L);
		}
		pager.setTotal(total);
		finBillAccout.setPager(pager);
		
		List<FinBillAccountDO> finBillAccountList = finBillAccountService.getFinBillAccountList(finBillAccout);
		if (finBillAccountList == null) {
			finBillAccountList = new ArrayList<FinBillAccountDO>();
		}
		batchResult.setDataObject(finBillAccountList);
		batchResult.setPager(pager);
		responseJson(batchResult);
	}
	
	/**
	* <p>Description: 新增账单科目</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/addFinBillAccount")
	@ResponseBody
	public void addFinBillAccount(@ModelAttribute FinBillAccountDO finBillAccout, Model model, HttpServletRequest request) {
		if (finBillAccout == null || StringUtils.isEmpty(finBillAccout.getBillAccountName())) {
			log.error(this.getClass() + ".addFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_BILL_ACCOUNT_NAME);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_BILL_ACCOUNT_NAME));
			return;
		}
		
		//取当前登录用户
		UserDO user = getCurrentLoginUser();
		if (user == null) {
			log.error(this.getClass() + ".addFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER));
			return;
		}
		
		int result = 0;
		try {
			result = finBillAccountService.addFinBillAccount(finBillAccout, user);
		} catch (Exception e) {
			log.error(this.getClass() + ".addFinBillAccount() error : ", e);
		}
		if (result == 0) {
			log.error(this.getClass() + ".addFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_ERROR_ADD);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_ERROR_ADD));
			return;
		}else {			
			responseJson(new JsonResult());
		}
	}
	
	/**
	* <p>Description: 修改账单科目</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/modifyFinBillAccount")
	@ResponseBody
	public void modifyFinBillAccount(@ModelAttribute FinBillAccountDO finBillAccout, Model model, HttpServletRequest request) {
		if (finBillAccout == null || StringUtils.isEmpty(finBillAccout.getBillAccountName())) {
			log.error(this.getClass() + ".modifyFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_BILL_ACCOUNT_NAME);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_BILL_ACCOUNT_NAME));
			return;
		}
		
		//取当前登录用户
		UserDO user = getCurrentLoginUser();
		if (user == null) {
			log.error(this.getClass() + ".modifyFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER);
			responseJson( new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_SESSION_USER));
			return;
		}
		
		int result = 0;
		try {
			result = finBillAccountService.modifyFinBillAccount(finBillAccout, user);
		} catch (Exception e) {
			log.error(this.getClass() + ".modifyFinBillAccount() error : ", e);
		}
		if (result == 0) {
			log.error(this.getClass() + ".modifyFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_ERROR_MODIFY);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_ERROR_MODIFY));
			return;
		}else if (result == 2) {
			log.error(this.getClass() + ".modifyFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_EMPTY_MODIFY_BILL_ACCOUNT);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_EMPTY_MODIFY_BILL_ACCOUNT));
			return;
		}else {
			responseJson(new JsonResult());
		}
	}
	
	/**
	* <p>Description: 删除账单科目</p>
	* @param
	* @author bingo
	* @date 2017-6-15
	 */
	@RequestMapping(value = "/removeFinBillAccount")
	@ResponseBody
	public void removeFinBillAccount(@ModelAttribute FinBillAccountDO finBillAccout, Model model, HttpServletRequest request) {
		int result = 0;
		try {
			result = finBillAccountService.removeFinBillAccount(finBillAccout);
		} catch (Exception e) {
			log.error(this.getClass() + ".removeFinBillAccount() error : ", e);
		}
		
		if (result == 0) {
			log.error(this.getClass() + ".removeFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_ERROR_REMOVE);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_ERROR_REMOVE));
			return;
		}else if (result == 2) {
			log.error(this.getClass() + ".removeFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_REMOVE_BILL_ACCOUNT_CONFIG_RELA);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_REMOVE_BILL_ACCOUNT_CONFIG_RELA));
			return;
		}else if (result == 3) {
			log.error(this.getClass() + ".removeFinBillAccount() error : " + ResultCodeConstants.ERROR_MSG_REMOVE_BILL_ACCOUNT_RELATION);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_REMOVE_BILL_ACCOUNT_RELATION));
			return;
		}else {
			responseJson(new JsonResult());
		}
	}
	
	/**
	* <p>Description: 获取账单科目下拉框</p>
	* @param
	* @author bingo
	* @date 2017-8-22
	 */
	@RequestMapping(value = "/getFinBillAccountComboBox")
	@ResponseBody
	public void getFinBillAccountComboBox(Model model,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		
		List<FinBillAccountDO> finBillAccountList = finBillAccountService.getFinBillAccountList(null);
		if (finBillAccountList == null) {
			finBillAccountList = new ArrayList<FinBillAccountDO>();
		}
		
		batchResult.setDataObject(finBillAccountList);
		responseJson(batchResult);
	}
}
