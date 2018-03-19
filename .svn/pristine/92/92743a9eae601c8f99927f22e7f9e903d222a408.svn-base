package com.wanma.ims.controller.parking;

import com.wanma.ims.common.domain.ParkingLockDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.parkinglock.ParkingLockService;
import com.wanma.ims.util.ErrorHtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 地锁管理-地锁管理
 * xyc
 */
@RequestMapping("/manage/parkingLock")
@Controller
public class ParkingLockController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParkingLockService parkingLockService;

    /**
     * 查询地锁列表
     */
    @RequestMapping(value = "/getParkingLockList", method = RequestMethod.POST)
    @ResponseBody
    public void getParkingLockList(ParkingLockDO searchModel, Pager pager) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            long total = parkingLockService.countParkingLock(searchModel, loginUser);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            searchModel.setPager(pager);
            result.setPager(pager);
            List<ParkingLockDO> resultList = parkingLockService.getParkingLockList(searchModel, loginUser);
            result.setDataObject(resultList);
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getParkingLockList is error|searchModel={}|loginUser={}", searchModel, loginUser, e);
            responseJson(new JsonException("查询地锁列表失败！"));
        }
    }

    /**
     * 获取单个地锁详情
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public void getParkingLock(Long parkingLockId) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            result.setDataObject(parkingLockService.getParkingLockDetail(parkingLockId));
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getParkingLock is error|parkingLockId={}|loginUser={}", parkingLockId, loginUser, e);
            responseJson(new JsonException("获取单个地锁详情失败！"));
        }
    }

    /***
     * 添加地锁
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addParkingLock(ParkingLockDO parkingLock) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = parkingLockService.addParkingLock(parkingLock, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }
            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-addParkingLock is error|newParkingLock={}|loginUser={}", parkingLock, loginUser, e);
            responseJson(new JsonException("添加地锁失败！"));
        }
    }

    /***
     * 修改地锁
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public void modifyParkingLock(ParkingLockDO parkingLock) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = parkingLockService.modifyParkingLock(parkingLock, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-modifyParkingLock is error|modifyParkingLock={}|loginUser={}", parkingLock, loginUser, e);
            responseJson(new JsonException("修改地锁失败！"));
        }
    }

    /**
     * 操作地锁
     */
    @RequestMapping(value = "/operating", method = RequestMethod.POST)
    @ResponseBody
    public void operatingParkingLock(Long parkingLockId, Integer operating) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = parkingLockService.operatingParkingLock(parkingLockId, operating, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-operatingParkingLock is error|parkingLockId={}|operating={}|loginUser={}", parkingLockId, operating, loginUser, e);
            responseJson(new JsonException("操作地锁失败！"));
        }
    }

    /**
     * 导入地锁
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public void importParkingLock(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = parkingLockService.importParkingLock(multipartFile, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-importParkingLock is error|multipartFileName={}|loginUser={}", multipartFile.getOriginalFilename(), loginUser, e);
            responseJson(new JsonException(e.getMessage()));
        }
    }

    /**
     * 导出地锁
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public void exportParkingLock(HttpServletResponse response, ParkingLockDO searchModel) {
        UserDO loginUser = getCurrentLoginUser();

        try {
            parkingLockService.exportParkingLock(response, searchModel, loginUser);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-exportParkingLock is error|searchModel={}|loginUser={}", searchModel, loginUser, e);
            ErrorHtmlUtil.createErrorPage(response, "导出地锁信息失败！");
        }
    }

    /***
     * 批量绑定充电点
     */
    @RequestMapping(value = "/batchBindPowerStation", method = RequestMethod.POST)
    @ResponseBody
    public void batchBindPowerStation(String parkingLockIds, Long powerStationId) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = parkingLockService.batchBindPowerStation(parkingLockIds, powerStationId, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-batchBindPowerStation is error|parkingLockIds={}|powerStationId={}|loginUser={}", parkingLockIds, powerStationId, loginUser, e);
            responseJson(new JsonException("批量绑定充电点失败！"));
        }
    }

}