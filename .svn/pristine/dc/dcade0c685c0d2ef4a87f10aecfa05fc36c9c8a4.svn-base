package com.wanma.ims.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.CompanyChargeRelaDO;

public interface CompanyChargeRelaMapper {
    List<CompanyChargeRelaDO> getCompanyChargeRelaList(CompanyChargeRelaDO searchModel);

    int deleteCompanyChargeRelaById(Long id);

    int deleteByCpyId(@Param("cpyId") Long cpyId);

    int insertCompanyChargeRela(CompanyChargeRelaDO companyChargeRela);

    CompanyChargeRelaDO selectCompanyChargeRelaById(Long id);

    int updateCompanyChargeRelaSelective(CompanyChargeRelaDO companyChargeRela);
    
    int deleteBypsIdAndCpyId(@Param("cpyId") Long cpyId,@Param("psId") Long psId);
    
    int batchInsertCompanyChargeRela(List<CompanyChargeRelaDO> list);
    
    int deleteByPsId(@Param("psIds") List<Long> psIds);
    
    int updateByElectricPileId(@Param("epId") Long epId);
}