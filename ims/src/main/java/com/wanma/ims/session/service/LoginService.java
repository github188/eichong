package com.wanma.ims.session.service;

import com.wanma.ims.common.dto.BaseResultDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xyc on 2018/1/30.
 * 登陆逻辑处理接口
 */
public interface LoginService {
    void generateCaptcha(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height) throws Exception;

    BaseResultDTO login(String username, String passwd, String code, HttpServletRequest request, HttpServletResponse response) throws Exception;

    String logout(HttpServletRequest request, HttpServletResponse response);

    void unbindAdmin(String userAccount);
}
