package com.wanma.ims.controller.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.CarInfoDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.CarInfoService;

/**
 * Created by xyc on 2017/7/21.
 * 电动车型号controller
 */
@Controller
@RequestMapping("/manage/config/carInfo")
public class CarInfoController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CarInfoService carInfoService;

    /**
     * 获取所有电动车型号
     */
    @RequestMapping(value = "/getCarInfoList", method = RequestMethod.POST)
    @ResponseBody
    public void getCarInfoList(CarInfoDO searchModel, Pager pager) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            long total = carInfoService.countCarInfo(searchModel);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            searchModel.setPager(pager);
            List<CarInfoDO> resultList = carInfoService.getCarInfoList(searchModel);
            result.setPager(pager);
            result.setDataObject(resultList);

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getCarInfoList is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("查询电动车型号列表失败，请刷新页面后重试！"));
        }
    }

    /**
     * 获取单个电动车型号
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public void getCarInfo(Long carInfoId) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            result.setDataObject(carInfoService.getCarInfoById(carInfoId));

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getCarInfo is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("获取单个电动车型号失败，请刷新页面后重试！"));
        }
    }

    /**
     * 新增电动车型号
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addCarInfo(CarInfoDO carInfo) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = carInfoService.addCarInfo(carInfo, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-addCarInfo is error|addCarInfo={}|loginUser={}", carInfo, loginUser, e);
            responseJson(new JsonException("添加失败，请刷新页面后重试！"));
        }
    }

    /**
     * 修改电动车型号
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public void modifyCarInfo(CarInfoDO carInfo) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = carInfoService.modifyCarInfo(carInfo, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-modifyCarInfo is error|modifyCarInfo={}|loginUser={}", carInfo, loginUser, e);
            responseJson(new JsonException("修改失败，请刷新页面后重试！"));
        }
    }

    /**
     * 删除电动车型号
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public void delCarInfo(String carInfoIds) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = carInfoService.delCarInfo(carInfoIds, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-delCarInfo is error|delCarInfoIds={}|loginUser={}", carInfoIds, loginUser, e);
            responseJson(new JsonException("删除失败，请刷新页面后重试！"));
        }
    }
}
