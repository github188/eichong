package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.MultiMediaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MultiMediaMapper {
    int insertMultiMedia(MultiMediaDO multiMedia);

    int deleteByFileId(String fileId);

    int deleteByReferenceId(@Param("businessType") String businessType, @Param("referenceId") String referenceId, @Param("fileName") String fileName);

    int updateByFileId(MultiMediaDO multiMedia);

    MultiMediaDO selectByFileId(String fileId);

    List<MultiMediaDO> selectByReferenceId(@Param("businessType") String businessType, @Param("referenceId") String referenceId);

}