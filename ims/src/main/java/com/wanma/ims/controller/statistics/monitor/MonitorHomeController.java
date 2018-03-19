package com.wanma.ims.controller.statistics.monitor;

import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.core.GlobalSystem;
import com.wanma.ims.service.OrderStatisticService;
import com.wanma.ims.service.PowerStationStatisticService;
import com.wanma.ims.util.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 实时监控-监控首页
 */
@Controller
@RequestMapping("/manage/monitor")
public class MonitorHomeController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderStatisticService orderStatisticService;
    @Autowired
    private PowerStationStatisticService powerStationStatisticService;

    /**
     * 获取电桩数地图
     */
    @RequestMapping("/getElectricPileMap")
    public void getElectricPileMap() {
        JsonResult result = new JsonResult();
        result.setDataObject(powerStationStatisticService.getElectricPileMap());
        responseJson(result);
    }

    /**
     * 获取监控首页的实时数据
     */
    // A.充电电量 B.充电时长 C.充电次数 D:充电金额 time:日期
    @RequestMapping("/countReportCity")
    public void countReportCity() {
        JsonResult result = new JsonResult();
        Map<String, Object> params = initParam();
        Map<String, String> map = orderStatisticService.countCityCharge(params);
        result.setDataObject(map);
        responseJson(result);
    }

    /**
     * 根据所选城市地区获取充电点分页
     */
    @RequestMapping("/getPowerStationPage")
    public void getPowerStationPage(Pager pager) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();
        Map<String, Object> searchModel = new HashMap<>();
        try {
            searchModel = initParam();
            searchModel.put("pager", pager);
            long total = powerStationStatisticService.countPowerStationMap(searchModel);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }

            pager.setTotal(total);
            result.setPager(pager);
            result.setDataObject(powerStationStatisticService.getPowerStationMap(searchModel));
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getPowerStationPage is error|searchModel={}|loginUser={}", searchModel, loginUser, e);
            responseJson(new JsonException("根据所选城市地区获取充电点分页失败，请刷新页面后重试！"));
        }
    }

    /**
     * 获取充电点地图
     */
    @RequestMapping("/getPowerStationMap")
    public void getPowerStationMap() {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();
        Map<String, Object> searchModel = new HashMap<>();
        try {
            searchModel = initParam();
            result.setDataObject(powerStationStatisticService.getPowerStationMap(searchModel));
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getPowerStationMap is error|searchModel={}|loginUser={}", searchModel, loginUser, e);
            responseJson(new JsonException("获取充电点地图失败，请刷新页面后重试！"));
        }
    }

    /**
     * 获取单个充电点电桩枪头详情
     */
    @RequestMapping("/getPowerStationPileHeadDetail")
    public void getPowerStationPileHeadDetail(Long powerStationId, Pager pager) {
        JsonResult result = new JsonResult();
        Map<String, Object> searchModel = new HashMap<>();
        searchModel.put("powerStationId", powerStationId);
        searchModel.put("pager", pager);
        long total = powerStationStatisticService.countPowerStationPileHeadDetail(searchModel);
        if (total <= pager.getOffset()) {
            pager.setPageNo(1L);
        }

        pager.setTotal(total);
        result.setPager(pager);
        result.setDataObject(powerStationStatisticService.getPowerStationPileHeadDetail(searchModel));
        responseJson(result);
    }

    /**
     * 获取单个充电点电桩枪头数量
     */
    @RequestMapping("/getPowerStationPileHeadNum")
    public void getPowerStationPileHeadNum(Long powerStationId) {
        JsonResult result = new JsonResult();
        result.setDataObject(powerStationStatisticService.getPowerStationPileHeadNum(powerStationId));
        responseJson(result);
    }

    /**
     * 获取当天故障电桩
     */
    @RequestMapping("/getNowErrorPile")
    public void getNowErrorPile(Map<String, String> params) {
        JsonResult result = new JsonResult();
        try {
            String httpResult = HttpRequestUtil.post(GlobalSystem.getConfig("hbaseUrl") + "/getErrorData", params);
            result.setDataObject(httpResult);
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getNowErrorPile is error|params={}",params, e);
            responseJson(new JsonException("获取当天故障电桩失败，请刷新页面后重试！"));
        }
    }

    private Map<String, Object> initParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("provinceCode", request.getParameter("provinceCode"));
        params.put("cityCode", request.getParameter("cityCode"));
        params.put("areaCode", request.getParameter("areaCode"));
        params.put("powerStationName", request.getParameter("powerStationName"));

        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "-";
        String month = (cal.get(Calendar.MONTH) + 1) + "-";
        int day = cal.get(Calendar.DAY_OF_MONTH);
        params.put("startGmtCreate", year + month + (day - 1));
        params.put("endGmtCreate", year + month + day);
        return params;
    }
}
