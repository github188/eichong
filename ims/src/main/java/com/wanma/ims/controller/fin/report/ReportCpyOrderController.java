
package com.wanma.ims.controller.fin.report;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wanma.ims.common.domain.ReportOrderDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.ReportCpyOrderService;
import com.wanma.ims.util.ErrorHtmlUtil;
import com.wanma.ims.util.ImsBaseUtil;

/**
 * 大客户充电统计报表
 */
@Controller
@RequestMapping("/manage/fin/report/")
public class ReportCpyOrderController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ReportCpyOrderService reportCpyOrderService;

    @RequestMapping("/cpy/getReportCpyOrder")
    @ResponseBody
    public void getReportCpyOrder(ReportOrderDO reportOrderDO, Pager pager) {
        JsonResult result = new JsonResult();
        try {
            reportOrderDO.setCpyNumberList(getCurrentLoginUser().getCpyNumberList());
            Long total = reportCpyOrderService.countReportCpyOrder(reportOrderDO);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            reportOrderDO.setPager(pager);
            List<ReportOrderDO> list = reportCpyOrderService.getReportCpyOrder(reportOrderDO);
            result.setPager(pager);
            result.setDataObject(list);
        } catch (Exception e) {
            LOGGER.debug(this.getClass() + ".getReportCpyOrder() error : ", e);
            responseJson(new JsonException("大客户订单分页列表失败"));
            return;
        }
        responseJson(result);
    }

    /**
     * 订单-
     */
    @RequestMapping(value = "/order/exportReportOrder")
    @ResponseBody
    public void exportReportOrder(ReportOrderDO reportOrderDO) {
        try {
            reportOrderDO.setCpyNumberList(getCurrentLoginUser().getCpyNumberList());
            Long total = reportCpyOrderService.countReportCpyOrder(reportOrderDO);
            if (total > DownFileConstants.MAX_SIZE) {
                ErrorHtmlUtil.createErrorPage(response, ResultCodeConstants.ERROR_MSG_EXPORT_MAX_DATA);
                return;
            }
            List<ReportOrderDO> list = reportCpyOrderService.getReportCpyOrder(reportOrderDO);
            if (CollectionUtils.isEmpty(list)) {
                ErrorHtmlUtil.createErrorPage(response, ResultCodeConstants.ERROR_MSG_EXPORT_DATA_EMPTY);
                return;
            }
            List<String> headList = Lists.newArrayList("serviceTime,时间", "cpyName,渠道名称", "accountNo,大账户号", "payType,付费策略", "tradeType,付费方式", "totalMoney,总金额", "totalElectricCharge,总电量", "totalElectricMoney,总电费",
                    "totalServiceMoney,总服务费 ", "totalfavMoney,总优惠 ", "JPower,尖电量", "FPower,峰电量", "PPower,平电量", "GPower,谷电量", "JMoney,尖电费", "FMoney,峰电费", "PMoney,平电费", "GMoney,谷电费", "JServiceMoney,尖服务费", "FServiceMoney,峰服务费", "PServiceMoney,平服务费", "GServiceMoney,谷服务费",
                    "totalFavMoney,优惠后总金额", "favMoney,优惠后电费", "favServiceMoney,优惠后服务费");
            List<Map<String, Object>> datalist = createDataExcelList(list);
            ImsBaseUtil.responseExcel(response, headList, datalist, DownFileConstants.FILE_ORDER_EXPORT,DownFileConstants.FILE_ORDER_EXPOR_SHEET);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + ".exportReportOrder() error : ", e);
            ErrorHtmlUtil.createErrorPage(response, "导出渠道报表数据失败");
        }
    }

    private List<Map<String, Object>> createDataExcelList(List<ReportOrderDO> list) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (ReportOrderDO order : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("serviceTime", order.getServiceTime());
            map.put("cpyName", order.getCpyName());
            map.put("accountNo", order.getAccountNo());
            map.put("payType", "大账户付费");
            if (order.getTradeType() == 1) {
                map.put("tradeType", "信用账户");
            } else {
                map.put("tradeType", "储值账户");
            }
            BigDecimal serviceMoney = getBigDecimal(order.getTotalServiceMoney());
            BigDecimal favServiceMoney = getBigDecimal(order.getFavServiceMoney());
            map.put("totalMoney", format("%.4f", getBigDecimal(order.getTotalElectricMoney()).add(serviceMoney)));
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
