package com.wanma.ims.controller;

import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.constants.WanmaConstants;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class BaseController {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected HttpServletResponse response;
    protected HttpServletRequest request;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
    }

    /**
     * 获取当前登录用户ID
     */
    public Long getCurrentUserId() {
        return getCurrentLoginUser().getUserId();
    }

    /**
     * 获取当前登录用户账号
     */
    public String getCurrentUserName() {
        return getCurrentLoginUser().getUserAccount();
    }

    /**
     * 获取当前用户信息(获取用户其他信息可以从这个获取，比如Level级别,adminType)
     *
     * @return UserDO
     */
    public UserDO getCurrentLoginUser() {
        UserDO login = null;
        try {
            login = (UserDO) request.getSession().getAttribute(WanmaConstants.SESSION_USER);
            if (null == login) {
                LOGGER.error(this.getClass() + "-getCurrentLoginUser is error, login is null");
            }
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getCurrentLoginUser is error", e);
        }
        return login;
    }

    /**
     * 页面输出JSON串
     */
    public void responseJson(Object obj) {
        PrintWriter out = null;
        try {
            if (null == response) {
                response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
            }
            response.setContentType("text/json; charset=UTF-8");
            JSONObject jsonObj = JSONObject.fromObject(obj);
            String json = jsonObj.toString();
            LOGGER.debug("json:" + json);
            out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (Exception e) {
            LOGGER.error("JSON转换异常|obj={}|response={}", obj, response, e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
}
