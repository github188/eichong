package com.wanma.ims.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xyc on 2017/9/20.
 * 创建错误html页面工具
 */
public class ErrorHtmlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHtmlUtil.class);

    public static void createErrorPage(HttpServletResponse response, String errorMsg) {
        try {
            response.setCharacterEncoding("UTF-8");
            String html = "<html><head><script>window.onload=function(){alert('" + errorMsg + "');window.history.back();}</script></head></html>";
            response.getWriter().print(html);
        } catch (IOException e) {
            LOGGER.error("ErrorHtmlUtil-exportUser is error|errorMsg={}", errorMsg, e);
        }
    }
}
