package com.wanma.ims.controller.config;

import com.wanma.ims.common.domain.CarVinRelaDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.UserVinRelaDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.CarVinRelaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * VIN码鉴权表
 */
@RequestMapping("/manage/config")
@Controller
public class CarVinRelaController extends BaseController{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CarVinRelaService carVinRelaService;
	
	/**
	* <p>Description: 获取VIN码鉴权列表</p>
	* @param
	* @author bingo
	* @date 2017-7-5
	 */
	@RequestMapping(value = "/getCarVinRela")
	@ResponseBody
	public void getCarVinRelaList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute CarVinRelaDO carVinRela, Model model,HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		Long total = 0L;
		try {
			total = carVinRelaService.getCarVinRelaCount(carVinRela);
		} catch (Exception e) {
			responseJson(e);
		}
		if (total <= pager.getOffset()) {
			pager.setPageNo(1L);
		}
		pager.setTotal(total);
		carVinRela.setPager(pager);
		
		List<CarVinRelaDO> carVinRelaList = carVinRelaService.getCarVinRelaList(carVinRela);
		if (carVinRelaList == null) {
			carVinRelaList = new ArrayList<CarVinRelaDO>();
		}
		batchResult.setDataObject(carVinRelaList);
		batchResult.setPager(pager);
		
		responseJson(batchResult);
	}
	
	/**
	* <p>Description: 新增VIN码鉴权</p>
	* @param
	* @author bingo
	* @date 2017-7-5
	 */
	@RequestMapping(value = "/addCarVinRela")
	@ResponseBody
	public void addCarVinRela(@ModelAttribute CarVinRelaDO carVinRelaDO, Model model, HttpServletRequest request) {
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		try {
			baseResultDTO = carVinRelaService.addCarVinRela(carVinRelaDO);
		} catch (Exception e) {
			log.error(this.getClass() + ".addCarVinRela() error : ", e);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, e.toString()));
		}
		
		if(!baseResultDTO.isSuccess()){
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, baseResultDTO.getErrorDetail()));
		}else {
			responseJson(new JsonResult());
		}
	}
	
	/**
	* <p>Description: 删除VIN码鉴权</p>
	* @param
	* @author bingo
	* @date 2017-7-5
	 */
	@RequestMapping(value = "/removeCarVinRela")
	@ResponseBody
	public void removeCarVinRela(@ModelAttribute CarVinRelaDO carVinRela, Model model, HttpServletRequest request) {
		int result = 0;
		try {
			result = carVinRelaService.removeCarVinRela(carVinRela);
		} catch (Exception e) {
			log.error(this.getClass() + ".removeCarVinRela() error : ", e);
		}
		
		if (result == 0) {
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_ERROR_REMOVE));
		}else {
			responseJson(new JsonResult());
		}
	}
	
    /**
     * 导入VIN码
     */
    @RequestMapping(value = "/importCarVinRela")
    @ResponseBody
    public void importCarVinRela(@ModelAttribute CarVinRelaDO carVinRelaDO, @RequestParam(value = "file", required = false) MultipartFile file) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();
        
        try {
            BaseResultDTO resultDTO = carVinRelaService.importCarVinRela(file, carVinRelaDO, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            responseJson(new JsonException("导入VIN码失败！"));
        }
    }

	/**
	 * 用户主页 卡主页 绑定vin码
	 */
	@RequestMapping(value = "/bindVinCodeForUser")
	@ResponseBody
	public void bandVinCodeForUser(@ModelAttribute UserVinRelaDO userVinRelaDO,String vinIds){
		JsonResult result = new JsonResult();
		try {
			BaseResultDTO resultDTO  = carVinRelaService.bindVinCodeForUser(userVinRelaDO, vinIds);
			if (resultDTO.isFailed()) {
				result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
			}
			responseJson(result);
		}catch(Exception e) {
			LOGGER.error("CarVinRelaController-bandVinCodeForUser is error|delIds={}|loginUser={}", vinIds, userVinRelaDO, e);
			responseJson(new JsonException("绑定vin码失败！"));
		}
	}

	/**
	 * 用户主页 卡主页 vin码信息
	 */
	@RequestMapping(value = "/getVinInfoByUser")
	@ResponseBody
	public void getVinInfoByUser(Long userId){
		JsonResult result = new JsonResult();
		try {
			List<UserVinRelaDO> userVinRelaDOList = carVinRelaService.getVinInfoByUserId(userId);
			result.setDataObject(userVinRelaDOList);
			responseJson(result);
		}catch (Exception e){
			LOGGER.error("CarVinRelaController-getVinInfoByUser is error|delIds={}|loginUser={}", userId, e);
			responseJson(new JsonException("获取vin码信息失败！"));
		}
	}

	/**
	 * 用户主页 卡主页 解绑vin码
	 */
	@RequestMapping(value = "/unBindUserVinRelaById")
	@ResponseBody
	public void unBindUserVinRelaById(Long pkId){
		JsonResult result = new JsonResult();
		try {
			if(!carVinRelaService.deleteUserVinRela(pkId)){
				responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_USER_VIN_RELA_DELECT));
				return;
			}
			responseJson(result);
		}catch (Exception e){
			LOGGER.error("CarVinRelaController-unBindUserVinRelaById is error|delIds={}|loginUser={}", pkId, e);
			responseJson(new JsonException("解绑vin码失败！"));
		}
	}

}
