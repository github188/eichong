package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.AreaDO;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.ProvinceDO;
import com.wanma.ims.mapper.InitialMapper;
import com.wanma.ims.service.InitialService;

@Service
public class InitialServiceImpl implements InitialService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InitialMapper initialMapper;

    @Override
    public List<ProvinceDO> getProvinceListByIds(List<String> ids) {
        return initialMapper.selectProvinceList(ids);
    }

    @Override
    public List<CityDO> getCityListByIds(List<String> ids,String provinceCode) {
        return initialMapper.selectCityList(ids,provinceCode);
    }

    @Override
    public Map<String, ProvinceDO> getProvinceMapByIds(List<String> ids) {
        Map<String, ProvinceDO> map = new HashMap<>();
        if (CollectionUtils.isEmpty(ids)) {
            return map;
        }

        try {
            List<ProvinceDO> list = getProvinceListByIds(ids);
            if (CollectionUtils.isEmpty(list)) {
                return map;
            }
            map = Maps.uniqueIndex(list, new Function<ProvinceDO, String>() {
                @Override
                public String apply(ProvinceDO input) {
                    return input.getProvinceId();
                }
            });
        } catch (Exception e) {
            LOGGER.debug("InitialServiceImpl called getProvinceMapByIds failed", e);
        }
        return map;
    }

    @Override
    public Map<String, CityDO> getCityMapByIds(List<String> ids) {
        Map<String, CityDO> map = new HashMap<>();
        if (CollectionUtils.isEmpty(ids)) {
            return map;
        }

        try {
            List<CityDO> list = getCityListByIds(ids,null);
            if (CollectionUtils.isEmpty(list)) {
                return map;
            }
            map = Maps.uniqueIndex(list, new Function<CityDO, String>() {
                @Override
                public String apply(CityDO input) {
                    return input.getCityId();
                }
            });
        } catch (Exception e) {
            LOGGER.debug("InitialServiceImpl called getCityMapByIds failed", e);
        }
        return map;
    }

    @Override
    public List<AreaDO> getAreaListByIds(List<String> ids,String cityCode) {
        return initialMapper.selectAreaList(ids,cityCode);
    }

    @Override
    public Map<String, AreaDO> getAreaMapByIds(List<String> ids) {
        Map<String, AreaDO> areaMap = new HashMap<>();
        if (CollectionUtils.isEmpty(ids)) {
            return areaMap;
        }

        try {
            List<AreaDO> areaList = getAreaListByIds(ids,null);
            areaMap = Maps.uniqueIndex(areaList, new Function<AreaDO, String>() {
                @Override
                public String apply(AreaDO area) {
                    return area.getAreaId();
                }
            });
        } catch (Exception e) {
            LOGGER.debug("InitialServiceImpl called getAreaMapByIds failed", e);
        }

        return areaMap;
    }

    @Override
    public Map<String, List<CityDO>> getCityMap(String provinceCode) {
        List<CityDO> cityList = this.getCityListByIds(null,provinceCode);
        Map<String, List<CityDO>> map = new HashMap<>();
        List<CityDO> list;
        for (CityDO cityDO : cityList) {
        	list = map.get(cityDO.getProvinceId());
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(cityDO);
            map.put(cityDO.getProvinceId(), list);
        }
        return map;
    }

    @Override
    public Map<String, List<AreaDO>> getAreaMap(String cityCode) {
    	List<AreaDO> areaList = this.getAreaListByIds(null,cityCode);
        Map<String, List<AreaDO>> map = new HashMap<>();
        List<AreaDO> list;
        for (AreaDO areaDO : areaList) {
            list = map.get(areaDO.getCityId());
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(areaDO);
            map.put(areaDO.getCityId(), list);
        }
        return map;
    }

	@Override
	public List<CityDO> getCityListByName(String cityName) {
		 return initialMapper.selectCityListByName(cityName);
	}

}
