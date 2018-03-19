package com.wanma.ims.controller.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.ConfigContentService;
import com.wanma.ims.util.SplitterUtil;

/**
 * Created by xyc on 2017/7/21.
 * 配置文件controller
 */
@Controller
@RequestMapping("/manage/config/content")
public class ConfigContentController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigContentService configContentService;

    /**
     * 根据配置文件ids获取配置文件Map
     */
    @RequestMapping(value = "/getConfigContentMap", method = RequestMethod.POST)
    @ResponseBody
    public void getConfigContentMap(String configParameterIds) {
        JsonResult result = new JsonResult();

        try {
            result.setDataObject(configContentService.batchGetConfigContentMap(SplitterUtil.splitToIntegerList(configParameterIds, ",", null)));

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getConfigContentMap is error|loginUser={}", getCurrentLoginUser(), e);
            responseJson(new JsonException("获取配置文件Map失败，请刷新页面后重试！"));
        }
    }
}
