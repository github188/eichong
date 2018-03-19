package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

/**
 * Created by xyc on 2017/7/10.
 * 配置文件逻辑处理接口
 */
public interface ConfigContentService {

    /**
     * 获取配置文件map， mapType: map的类型 1 key为id，value为content内容 2 key为content内容，value为id
     */
    Map<String, String> getConfigContentMap(Integer configParameterId, Integer mapType);

    /**
     * 批量获取配置文件map
     */
    Map<String, Map<String, String>> batchGetConfigContentMap(List<Integer> configParameterIds);

}
