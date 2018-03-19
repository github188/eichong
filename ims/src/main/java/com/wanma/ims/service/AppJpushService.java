package com.wanma.ims.service;

import com.wanma.ims.common.domain.JpushDO;

/**
 * 极光推送业务处理实现
 */
public interface AppJpushService {
    /**
     * @Title: getByuserInfo
     * @Description: 根据用户id获取用户推送信息
     */
    JpushDO getByuserInfo(Long jpushUserinfo);

}
