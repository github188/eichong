package com.echong.controller;

import com.echong.model.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zangyaoyi on 2016/12/29.
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AbstractController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    ResultData<String> index() {
        logger.warn("getResult begin");
        return new ResultData<>("welcome");
    }

}
