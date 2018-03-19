/**     
 * @Title:  TblApplyEpController.java   
 * @Package com.wanma.controller.itf   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年12月2日 下午2:47:42   
 * @version V1.0     
 */  
package com.wanma.controller.itf;

//import javax.servlet.http.HttpServletRequest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblApplyEp;
import com.wanma.service.TblApplyEpService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.ResultResponse;
//import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.SuccessResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.RegexUtil;

/**
 * @desc 申请建桩Controller
 * @author cdy
 *
 */
@Controller
@RequestMapping("/itf/applyep")
public class TblApplyEpController {
	
	@Autowired
	private TblApplyEpService tblApplyEpService;
	
	/**
	 * @Description: 我的申请
	 * @return: ResponseBody
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String index(TblApplyEp applyEp) {
		// 验证userId是否为空
		if (applyEp.getAepUserId() != null&&applyEp.getAepUserId()!=-1) {
			List<TblApplyEp> aepList = tblApplyEpService.getByAepUserId(applyEp);
			return new ResultResponse<List<TblApplyEp>>(aepList).toString();
		} else {
			return new FailedResponse(1050,"用户ID不能为空").toString();
		}
	}
	/**
	 * @Description: 新建电桩
	 * @return: ResponseBody
	 */
	@RequestMapping(value = "/new")
	@ResponseBody
	public String New(TblApplyEp applyEp,HttpServletRequest request) {
		//验证token有效性
		String token=request.getParameter("token");
		if(!validToken(applyEp.getAepOrigin()+"", token)){
			return new FailedResponse(1004,"非法数据").toString();
		}
		/* 验证联系人姓名是否为空 */
		if (StringUtils.isBlank(applyEp.getAepName())) {
			return new FailedResponse(1050,"联系人姓名不能为空").toString();
		}
		/* 验证联系人电话是否为空 */
		if (StringUtils.isBlank(applyEp.getAepPhone())) {
			return new FailedResponse(1050,"联系人电话不能为空").toString();
		}
		/* 验证联系人电话格式是否正确 */
		// 匹配规则：11位手机号 | 3/4位区号-7/8位电话号

		if (!RegexUtil.isTel(applyEp.getAepPhone())) {
			return new FailedResponse(1050,"联系人电话格式错误").toString();
		}
		/* 验证建桩地址是否为空 */
		if (StringUtils.isBlank(applyEp.getAepPCode()) || 
				 StringUtils.isBlank(applyEp.getAepCCode()) ||
				 StringUtils.isBlank(applyEp.getAepACode()) || 
				 StringUtils.isBlank(applyEp.getAepAddr())) {
			return new FailedResponse(1050,"建桩地址不能为空").toString();
		}
		/* 验证联系人是否正确 */
		if (applyEp.getAepUserId() == null) {
			return new FailedResponse(1050,"申请人不能为空").toString();
		}
		/* 用户来源不能为空 */
		if (applyEp.getAepOrigin() == null) {
			return new FailedResponse(1050,"用户来源不能为空").toString();
		}
		
		applyEp.setAepStatus(WanmaConstants.APPLY_EP_STATUS_APPLY);
		/* 将数据转交Service层处理 */
		Long aepId = tblApplyEpService.insertEp(applyEp);
		/* 返回成功响应 */
		if (aepId != null) {
			return new SuccessResponse().toString();
		} else {
			return new FailedResponse(2002,"系统错误，创建失败").toString();
		}
	}
	
	
	/**
	 * @Description: 验证来源有效
	 * @return: ResponseBody
	 */
	@RequestMapping(value = "/valid")
	@ResponseBody
	public String valid(HttpServletRequest request) {
		String aepOrigin=request.getParameter("aepOrigin");
		String token=request.getParameter("token");
		if(validToken(aepOrigin, token)){
			return new SuccessResponse().toString();
		}
		return new FailedResponse(1004,"非法数据").toString();
	}
	
	private boolean validToken(String aepOrigin,String token){
		//1富士康;2吉利;3绿地;4;浙誉;5.车纷享
		if("99".equals(aepOrigin)){
			return true;
		}else if("1".equals(aepOrigin)&&"78086911cf283cfc06c09873e11f506f".equals(token)){
			return true;
		}else if("2".equals(aepOrigin)&&"f266cb7434454a2297a21959cc39f5cb".equals(token)){
			return true;
		}else if("3".equals(aepOrigin)&&"fa9c3b22d3767cbf95c085722e4bc449".equals(token)){
			return true;
		}else if("4".equals(aepOrigin)&&"bd3800881e043d8a1e291d2932f3753d".equals(token)){
			return true;
		}else if("5".equals(aepOrigin)&&"42667072fb46db7782ab083b6d44904e".equals(token)){
			return true;
		}
		return false;
	}
	
	/*public static void main(String[] args) {
		System.out.println(MD5Util.Md5("icfsk20151221"));
		System.out.println(MD5Util.Md5("icjl20151221"));
		System.out.println(MD5Util.Md5("icld20151221"));
		System.out.println(MD5Util.Md5("iczy20151221"));
		System.out.println(MD5Util.Md5("iccfx20151221"));
	}*/
}
