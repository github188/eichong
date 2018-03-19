package com.wanma.support.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wanma.model.TblUser;

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
    public static String WEB_SESSION_USER_pk = "userPk";
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
        return (TblUser) request.getSession().getAttribute(WEB_SESSION_USER_KEY);
    }

    /**
     * 添加用户至session
     */
    public static void addWebUser(HttpServletRequest request, TblUser user) {
        if(user==null) return;
        HttpSession session = request.getSession();
        session.setAttribute(WEB_SESSION_USER_KEY, user);
        session.setAttribute(WEB_SESSION_USER_pk, user.getUserId());
    }

    /**
     * 移除用户信息
     *
     * @param request
     */
    public static boolean removeWebUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(WEB_SESSION_USER_KEY);
        session.removeAttribute(WEB_SESSION_USER_pk);
        session.invalidate();
        return true;
    }

    public static Long getUserId(HttpServletRequest request){
        return (Long) request.getSession().getAttribute(WEB_SESSION_USER_pk);
    }

    @SuppressWarnings("unchecked")
	public static String getMagCode(HttpSession session, String phone) {
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
}
