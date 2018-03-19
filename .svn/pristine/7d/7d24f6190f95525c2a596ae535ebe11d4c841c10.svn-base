package com.wanma.app.controller;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.app.service.parkinglock.ParkingLockService;
import com.wanma.app.util.ResultUtil;
import com.wanma.common.ParkingLockConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 地锁接口
 */
@Controller
@RequestMapping("/app/parkingLock")
public class ParkingLockController {

    private static Logger log = Logger.getLogger(ParkingLockController.class);

    @Autowired
    private ParkingLockService parkingLockService;

    /**
     * 降锁
     */
    @RequestMapping(value = "/lowerParkingLock", method = RequestMethod.POST)
    @ResponseBody
    public String lowerParkingLock(HttpServletRequest request) throws AppRequestErrorException {

        //用户id
        String userId = RequestParamUtil.getEncodeParam(request, "userId");
        //地锁编码
        String parkingLockCode = RequestParamUtil.getEncodeParam(request, "parkingLockCode");
        //地锁平台
        String parkingLockPlatform = RequestParamUtil.getEncodeParam(request, "parkingLockPlatform");

        try {
            return new AccessSuccessResult(parkingLockService.lowerParkingLock(Long.valueOf(userId), parkingLockCode, Long.valueOf(parkingLockPlatform))).toString();
        } catch (Exception e) {
            // 保存错误LOG
            log.error("lowerParkingLock is error|userId=" + userId + "|parkingLockCode=" + parkingLockCode + "|parkingLockPlatform=" + parkingLockPlatform + "", e);
            return new AccessSuccessResult(ResultUtil.getResultMap(ParkingLockConstants.ERROR_CODE, ParkingLockConstants.ERROR_MSG)).toString();
        }
    }
}
