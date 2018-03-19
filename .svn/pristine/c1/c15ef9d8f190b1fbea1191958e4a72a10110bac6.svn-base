package com.wanma.ims.controller.parking;

import com.google.common.base.Strings;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.service.parkinglock.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接收地锁第三方平台回调接口
 * xyc
 */
@RequestMapping("/parkingLockPlatform")
@Controller
public class ParkingLockPlatformController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlatformService parkingLockPlatformService;

    /**
     * 异步地锁状态
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public void asyncModifyLockStatus(HttpServletRequest request, HttpServletResponse response, @RequestBody String result) {
        try {
            String spaceId = request.getParameter("spaceId");
            System.out.println(result);
            if (Strings.isNullOrEmpty(spaceId)) {
                response.getWriter().print("fail");
            }
            response.getWriter().print("success");
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-asyncModifyLockStatus is error", e);
        }
    }

    /**
     * 异步接收慧泊金地锁状态
     */
    @RequestMapping(value = "/wiparkingLockStatus", method = RequestMethod.POST)
    @ResponseBody
    public void asyncModifyWiparkingLockStatus(@RequestBody String result) {
        try {
            LOGGER.debug(this.getClass() + "-asyncModifyWiparkingLockStatus  asyncModify is start|result={}", result);
            if (Strings.isNullOrEmpty(result)) {
                LOGGER.warn(this.getClass() + "-asyncModifyWiparkingLockStatus is error, result is null");
            }

            if (!parkingLockPlatformService.asyncModifyLockStatus(result, WanmaConstants.PARKING_LOCK_PLATFORM_WIPARKING)) {
                LOGGER.error(this.getClass() + "-asyncModifyWiparkingLockStatus is error, asyncModifyLockStatus is fail|result={}", result);
            }

            LOGGER.debug(this.getClass() + "-asyncModifyWiparkingLockStatus  asyncModify is end|result={}", result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-asyncModifyWiparkingLockStatus is error", e);
        }
    }
}