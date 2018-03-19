package com.wanma.ims.service;

import com.wanma.ims.common.domain.ReportOrderDO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xyc on 2017/9/25.
 * 充电点充电统计接口
 */
public interface PowerStationChargeStatisticsService {

    /**
     * 统计充电点充电数据
     */
    List<ReportOrderDO> statisticsOrderWithPowerStation(ReportOrderDO searchModel);

    long countOrderWithPowerStation(ReportOrderDO searchModel);

    /**
     * 导出充电点充电数据
     */
    void exportStatisticsPowerStationChargeOrder(HttpServletResponse response, ReportOrderDO searchModel);

    /**
     * 统计渠道分站充电数据
     */
    List<ReportOrderDO> statisticsOrderWithCompanyAndPowerStation(ReportOrderDO searchModel);

    long countOrderWithCompanyAndPowerStation(ReportOrderDO searchModel);

    /**
     * 导出渠道分站充电数据
     */
    void exportStatisticsCompanyAndPowerStationChargeOrder(HttpServletResponse response, ReportOrderDO searchModel);
}
