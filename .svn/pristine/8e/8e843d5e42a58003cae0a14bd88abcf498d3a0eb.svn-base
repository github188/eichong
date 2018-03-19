package com.wanma.ims.controller.statistics.monitor;

import com.google.common.collect.Lists;
import com.wanma.ims.service.PowerStationStatisticService;
import com.wanma.ims.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.OrderStatisticService;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;


/**
 * 大屏展示--演示
 */
@Controller
@RequestMapping("/screen")
public class ScreenController extends BaseController {

    @Autowired
    private OrderStatisticService orderStatisticService;
    @Autowired
    private PowerStationStatisticService powerStationStatisticService;

    /**
     * 地图：万马的充电站点按实际经纬度展示在地图中
     */
    @RequestMapping(value = "/cityMap", method = RequestMethod.GET)
    public void getPowerStationCityMap() {
        JsonResult result = new JsonResult();
        try {
            List<Map<String, Object>> resultList = powerStationStatisticService.getPowerStationCityMap();
            processResultList(resultList);

            result.setDataObject(resultList);
        } catch (Exception e) {
            LOGGER.error("getPowerStationCityMap error", e);
            responseJson(new JsonException("大屏-万马的充电站点按实际经纬度展示在地图中失败"));
            return;
        }
        responseJson(result);
    }

    private void processResultList(List<Map<String, Object>> resultList) {
        addMap(resultList, "117.214924", "31.769628", 5);
        addMap(resultList, "117.279468", "31.893887", 5);
        addMap(resultList, "117.135445", "31.851174", 5);
        addMap(resultList, "112.601999", "37.854949", 5);
        addMap(resultList, "112.562441", "37.863277", 5);
        addMap(resultList, "112.565016", "37.810946", 5);
        addMap(resultList, "112.590287", "37.788472", 5);
        addMap(resultList, "115.471712", "38.875973", 5);
        addMap(resultList, "115.394293", "38.920795", 5);
        addMap(resultList, "113.324688", "22.934288", 5);
        addMap(resultList, "113.356254", "23.292864", 5);
        addMap(resultList, "113.285687", "23.082817", 5);
        addMap(resultList, "111.026607", "19.650966", 5);
        addMap(resultList, "110.264097", "19.995829", 5);
        addMap(resultList, "110.299494", "20.000667", 5);
        addMap(resultList, "109.353851", "18.292295", 5);
        addMap(resultList, "104.001015", "30.602848", 5);
        addMap(resultList, "113.153922", "23.202761", 5);
        addMap(resultList, "121.4855", "31.36985", 5);
        addMap(resultList, "121.630475", "31.212526", 5);
        addMap(resultList, "119.335306", "26.040996", 5);
        addMap(resultList, "105.574493", "30.507369", 5);
        addMap(resultList, "121.775954", "31.106907", 5);
        addMap(resultList, "114.321634", "30.474527", 5);
        addMap(resultList, "114.350871", "30.56409", 5);
        addMap(resultList, "114.232624", "30.555358", 5);
        addMap(resultList, "109.279867", "34.387108", 5);
        addMap(resultList, "108.898984", "34.228411", 5);
        addMap(resultList, "113.718219", "22.986505", 5);
        addMap(resultList, "113.67222", "22.935911", 5);
        addMap(resultList, "121.470948", "31.364062", 5);
        addMap(resultList, "121.448965", "31.355603", 5);
        addMap(resultList, "121.787906", "31.108836", 5);
    }

    private void addMap(List<Map<String, Object>> resultList, String lng, String lat, int value) {
        Map<String, Object> map = new HashMap<>();
        map.put("lng", lng);
        map.put("lat", lat);
        map.put("value", value);
        map.put("type", "1");
        resultList.add(map);
    }

    /**
     * 实时充电度数和充电金额<取当天数据>
     */
    @RequestMapping(value = "/relatime", method = RequestMethod.GET)
    public void relatime() {
        JsonResult result = new JsonResult();
        try {
            Map<String, Object> params = initParam();
            Map<String, BigDecimal> map = orderStatisticService.countNowCharge(params);
            Map<String, Long> resultMap = new HashMap<>();
            for (String key : map.keySet()) {
                BigDecimal value = map.get(key);
                resultMap.put(key, value.longValue());
            }
            result.setDataObject(Lists.newArrayList(resultMap));
        } catch (Exception e) {
            LOGGER.error("relatime error", e);
            responseJson(new JsonException("大屏-当天充电量和金额失败"));
            return;
        }
        responseJson(result);
    }

    /**
     * 总充电量
     */
    @RequestMapping(value = "/totalElectric", method = RequestMethod.GET)
    public void totalElectric() {
        JsonResult result = new JsonResult();
        try {
            Map<String, Object> params = initParam();
            Long total = orderStatisticService.countTotalElectric(params).longValue() * 5;
            Map<String, Long> resultMap = new HashMap<>();
            resultMap.put("total", total);
            resultMap.put("carbon", (long) (total * 0.785 / 1000));
            resultMap.put("fuelSavings", (long) (total / 0.2 * 0.09 / 10000));
            result.setDataObject(Lists.newArrayList(resultMap));
        } catch (Exception e) {
            LOGGER.error("totalElectric error", e);
            responseJson(new JsonException("大屏-总充电量失败"));
            return;
        }
        responseJson(result);
    }

    /**
     * 当日按城市进行充电量统计<top10>
     */
    @RequestMapping(value = "/statistics4Electric", method = RequestMethod.GET)
    public void statistics4Electric() {
        JsonResult result = new JsonResult();
        try {
            Map<String, Object> params = initParam();
            List<Map<String, String>> mapList = orderStatisticService.getNowCityCharge(params);
            result.setDataObject(mapList);
        } catch (Exception e) {
            LOGGER.error("statistics4Electric error", e);
            responseJson(new JsonException("当日按充电站进行充电量统计失败"));
            return;
        }
        responseJson(result);
    }

    /**
     * 当日按充电站进行充电量统计<top10>
     */
    @RequestMapping(value = "/statistics4PowerStation", method = RequestMethod.GET)
    public void statistics4PowerStation() {
        JsonResult result = new JsonResult();
        try {
            Map<String, Object> params = new HashMap<>();
            List<Map<String, String>> mapList = orderStatisticService.getNowPowerStationCharge(params);
            result.setDataObject(mapList);
        } catch (Exception e) {
            LOGGER.error("statistics4PowerStation error", e);
            responseJson(new JsonException("当日按充电站进行充电量统计失败"));
            return;
        }
        responseJson(result);
    }

    /**
     * 按月度进行所有站点充电量统计
     */
    @RequestMapping(value = "/statistics4Year", method = RequestMethod.GET)
    public void statistics4Year() {
        JsonResult result = new JsonResult();
        try {
            Map<String, Object> params = new HashMap<>();
            List<Map<String, String>> mapList = orderStatisticService.getMonthCharge(params);
            result.setDataObject(mapList);
        } catch (Exception e) {
            LOGGER.error("statistics4Year error", e);
            responseJson(new JsonException("按月度进行所有站点充电量统计失败"));
            return;
        }
        responseJson(result);
    }

    private Map<String, Object> initParam() {
        Map<String, Object> params = new HashMap<String, Object>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -5);
        params.put("startGmtCreate", cal.getTime());

        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -5);
        params.put("endGmtCreate", cal.getTime());
        return params;
    }
}
