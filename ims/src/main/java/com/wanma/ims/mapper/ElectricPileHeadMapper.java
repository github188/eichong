package com.wanma.ims.mapper;


import com.wanma.ims.common.domain.ElectricPileHeadDO;

public interface ElectricPileHeadMapper {
    int deleteByElectricPileId(Long electricPileId);

    int insertElectricPileHead(ElectricPileHeadDO electricPileHead);

    ElectricPileHeadDO selectById(Long id);

    int updateByIdSelective(ElectricPileHeadDO electricPileHead);

}