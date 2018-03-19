package com.wanma.ims.service;


import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.ElectricPileHeadDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 电桩逻辑处理接口
 * xyc
 */
public interface ElectricPileService {

    /**
     * 查询电桩列表
     */
    List<ElectricPileDO> getElectricPileList(ElectricPileDO searchModel, UserDO loginUser);

    /**
     * 查询电桩总数
     */
    long countElectricPile(ElectricPileDO searchModel, UserDO loginUser);

    /**
     * 通过电桩Ids获取电桩List
     */
    List<ElectricPileDO> selectByElectricPileIds(List<Long> electricPileIds);

    /**
     * 检查电桩编号的唯一性
     */
    boolean checkElectricPileCodeIsUnique(String code);

    /**
     * 增加电桩
     */
    BaseResultDTO addElectricPile(ElectricPileDO electricPile, UserDO loginUser);

    /**
     * 移除电桩
     */
    BaseResultDTO deleteElectricPile(String ids, UserDO loginUser);

    /**
     * 修改电桩
     */
    BaseResultDTO modifyElectricPile(ElectricPileDO electricPile, UserDO loginUser);

    /**
     * 获取单个电桩详情
     */
    ElectricPileDO getElectricPileById(Long electricPileId, UserDO loginUser);

    /**
     * 导入电桩
     */
    BaseResultDTO importElectricPile(MultipartFile multipartFile, UserDO loginUser) throws Exception;

    /**
     * 导出电桩
     */
    void exportElectricPile(HttpServletResponse response, ElectricPileDO searchModel, UserDO loginUser);

    /**
     * 绑定电桩
     */
    BaseResultDTO bandElectricPile(List<ElectricPileDO> electricPileList, Integer bindType, Long relatedId, UserDO loginUser) throws Exception;

    /**
     * 解绑电桩
     */
    BaseResultDTO unbindElectricPile(String electricPileIds, Integer bindType, UserDO loginUser);

    /**
     * 修改资产归属
     */
    BaseResultDTO modifyElectricPileCompany(String electricPileIds, Long companyId, Long companyNumber, UserDO loginUser);

    /**
     * 通过枪头主键查询枪头信息
     */
    ElectricPileHeadDO selectPileHeadById(Long id);

    /**
     * 审核电桩
     */
    BaseResultDTO auditElectricPile(String electricPileIds, UserDO loginUser);
}
