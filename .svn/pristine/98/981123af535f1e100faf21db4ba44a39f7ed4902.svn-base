package com.wanma.ims.session.service.impl;

import com.google.common.base.Strings;
import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CompanyMapper;
import com.wanma.ims.service.CommonRedisService;
import com.wanma.ims.session.cookie.CookieHelper;
import com.wanma.ims.session.cookie.CookieSession;
import com.wanma.ims.session.service.SessionService;
import com.wanma.ims.util.Base64EncodeUtil;
import com.wanma.ims.util.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xyc on 2018/1/30.
 * session逻辑处理类
 */
@Component("sessionService")
public class SessionServiceImpl implements SessionService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonRedisService commonRedisService;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public HttpSession getSession(HttpServletRequest request, HttpServletResponse response) {
        String userId = "";
        UserDO loginUser;

        try {
            userId = CookieHelper.getCookie(request, WanmaConstants.COOKIE_USER_ID);
            if (Strings.isNullOrEmpty(userId)) {
                LOGGER.warn("getSession is failed,userId is null|ip={}|url={}|param={}", NetUtils.getRequestRealIp(request), request.getRequestURL(), request.getQueryString());
                deleteSession(request, response);
                return null;
            }

            userId = Base64EncodeUtil.getUrlDecodedString(userId, "UTF-8");
        } catch (Exception e) {
            LOGGER.warn("getSession is failed,decoded userId is error|ip={}|userId={}", NetUtils.getRequestRealIp(request), userId, e);
            deleteSession(request, response);
            return null;
        }

        loginUser = commonRedisService.getCurrentLogin(Long.valueOf(userId));
        if (loginUser == null) {
            LOGGER.warn("getSession is failed,loginUser is null|ip={}|userId={}", NetUtils.getRequestRealIp(request), userId);
            deleteSession(request, response);
            return null;
        }

        HttpSession session = new CookieSession(request.getSession().getServletContext());
        session.setAttribute(WanmaConstants.SESSION_USER, loginUser);
        return session;
    }

    @Override
    public void deleteSession(HttpServletRequest request, HttpServletResponse response) {
        CookieHelper.deleteCookie(request, response, WanmaConstants.USER_COOKIE_PATH, WanmaConstants.COOKIE_USER_ID);
        CookieHelper.deleteCookie(request, response, WanmaConstants.USER_COOKIE_PATH, WanmaConstants.COOKIE_USER_NAME);
        CookieHelper.deleteCookie(request, response, WanmaConstants.USER_COOKIE_PATH, WanmaConstants.COOKIE_USER_LEVEL);
        CookieHelper.deleteCookie(request, response, WanmaConstants.USER_COOKIE_PATH, WanmaConstants.COOKIE_CPY_ID);
        CookieHelper.deleteCookie(request, response, WanmaConstants.USER_COOKIE_PATH, WanmaConstants.COOKIE_CPY_CODE);
    }

    @Override
    public void setSessionAndRedis(HttpServletRequest request, HttpServletResponse response, UserDO loginUser) {
        String encodingUserId = Base64EncodeUtil.getUrlEncodedString(loginUser.getUserId() + "", "UTF-8");
        String encodingUserName = Base64EncodeUtil.getUrlEncodedString(loginUser.getUserName(), "UTF-8");
        CookieHelper.setCookie(response, WanmaConstants.COOKIE_USER_ID, WanmaConstants.USER_COOKIE_PATH, encodingUserId, WanmaConstants.DEFAULT_COOKIE_MAX_AGE);
        CookieHelper.setCookie(response, WanmaConstants.COOKIE_USER_NAME, WanmaConstants.USER_COOKIE_PATH, encodingUserName, WanmaConstants.DEFAULT_COOKIE_MAX_AGE);
        CookieHelper.setCookie(response, WanmaConstants.COOKIE_USER_LEVEL, WanmaConstants.USER_COOKIE_PATH, loginUser.getUserLevel() + "", WanmaConstants.DEFAULT_COOKIE_MAX_AGE);
        if (loginUser.getCpyId() != null){
            CompanyDO companyDO = companyMapper.selectCpyListById(new Long(loginUser.getCpyId()));
            CookieHelper.setCookie(response, WanmaConstants.COOKIE_CPY_CODE, WanmaConstants.USER_COOKIE_PATH,companyDO.getCpyProvince()+":"+companyDO.getCpyCity(), WanmaConstants.DEFAULT_COOKIE_MAX_AGE);

        }
        CookieHelper.setCookie(response, WanmaConstants.COOKIE_CPY_ID, WanmaConstants.USER_COOKIE_PATH, loginUser.getCpyId() == null ? "" : loginUser.getCpyId() + "", WanmaConstants.DEFAULT_COOKIE_MAX_AGE);

        //当前登录人的公司标识、公司ID放入缓存
        commonRedisService.initCurrentLogin(loginUser);
    }
}
