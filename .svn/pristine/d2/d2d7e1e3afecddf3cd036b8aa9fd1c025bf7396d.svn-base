package com.wanma.controller.common;

import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;
import com.wanma.service.PileFilterService;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblPowerstationService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.simple.JudgeNullUtils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gx
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
@RequestMapping("/eichong")
public class ChargePointController {
    @Autowired
    private TblPowerstationService pService;
    @Autowired
    private TblElectricpileService eService;
    @Autowired
    private PileFilterService pileFilterService;
    @Autowired
    private TblElectricpileHeadService tblElectricpileHeadService;
    @Autowired
    private TblElectricpileService tblElectricpileService;

    /**
     * @Description: 根据城市编号查询充电点信息
     * @return
     */
    @RequestMapping("/syncStubGroupInfo")
    @ResponseBody
    public String getPointByCityNo(HttpServletRequest request) {
        String cityCode = request.getParameter("cityCode");
        int org = Integer.parseInt(request.getParameter("org"));

        TblPowerstation model = new TblPowerstation();
        model.setPostOwnCityCode(cityCode);
        model.setOrg(org);
        if (StringUtils.isBlank(cityCode))
            return new FailedResponse(1001, "params error").toString();
        return new ResultResponse(pService.getPointsInfoByCityCode(model)).toString();
    }

    /**
     * @Description: 查询充电点详情
     * @return
     */
    @RequestMapping("/syncStubInfo")
    @ResponseBody

    public String syncStubInfo(HttpServletRequest request) {
        String stubGroupId = request.getParameter("stubGroupId");
        int org = Integer.parseInt(request.getParameter("org"));
        TblPowerstation model = new TblPowerstation();
        model.setPkPowerstation(Integer.parseInt(stubGroupId));
        model.setOrg(org);
        if (StringUtils.isBlank(stubGroupId))
            return new FailedResponse(1001, "params error").toString();
        Map<String, Object> data = pService.selectDetail(model);
        data.put("StubInfo", eService.selectDetailList(data));
        return new ResultResponse(data).toString();
    }

    /**
     * @Description: 电桩编号查询电桩ID
     * @return
     */
    @RequestMapping("/getStubId")
    @ResponseBody

    public String getStubId(HttpServletRequest request) {
        String org = request.getParameter("org");
        String qrCode = request.getParameter("gunQrCode");
        if (StringUtils.isBlank(org)
                || StringUtils.isBlank(qrCode)) {
            return new FailedResponse(1001, "params error").toString();
        }
        if (qrCode.length() != 6) {
            return new FailedResponse(1001, "gunQrCode is error:length != 6").toString();
        }

        TblElectricpilehead tblElectricpilehead = tblElectricpileHeadService.getHeadByQrCode(qrCode);
        if (tblElectricpilehead == null || tblElectricpilehead.getPkElectricpile() == null) {
            return new FailedResponse(1001, "gunQrCode is error: not have gunHead").toString();
        }

        int pkElectricpile = tblElectricpilehead.getPkElectricpile();
        TblElectricpile tblElectricpile = new TblElectricpile();
        tblElectricpile.setPkElectricpile(pkElectricpile);
        tblElectricpile = tblElectricpileService.selectOne(tblElectricpile);

        boolean ok = pileFilterService.checkPileIsOk(org, tblElectricpile.getElpiElectricpilecode());
        if (!ok) {
            return new FailedResponse(1001, "gunQrCode is error: No permission to view the").toString();
        }

        Map<String, Object> data = new HashedMap();
        data.put("pileNo", tblElectricpile.getElpiElectricpilecode());
        data.put("gunNo", tblElectricpilehead.getEpheElectricpileHeadId().toString());
        return new ResultResponse(data).toString();
    }
    
    /**
     * 桩体信息查询接口
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectPileInfo")
    public String selectPileInfo(HttpServletRequest request){
    	String elpiElectricpilecode = request.getParameter("elpiElectricpilecode");
    	String ePHeElectricpileHeadId = request.getParameter("ePHeElectricpileHeadId");
    	if(StringUtils.isBlank(elpiElectricpilecode) || StringUtils.isBlank(ePHeElectricpileHeadId)){
    		return new FailedResponse(1050,"error.msg.miss.parameter").toString();
    	}
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("elpiElectricpilecode", elpiElectricpilecode);
    	map.put("ePHeElectricpileHeadId", ePHeElectricpileHeadId);
    	//响应map
    	Map<String,Object> data =new HashMap<String,Object>();
    	//获取电桩信息
    	Map<String,Object> ele =null;
    	try {
    		ele = eService.getElectricpileInfo(map);
    		int compNum = Integer.parseInt(ele.get("company_number").toString());
			int state = Integer.parseInt(ele.get("elpiState").toString());
			if(compNum > 0 && state == 10){
				return new FailedResponse(1001, "该桩目前不对外开放，暂不提供二维码充电功能").toString();
			}
			//计算当前电费
			String mark = eService.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(ele.get("rateDate")));
			switch(mark){
				case "1":
					ele.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(ele.get("jPrice"))));
					break;
				case "2":
					ele.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(ele.get("fPrice"))));
					break;
				case "3":
					ele.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(ele.get("pPrice"))));
					break;
				case "4":
					ele.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(ele.get("gPrice"))));
					break;
				default:
					ele.put("currentRate",new BigDecimal(0));
			}
			//响应参数
			data.put("pkElectricpile", ele.get("pkElectricpile"));
			data.put("elpiElectricpilename", ele.get("elpiElectricpilename"));
			data.put("elpiElectricpilecode", elpiElectricpilecode);
			data.put("ePHeElectricpileHeadId", ePHeElectricpileHeadId);
			data.put("elPiChargingMode", ele.get("elPiChargingMode"));
			data.put("elPiPowerSize", ele.get("elPiPowerSize"));
			data.put("elPiPowerInterface", ele.get("elPiPowerInterface"));
			data.put("ePHe_ElectricpileHeadState", ele.get("epHeadState"));
			data.put("elpiElectricpileaddress", ele.get("elpiElectricpileaddress"));
			data.put("comm_status", ele.get("commStatus"));
			data.put("oCurrent", ele.get("oCurrent"));
			data.put("parkFee", ele.get("parkFee"));
			data.put("currentRate", ele.get("currentRate"));
			data.put("serPrice", ele.get("serPrice"));
			data.put("rateId", ele.get("rateId"));
		} catch (Exception e) {			
			// 返回错误信息
			return new FailedResponse(2004,"error.msg.invalid.parameter").toString();
		}
    	
    	 return new ResultResponse(data).toString();
    	
    }

}
