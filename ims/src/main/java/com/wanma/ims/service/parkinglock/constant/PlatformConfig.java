package com.wanma.ims.service.parkinglock.constant;


import com.wanma.ims.core.GlobalSystem;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台配置文件
 */
public class PlatformConfig {

    public static final String WIPARKING_PLATFORM_URL = GlobalSystem.getConfig("wiparking_platform_url");

    public static final Integer WIPARKING_PLATFORM_SDK_ID = Integer.valueOf(GlobalSystem.getConfig("wiparking_platform_sdk_id"));

    public static final String WIPARKING_PLATFORM_TOKEN = GlobalSystem.getConfig("wiparking_platform_token");

}
