package com.echong.service.impl;

import com.alibaba.fastjson.JSON;
import com.echong.constant.CommonConsts;
import com.echong.dto.Callback;
import com.echong.service.EChongService;
import com.echong.utils.HttpUtils;
import org.springframework.stereotype.Service;

/**
 * Created by zangyaoyi on 2016/12/30.
 */
@Service
public class EChongServiceImpl implements EChongService {
    @Override
    public String sendCallback(Callback callback) {
        return HttpUtils.send2EChong(CommonConsts.E_CHONG_CALLBACK_URL, JSON.toJSONString(callback));
    }

}
