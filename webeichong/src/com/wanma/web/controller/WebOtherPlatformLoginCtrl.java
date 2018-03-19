package com.wanma.web.controller;

import com.bluemobi.product.utils.HttpUtil;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.model.Userinfo;
import com.wanma.web.support.model.BaseAccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by haner on 15/4/14.
 * 第三方平台登陆回调地址
 */
@Controller
@RequestMapping("/otherPlatform")
public class WebOtherPlatformLoginCtrl {

    //外网域名


    @RequestMapping("/qqLogin")
    public void qqLogin(){

    }


    @RequestMapping("/weixinLogin")
    public void weixinLogin(@RequestParam String code){
    }

    @RequestMapping("/sinaLogin")
    public void sinaLogin(){

    }

    /**
     * 获取用户信息
     * @param object
     * @return
     */
    private Userinfo getUserInfo(JsonObject object){
        return null;
    }

    /**
     * 刷新页面
     * @return
     */
    private String refreshPage(){
        return "<script type=\"text/javascript\">window.location.replace(window.location);</script>";
    }
}
