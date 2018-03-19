package com.wanma.controller;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.model.common.BasicMutiFileModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.service.RoleService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.web.support.utils.ApiUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约管理控制器
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/common")
public class CmsCommonController {
	
	@Autowired
	private RoleService roleService;
	/***
	 * token
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getToken")
	@ResponseBody
	public String getToken() {
		String token="";
		try {
			token=ApiUtil.getToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JsonObject(token).toString();
	}
	
	/***
	 * sendRequest
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendRequest")
	@ResponseBody
	public String sendRequest(Model model,HttpServletRequest request,@RequestParam Map<String, String> params) {
		String token="";
		String result="";
		try {
			token=ApiUtil.getToken();
			params.put("t", token);
			result=ApiUtil.sendRequest(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 唯一性检查
	 */
	@RequestMapping(value = "/checkUnique")
	@ResponseBody
	public String checkUnique(HttpServletRequest request) {
		String tName=request.getParameter("tName");
		String tProperty=request.getParameter("tProperty");
		String pkTProperty=request.getParameter("pkTProperty");
		String pkTValue=request.getParameter("pkTValue");
		String property=request.getParameter("property");
		String value=request.getParameter(property);
		Map<String,String> map=new HashMap<String, String>();
		map.put("tName",tName);
		map.put("tProperty",tProperty);
		map.put("pkTProperty",pkTProperty);
		map.put("pkTValue",pkTValue);
		map.put("value",value);
		int count=roleService.checkCommonUnique(map);
		// 返回处理结果信息
		return (count==0)+"";
	}
	
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile[] file, HttpServletRequest request) {
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DwzAjaxResult dwzResult=null;
		BasicMutiFileModel mutiFileModel=new BasicMutiFileModel();
		mutiFileModel.setAllMultiFile(file);
		String referenceId=request.getParameter("referenceId");
		referenceId=StringUtils.isNotBlank(referenceId)?referenceId:ProcessInfoCommon.getRandomKey(4);
		String type=request.getParameter("type");
		String isZip=request.getParameter("isZip");
		boolean zipFlag=StringUtils.isNotBlank(isZip)?true:false;
		String result=MultipartFileUtil.saveAllMultiFile(mutiFileModel, type, referenceId,zipFlag);
		List<String> fileList=MultipartFileUtil.getAllMultiUrl(type, referenceId);
		if(CommonConsts.PROCESS_STATUS_OK.equals(result)){
			dwzResult = new DwzAjaxResult("200", referenceId, fileList.get(fileList.size()-1),
					"", "");
		}else{
			dwzResult = new DwzAjaxResult("300", "新增失败", "", "",
					"");
		}
		
		return new JsonObject(dwzResult).toString();
	}
	
	@RequestMapping(value = "/deleteFile")
	@ResponseBody
	public String deleteFile(HttpServletRequest request) {
		DwzAjaxResult dwzResult=null;
		String businessType=request.getParameter("businessType");
		String referenceId=request.getParameter("referenceId");
		String fileName=request.getParameter("fileName");
		List<String> fileList=new ArrayList<String>();
		fileList.add(fileName);
		String result=MultipartFileUtil.delelteMulti(fileList, businessType, referenceId);
		if(CommonConsts.PROCESS_STATUS_OK.equals(result)){
			dwzResult = new DwzAjaxResult("200", "删除成功", "",
					"", "");
		}else{
			dwzResult = new DwzAjaxResult("300", "删除失败", "", "",
					"");
		}
		
		return new JsonObject(dwzResult).toString();
	}
    @RequestMapping(value = "/getProperties")
    @ResponseBody
    public String getProperties() {

        return MessageManager.getMessageManager()
                .getSystemProperties("hbaseUrl") ;
    }
}
