package com.wanma.ims.session.cookie;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.wanma.ims.core.GlobalSystem;
import com.wanma.ims.session.AesEncryption;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 暂时使用
 */
public class CookieHelper {
    private static final Gson GSON = new Gson();

    public static void setCookie(HttpServletResponse response, String name, String path, String value, int maxAge) {
        if (Strings.isNullOrEmpty(value) || Strings.isNullOrEmpty(name)) {
            return;
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setDomain(GlobalSystem.getConfig("imsDomain"));
        response.addCookie(cookie);
    }

    public static <T> T getCookie(HttpServletRequest request, String name, Class<T> clazz, String encryptKey) {
        String cookieValue = getCookie(request, name);

        if (cookieValue == null) {
            return null;
        }

        if (!Strings.isNullOrEmpty(encryptKey)) {
            cookieValue = new AesEncryption(encryptKey).deserialize(cookieValue);
        }
        return GSON.fromJson(cookieValue, clazz);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        String cookieValue = null;
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookieValue = cookie.getValue();
                break;
            }
        }
        return cookieValue;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String path, String name) {
        Cookie cookie = null;
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return;
        }
        for (Cookie oldCookie : cookies) {
            if (name.equals(oldCookie.getName())) {
                cookie = oldCookie;
                break;
            }
        }

        if (cookie != null) {
            cookie.setDomain(GlobalSystem.getConfig("imsDomain"));
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }
}
