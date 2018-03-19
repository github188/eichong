package com.wanma.ims.service.impl;

import com.wanma.ims.common.domain.ConfigContentDO;
import com.wanma.ims.mapper.ConfigContentMapper;
import com.wanma.ims.service.ConfigContentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xyc on 2017/7/10.
 * 配置文件逻辑处理接口
 */
@Service
public class ConfigContentServiceImpl implements ConfigContentService {
    @Autowired
    private ConfigContentMapper configContentMapper;

    @Override
    public Map<String, String> getConfigContentMap(Integer configParameterId, Integer mapType) {
        Map<String, String> result = new HashMap<>();

        ConfigContentDO searchModel = new ConfigContentDO();
        searchModel.setConfigParameterId(configParameterId);
        searchModel.setStatus(0);

        List<ConfigContentDO> configContentList = configContentMapper.getConfigContentList(searchModel);
        if (CollectionUtils.isEmpty(configContentList)) {
            return result;
        }

        for (ConfigContentDO config : configContentList) {
            if (mapType.equals(1)) {
                result.put(config.getId() + "", config.getContent());
            } else if (mapType.equals(2)) {
                result.put(config.getContent(), config.getId() + "");
            }
        }

        return result;
    }

    @Override
    public Map<String, Map<String, String>> batchGetConfigContentMap(List<Integer> configParameterIds) {
        Map<String, Map<String, String>> result = new HashMap<>();
        if (CollectionUtils.isEmpty(configParameterIds)) {
            return result;
        }

        List<ConfigContentDO> configContentList = configContentMapper.getConfigContentListByParameterIds(configParameterIds);
        if (CollectionUtils.isEmpty(configContentList)) {
            return result;
        }

        for (ConfigContentDO content : configContentList) {
            String key = content.getConfigParameterId() + "";
            Map<String, String> contentMap = result.get(key);
            if (contentMap == null) {
                contentMap = new HashMap<>();
                result.put(key, contentMap);
            }
            contentMap.put(content.getId() + "", content.getContent());
        }

        return result;
    }
}
