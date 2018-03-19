package com.wanma.ims.controller;

import com.google.common.base.Strings;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.session.service.LoginService;
import com.wanma.ims.session.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 */
@Controller
public class LoginController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionService sessionService;
    @Autowired
    private LoginService loginService;

    /**
     * 登录跳转
     */
    @RequestMapping(value = "/tologin")
    public String toLogin() {
        return "login";
    }

    /**
     * 验证码图形生成
     */
    @RequestMapping(value = "/common/getValidCode", method = RequestMethod.GET)
    @ResponseBody
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            String width = request.getParameter("width");
            String height = request.getParameter("height");
            loginService.generateCaptcha(request, response, Strings.isNullOrEmpty(width) ? 0 : Integer.valueOf(width), Strings.isNullOrEmpty(height) ? 0 : Integer.valueOf(height));
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-generateCaptcha is error", e);
        }
    }

    /**
     * 用户登录 校验用户名、密码 、验证码,当天连续登录不能超过5次
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String username, String passwd, String code, HttpServletRequest request, HttpServletResponse response) {
        try {
            JsonResult result = new JsonResult();
            BaseResultDTO resultDTO = loginService.login(username, passwd, code, request, response);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + ".login() error|username={}", username, e);
            responseJson(new JsonException("系统错误，登陆失败！"));
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if (sessionService.getSession(request, response) == null) {
            return "login";
        } else {
            return "index";
        }
    }

    /**
     * 用户登出处理
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return loginService.logout(request, response);
    }

    @RequestMapping(value = "/manage/unbindAdmin")
    @ResponseBody
    private void unbindAdmin(String userAccount) {
        JsonResult result = new JsonResult();
        try {
            loginService.unbindAdmin(userAccount);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-unbindAdmin is error|userAccount={}", userAccount, e);
        }
        responseJson(result);
    }
}
