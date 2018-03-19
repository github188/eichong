package com.wanma.ims.controller.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.CarCompanyDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.CarCompanyService;

/**
 * Created by xyc on 2017/7/21.
 * 电动车品牌controller
 */
@Controller
@RequestMapping("/manage/config/carCompany")
public class CarCompanyController extends BaseController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CarCompanyService carCompanyService;

    /**
     * 获取电动车品牌列表
     */
    @RequestMapping(value = "/getCarCompanyList", method = RequestMethod.POST)
    @ResponseBody
    public void getCarCompanyList(CarCompanyDO searchModel, Pager pager) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            long total = carCompanyService.countCarCompany(searchModel);
            if (total <= pager.getOffset()) {
                pager.setPageNo(1L);
            }
            pager.setTotal(total);
            searchModel.setPager(pager);
            List<CarCompanyDO> resultList = carCompanyService.getCarCompanyList(searchModel);
            result.setPager(pager);
            result.setDataObject(resultList);

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getCarCompanyList is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("查询电动车品牌列表失败，请刷新页面后重试！"));
        }
    }

    /**
     * 获取所有电动车品牌
     */
    @RequestMapping(value = "/getAllCarCompany", method = RequestMethod.POST)
    @ResponseBody
    public void getAllCarCompany() {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            result.setDataObject(carCompanyService.getAllCarCompany());

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getAllCarCompany is error|loginUser={}", loginUser, e);
            responseJson(new JsonException("获取所有电动车品牌失败，请刷新页面后重试！"));
        }
    }

    /**
     * 获取单个电动车品牌
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public void getCarCompany(Long carCompanyId) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            result.setDataObject(carCompanyService.getCarCompanyById(carCompanyId));

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-getCarCompany is error|carCompanyId={}|loginUser={}", carCompanyId, loginUser, e);
            responseJson(new JsonException("获取单个电动车品牌失败，请刷新页面后重试！"));
        }
    }

    /**
     * 新增电动车品牌
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addCarCompany(CarCompanyDO carCompany) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = carCompanyService.addCarCompany(carCompany, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-addCarCompany is error|addCarCompany={}|loginUser={}", carCompany, loginUser, e);
            responseJson(new JsonException("添加失败，请刷新页面后重试！"));
        }
    }

    /**
     * 修改电动车品牌
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public void modifyCarCompany(CarCompanyDO carCompany) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = carCompanyService.modifyCarCompany(carCompany, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-modifyCarCompany is error|modifyCarCompany={}|loginUser={}", carCompany, loginUser, e);
            responseJson(new JsonException("修改失败，请刷新页面后重试！"));
        }
    }

    /**
     * 删除电动车品牌
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public void delCarCompany(String carCompanyIds) {
        JsonResult result = new JsonResult();
        UserDO loginUser = getCurrentLoginUser();

        try {
            BaseResultDTO resultDTO = carCompanyService.delCarCompany(carCompanyIds, loginUser);
            if (resultDTO.isFailed()) {
                result = new JsonResult(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
            }

            responseJson(result);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-delCarCompany is error|delCarCompanyIds={}|loginUser={}", carCompanyIds, loginUser, e);
            responseJson(new JsonException("删除失败，请刷新页面后重试！"));
        }
    }
}
