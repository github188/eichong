package com.wanma.ims.controller.electric;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.ConcentratorDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.ResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.controller.vo.ConcentratorVO;
import com.wanma.ims.service.CompanyService;
import com.wanma.ims.service.ConcentratorService;

/**
 * 电桩管理-集中器管理 
 * @author maoben
 */
@RequestMapping("/manage/electric")
@Controller
public class ConcentratorController extends BaseController{
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ConcentratorController.class);
	@Autowired
	private ConcentratorService concentratorService;
	@Autowired
	private CompanyService companyService;
	/**
	 * 集中器列表
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getConcentratorList")
	@ResponseBody
	public void getConcentratorList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute ConcentratorDO concentrator, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		List<ConcentratorDO> concentratorList = null;
		long total = 0;
		try {
			total = concentratorService.getConcentratorCount(concentrator);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			concentrator.setPager(pager);
			concentratorList = concentratorService.getConcentratorList(concentrator);
			List<ConcentratorVO> VOList = new ArrayList<ConcentratorVO>();
			for (ConcentratorDO concentratorDO : concentratorList) {
				VOList.add(new ConcentratorVO(concentratorDO));
			}
			pager.setTotal(total);
			result.setDataObject(VOList);
			result.setPager(pager);
		} catch (Exception e) {
			LOGGER.error("ConcentratorController-getConcentratorList is error",e);
		}
		responseJson(result);
		
	}
	/**
	 * 详情页，编辑页 根据集中器主键获取基本信息
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getConcentratorInfoById")
	@ResponseBody
	public void getConcentratorInfoById(@ModelAttribute ConcentratorDO concentrator, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			concentrator = concentratorService.getconcentratorInfo(concentrator);
			ConcentratorVO concentratorVO = new ConcentratorVO(concentrator);
			//获取绑定的电桩列表
			result.setDataObject(concentratorVO);
		} catch (Exception e) {
			LOGGER.error("ConcentratorController-getConcentratorInfoById is error",e);
		}
		responseJson(result);
	}
	/**
	 * 编辑集中器
	 */
	@RequestMapping(value = "/modifyConcentrator")
	@ResponseBody
	public void modifyConcentrator(@ModelAttribute ConcentratorDO concentrator,String ids, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			concentratorService.modifyConcentrator(concentrator,ids,getCurrentLoginUser(),request);
		} catch (Exception e) {
			LOGGER.error("ConcentratorController-modifyConcentrator is error",e);
			responseJson(new JsonException("编辑集中器失败！"));
		}
		responseJson(result);
	}
	/**
	 * 新增加集中器
	 */
	@RequestMapping(value = "/addConcentrator")
	@ResponseBody
	public void addConcentrator(@ModelAttribute ConcentratorDO concentrator, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		 UserDO loginUser = getCurrentLoginUser();
		 concentrator.setCoctUserId(loginUser.getUserId());
		 concentrator.setModifier(loginUser.getUserAccount());
		 concentrator.setState(1);
		try {
			if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_SUPER||loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_ADMIN){
				concentrator.setCreator("爱充网");
			}else{
				 CompanyDO companyDO = new CompanyDO();
				 companyDO.setCpyId(loginUser.getCpyId());
				 companyDO = companyService.getCompanyByCpyInfo(companyDO);
				 concentrator.setCreator(companyDO.getCpyName());
			}
			if(!concentratorService.addConcentrator(concentrator)){
				responseJson(new JsonResult(false,ResultCodeConstants.ERROR_MSG_ADD_CONCENTRATOR,"新增集中器失败！"));
				return;
			}
		} catch (Exception e) {
			LOGGER.error("ConcentratorController-addConcentrator is error",e);
			responseJson(new JsonException(ResultCodeConstants.ERROR_MSG_ADD_CONCENTRATOR));
		}
		responseJson(result);
	}
	/**
	 * 删除集中器
	 */
	@RequestMapping(value = "/removeConcentrator")
	@ResponseBody
	public void removeConcentrator(@ModelAttribute ConcentratorDO concentrator, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			concentrator = concentratorService.getconcentratorInfo(concentrator);
			if(concentrator.getState()==1){
				responseJson(new JsonResult(false,ResultCodeConstants.ERROR_MSG_REMOVE_CONCENTRATOR,"上线的集中器不能删除！"));
				return;
			}
			  ResultDTO<String> resultDTO =concentratorService.removeConcentrator(concentrator,request,getCurrentLoginUser());
			  if (resultDTO.isFailed()) {
	                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
	            } else {
	                result.setDataObject(resultDTO.getModule());
	           }
		} catch (Exception e) {
			LOGGER.error("ConcentratorController-removeConcentrator is error",e);
			responseJson(new JsonException("删除集中器失败！"));
		}
		responseJson(result);
	}

}
