package com.wanma.ims.session.service;

import com.wanma.ims.common.domain.UserDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xyc on 2018/1/30.
 * session逻辑处理接口
 */
public interface SessionService {
    HttpSession getSession(HttpServletRequest request, HttpServletResponse response);

    void deleteSession(HttpServletRequest request, HttpServletResponse response);

    void setSessionAndRedis(HttpServletRequest request, HttpServletResponse response, UserDO loginUser);
}
