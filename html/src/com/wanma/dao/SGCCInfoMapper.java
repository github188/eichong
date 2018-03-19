package com.wanma.dao;

import com.wanma.model.sgcc.SyncConnectorInfo;
import com.wanma.model.sgcc.SyncEquipmentInfo;
import com.wanma.model.sgcc.SyncEquipmentStatsInfo;
import com.wanma.model.sgcc.SyncStationInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zangyaoyi
 * @date 2017/6/5
 */
@Repository
public interface SGCCInfoMapper {
    List<SyncStationInfo> listStationInfo(@Param("operateId") int operateId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<SyncEquipmentInfo> listEquipmentInfo(@Param("operateId") int operateId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<SyncConnectorInfo> listConnectorInfo(@Param("operateId") int operateId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<SyncEquipmentStatsInfo> listEquipmentStatsInfo(@Param("operateId") int operateId, @Param("startTime") String startTime);

    List<Map<String, String>> listStation(@Param("operateId") int operateId, @Param("startTime") String startTime);

    Integer getOperateId(@Param("companyNumber") Integer companyNumber);

}
