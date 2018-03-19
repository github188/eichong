package com.wanma.ims.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.FinAccountConfigRelaDO;

/**
 * 账务配置
 * @author bingo
 * @date 2017-06-13
 */
public interface FinAccountConfigRelaMapper {
	
	public Long getFinAccountConfigRelaCount(FinAccountConfigRelaDO finAccountConfigRela);
	
	public List<FinAccountConfigRelaDO> getFinAccountConfigRelaList(FinAccountConfigRelaDO finAccountConfigRela);
	
	public int addFinAccountConfigRela(FinAccountConfigRelaDO finAccountConfigRela);
	
	public int modifyFinAccountConfigRela(FinAccountConfigRelaDO finAccountConfigRela);
	
	public int removeFinAccountConfigRela(FinAccountConfigRelaDO finAccountConfigRela);
	
	public List<FinAccountConfigRelaDO> getFinAccountConfigRela4Cpy(FinAccountConfigRelaDO finAccountConfigRela);
	
	public List<FinAccountConfigRelaDO> selectFinAccountConfigRela(@Param("cpyId") Long cpyId);
}