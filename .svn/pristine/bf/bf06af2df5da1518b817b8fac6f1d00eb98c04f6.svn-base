package com.bluemobi.product.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;

@Controller
public class BaseController {
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				new SimpleDateFormat("yyyy-MM-dd"), true));
//		binder.registerCustomEditor(int.class, new CustomNumberEditor(
//				int.class, true));
//		binder.registerCustomEditor(int.class, new LongEditor());
/*		binder.registerCustomEditor(long.class, new CustomNumberEditor(
				long.class, true));*/
//		binder.registerCustomEditor(long.class, new LongEditor());
//		binder.registerCustomEditor(double.class, new DoubleEditor());
//		binder.registerCustomEditor(float.class, new FloatEditor());
	}
	
	/**
	 * @Title: checkOprateValid 
	 * @Description: 检验用户是否对角色有操作权限
	 * @author wbc	
	 * 2015年11月4日 	
	 * @return: boolean 
	 */
	protected boolean checkOprateValid(HttpServletRequest request) {
		int userlevel=SessionMgr.getWebUser(request).getUserLevel();
		boolean flag=true;
		if(userlevel>WanmaConstants.USER_LEVEL_ADMIN){
			flag=false;
		}
		return flag;
	}
}
