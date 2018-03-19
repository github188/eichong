/**
 * FileName:CommonTagSupport.java
 * Author: Administrator
 * Create: 2014年6月29日
 * Last Modified: 2014年6月29日
 * Version: V1.0 
 */
package com.bluemobi.product.web.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.model.MenuModel;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

/**
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月29日
 */
public class CommonTagSupport extends TagSupport {
	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -8665364737414431971L;

	/** DZW class */
	private String dwzClass = "";

	/** 是否接受控制 */
	private boolean isAuth = TagConst.CONTROL_NO;

	/** 事件ID */
	private String actionId = "";

	/**
	 * 取得登录用户ID
	 * 
	 * @since Version 1.0
	 * 
	 * @return String 登录用户ID
	 * @throws 无
	 */
	public String getLoginUserId() {
		// 登录用户信息
		TblUser loginUser = null;
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		// 从会话中取得登录用户信息
		loginUser = SessionMgr.getWebUser(request);
		// 返回登录用户ID
		return loginUser.getUserId().toString();
	}

	/**
	 * @Title: checkAuthByUrl
	 * @Description: TODO
	 * @author wbc 2015年11月3日
	 * @return: boolean
	 */
	public boolean checkAuthByUrl(String href) {
		if (StringUtils.isBlank(href))
			return false;
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		String contextPath = request.getContextPath();
		href = href.indexOf(contextPath) >= 0 ? href.substring(contextPath
				.length()) : href;
		href = href.indexOf("?") > 0 ? href.substring(0, href.lastIndexOf("?"))
				: href;
		List<MenuModel> menuList = SessionMgr.getUserMenus(request);
		boolean flag = false;
		for (MenuModel menu : menuList) {
			// System.out.println(href+":"+menu.getUrl()+":"+href.indexOf(menu.getUrl()));
			if (StringUtils.isNotBlank(menu.getUrl())
					&& menu.getUrl().indexOf(href) >= 0) {
				flag = true;
				break;
			}
		}
		// System.out.println("检验权限:"+href+":"+flag);
		return flag;
	}

	public String getDwzClass() {
		return dwzClass;
	}

	public void setDwzClass(String pDwzClass) {
		this.dwzClass = pDwzClass;
	}

	/**
	 * 是否接受控制的取得。
	 * 
	 * @return 是否接受控制
	 */
	public boolean getIsAuth() {
		return isAuth;
	}

	/**
	 * 是否接受控制的设定。
	 * 
	 * @param pIsAuth
	 *            是否接受控制
	 */
	public void setIsAuth(boolean pIsAuth) {
		this.isAuth = pIsAuth;
	}

	/**
	 * 事件ID的取得。
	 * 
	 * @return 事件ID
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * 事件ID的设定。
	 * 
	 * @param pActionId
	 *            事件ID
	 */
	public void setActionId(String pActionId) {
		this.actionId = pActionId;
	}

	public static void main(String[] args) {
		System.out.println("/admin/userManager/newUser.do"
				.indexOf("userManager/newUser.do"));
	}
}
