package com.wanma.ims.mapper;


import com.wanma.ims.common.domain.ConfigContentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigContentMapper {
    List<ConfigContentDO> getConfigContentList(ConfigContentDO searchModel);

    List<ConfigContentDO> getConfigContentListByParameterIds(@Param("configParameterIds") List<Integer> configParameterIds);

    int deleteById(Integer id);

    int insertConfigContent(ConfigContentDO configContent);

    ConfigContentDO selectById(Integer id);

    int updateSelective(ConfigContentDO configContent);
}