package com.wanma.ims.service.parkinglock;


import com.wanma.ims.common.domain.ParkingLockDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 地锁逻辑处理接口
 * xyc
 */
public interface ParkingLockService {

    /**
     * 查询地锁列表
     */
    List<ParkingLockDO> getParkingLockList(ParkingLockDO searchModel, UserDO loginUser);

    /**
     * 查询地锁总数
     */
    long countParkingLock(ParkingLockDO searchModel, UserDO loginUser);

    /**
     * 获取单个地锁详情
     */
    ParkingLockDO getParkingLockDetail(Long parkingLockId);

    /**
     * 新增地锁
     */
    BaseResultDTO addParkingLock(ParkingLockDO parkingLock, UserDO loginUser);

    /**
     * 修改地锁
     */
    BaseResultDTO modifyParkingLock(ParkingLockDO parkingLock, UserDO loginUser);

    /**
     * 操作地锁
     */
    BaseResultDTO operatingParkingLock(Long parkingLockId, Integer operating, UserDO loginUser);

    /**
     * 导入地锁
     */
    BaseResultDTO importParkingLock(MultipartFile multipartFile, UserDO loginUser) throws Exception;

    /**
     * 导出地锁
     */
    void exportParkingLock(HttpServletResponse response, ParkingLockDO searchModel, UserDO loginUser);

    /**
     * 批量绑定充电点
     */
    BaseResultDTO batchBindPowerStation(String parkingLockIds, Long powerStationId, UserDO loginUser);
}
