package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.ChargingFaultRecord;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by 18486 on 2018/1/25.
 */
@MapperScan
public interface ChargingFaultRecordMapper {


    long getChargingFaultRecordCount(ChargingFaultRecord chargingfaultrecord);

    List<ChargingFaultRecord> getChargingFaultRecordList(ChargingFaultRecord chargingfaultrecord);
}
