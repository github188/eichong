package com.wanma.controller.cczc;

import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPowerstation;
import com.wanma.service.PileFilterService;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblPowerstationService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lhy
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Controller
@RequestMapping("/cczc")
public class CczcChargePointController {
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
    public String syncStubGroupInfo(HttpServletRequest request) {
        String cityCode = request.getParameter("cityCode");
        int org = WanmaConstants.CCZC_CODE;

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
        int org = WanmaConstants.CCZC_CODE;
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

}
