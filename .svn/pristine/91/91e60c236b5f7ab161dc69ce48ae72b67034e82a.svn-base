package com.wanma.ims.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.ProvinceDO;
import com.wanma.ims.common.domain.ReportOrderDO;
import com.wanma.ims.common.domain.UserTagDO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.mapper.ReportOrderMapper;
import com.wanma.ims.service.InitialService;
import com.wanma.ims.service.PowerStationChargeStatisticsService;
import com.wanma.ims.service.TagService;
import com.wanma.ims.util.ErrorHtmlUtil;
import com.wanma.ims.util.ImsBaseUtil;

/**
 * Created by xyc on 2017/9/25.
 * 充电点充电统计实现类
 */
@Service
public class PowerStationChargeStatisticsServiceImpl implements PowerStationChargeStatisticsService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReportOrderMapper reportOrderMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private InitialService initialService;

    @Override
    public List<ReportOrderDO> statisticsOrderWithPowerStation(ReportOrderDO searchModel) {
        replaceSearchModel(searchModel);
        List<ReportOrderDO> resultList = reportOrderMapper.selectOrderByPowerStation(searchModel);

        if (CollectionUtils.isNotEmpty(resultList)) {
            Set<String> provinceCodeSet = new HashSet<>();
            Set<String> cityCodeSet = new HashSet<>();

            Map<String, String> map = new HashMap<String, String>();
            map.put("1", "大账户付费");
            map.put("2", "个人付费");
            map.put("3", "大账户为个人配资");

            for (ReportOrderDO reportOrder : resultList) {
                provinceCodeSet.add(reportOrder.getProvinceCode());
                cityCodeSet.add(reportOrder.getCityCode());
                reportOrder.setPayType(map.get(reportOrder.getPayType()));
            }

            Map<String, ProvinceDO> provinceMap = initialService.getProvinceMapByIds(new ArrayList<>(provinceCodeSet));
            Map<String, CityDO> cityMap = initialService.getCityMapByIds(new ArrayList<>(cityCodeSet));

            for (ReportOrderDO reportOrder : resultList) {
                ProvinceDO province = provinceMap.get(reportOrder.getProvinceCode());
                if (province != null) {
                    reportOrder.setCity(province.getProvinceName());
                }

                CityDO city = cityMap.get(reportOrder.getCityCode());
                if (city != null) {
                    if (!Strings.isNullOrEmpty(reportOrder.getCity())) {
                        reportOrder.setCity(reportOrder.getCity() + city.getCityName());
                    } else {
                        reportOrder.setCity(city.getCityName());
                    }
                }
            }
        }

        return resultList;
    }

    @Override
    public long countOrderWithPowerStation(ReportOrderDO searchModel) {
        replaceSearchModel(searchModel);
        return reportOrderMapper.countOrderByPowerStation(searchModel);
    }

    @Override
    public List<ReportOrderDO> statisticsOrderWithCompanyAndPowerStation(ReportOrderDO searchModel) {
        replaceSearchModel(searchModel);
        List<ReportOrderDO> resultList = reportOrderMapper.selectOrderByCpyAndPowerStation(searchModel);

        if (CollectionUtils.isNotEmpty(resultList)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("1", "大账户付费");
            map.put("2", "个人付费");
            map.put("3", "大账户为个人配资");

            for (ReportOrderDO reportOrder : resultList) {
                reportOrder.setPayType(map.get(reportOrder.getPayType()));
            }
        }

        return resultList;
    }

    @Override
    public long countOrderWithCompanyAndPowerStation(ReportOrderDO searchModel) {
        replaceSearchModel(searchModel);
        return reportOrderMapper.countOrderByCpyAndPowerStation(searchModel);
    }

    private void replaceSearchModel(ReportOrderDO searchModel) {
        if (searchModel.getTagId() != null) {
            UserTagDO userTagSearchModel = new UserTagDO();
            userTagSearchModel.setTagId(searchModel.getTagId());
            List<UserTagDO> userTagList = tagService.getUserTagList(userTagSearchModel);
            List<Long> userIds = Lists.transform(userTagList, new Function<UserTagDO, Long>() {
                @Override
                public Long apply(UserTagDO input) {
                    return input.getUserId();
                }
            });

            if (CollectionUtils.isEmpty(userIds)) {
                userIds = Lists.newArrayList(-1L);
            }

            searchModel.setUserIds(userIds);
        }
    }

    @Override
    public void exportStatisticsPowerStationChargeOrder(HttpServletResponse response, ReportOrderDO searchModel) {
        List<ReportOrderDO> resultList = statisticsOrderWithPowerStation(searchModel);
        List<String> headList = Lists.newArrayList("serviceTime,时间", "powerStationName,充电点名称", "city,城市", "totalMoney,总金额", "totalElectricCharge,总电量", "totalElectricMoney,总电费",
                "totalServiceMoney,总服务费 ", "totalfavMoney,总优惠 ", "JPower,尖电量", "FPower,峰电量", "PPower,平电量", "GPower,谷电量", "JMoney,尖电费", "FMoney,峰电费", "PMoney,平电费", "GMoney,谷电费", "JServiceMoney,尖服务费", "FServiceMoney,峰服务费", "PServiceMoney,平服务费", "GServiceMoney,谷服务费",
                "totalFavMoney,优惠后总金额", "favMoney,优惠后电费", "favServiceMoney,优惠后服务费");
        exportStatisticsChargeOrder(response, resultList, searchModel, headList, DownFileConstants.FILE_POWER_STATION_ORDER_EXPORT, DownFileConstants.FILE_POWER_STATION_ORDER_EXPOR_SHEET);
    }

    @Override
    public void exportStatisticsCompanyAndPowerStationChargeOrder(HttpServletResponse response, ReportOrderDO searchModel) {
        List<ReportOrderDO> resultList = statisticsOrderWithCompanyAndPowerStation(searchModel);
        List<String> headList = Lists.newArrayList("serviceTime,时间", "powerStationName,充电点名称", "cpyName,渠道名称", "payType,付费策略", "totalMoney,总金额", "totalElectricCharge,总电量", "totalElectricMoney,总电费",
                "totalServiceMoney,总服务费 ", "totalfavMoney,总优惠 ", "JPower,尖电量", "FPower,峰电量", "PPower,平电量", "GPower,谷电量", "JMoney,尖电费", "FMoney,峰电费", "PMoney,平电费", "GMoney,谷电费", "JServiceMoney,尖服务费", "FServiceMoney,峰服务费", "PServiceMoney,平服务费", "GServiceMoney,谷服务费",
                "totalFavMoney,优惠后总金额", "favMoney,优惠后电费", "favServiceMoney,优惠后服务费");
        exportStatisticsChargeOrder(response, resultList, searchModel, headList, DownFileConstants.FILE_COMPANY_AND_POWER_STATION_ORDER_EXPORT, DownFileConstants.FILE_COMPANY_AND_POWER_STATION_ORDER_EXPOR_SHEET);
    }

    private void exportStatisticsChargeOrder(HttpServletResponse response, List<ReportOrderDO> resultList, ReportOrderDO searchModel, List<String> headList, String xlsName, String sheetName) {
        if (CollectionUtils.isEmpty(resultList)) {
            LOGGER.warn(this.getClass() + "-exportStatisticsPowerStationChargeOrder exportResultList is empty|searchModel={}", searchModel);
            ErrorHtmlUtil.createErrorPage(response, "您导出的数据不存在，请修改正确的查询条件后再导出！");
            return;
        }

        List<Map<String, Object>> dataList = createDataExcelList(resultList);
        ImsBaseUtil.responseExcel(response, headList, dataList, xlsName,"充电范围");

    }

    private List<Map<String, Object>> createDataExcelList(List<ReportOrderDO> list) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (ReportOrderDO order : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("serviceTime", order.getServiceTime());
            map.put("cpyName", order.getCpyName());
            map.put("powerStationName", order.getPowerStationName());
            map.put("city", order.getCity());
            map.put("payType", order.getPayType());
            BigDecimal totalServiceMoney = getBigDecimal(order.getTotalServiceMoney());
            BigDecimal favServiceMoney = getBigDecimal(order.getFavServiceMoney());
            map.put("totalMoney", format("%.4f", getBigDecimal(order.getTotalElectricMoney()).add(totalServiceMoney)));
            map.put("totalElectricCharge", format("%.2f", getBigDecimal(order.getTotalElectricCharge())));
            map.put("totalElectricMoney", format("%.4f", getBigDecimal(order.getTotalElectricMoney())));
            map.put("totalServiceMoney", format("%.4f", getBigDecimal(order.getTotalServiceMoney())));
            map.put("totalfavMoney", format("%.4f", getBigDecimal(order.getTotalfavMoney())));
            map.put("JPower", format("%.2f", getBigDecimal(order.getJPower())));
            map.put("FPower", format("%.2f", getBigDecimal(order.getFPower())));
            map.put("PPower", format("%.2f", getBigDecimal(order.getPPower())));
            map.put("GPower", format("%.2f", getBigDecimal(order.getGPower())));
            map.put("JMoney", format("%.4f", getBigDecimal(order.getJMoney())));
            map.put("FMoney", format("%.4f", getBigDecimal(order.getFMoney())));
            map.put("PMoney", format("%.4f", getBigDecimal(order.getPMoney())));
            map.put("GMoney", format("%.4f", getBigDecimal(order.getGMoney())));
            map.put("JServiceMoney", format("%.4f", getBigDecimal(order.getJServiceMoney())));
            map.put("FServiceMoney", format("%.4f", getBigDecimal(order.getFServiceMoney())));
            map.put("PServiceMoney", format("%.4f", getBigDecimal(order.getPServiceMoney())));
            map.put("GServiceMoney", format("%.4f", getBigDecimal(order.getGServiceMoney())));

            map.put("totalFavMoney", format("%.4f", getBigDecimal(order.getFavMoney()).add(favServiceMoney)));
            map.put("favMoney", order.getFavMoney());
            map.put("favServiceMoney", order.getFavServiceMoney());
            dataList.add(map);
        }
        return dataList;
    }

    private BigDecimal getBigDecimal(String param) {
        if (Strings.isNullOrEmpty(param)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(param);
    }

    private String format(String format, Object param) {
        String result = String.format(format, param);
        return "null".equals(result) ? "" : result;
    }
}
