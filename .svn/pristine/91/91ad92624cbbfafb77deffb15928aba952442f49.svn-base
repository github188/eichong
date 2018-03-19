package com.wanma.web.support.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aaron on 2015/4/1.
 */
public class CookieUtil {
    private CookieUtil(){}

    /**
     * 获取cookie
     * @param request
     * @param key
     * @return
     */
    public static String getCookieByKey(HttpServletRequest request,String key) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletResponse response,String name,String value){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(60*60*24*7);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * @param response
     * @param key
     */
    public static void removeCookie(HttpServletResponse response,String key){
        Cookie cookie = new Cookie(key,null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
