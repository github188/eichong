package com.wanma.controller.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.controller.cczc.CczcChargeOrderController;
import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblElectricpile;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TblElectricpileService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.ResultResponse;

/**
 * 开放的公共接口
 * @author lyh
 *
 */
@Controller
@RequestMapping("/eichong")
public class ChargeOrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CczcChargeOrderController.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Autowired
	private TblChargingOrderService ordService;
	@Autowired
	TblElectricpileService eService;
	
    /**
     * @Description: 根据订单号查询订单详情（已完成的充电订单）
     * @return
     * @throws InterruptedException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getOrderInfo")
    @ResponseBody
    public String getOrderInfo(HttpServletRequest request) throws Exception {
        String org = request.getParameter("org");
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(org) || StringUtils.isBlank(orderId)){
        	 return new FailedResponse(1001, "params error").toString();
        }          
        TblChargingOrder model = new TblChargingOrder();
        model.setChorCode(orderId);
        model = ordService.selectOne(model);
        if (model == null) {
            ResultResponse resultRespone = new ResultResponse();
            resultRespone.setStatus(2004);
            resultRespone.setMsg("查询数据库失败，不存在该条订单！");
            return resultRespone.toString();
        }
        LOGGER.info("TblChargingOrder：", model.toString());
        int chorSts = Integer.parseInt(model.getChorChargingstatus());
        Map<String, Object> data = new HashMap<String, Object>();
        if (chorSts == 2 || chorSts == 3) {
            LOGGER.info("订单已完成，获取历史订单详情！");
            data.put("orderId", model.getChorCode());
            data.put("stubId", model.getChorPilenumber());
            data.put("outOrderId", model.getChorParterExtradata());
            data.put("driverId", model.getChorParterUserLogo());
            data.put("timeStart", model.getBeginChargetime());
            data.put("timeEnd", model.getEndChargetime());
            data.put("timeCharge", (sdf.parse(model.getEndChargetime()).getTime() - sdf.parse(model.getBeginChargetime()).getTime()) / 1000);

            data.put("feeTotal", new BigDecimal(model.getChorMoeny()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            
            data.put("cuspElect", model.getChOr_tipPower());
            data.put("peakElect",model.getChOr_peakPower());
            data.put("flatElect", model.getChOr_usualPower());
            data.put("valleyElect",model.getChOr_valleyPower());

            TblElectricpile eModel = new TblElectricpile();
            eModel.setElpiElectricpilecode(model.getChorPilenumber());
            eModel = eService.selectOne(eModel);
            if ("5".equals(eModel.getChargingMode()))
                data.put("chargeType", 1);
            else
                data.put("chargeType", 0);
            data.put("power", Double.valueOf(model.getChorQuantityelectricity()));
            data.put("soc", 0.00);
            int rtSts;
            String endInfo;
            rtSts = 2;
            endInfo = "正常结束";
            data.put("status", rtSts);
            data.put("endInfo", endInfo);
            data.put("feeService", model.getChorServicemoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            data.put("feeElectric", model.getChorChargemoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            data.put("cityCode", eModel.getElpiOwncitycode());
            return new ResultResponse(data).toString();
        }else{
        	 ResultResponse resultRespone = new ResultResponse();
             resultRespone.setStatus(2005);
             resultRespone.setMsg("没有相关的订单信息！");
             return resultRespone.toString();
        }      
    }
}
