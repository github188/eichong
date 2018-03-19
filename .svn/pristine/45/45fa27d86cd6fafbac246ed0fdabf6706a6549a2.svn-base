package com.base.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pub.model.MenuModel;
import com.pub.model.RoleModel;
import com.pub.model.TblUser;

/**
 * Created by Haner on 2015/3/20.
 * session manger
 */
public class SessionMgr {
    //system validate code KEY
    public static String WEB_CODE_KEY = "com.web.validator.code";
    //session user KEY
    public static String WEB_SESSION_USER_KEY = "webUser";
    //session user PK
    public static String WEB_SESSION_USER_PK = "userPk";
    public static String WEB_SESSION_USER_ROLES="userRoles";
    public static String WEB_SESSION_USER_MENUS="userMenus";
    /**
     * 短信验证码
     */
    public static final String MESSAGE_CODE = "com.dh.code";

    /**
     * 系统验证码
     *
     * @param request
     * @return
     */
    public static String getWebCode(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(WEB_CODE_KEY);
    }

    /**
     * 添加验证码
     *
     * @param request
     * @param code
     */
    public static void addWebCode(HttpServletRequest request, String code) {
        request.getSession().setAttribute(WEB_CODE_KEY, code);
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    public static TblUser getWebUser(HttpServletRequest request) {
        return (TblUser) request.getSession().getAttribute(WanmaConstants.SESS_PRINCIPAL);
    }

    /**
     * 添加用户至session
     */
    public static void addWebUser(HttpServletRequest request, TblUser user) {
        if(user==null) return;
        request.getSession().setAttribute(WanmaConstants.SESS_PRINCIPAL, user);
        request.getSession().setAttribute("userLevel", user.getUserLevel());
    }

    /**
     * 移除用户信息
     *
     * @param request
     */
    public static boolean removeWebUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(WanmaConstants.SESS_PRINCIPAL);
        session.invalidate();
        return true;
    }

    public static Long getUserId(HttpServletRequest request){
        return getWebUser(request).getUserId();
    }

    public static String getMsgCode(HttpSession session, String phone) {
        Map<String, String> map = (Map<String, String>) session.getAttribute(MESSAGE_CODE);
        if (null == map) {
            return null;
        }
        return map.get(phone);
    }

    public static void addMsgCode(Map<String, String> msgCode, HttpSession session) {
        session.setAttribute(MESSAGE_CODE, msgCode);
    }

    public static void removeMsgCode(HttpSession session) {
        session.removeAttribute(MESSAGE_CODE);
    }
    
    public static void addUserRoles(HttpServletRequest request,List<RoleModel> roleList) {
        if(roleList==null) return;
        request.getSession().setAttribute(WEB_SESSION_USER_ROLES, roleList);
    }
    
    public static List<RoleModel> getUserRoles(HttpServletRequest request){
    	return (List<RoleModel>) request.getSession().getAttribute(WEB_SESSION_USER_ROLES);
    }
    
    public static void addUserMenus(HttpServletRequest request,List<MenuModel> menuList) {
        if(menuList==null) return;
        request.getSession().setAttribute(WEB_SESSION_USER_MENUS, menuList);
    }
    
    public static List<MenuModel> getUserMenus(HttpServletRequest request){
    	return (List<MenuModel>) request.getSession().getAttribute(WEB_SESSION_USER_MENUS);
    }
}
