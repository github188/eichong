package com.wanma.ims.controller.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.PileMakerDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.PileMakerService;

/**
 * Created by xyc on 2017/7/21.
 * 电桩制造商controller
 */
@RequestMapping("/manage/config/pileMaker")
@Controller
public class PileMakerController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PileMakerService pileMakerService;

    /**
     * 获取所有电桩制造商
     */
    @RequestMapping(value = "/getPileMakerList", method = RequestMethod.POST)
    @ResponseBody
    public void getPileMakerList(PileMakerDO searchModel, Pager pager) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            long total = pileMakerService.countPileMaker(searchModel);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            searchModel.setPager(pager);
            List<PileMakerDO> resultList = pileMakerService.getPileMakerList(searchModel);
            result.setPager(pager);
            result.setDataObject(resultList);

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getPileMakerList is error|searchModel={}|loginUser={}", searchModel, loginUser, e);
            responseJson(new JsonException("查询电桩制造商列表失败，请刷新页面后重试！"));
        }
    }


    /**
     * 获取单个电桩制造商
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public void getPileMaker(Long pileMakerId) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            PileMakerDO pileMaker = pileMakerService.getPileMakerById(pileMakerId);
            result.setDataObject(pileMaker);

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getPileMaker is error|pileMakerId={}|loginUser={}", pileMakerId, loginUser, e);
            responseJson(new JsonException("获取单个电桩制造商失败，请刷新页面后重试！"));
        }
    }

    /**
     * 新增电桩制造商
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addPileMaker(PileMakerDO pileMaker) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = pileMakerService.addPileMaker(pileMaker, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-addPileMaker is error|addPileMaker={}|loginUser={}", pileMaker, loginUser, e);
            responseJson(new JsonException("添加失败，请刷新页面后重试！"));
        }
    }

    /**
     * 修改电桩制造商
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public void modifyPileMaker(PileMakerDO pileMaker) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = pileMakerService.modifyPileMaker(pileMaker, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-modifyPileMaker is error|modifyPileMaker={}|loginUser={}", pileMaker, loginUser, e);
            responseJson(new JsonException("修改失败，请刷新页面后重试！"));
        }
    }

    /**
     * 删除电桩制造商
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public void delPileMaker(String pileMakerIds) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = pileMakerService.delPileMaker(pileMakerIds, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-delPileMaker is error|delPileMakerIds={}|loginUser={}", pileMakerIds, loginUser, e);
            responseJson(new JsonException("删除失败，请刷新页面后重试！"));
        }
    }
}
