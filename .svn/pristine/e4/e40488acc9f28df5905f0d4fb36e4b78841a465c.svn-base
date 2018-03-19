package com.wanma.ims.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.CompanyChargeRelaDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.BatchResultDTO;

/**
 * Created by xyc on 2017/7/25.
 * 充电范围逻辑处理接口
 */
public interface CompanyChargeRelaService {

    /**
     * 公司主页充电范围
     */
    List<Integer> companyIndexChargeRela(CompanyChargeRelaDO searchModel);

    /**
     * 获取充电点和电桩
     */
    List<CityDO> getStationAndPile(Long cpyId, UserDO loginUser);

    /**
     * 设置充电范围
     */
    BaseResultDTO setCompanyChargeRela(Long cpyId, List<CompanyChargeRelaDO> relaList, UserDO loginUser) throws Exception;
    
    /**
     * 导入 
     */
    BatchResultDTO<CompanyChargeRelaDO> parseCompanyChargeRelaExcel(MultipartFile importFile);
    
    BaseResultDTO addCompanyChargeRela(List<CompanyChargeRelaDO> relaList) throws Exception;


}
