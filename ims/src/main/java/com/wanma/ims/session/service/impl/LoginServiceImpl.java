package com.wanma.ims.session.service.impl;

import com.google.common.base.Strings;
import com.wanma.ims.common.domain.LogCommitDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.core.GlobalSystem;
import com.wanma.ims.redis.RedisUtil;
import com.wanma.ims.service.CommonRedisService;
import com.wanma.ims.service.LogCommitService;
import com.wanma.ims.service.UserService;
import com.wanma.ims.session.cookie.CookieHelper;
import com.wanma.ims.session.service.LoginService;
import com.wanma.ims.session.service.SessionService;
import com.wanma.ims.util.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xyc on 2018/1/30.
 * 登陆逻辑处理类
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final int MAX_LOGIN_ERROR_COUNT = 5;
    private static final String REDIRECT_URL = "login";

    @Autowired
    private RedisUtil redisService;
    @Autowired
    private LogCommitService logCommitService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CommonRedisService commonRedisService;
    @Autowired
    private UserService userService;

    @Override
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height) throws Exception {
        CaptchaUtil captchaUtil = new CaptchaUtil(width, height);
        String encodingCaptcha = Base64EncodeUtil.getUrlEncodedString(captchaUtil.getCode(), "UTF-8");
        CookieHelper.setCookie(response, WanmaConstants.IMS_CAPTCHA, WanmaConstants.USER_COOKIE_PATH, encodingCaptcha, WanmaConstants.DEFAULT_COOKIE_MAX_AGE);
        captchaUtil.write(response.getOutputStream());
    }

    @Override
    public BaseResultDTO login(String username, String passwd, String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 校验用户名
        if (Strings.isNullOrEmpty(username)) {
            LOGGER.warn(this.getClass() + "-login is failed, username is null");
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "用户名为空");
        }
        // 校验密码
        if (Strings.isNullOrEmpty(passwd)) {
            LOGGER.warn(this.getClass() + "-login is failed, passwd is null");
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "用户不存在");
        }
        // 校验验证码
        if (!checkCaptcha(request, code, username)) {
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "验证码不正确");
        }

        UserDO tempUser = new UserDO();
        tempUser.setUserAccount(username);
        tempUser.setUserPassword(passwd);

        // 验证当天登录出错次数
        int loginErrorCount = getLoginErrorCount(tempUser);
        if (loginErrorCount >= MAX_LOGIN_ERROR_COUNT) {
            LOGGER.warn(this.getClass() + "-login is failed, loginErrorCount >= 5|username={}|loginErrorCount={}", username, loginErrorCount);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "登陆次数超过5次");
        }

        // 校验账号
        UserDO loginUser = userService.loginUser(tempUser);
        if (loginUser == null) {
            LOGGER.warn(this.getClass() + "-login is failed, loginUser is null|username={}|loginErrorCount={}", username);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "用户不存在");
        }

        // 数据库获取的密码进行二次加密md5（数据库密码 + 账户）
        String loginPassword = loginUser.getUserPassword();
        String loginAccount = loginUser.getUserAccount();
        loginUser.setUserPassword(MD5Util.Md5(loginPassword + loginAccount));
        if (!loginUser.getUserPassword().equals(tempUser.getUserPassword().substring(0, tempUser.getUserPassword().length() - 1))) {
            setLoginErrorCount(tempUser);
            LOGGER.warn(this.getClass() + "-login is failed, userPassword is error|username={}|loginErrorCount={}", username);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "密码不正确");
        }
        sessionService.setSessionAndRedis(request, response, loginUser);

        addLoginLog(request, loginUser);

        return new BaseResultDTO();
    }

    private Boolean checkCaptcha(HttpServletRequest request, String captcha, String username) {
        // 检查是否需要输入验证码 0.需要 1.不需要
        if (WanmaConstants.CHECK_AUTH_CODE.equals(GlobalSystem.getConfig("notCheckAuthCode"))) {
            String decodedCaptcha = Base64EncodeUtil.getUrlDecodedString(CookieHelper.getCookie(request, WanmaConstants.IMS_CAPTCHA), "UTF-8");
            if (StringUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(decodedCaptcha)) {
                LOGGER.warn(this.getClass() + "-login is failed, checkCaptcha is failed|username={}|captcha={}|decodedCaptcha={}", username, captcha, decodedCaptcha);
                return false;
            }
        }
        return true;
    }

    private void addLoginLog(HttpServletRequest request, UserDO loginUser) {
        /***登录日志****/
        LogCommitDO logCommitDO = new LogCommitDO();
        logCommitDO.setLogName(ObjectUtil.nvlStrEmpty(loginUser.getUserName()));
        logCommitDO.setLogIpAddress(getIpAddr(request));
        logCommitDO.setLogContent("登录成功");
        logCommitDO.setCreatorId(loginUser.getUserId());
        logCommitService.addLogCommit(logCommitDO);
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private void setLoginErrorCount(UserDO user) {
        try {
            int failNum = getLoginErrorCount(user);
            String currentDate = DateUtil.getNow(DateUtil.TYPE_COM_YMD);
            redisService.strSet("app:lpw:" + user.getUserAccount() + ":" + currentDate, failNum + 1 + "");
        } catch (Exception e) {
            LOGGER.error(this.getClass() + ".setLoginErrorCount() error:", e);
        }
    }

    private int getLoginErrorCount(UserDO user) {
        try {
            String currentDate = DateUtil.getNow(DateUtil.TYPE_COM_YMD);
            String failNum = redisService.strGet("app:lpw:" + user.getUserAccount() + ":" + currentDate);
            return StringUtils.isNotBlank(failNum) ? new Integer(failNum) : 0;
        } catch (Exception e) {
            LOGGER.error(this.getClass() + ".getLoginErrorCount() error:", e);
        }
        return 0;
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String userId = CookieHelper.getCookie(request, WanmaConstants.COOKIE_USER_ID);
        if (!Strings.isNullOrEmpty(userId)) {
            userId = Base64EncodeUtil.getUrlDecodedString(userId, "UTF-8");
            commonRedisService.removeCurrentLogin(Long.valueOf(userId));
        }
        sessionService.deleteSession(request, response);
        return "redirect:/tologin.do";
    }

    @Override
    public void unbindAdmin(String userAccount) {
        String currentDate = DateUtil.getNow(DateUtil.TYPE_COM_YMD);
        redisService.strRemove("app:lpw:" + userAccount + ":" + currentDate);
    }
}
