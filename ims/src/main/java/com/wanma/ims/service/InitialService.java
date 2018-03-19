package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

import com.wanma.ims.common.domain.AreaDO;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.ProvinceDO;


/**
 * <p>公共的service<p>
 * zhangchunyan
 * 2017-6-23
 */
public interface InitialService {

    /**
     * <p>根据主键查询省列表<p>
     * @param ids 可以为空
     * @return list
     */
    List<ProvinceDO> getProvinceListByIds(List<String> ids);

    /**
     * <p>根据主键查询省map<p>
     * @param ids 可以为空
     * @return map
     */
    Map<String, ProvinceDO> getProvinceMapByIds(List<String> ids);

    /**
     * <p>根据主键查询市列表<p>
     * @param ids 可以为空
     * @return list
     */
    List<CityDO> getCityListByIds(List<String> ids,String provinceCode);

    /**
     * <p>根据主键查询市map<p>
     * @param ids 可以为空
     * @return map
     */
    Map<String, CityDO> getCityMapByIds(List<String> ids);

    /**
     * <p>根据主键查询区列表<p>
     * @param ids 可以为空
     * @return list
     */
    List<AreaDO> getAreaListByIds(List<String> ids,String cityCode);

    /**
     * <p>根据主键查询区map<p>
     * @param ids 可以为空
     * @return map
     */
    Map<String, AreaDO> getAreaMapByIds(List<String> ids);

    /**
     * <p>查询市区列表<p>
     * @return map
     */
    Map<String, List<CityDO>> getCityMap(String provinceCode);
    
    /**
     * <p>查询区列表<p>
     * @return map
     */
    Map<String,List<AreaDO>> getAreaMap(String cityCode);

    /**
     * <p>根据城市名称查询城市<p>
     * @return list  
     */
    List<CityDO> getCityListByName(String cityName);
    

}
