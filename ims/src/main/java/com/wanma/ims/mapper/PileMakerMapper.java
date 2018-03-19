package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.PileMakerDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PileMakerMapper {
    int deletePileMakerById(Long id);

    int insertPileMaker(PileMakerDO pileMaker);

    PileMakerDO getPileMakerById(Long id);

    List<PileMakerDO> getPileMakerListByIds(@Param("ids") List<Long> ids);

    List<PileMakerDO> getPileMakerList(PileMakerDO searchModel);

    long countPileMaker(PileMakerDO searchModel);

    int updatePileMakerByIdSelective(PileMakerDO pileMaker);

    int updatePileMakerById(PileMakerDO pileMaker);
}