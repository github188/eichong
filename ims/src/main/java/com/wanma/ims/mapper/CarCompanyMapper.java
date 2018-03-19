package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.CarCompanyDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarCompanyMapper {
    int deleteCarCompanyById(Long id);

    int insertCarCompany(CarCompanyDO carCompany);

    CarCompanyDO selectCarCompanyById(Long id);

    List<CarCompanyDO> selectCarCompanyByIds(@Param("ids") List<Long> ids);

    List<CarCompanyDO> getCarCompanyList(CarCompanyDO searchModel);

    long countCarCompany(CarCompanyDO searchModel);

    int updateCarCompanyByIdSelective(CarCompanyDO carCompany);

    int updateCarCompanyById(CarCompanyDO carCompany);
}