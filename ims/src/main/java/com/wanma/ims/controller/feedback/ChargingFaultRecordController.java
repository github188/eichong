package com.wanma.ims.controller.feedback;

/**
 * Created by 18486 on 2018/1/25.
 */

import com.wanma.ims.common.domain.ChargingFaultRecord;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.ChargingFaultRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * ccu充电故障记录
 */
@Controller
@RequestMapping("/manage/chargingFaultRecord")
public class ChargingFaultRecordController extends BaseController{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired private ChargingFaultRecordService chargingFaultRecordService;

/**
 * 获取ccu充电故障记录列表
 */
    @RequestMapping(value = "getChargingFaultRecordList")
    @ResponseBody
    public  void getChargingFaultRecordList(ChargingFaultRecord chargingfaultrecord,Pager pager){
        JsonResult result = new JsonResult();
        try {
            pager.setPageSize(Long.valueOf(100));
            long total = chargingFaultRecordService.getChargingFaultRecordCount(chargingfaultrecord);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            chargingfaultrecord.setPager(pager);
            List<ChargingFaultRecord> faultRecordList = chargingFaultRecordService.getChargingFaultRecordList(chargingfaultrecord);
            faultRecordList=changeFaultCause(faultRecordList);
            result.setDataObject(faultRecordList);
            result.setPager(pager);
            responseJson(result);
        }catch (Exception e){
            LOGGER.error(this.getClass() + "-getChargingFaultRecordList is error|loginUser={}", chargingfaultrecord, e);
            responseJson(new JsonException("获取ccu充电故障记录列表失败！"));
        }
    }
    private List<ChargingFaultRecord> changeFaultCause(List<ChargingFaultRecord> faultRecordList){
        for (ChargingFaultRecord chargingFaultRecord:faultRecordList){
            String cFReFaultCause = chargingFaultRecord.getcFReFaultCause();
            String str[] = cFReFaultCause.split("\\|");
            if (str.length>1){
                Map orderStopReasonMap = WanmaConstants.getOrderStopReson();
                if (orderStopReasonMap.get(str[0])!=null){
                    cFReFaultCause = orderStopReasonMap.get(str[0]).toString();
                }else {
                    cFReFaultCause = str[0];
                }
                cFReFaultCause+="|";
                if ("249".equals(str[0])){
                    Map startupErrorDetailMap = WanmaConstants.getStartErrorDetail();
                    cFReFaultCause += startupErrorDetailMap.get(str[1]);
                }else {
                    Map pileErrorDetailMap = WanmaConstants.getPileErrorDetail();
                    cFReFaultCause += pileErrorDetailMap.get(str[1]);
                    }
            }
            chargingFaultRecord.setcFReFaultCause(cFReFaultCause);
         }
        return faultRecordList;
    }








}
