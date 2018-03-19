package com.wanma.ims.controller.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.OrderStatisticService;

/**
 * 统计分析-充电消费统计-城市充电统计
 */
@Controller
@RequestMapping("/manage/statistic")
public class StatisticCityController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderStatisticService orderStatisticService;

    // A.充电度数 B.充电时长 C.充电次数 D:充电金额 time:日期
    @RequestMapping("/countReportCity")
    public void countReportCity() {
        JsonResult result = new JsonResult();
        Map<String, Object> params = initParam();
        Map<String, String> map = orderStatisticService.countCityCharge(params);
        result.setDataObject(map);
        responseJson(result);
    }

    @RequestMapping("/getReportCityData")
    public void getReportCityData() {
        JsonResult result = new JsonResult();
        // A.充电度数 B.充电时长 C.充电次数 D:充电金额 time:日期
        Map<String, Object> params = initParam();
        List<Map<String, String>> list = orderStatisticService.getCityChargeDetail(params);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Object> columns = new ArrayList<Object>();
        List<Object> charge = new ArrayList<Object>();
        String type = request.getParameter("type");
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, String> map : list) {
                columns.add(map.get("time"));
                if ("A".equals(type)) {
                    charge.add(map.get("A"));
                }
                if ("C".equals(type)) {
                    charge.add(map.get("C"));
                }
                if ("D".equals(type)) {
                    charge.add(map.get("D"));
                }
            }
        }
        if("A".equals(type)){
            resultMap.put("legend", new String[]{"充电度数"});
            resultMap.put("充电度数", charge);
        }
        if("C".equals(type)){
            resultMap.put("legend", new String[]{"充电次数"});
            resultMap.put("充电次数", charge);
        }
        if("D".equals(type)){
            resultMap.put("legend", new String[]{"充电金额"});
            resultMap.put("充电金额", charge);
        }
        resultMap.put("columns", columns);
        result.setDataObject(resultMap);
        responseJson(result);
    }

    @RequestMapping("/getReportCityDetail")
    public void getReportCityDetail(Pager pager) {
        JsonResult result = new JsonResult();
        try {
            Map<String, Object> params = initParam();
            params.put("pager", pager);
            Long total = orderStatisticService.countCityChargeDetail(params);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            List<Map<String, String>> map = orderStatisticService.getCityChargeDetail(params);
            result.setPager(pager);
            result.setDataObject(map);
            responseJson(result);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + ".getReportCityDetail() error : ", e);
            responseJson(new JsonException("城市充电统计失败"));
            return;
        }
        responseJson(result);
    }

    private Map<String, Object> initParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        String provinceCode = request.getParameter("provinceCode");
        String cityCode = request.getParameter("cityCode");
        String startGmtCreate = request.getParameter("startGmtCreate");
        String endGmtCreate = request.getParameter("endGmtCreate");
        params.put("provinceCode", provinceCode);
        params.put("cityCode", cityCode);
        params.put("startGmtCreate", startGmtCreate);
        params.put("endGmtCreate", endGmtCreate);
        return params;
    }
}
